package com.example.bookstoreapp.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bookstoreapp.R;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {
    private TextView tvVanHoc;
    private TextView tvKinhTe;
    private TextView tvThieuNhi;

    public CategoryFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category, container, false);

        tvVanHoc = view.findViewById(R.id.tvVanHoc);
        tvKinhTe = view.findViewById(R.id.tvKinhTe);
        tvThieuNhi = view.findViewById(R.id.tvThieuNhi);


        setFragment(new CategoryLiteraryFragment());
        tvVanHoc.setTextColor(Color.rgb(255,155,28));
        tvVanHoc.setBackgroundColor(Color.rgb(255,255,255));

        tvVanHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new CategoryLiteraryFragment());
                tvVanHoc.setTextColor(Color.rgb(255,155,28));
                tvVanHoc.setBackgroundColor(Color.rgb(255,255,255));

                tvKinhTe.setTextColor(Color.rgb(0,0,0));
                tvKinhTe.setBackgroundColor(Color.rgb(245,245,245));

                tvThieuNhi.setTextColor(Color.rgb(0,0,0));
                tvThieuNhi.setBackgroundColor(Color.rgb(245,245,245));
            }
        });

        tvKinhTe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new CategoryEconomyFragment());
                tvKinhTe.setTextColor(Color.rgb(255,155,28));
                tvKinhTe.setBackgroundColor(Color.rgb(255,255,255));

                tvVanHoc.setTextColor(Color.rgb(0,0,0));
                tvVanHoc.setBackgroundColor(Color.rgb(245,245,245));

                tvThieuNhi.setTextColor(Color.rgb(0,0,0));
                tvThieuNhi.setBackgroundColor(Color.rgb(245,245,245));
            }
        });
        tvThieuNhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new CategoryChildrenFragment());
                tvThieuNhi.setTextColor(Color.rgb(255,155,28));
                tvThieuNhi.setBackgroundColor(Color.rgb(255,255,255));

                tvVanHoc.setTextColor(Color.rgb(0,0,0));
                tvVanHoc.setBackgroundColor(Color.rgb(245,245,245));

                tvKinhTe.setTextColor(Color.rgb(0,0,0));
                tvKinhTe.setBackgroundColor(Color.rgb(245,245,245));
            }
        });
        return view;
    }


    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.CategoryFrameLayout, fragment);
        fragmentTransaction.commit();
    }
}