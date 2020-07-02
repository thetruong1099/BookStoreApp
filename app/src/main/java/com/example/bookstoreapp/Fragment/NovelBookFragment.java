package com.example.bookstoreapp.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookstoreapp.Adapter.BookHomeAdapter;
import com.example.bookstoreapp.Model.Book;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.BookService;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NovelBookFragment extends Fragment {
    private BookService bookService;
    private RecyclerView novelBookRecyclerView;
    private List<Book> bookBestSaleList;
    private BookHomeAdapter bookHomeAdapter;

    public NovelBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_novel_book, container, false);

        novelBookRecyclerView = view.findViewById(R.id.novelBookRecyclerView);
//        setNovelBookRecyclerView();
        return view;
    }

//    private void setNovelBookRecyclerView() {
//        bookService = new BookService();
//        bookBestSaleList = new ArrayList<>();
// //       bookService.getBookOfTheLoai();
//
//
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (bookService.isStatus()) {
//                    bookBestSaleList = bookService.getBookBestSaleList();
////                    System.out.println("==========================");
////                    System.out.println(bookBestSaleList.get(1).getTenSach());
//                    bookHomeAdapter = new BookHomeAdapter(bookBestSaleList,getContext());
//                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
//                    novelBookRecyclerView.setLayoutManager(layoutManager);
//                    novelBookRecyclerView.setAdapter(bookHomeAdapter);
//                    handler.removeCallbacks(this);
//                }
//                else handler.postDelayed(this, 1);
//            }
//        }, 1);
//    }
}