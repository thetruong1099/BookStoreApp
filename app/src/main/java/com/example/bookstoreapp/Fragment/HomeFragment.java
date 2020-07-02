package com.example.bookstoreapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import com.example.bookstoreapp.Adapter.BestBookAdapter;
import com.example.bookstoreapp.Adapter.BookHomeAdapter;
import com.example.bookstoreapp.Adapter.SliderAdapter;
import com.example.bookstoreapp.Model.Book;
import com.example.bookstoreapp.Model.SliderModel;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.BannerService;
import com.example.bookstoreapp.Service.BookService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    //Banner Slider
    private BannerService bannerService;
    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderList;
    SliderAdapter sliderAdapter;
    //Banner Slider

    //Best Sale
    private BookService bookService;
    private RecyclerView bestSaleRecyclerView;
    private List<Book> bookBestSaleList;
    private BookHomeAdapter bookHomeAdapter;
    //Best Sale

    //Best Book
    private RecyclerView bestBookRecyclerView;
    private List<Book> bestBookList;
    private BestBookAdapter bestBookAdapter;
    //Best Book






    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , final ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_home , container , false );


        //Banner Slider
        bannerSliderViewPager = view.findViewById(R.id.banner_view_pager);
        bannerSlider();
        //Banner Slider

        //Best Sale
        bestSaleRecyclerView = view.findViewById(R.id.best_sale_book_recycler);
        setBestSaleRecyclerView();

        //Best Sale

        //Best Book
        bestBookRecyclerView = view.findViewById(R.id.best_book_recycler);
//        setBestBookRecyclerView();
        //Best Book


        return view;
    }


    private void bannerSlider() {

        bannerService = new BannerService();

        sliderList = new ArrayList<>();
        bannerService.getAlldata();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (bannerService.isStatus()) {
                    sliderList = bannerService.getBannnerlist();
                    sliderAdapter = new SliderAdapter(sliderList, getContext());
                    bannerSliderViewPager.setAdapter(sliderAdapter);
                    handler.removeCallbacks(this);
                }
                else handler.postDelayed(this, 1);
            }
        }, 1);
    }


    private void setBestSaleRecyclerView() {
        bookService = new BookService();
        bookBestSaleList = new ArrayList<>();
        bookService.getBookBestSaleData();
        final Handler handler = new Handler();
//        System.out.println("================= Best Sale");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                System.out.println(bookService.isStatusBestSale());
                if (bookService.isStatusBestSale()) {
                    bookBestSaleList = bookService.getBookBestSaleList();
//                    System.out.println("================= Best Sale");
//
//                    System.out.println(bookBestSaleList.get(1).getTenSach());
//                    System.out.println(bookBestSaleList.get(1).getAnh());
                    bookHomeAdapter = new BookHomeAdapter(bookBestSaleList,getContext());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                    bestSaleRecyclerView.setLayoutManager(layoutManager);
                    bestSaleRecyclerView.setAdapter(bookHomeAdapter);
                    handler.removeCallbacks(this);
                }
                else {
                    handler.postDelayed(this, 1);
                }
            }
        }, 1);
    }

    private void setBestBookRecyclerView() {
        bookService = new BookService();
        bestBookList = new ArrayList<>();
        FirebaseFirestore.getInstance().collection("books")
                .orderBy("diemDanhGia", Query.Direction.DESCENDING)
                .limit(10)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Book book = new Book();
                                book.setId(document.getId());
                                book.setAnh(document.getString("anh"));
                                book.setGiamGia(Math.toIntExact(document.getLong("giamGia")));
                                book.setTenSach(document.getString("tenSach"));
                                book.setDiemDanhGia(Math.toIntExact(document.getLong("diemDanhGia")));
                                book.setGiaGoc(Math.toIntExact(document.getLong("giaGoc")));
                                bestBookList.add(book);
                            }
                            bestBookAdapter = new BestBookAdapter(bestBookList,getContext());
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
                            bestBookRecyclerView.setLayoutManager(layoutManager);
                            bestBookRecyclerView.setAdapter(bestBookAdapter);

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }
}
