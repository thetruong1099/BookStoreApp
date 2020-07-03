package com.example.bookstoreapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstoreapp.Model.Bill;
import com.example.bookstoreapp.R;

import java.util.List;

public class ShoppingHistoryAdapter extends RecyclerView.Adapter<ShoppingHistoryAdapter.ViewHolder> {

    private List<Bill> billList;
    private Context context;

    public ShoppingHistoryAdapter(List<Bill> billList, Context context) {
        this.billList = billList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDonHangShoppingHistory;
        private TextView tvTrangThaiShoppingHistory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDonHangShoppingHistory = itemView.findViewById(R.id.tvDonHangShoppingHistory);
            tvTrangThaiShoppingHistory = itemView.findViewById(R.id.tvTrangThaiShoppingHistory);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.shopping_history_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Bill bill = billList.get(position);
        String maDonHang = "Đơn Hàng - " +  bill.getId();
        String trangThai = bill.getTrangThai();

        holder.tvDonHangShoppingHistory.setText(maDonHang);
        holder.tvTrangThaiShoppingHistory.setText(trangThai);
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }


}
