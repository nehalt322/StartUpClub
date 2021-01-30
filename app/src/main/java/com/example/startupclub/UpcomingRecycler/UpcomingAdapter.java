package com.example.startupclub.UpcomingRecycler;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.startupclub.R;

import java.util.ArrayList;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder> {
    private Context mcontext;
    private ArrayList<UpcomingModal> mlist;

    public UpcomingAdapter(Context mcontext, ArrayList<UpcomingModal> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
        Log.d("Recycler View", "The size of the mlist is: "+mlist.size());
    }

    @NonNull
    @Override
    public UpcomingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.event,parent,false);
        return new UpcomingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingViewHolder holder, int position) {
        UpcomingModal items = mlist.get(position);
        Log.d("Recycler View", "OnBindViewHolder is called: ");
        holder.upeventtitle.setText(items.getTitle());
        holder.upeventvenue.setText(items.getVenue());
        holder.upeventdate.setText(items.getDate());
        holder.upeventtime.setText(items.getTime());
        Glide.with(mcontext).load(items.getURL()).into(holder.upeventimage);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class UpcomingViewHolder extends RecyclerView.ViewHolder {
        private TextView upeventtitle;
        private TextView upeventvenue;
        private TextView upeventdate;
        private TextView upeventtime;
        private ImageView upeventimage;

        public UpcomingViewHolder(@NonNull View itemView) {
            super(itemView);
            upeventtitle=itemView.findViewById(R.id.event_title);
            upeventvenue=itemView.findViewById(R.id.event_venue);
            upeventdate=itemView.findViewById(R.id.event_date);
            upeventtime=itemView.findViewById(R.id.event_time);
            upeventimage=itemView.findViewById(R.id.ongoing_event_image);

        }
    }
}
