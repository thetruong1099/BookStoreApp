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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> bookList;
    private Context context;

    public BookAdapter(List<Book> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout bookListItemLayout;
        private ImageView ivAnhBiaSachBookListSearch;
        private TextView tvGiamGiaBookListSearch;
        private TextView tvTenSachBookListSearch;
        private TextView tvGiaMoiBookListSearch;
        private TextView tvGiaGocBookListSearch;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookListItemLayout = itemView.findViewById(R.id.bookListItemLayout);
            ivAnhBiaSachBookListSearch = itemView.findViewById(R.id.ivAnhBiaSachBookListSearch);
            tvGiamGiaBookListSearch = itemView.findViewById(R.id.tvGiamGiaBookListSearch);
            tvTenSachBookListSearch = itemView.findViewById(R.id.tvTenSachBookListSearch);
            tvGiaMoiBookListSearch = itemView.findViewById(R.id.tvGiaMoiBookListSearch);
            tvGiaGocBookListSearch = itemView.findViewById(R.id.tvGiaGocBookListSearch);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.book_list_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Book book = bookList.get(position);
        final String resource = book.getAnh();
        final String tenSach = book.getTenSach();
        final int giamGia = book.getGiamGia();
        final int giaGoc = book.getGiaGoc();
        final int giaBan = giaGoc -  giaGoc*giamGia/100;


        Glide.with(context).load(resource).into(holder.ivAnhBiaSachBookListSearch);
        holder.tvGiamGiaBookListSearch.setText("-"+giamGia+"%");
        holder.tvTenSachBookListSearch.setText(tenSach);
        holder.tvGiaMoiBookListSearch.setText(giaBan + " đ");
        holder.tvGiaGocBookListSearch.setText(giaGoc + " đ");
        holder.tvGiaGocBookListSearch.setPaintFlags(holder.tvGiaGocBookListSearch.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.bookListItemLayout.setOnClickListener(new View.OnClickListener() {
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
        return bookList.size();
    }


}
