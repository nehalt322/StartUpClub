package com.example.startupclub.PreviousRecycler;

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

public class PreviousAdapter extends RecyclerView.Adapter<PreviousAdapter.PreviousViewHolder> {
    private Context pcontext;
    private ArrayList<PreviousModal> plist;

    public PreviousAdapter(Context pcontext, ArrayList<PreviousModal> plist) {
        this.pcontext = pcontext;
        this.plist = plist;
        Log.d("Recycler View", "The size of the mlist is: "+plist.size());
    }

    @NonNull
    @Override
    public PreviousViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(pcontext).inflate(R.layout.event,parent,false);
        return new PreviousViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PreviousViewHolder holder, int position) {
        PreviousModal items = plist.get(position);
        Log.d("Recycler View", "OnBindViewHolder is called: ");
        holder.pr_eventtitle.setText(items.getTitle());
        holder.pr_eventvenue.setText(items.getVenue());
        holder.pr_eventdate.setText(items.getDate());
        holder.pr_eventtime.setText(items.getTime());
        Glide.with(pcontext).load(items.getURL()).into(holder.pr_eventimage);

    }

    @Override
    public int getItemCount() {
        return plist.size();
    }

    public class PreviousViewHolder extends RecyclerView.ViewHolder {
        private TextView pr_eventtitle;
        private TextView pr_eventvenue;
        private TextView pr_eventdate;
        private TextView pr_eventtime;
        private ImageView pr_eventimage;

        public PreviousViewHolder(@NonNull View itemView) {
            super(itemView);
            pr_eventtitle=itemView.findViewById(R.id.event_title);
            pr_eventvenue=itemView.findViewById(R.id.event_venue);
            pr_eventdate=itemView.findViewById(R.id.event_date);
            pr_eventtime=itemView.findViewById(R.id.event_time);
            pr_eventimage=itemView.findViewById(R.id.ongoing_event_image);
        }
    }
}
