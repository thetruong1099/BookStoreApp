package com.example.bookstoreapp.Adapter;

import android.content.Context;
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
import com.example.bookstoreapp.Model.Book;
import com.example.bookstoreapp.R;

import java.util.List;

public class BestBookAdapter extends RecyclerView.Adapter<BestBookAdapter.ViewHolder> {
    private List<Book> bookList;
    private Context context;

    public BestBookAdapter(List<Book> bookList, Context context) {
        this.bookList = bookList;
        this.context = context;
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private ConstraintLayout bestbookConstraintLauout;
        private ImageView ivAnhBiaBestBook;
        private TextView tvGiamGiaBestBook;
        private TextView tvTenSachBestBook;
        private TextView tvDiemDanhGiaBestBook;
        private TextView tvGiaMoiBestBook;
        private TextView tvGiaGocBestBook;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bestbookConstraintLauout = itemView.findViewById(R.id.bookBestBookLayout);
            ivAnhBiaBestBook = itemView.findViewById(R.id.ivAnhBiaSachBestBook);
            tvGiamGiaBestBook = itemView.findViewById(R.id.tvGiamGiaBestBook);
            tvTenSachBestBook = itemView.findViewById(R.id.tvTenSachBestBook);
            tvDiemDanhGiaBestBook = itemView.findViewById(R.id.tvDiemDanhGiaBestBook);
            tvGiaMoiBestBook = itemView.findViewById(R.id.tvGiaMoiBestBook);
            tvGiaGocBestBook = itemView.findViewById(R.id.tvGiaGocBestBook);
        }
    }

    @NonNull
    @Override
    public BestBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.best_book_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BestBookAdapter.ViewHolder holder, int position) {
        final  Book book = bookList.get(position);
        String tenSach = book.getTenSach();
        int giamGia = book.getGiamGia();
        int giaGoc = book.getGiaGoc();
        int giaBan = giaGoc -  giaGoc*giamGia/100;
        int diemDanhGia = book.getDiemDanhGia();


        Glide.with(context).load(book.getAnh()).into(holder.ivAnhBiaBestBook);
        holder.tvGiamGiaBestBook.setText("-"+giamGia+"%");
        holder.tvTenSachBestBook.setText(tenSach);
        holder.tvGiaMoiBestBook.setText(giaBan + " đ");
        holder.tvGiaGocBestBook.setText(giaGoc + " đ");
        holder.tvGiaGocBestBook.setPaintFlags(holder.tvGiaGocBestBook.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvDiemDanhGiaBestBook.setText(diemDanhGia+" điểm");
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


}
