package com.hackdroid.droidcontacts.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hackdroid.droidcontacts.Model.ContactModel;
import com.hackdroid.droidcontacts.R;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.Viewholder> {
    List<ContactModel> list;
    Activity activity;
    Context context;

    public ContactsAdapter(List<ContactModel> list, Activity activity, Context context) {
        this.list = list;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_call_logs, parent, false);
        return new ContactsAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.mobilenumber.setText(list.get(position).getMobileNumber() + "\n" +
                list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView mobilenumber;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            mobilenumber = itemView.findViewById(R.id.mobilenumber);
        }
    }
}
