package com.example.bookstoreapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookstoreapp.Adapter.BookAdapter;
import com.example.bookstoreapp.Model.Book;
import com.example.bookstoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class BookListFragment extends Fragment {

    private ImageView ivBackToHomeFragmentFromBookListFragment;
    private TextView edtSearchBookList;
    private String searchKey;

    //bookList
    private RecyclerView bookListRecyclerViewSearch;
    private List<Book> bookList;
    private BookAdapter bookAdapter;
    //bookList

    public BookListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_book_list, container, false);

        ivBackToHomeFragmentFromBookListFragment = view.findViewById(R.id.ivBackToHomeFragmentFromBookListFragment);
        edtSearchBookList = view.findViewById(R.id.edtSearchBookList);

        ivBackToHomeFragmentFromBookListFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new HomeFragment());
            }
        });


        getDataFrament();

        bookListRecyclerViewSearch = view.findViewById(R.id.bookListRecyclerViewSearch);
        setBookListRecyclerViewSearch(searchKey);



        edtSearchBookList.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (i == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    setBookListRecyclerViewSearch(edtSearchBookList.getText().toString().trim());
                    return true;
                }
                return false;
            }
        });

        return view;
    }


    private void getDataFrament() {
        Bundle bundle = this.getArguments();
        searchKey = bundle.getString("tuKhoa");
        edtSearchBookList.setText(searchKey);
    }

    private void setBookListRecyclerViewSearch(String tuKhoa) {
        bookList = new ArrayList<>();
        FirebaseFirestore.getInstance().collection("books")
                .whereGreaterThan("tenSach",tuKhoa)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                Book book = new Book();
                                book.setId(documentSnapshot.getId());
                                book.setAnh(documentSnapshot.getString("anh"));
                                book.setGiamGia(Math.toIntExact(documentSnapshot.getLong("giamGia")));
                                book.setTenSach(documentSnapshot.getString("tenSach"));
                                book.setDiemDanhGia(Math.toIntExact(documentSnapshot.getLong("diemDanhGia")));
                                book.setGiaGoc(Math.toIntExact(documentSnapshot.getLong("giaGoc")));
                                bookList.add(book);
                            }
                            bookAdapter = new BookAdapter(bookList,getContext());
                            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                            bookListRecyclerViewSearch.setLayoutManager(layoutManager);
                            bookListRecyclerViewSearch.setAdapter(bookAdapter);
                        }
                        else {
                            Log.e(TAG, "Lỗi không tìm thấy");
                        }
                    }
                });
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}