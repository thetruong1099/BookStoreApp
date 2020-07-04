package com.example.bookstoreapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.bookstoreapp.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class CategoryEconomyFragment extends Fragment {
    private LinearLayout btnNhanVatCateGory;
    private LinearLayout btnQuanTriCategory;
    private LinearLayout btnMarkettingCategory;
    private LinearLayout btnPhanTichKinhTeCategory;

    public CategoryEconomyFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category_economy, container, false);

        btnNhanVatCateGory = view.findViewById(R.id.btnNhanVatCateGory);
        btnQuanTriCategory = view.findViewById(R.id.btnQuanTriCategory);
        btnMarkettingCategory = view.findViewById(R.id.btnMarkettingCategory);
        btnPhanTichKinhTeCategory = view.findViewById(R.id.btnPhanTichKinhTeCategory);

        btnNhanVatCateGory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("theLoai", "Nhân vật - Bài học kinh doanh");
                BookListFragment bookListFragment = new BookListFragment();
                bookListFragment.setArguments(bundle);
                setFragment(bookListFragment);
            }
        });

        btnQuanTriCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("theLoai", "Quản Trị - Lãnh Đạo");
                BookListFragment bookListFragment = new BookListFragment();
                bookListFragment.setArguments(bundle);
                setFragment(bookListFragment);
            }
        });

        btnMarkettingCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("theLoai", "Marketing - Bán Hàng");
                BookListFragment bookListFragment = new BookListFragment();
                bookListFragment.setArguments(bundle);
                setFragment(bookListFragment);
            }
        });

        btnPhanTichKinhTeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("theLoai", "Phân Tích Kinh Tế");
                BookListFragment bookListFragment = new BookListFragment();
                bookListFragment.setArguments(bundle);
                setFragment(bookListFragment);
            }
        });


        return view;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}