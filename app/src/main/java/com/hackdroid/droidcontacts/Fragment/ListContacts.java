package com.hackdroid.droidcontacts.Fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hackdroid.droidcontacts.Adapter.ContactsAdapter;
import com.hackdroid.droidcontacts.Model.ContactModel;
import com.hackdroid.droidcontacts.R;
import com.hackdroid.droidcontacts.Utils.FetchContacts;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListContacts extends Fragment {
    String TAG = ListContacts.class.getSimpleName();

    RecyclerView listContacts;
    static final int PERMISSION_REQ = 1009;

    public ListContacts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listContacts = view.findViewById(R.id.listContacts);

        /**
         * first check for permission
         * */
        if (permissionAvailable()) {
            //permission avail
            Log.d(TAG, "onViewCreated: Permission available");
            getContacts();
//            Log.d(TAG, "onViewCreated: " + list);
        } else {
            Log.d(TAG, "onViewCreated: No permissionn valialeb");
            askForPermission();
        }
//        getContacts(getContext());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQ:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: Permission granted");
                    getContacts();
//                    Log.d(TAG, "onRequestPermissionsResult: " + list);

                } else {
                    Log.d(TAG, "onRequestPermissionsResult: permission denied");
                }
                break;
        }
    }

    private void askForPermission() {
        requestPermissions(new String[]{Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS}, PERMISSION_REQ);
    }

    private boolean permissionAvailable() {

        /**
         * check permission for read contacts and write contacts
         * */
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }

        return true;
    }


    public void getContacts() {
        new FetchContacts(getActivity(), new FetchContacts.OnContactFetchListener() {
            @Override
            public void onContactFetch(List<ContactModel> contacts) {
                // Here you will get the contacts
                Log.d(TAG, "onContactFetch: " + contacts);
                setAdapter(contacts);

            }
        }).execute();

    }

    private void setAdapter(List<ContactModel> list) {
        listContacts.setHasFixedSize(true);
        ContactsAdapter contactsAdapter = new ContactsAdapter(list, getActivity(), getContext());
        listContacts.setAdapter(contactsAdapter);
        listContacts.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}
