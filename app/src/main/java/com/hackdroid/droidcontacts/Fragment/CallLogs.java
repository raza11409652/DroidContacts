package com.hackdroid.droidcontacts.Fragment;


import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hackdroid.droidcontacts.Adapter.LogsAdapter;
import com.hackdroid.droidcontacts.Model.Logs;
import com.hackdroid.droidcontacts.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CallLogs extends Fragment {
    String TAG = CallLogs.class.getSimpleName();
    static final int REQUEST_PERMISSION = 109;
    List<Logs> list = new ArrayList<>();
    RecyclerView logsList;

    public CallLogs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_call_logs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logsList = view.findViewById(R.id.logs);
        logsList.setHasFixedSize(true);
        /**
         * check if call log permission is allowed
         * */
        if (isPermissionAvailable()) {
            Log.d(TAG, "onCreate: call log permission true");
            list = getCallDetails();
            setAdapter(list);
        } else {
            Log.d(TAG, "onCreate: call log permission not avalible");
            /**
             * Ask for permission
             * */
            askPermission();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (permissions[0].equals(Manifest.permission.READ_CALL_LOG) && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult: Permission available");
                    list = getCallDetails();
                    setAdapter(list);
                } else {
                    Log.d(TAG, "onRequestPermissionsResult: Permission denied");
                }
                break;
        }
    }

    private void askPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_PERMISSION);
        } else {
            Log.d(TAG, "askPermission: Permission not required");
        }
    }

    private boolean isPermissionAvailable() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Permission available");
            return true;
        }
        Log.d(TAG, "No permission available");
        return false;
    }

    private List<Logs> getCallDetails() {
        final List<Logs> tempLogs = new ArrayList<>();
        Cursor managedCursor = getActivity().managedQuery(CallLog.Calls.CONTENT_URI, null,
                null, null, null);
        int number = managedCursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = managedCursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = managedCursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = managedCursor.getColumnIndex(CallLog.Calls.DURATION);

//        managedCursor.getColumnIndex(CallLog.Calls.PRESENTATION_PAYPHONE);
        while (managedCursor.moveToNext()) {
            Log.d(TAG, "getCallDetails: " + managedCursor);
//            managedCursor.getColumnIndex(CallLog.Calls.)
            String phNumber = managedCursor.getString(number);
            String callType = managedCursor.getString(type);
            String callDate = managedCursor.getString(date);
//            Date callDayTime = new Date(Long.valueOf(callDate));
            String callDuration = managedCursor.getString(duration);
            Logs callLogs = new Logs(phNumber, callType, callDate, callDuration);
            tempLogs.add(callLogs);

        }
        managedCursor.close();

        return tempLogs;
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * check if call log permission is allowed
         * */
//        if (isPermissionAvailable()) {
//            Log.d(TAG, "onCreate: call log permission true");
//            list = getCallDetails();
//            setAdapter(list);
//        } else {
//            Log.d(TAG, "onCreate: call log permission not avalible");
//            /**
//             * Ask for permission
//             * */
//            askPermission();
//        }
    }

    private void setAdapter(List<Logs> list) {
        LogsAdapter logsAdapter = new LogsAdapter(list, getActivity(), getContext());
        logsList.setAdapter(logsAdapter);
        logsList.setLayoutManager(new LinearLayoutManager(getContext()));
        logsList.setItemAnimator(new DefaultItemAnimator());

    }
}
