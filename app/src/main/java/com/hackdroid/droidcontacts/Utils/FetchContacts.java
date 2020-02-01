package com.hackdroid.droidcontacts.Utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import com.hackdroid.droidcontacts.Model.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class FetchContacts extends AsyncTask<Void, Void, List<ContactModel>>  {
    private Context activity;
    private OnContactFetchListener listener;

    public FetchContacts(Context activity, OnContactFetchListener listener) {
        this.activity = activity;
        this.listener = listener;
    }


    @Override
    protected List<ContactModel> doInBackground(Void... voids) {
        List<ContactModel> list = new ArrayList<>();
        ContentResolver contentResolver = activity.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
                if (hasPhoneNumber > 0) {
                    String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Cursor phoneCursor = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{id},
                            null);
                    if (phoneCursor != null) {
                        if (phoneCursor.moveToNext()) {
                            String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            //At here You can add phoneNUmber and Name to you listView ,ModelClass,Recyclerview
                            ContactModel _contacts = new ContactModel();
                            _contacts.setId(id);
                            _contacts.setName(name);
                            _contacts.setMobileNumber(phoneNumber);
                            list.add(_contacts);
                            phoneCursor.close();
                        }


                    }
                }
            }
        }
        return list;

    }

    @Override
    protected void onPostExecute(List<ContactModel> list) {
        super.onPostExecute(list);
        if (listener != null) {
            listener.onContactFetch(list);
        }
    }

    public interface OnContactFetchListener {
        void onContactFetch(List<ContactModel>  list);
    }
}
