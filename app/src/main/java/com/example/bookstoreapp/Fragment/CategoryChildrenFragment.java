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
public class CategoryChildrenFragment extends Fragment {

    private LinearLayout btnKienThucGory;
    private LinearLayout btnMangaCategory;

    public CategoryChildrenFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_category_children, container, false);

        btnKienThucGory = view.findViewById(R.id.btnKienThucGory);
        btnMangaCategory = view.findViewById(R.id.btnMangaCategory);

        btnKienThucGory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("theLoai", "Kiến Thức Bách Khoa");
                BookListFragment bookListFragment = new BookListFragment();
                bookListFragment.setArguments(bundle);
                setFragment(bookListFragment);
            }
        });

        btnMangaCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("theLoai", "Manga - Comic");
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