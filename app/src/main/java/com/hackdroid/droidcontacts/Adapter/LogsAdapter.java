package com.hackdroid.droidcontacts.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hackdroid.droidcontacts.Model.Logs;
import com.hackdroid.droidcontacts.R;

import java.util.List;

public class LogsAdapter extends RecyclerView.Adapter<LogsAdapter.ViewHolder> {
    List<Logs> list;
    Activity activity;
    Context context;

    public LogsAdapter(List<Logs> list, Activity activity, Context context) {
        this.list = list;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_call_logs, parent, false);
        return new LogsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mobilenumber.setText(list.get(position).getMobile());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mobilenumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mobilenumber = itemView.findViewById(R.id.mobilenumber);
        }
    }
}
