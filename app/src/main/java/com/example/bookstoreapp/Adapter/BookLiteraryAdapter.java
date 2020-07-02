package com.example.bookstoreapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookstoreapp.Model.Book;
import com.example.bookstoreapp.R;

import java.util.List;

public class BookLiteraryAdapter extends RecyclerView.Adapter<BookLiteraryAdapter.ViewHolder> {

    private List<Book> booksList;
    private Context context;

    public BookLiteraryAdapter(List<Book> booksList, Context context) {
        this.booksList = booksList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout literaryRankItemLayout;
        private TextView tvRank;
        private TextView tvTenSachOfRank;


        public ViewHolder(@NonNull View itemView){
            super(itemView);
            literaryRankItemLayout = itemView.findViewById(R.id.literary_rank_item_layout);
            tvRank = itemView.findViewById(R.id.tvRank);
            tvTenSachOfRank = itemView.findViewById(R.id.tvTenSachOfRank);
        }
    }

    @NonNull
    @Override
    public BookLiteraryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_best_sale_layout,parent,false);

        return new BookLiteraryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookLiteraryAdapter.ViewHolder holder, int position) {
        final Book book = booksList.get(position);
        String tensach = book.getTenSach();
        int rank = position +1;
//        holder.tvRank.setText(rank);
//        holder.tvTenSachOfRank.setText(tensach);
//        holder.literaryRankItemLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }
}
