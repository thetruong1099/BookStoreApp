//package com.example.bookstoreapp.Fragment;
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.bookstoreapp.Adapter.BookLiteraryAdapter;
//import com.example.bookstoreapp.Model.Book;
//import com.example.bookstoreapp.R;
//import com.example.bookstoreapp.Service.BookService;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * A simple {@link Fragment} subclass.
// * create an instance of this fragment.
// */
//public class LiteraryRankFragment extends Fragment {
//
//    private BookService bookService;
//    private RecyclerView literaryRankRecyclerView;
//    private BookLiteraryAdapter bookLiteraryAdapter;
//    private List<Book> bookList;
//
//    public LiteraryRankFragment() {
//        // Required empty public constructor
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_literary_rank, container, false);
//        literaryRankRecyclerView = view.findViewById(R.id.literaryRankRecyclerView);
//        literaryrank();
//        return view;
//    }
//
//    private void literaryrank() {
//        bookService =  new BookService();
//        bookList = new ArrayList<>();
//        bookService.getBookOfLiterarybyDiemDanhGia();
//
//
//
//
//
//        bookList = bookService.getBookList();
//        bookLiteraryAdapter = new BookLiteraryAdapter(bookList,getContext());
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        literaryRankRecyclerView.setLayoutManager(layoutManager);
//        literaryRankRecyclerView.setAdapter(bookLiteraryAdapter);
//    }
//
//}