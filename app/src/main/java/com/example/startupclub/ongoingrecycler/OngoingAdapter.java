package com.example.startupclub.ongoingrecycler;

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

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.ongoingViewHolder> {
    private Context mcontext;
    private ArrayList<OngoingModal> mlist;

    public OngoingAdapter(Context mcontext, ArrayList<OngoingModal> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
        Log.d("Recycler View", "The size of the mlist is: "+mlist.size());
    }

    @NonNull
    @Override
    public OngoingAdapter.ongoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.event,parent,false);
        return new OngoingAdapter.ongoingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingAdapter.ongoingViewHolder holder, int position) {
        OngoingModal items=mlist.get(position);
        Log.d("Recycler View", "OnBindViewHolder is called: ");
        holder.eventtitle.setText(items.getTitle());
        holder.eventvenue.setText(items.getVenue());
        holder.eventdate.setText(items.getDate());
        holder.eventtime.setText(items.getTime());
        Glide.with(mcontext).load(items.getURL()).into(holder.eventimage);


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ongoingViewHolder extends RecyclerView.ViewHolder {
        private TextView eventtitle;
        private TextView eventvenue;
        private TextView eventdate;
        private TextView eventtime;
        private ImageView eventimage;
        public ongoingViewHolder(@NonNull View itemView) {
            super(itemView);

            eventtitle=itemView.findViewById(R.id.event_title);
            eventvenue=itemView.findViewById(R.id.event_venue);
            eventdate=itemView.findViewById(R.id.event_date);
            eventtime=itemView.findViewById(R.id.event_time);
            eventimage=itemView.findViewById(R.id.ongoing_event_image);
        }
    }
}
