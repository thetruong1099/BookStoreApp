package com.example.bookstoreapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.BannerService;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //Banner Slider
    private TextView tvtest;
    private BannerService bannerService;
    private ViewPager bannerSliderViewPager;
    private List<String> sliderList;
    private int currentPage =2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    //Banner Slider

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , final ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_home , container , false );


        //Banner Slider
        tvtest = view.findViewById(R.id.tvtest);
        bannerSliderViewPager = view.findViewById(R.id.banner_view_pager);
        bannerService = new BannerService();

        sliderList = new ArrayList<String>();
        bannerService.getAlldata();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (bannerService.isStatus()) {
                    sliderList = bannerService.getBannnerlist();
 //                   System.out.println("==========================");
 //                   System.out.println(sliderList);
                    tvtest.setText(sliderList.get(1));
                    handler.removeCallbacks(this);
                }
                else handler.postDelayed(this, 100);
            }
        }, 100);


        return view;
    }

}
