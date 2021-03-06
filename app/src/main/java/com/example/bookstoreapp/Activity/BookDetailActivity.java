package com.example.bookstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookstoreapp.Adapter.BookHomeAdapter;
import com.example.bookstoreapp.Fragment.ShoppingCartFragment;
import com.example.bookstoreapp.Model.Book;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.BookService;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.opencensus.tags.Tag;

public class BookDetailActivity extends AppCompatActivity {
    private ImageView ivAnhBiaSachDetail;
    private TextView tvTenSachDetail;
    private TextView tvDiemDanhGiaDeatail;
    private TextView tvGiaBanDetail;
    private TextView tvGiaGocDetail;
    private TextView tvGiamGiaDetail;
    private TextView tvMaSachDetail;
    private TextView tvTacGiaDetail;
    private TextView tvNhaCungCapDetail;
    private TextView tvNhaXuatBanDetail;
    private TextView tvNamXuatDetail;
    private TextView tvMoTaDetail;
    private ImageView ivGiamDetail;
    private TextView tvSoLuongDetail;
    private ImageView ivTangDetail;
    private Button btnMuaNgay;
    private Button btnThemVaoGioHang;
    private Button btnGuiDanhGia;
    private RatingBar ratingBar;

    String id, anh, tenSach, giamGia, giaGoc,giaBan;
    int soLuong = 1;
    int diemDanhGia = 0;
    int diemDanhGia1 = 0;
    float myRating = 0;

    private BookService bookService;
    private Book book;
    private boolean status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ivAnhBiaSachDetail = findViewById(R.id.ivAnhBiaSachDetail);
        tvTenSachDetail = findViewById(R.id.tvTenSachDetail);
        tvDiemDanhGiaDeatail = findViewById(R.id.tvDiemDanhGiaDetail);
        tvGiaBanDetail = findViewById(R.id.tvGiaBanDetail);
        tvGiamGiaDetail = findViewById(R.id.tvGiamGiaDetail);
        tvGiaGocDetail = findViewById(R.id.tvGiaGocDetail);
        tvMaSachDetail = findViewById(R.id.tvMaSachDetail);
        tvTacGiaDetail = findViewById(R.id.tvTacGiaDetail);
        tvNhaCungCapDetail = findViewById(R.id.tvNhaCungCapDetail);
        tvNhaXuatBanDetail = findViewById(R.id.tvNhaXuatBanDetail);
        tvNamXuatDetail = findViewById(R.id.tvNamXuatBanDetail);
        tvMoTaDetail = findViewById(R.id.tvMoTaDetail);
        ivGiamDetail = findViewById(R.id.ivGiamDetail);
        tvSoLuongDetail = findViewById(R.id.tvSoLuongDetail);
        ivTangDetail = findViewById(R.id.ivTangDetail);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);
        btnThemVaoGioHang = findViewById(R.id.btnThemGioHang);
        btnGuiDanhGia = findViewById(R.id.btnGuiDanhGiaBookDetail);
        ratingBar = findViewById(R.id.ratingBar);





        getandsetIntenData();
        setDataDetail();

        /////////////////////
        setDiemDanhGia();




        ////////////////
        tvSoLuongDetail.setText(String.valueOf(soLuong));
        ivTangDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tangSoLuong();
            }
        });
        ivGiamDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giamSoLuong();
            }
        });

        status = false;
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser!=null){
            final String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlerDialog();
                    setShoppingCart(userID);
                }
            });
            btnMuaNgay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(BookDetailActivity.this, MainActivity.class);
                    startActivity(intent);
                    setShoppingCart(userID);
                }
            });
            status = true;
        }else {
            btnMuaNgay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(BookDetailActivity.this, "Cần Đăng Nhập Để Mua Hàng", Toast.LENGTH_SHORT).show();
                }
            });
            btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(BookDetailActivity.this, "Cần Đăng Nhập Để Mua Hàng", Toast.LENGTH_SHORT).show();
                }
            });
            status = false;
        }


    }

    private void showAlerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(BookDetailActivity.this);
        builder.setMessage("Thêm vào giỏ Hàng thành công");
        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void getandsetIntenData() {
        //get Data
        id = getIntent().getStringExtra("id");
        anh = getIntent().getStringExtra("anh");
        tenSach = getIntent().getStringExtra("tenSach");
        giamGia = getIntent().getStringExtra("giamGia");
        giaGoc = getIntent().getStringExtra("giaGoc");
        giaBan = getIntent().getStringExtra("giaBan");


        //set Data
        Glide.with(getApplicationContext()).load(anh).into(ivAnhBiaSachDetail);
        tvTenSachDetail.setText(tenSach);
        tvGiaBanDetail.setText(giaBan + " đ");
        tvGiaGocDetail.setText(giaGoc + " đ");
        tvGiaGocDetail.setPaintFlags(tvGiaGocDetail.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvGiamGiaDetail.setText("-"+giamGia+"%");
    }

    private void tangSoLuong(){
        soLuong++;
        tvSoLuongDetail.setText(String.valueOf(soLuong));
    }

    private void giamSoLuong(){
        soLuong--;
        if(soLuong>0){
            tvSoLuongDetail.setText(String.valueOf(soLuong));
        }else {
            soLuong = 1;
            tvSoLuongDetail.setText("1");
        }

    }

    private void setDataDetail() {
        book = new Book();
        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("books").document(id);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                book = documentSnapshot.toObject(Book.class);
                tvDiemDanhGiaDeatail.setText(String.valueOf(book.getDiemDanhGia())+ " điểm");
                tvMaSachDetail.setText(id);
                tvTacGiaDetail.setText(book.getTacGia());
                tvNhaCungCapDetail.setText(book.getNhaCungCap());
                tvNhaXuatBanDetail.setText(book.getNhaXuatBan());
                tvNamXuatDetail.setText(String.valueOf(book.getNamXuatBan()));
                tvMoTaDetail.setText(book.getMoTa());

                diemDanhGia = book.getDiemDanhGia();
            }
        });
    }

    private void setShoppingCart(String userID){
        Map<String, Object> bookSC = new HashMap<>();
        bookSC.put("id",id);
        bookSC.put("anh",anh);
        bookSC.put("tenSach",tenSach);
        bookSC.put("giaBan",giaBan);
        bookSC.put("soLuong",soLuong);
        FirebaseFirestore.getInstance().collection( "users" ).document(userID).collection("listBookSC").document(id).set(bookSC);
    }

    private void setDiemDanhGia(){
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                myRating = ratingBar.getRating();
                diemDanhGia1 = (int) (diemDanhGia + myRating);
                setGuiDanhGia();
            }

        });

    }
    private void setGuiDanhGia(){
        bookService = new BookService();
        if(status){
            btnGuiDanhGia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvDiemDanhGiaDeatail.setText(String.valueOf(diemDanhGia1) + " điểm");
                    bookService.updateDiemDanhGia(id,diemDanhGia1);
                    Toast.makeText(BookDetailActivity.this, "Đã gửi điểm đánh giá", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            btnGuiDanhGia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(BookDetailActivity.this, "Cần Đăng Nhập Để Mua Hàng", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}