package com.example.startupclub;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.startupclub.PreviousRecycler.PreviousFragment;
import com.example.startupclub.UpcomingRecycler.UpcomingFragment;
import com.example.startupclub.ongoingrecycler.OngoingFragment;

public class PagerAdapter extends FragmentStateAdapter {
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new PreviousFragment();
            case 1 :
                return  new OngoingFragment();
            case 2 :
                return  new UpcomingFragment();
        }
        return new OngoingFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
