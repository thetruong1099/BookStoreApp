package com.example.bookstoreapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.bookstoreapp.Model.SliderModel;
import com.example.bookstoreapp.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private List<SliderModel> sliders;
    private LayoutInflater layoutInflater;
    private Context context;

    public SliderAdapter(List<SliderModel> sliders, Context context) {
        this.sliders = sliders;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliders.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);
        ImageView imageView;
        imageView = view.findViewById(R.id.banner_slider);
        Glide.with(container.getContext()).load(sliders.get(position).getBannerId()).into(imageView);
        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
