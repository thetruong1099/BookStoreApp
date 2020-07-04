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
public class CategoryLiteraryFragment extends Fragment {
    private LinearLayout btnNovelCateGory;
    private LinearLayout btnSortStoryCategory;
    private LinearLayout btnLightNovelCategory;

    public CategoryLiteraryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category_literary, container, false);

        btnNovelCateGory = view.findViewById(R.id.btnNovelCateGory);
        btnSortStoryCategory = view.findViewById(R.id.btnSortStoryCategory);
        btnLightNovelCategory = view.findViewById(R.id.btnLightNovelCategory);

        btnNovelCateGory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("theLoai", "Tiểu Thuyết");
                BookListFragment bookListFragment = new BookListFragment();
                bookListFragment.setArguments(bundle);
                setFragment(bookListFragment);
            }
        });

        btnSortStoryCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("theLoai", "Truyện Ngắn - Tản Văn");
                BookListFragment bookListFragment = new BookListFragment();
                bookListFragment.setArguments(bundle);
                setFragment(bookListFragment);
            }
        });

        btnLightNovelCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("theLoai", "Light Novel");
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