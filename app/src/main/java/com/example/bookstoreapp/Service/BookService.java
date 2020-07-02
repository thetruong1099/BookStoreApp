package com.example.bookstoreapp.Service;

import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bookstoreapp.Model.Book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class BookService {
    private List<Book> bookBestSaleList = new ArrayList<>();
    private List<Book> bestBookList = new ArrayList<>();
    private boolean statusbestsale = false;




    ////////////////////////////
    public boolean isStatusBestSale() {
        return statusbestsale;
    }

    public List<Book> getBookBestSaleList() {
        return bookBestSaleList;
    }

    public void getBookBestSaleData(){
        statusbestsale = false;
        Task<QuerySnapshot> documentSnapshot1  =  FirebaseFirestore.getInstance().collection("books").orderBy("giamGia", Query.Direction.DESCENDING).limit(10).get();
        documentSnapshot1.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Book book = new Book();
                                book.setId(document.getId());
                                book.setAnh(document.getString("anh"));
                                book.setGiamGia(Math.toIntExact(document.getLong("giamGia")));
                                book.setTenSach(document.getString("tenSach"));
                                book.setGiaGoc(Math.toIntExact(document.getLong("giaGoc")));
                                bookBestSaleList.add(book);
                            }
                            statusbestsale = true;
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
