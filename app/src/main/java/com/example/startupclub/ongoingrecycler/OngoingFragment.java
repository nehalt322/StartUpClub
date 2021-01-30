package com.example.startupclub.ongoingrecycler;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.startupclub.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OngoingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OngoingFragment extends Fragment {
    RecyclerView recyclerViewOngoingEvent;
    CardView cardlayoutNoOngoingData;
    DatabaseReference databaseReference;
    OngoingAdapter ongoingAdapter;
    private ArrayList<OngoingModal> elist;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;

    public OngoingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OngoingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OngoingFragment newInstance(String param1, String param2) {
        OngoingFragment fragment = new OngoingFragment();
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
        View view = inflater.inflate(R.layout.fragment_ongoing, container, false);
        recyclerViewOngoingEvent=view.findViewById(R.id.ongoing_recyclerview);
        cardlayoutNoOngoingData=view.findViewById(R.id.no_ongoing_event);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Event");
        context = getContext();

        eventupdate();

        return view;
    }

    private void eventupdate() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int count=0;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                elist=new ArrayList<>();
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
                         if(mdate.compareTo(mtoday)==0){
                             count++;
                             Log.d("Checking for count1", "The value of count is : "+count);
                             if(count>0){
                                 Log.d("Checking for count2", "Inside else block , events available: ");
                                 cardlayoutNoOngoingData.setVisibility(View.GONE);
                                 recyclerViewOngoingEvent.setVisibility(View.VISIBLE);
                                 OngoingModal modal = dataSnapshot.getValue(OngoingModal.class);
                                 Log.d("tag","the image is "+ dataSnapshot.getValue());
                                 elist.add(modal);
                                 Log.d("Recycler View", "The size of elist is: "+elist.size());
                             }else{
                                 Log.d("Checking for count3", "Inside else block , no events available: ");
                                 cardlayoutNoOngoingData.setVisibility(View.VISIBLE);
                                 recyclerViewOngoingEvent.setVisibility(View.GONE);
                             }
                         }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
                recyclerViewOngoingEvent.setHasFixedSize(true);
                recyclerViewOngoingEvent.setLayoutManager(linearLayoutManager);
                linearLayoutManager.setReverseLayout(true);
                ongoingAdapter=new OngoingAdapter(context,elist);
                recyclerViewOngoingEvent.setAdapter(ongoingAdapter);
                Log.d("Recycler View", "Recycler view is called: ");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}