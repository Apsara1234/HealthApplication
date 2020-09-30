package com.example.healthapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.healthapplication.Adapter.ViewPagerAdapter;
import com.example.healthapplication.BookingActivity;
import com.example.healthapplication.R;

public class HomeFragment extends Fragment {

    ViewPager viewpager;

    ImageView booking, bbooking;

    private int position;
    private static final int PAGE_NUM=4;

    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            viewpager.setCurrentItem(position,true);
            if(position>=PAGE_NUM) position=0;
            else position++;
            handler.postDelayed(runnable,2000);
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
          View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewpager = view.findViewById(R.id.viewpager);
        booking = view.findViewById(R.id.booking);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getActivity());
        viewpager.setAdapter(viewPagerAdapter);
        runnable.run();

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BookingActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}