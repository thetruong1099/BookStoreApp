package com.example.bookstoreapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookstoreapp.Activity.BookDetailActivity;
import com.example.bookstoreapp.Model.Book;
import com.example.bookstoreapp.R;

import java.util.List;

public class BookHomeAdapter extends RecyclerView.Adapter<BookHomeAdapter.ViewHolder> {

    private List<Book> booksBestSaleList;
    private Context context;

    public BookHomeAdapter(List<Book> booksBestSaleList, Context context) {
        this.booksBestSaleList = booksBestSaleList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout bookBestSaleLayout;
        private ImageView ivAnhSach;
        private TextView tvGiamGia;
        private TextView tvTenSach;
        private TextView tvGiaBan;
        private TextView tvGiaGoc;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            bookBestSaleLayout = itemView.findViewById(R.id.bookBestSaleLayout);
            ivAnhSach = itemView.findViewById(R.id.ivAnhBiaSach);
            tvGiamGia = itemView.findViewById(R.id.tvGiamGia);
            tvTenSach = itemView.findViewById(R.id.tvTenSach);
            tvGiaBan = itemView.findViewById(R.id.tvGiaMoi);
            tvGiaGoc = itemView.findViewById(R.id.tvGiaGoc);
        }

    }

    @NonNull
    @Override
    public BookHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.book_best_sale_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookHomeAdapter.ViewHolder holder, int position) {
        final Book book = booksBestSaleList.get(position);
        final String resource = book.getAnh();
        final String tenSach = book.getTenSach();
        final int giamGia = book.getGiamGia();
        final int giaGoc = book.getGiaGoc();
        final int giaBan = giaGoc -  giaGoc*giamGia/100;


 //       System.out.println(book.getAnh());
        Glide.with(context).load(resource).into(holder.ivAnhSach);
        holder.tvGiamGia.setText("-"+giamGia+"%");
        holder.tvTenSach.setText(tenSach);
        holder.tvGiaBan.setText(giaBan + " đ");
        holder.tvGiaGoc.setText(giaGoc + " đ");
        holder.tvGiaGoc.setPaintFlags(holder.tvGiaGoc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.bookBestSaleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BookDetailActivity.class);
                intent.putExtra("id",book.getId());
                intent.putExtra("anh", resource);
                intent.putExtra("tenSach",tenSach);
                intent.putExtra("giamGia",String.valueOf(giamGia));
                intent.putExtra("giaGoc",String.valueOf(giaGoc));
                intent.putExtra("giaBan",String.valueOf(giaBan));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return booksBestSaleList.size();
    }

}
