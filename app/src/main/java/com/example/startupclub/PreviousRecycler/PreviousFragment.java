package com.example.startupclub.PreviousRecycler;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.startupclub.R;
import com.example.startupclub.UpcomingRecycler.UpcomingAdapter;
import com.example.startupclub.UpcomingRecycler.UpcomingModal;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreviousFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviousFragment extends Fragment {
    RecyclerView recyclerViewPreviousEvent;
    CardView cardLayoutNoPreviousData;
    DatabaseReference databaseReference;
    PreviousAdapter previousAdapter;
    private ArrayList<PreviousModal> plist;
    private Context pcontext;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PreviousFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreviousFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreviousFragment newInstance(String param1, String param2) {
        PreviousFragment fragment = new PreviousFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_previous, container, false);
        recyclerViewPreviousEvent=view.findViewById(R.id.previous_recyclerview);
        cardLayoutNoPreviousData=view.findViewById(R.id.no_previous_event);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Event");
        pcontext = getContext();

        eventupdate();

        return view;
    }

    private void eventupdate() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count=0;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                plist=new ArrayList<>();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String datekey = dataSnapshot.child("date").getValue().toString();
                    Log.d("Looking for date1", "onDataChange: "+datekey);
                    String today = simpleDateFormat.format(new Date());
                    try {
                        Date mdate = simpleDateFormat.parse(datekey);
                        Log.d("Looking for date2", "onDataChange: "+mdate);
                        Date mtoday = simpleDateFormat.parse(today);
                        Log.d("Looking for date3", "onDataChange: "+mtoday);
                        Log.d("Comparsion", "The value of comparsion is : "+mdate.compareTo(mtoday));
                        if(mdate.compareTo(mtoday)<0){
                            count++;
                            Log.d("Checking for count1", "The value of count is : "+count);
                            if(count>0){
                                Log.d("Checking for count2", "Inside else block , events available: ");
                                cardLayoutNoPreviousData.setVisibility(View.GONE);
                                recyclerViewPreviousEvent.setVisibility(View.VISIBLE);
                                PreviousModal modal = dataSnapshot.getValue(PreviousModal.class);
                                Log.d("tag","the image is "+ dataSnapshot.getValue());
                                plist.add(modal);
                                Log.d("Recycler View", "The size of elist is: "+plist.size());
                            }else{
                                Log.d("Checking for count3", "Inside else block , no events available: ");
                                cardLayoutNoPreviousData.setVisibility(View.VISIBLE);
                                recyclerViewPreviousEvent.setVisibility(View.GONE);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(pcontext);
                recyclerViewPreviousEvent.setHasFixedSize(true);
                recyclerViewPreviousEvent.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setReverseLayout(true);
                previousAdapter=new PreviousAdapter(pcontext,plist);
                recyclerViewPreviousEvent.setAdapter(previousAdapter);
                Log.d("Recycler View", "Recycler view is called: ");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(pcontext,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}