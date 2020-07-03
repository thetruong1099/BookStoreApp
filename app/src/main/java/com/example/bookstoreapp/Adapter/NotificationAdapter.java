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

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<Bill> billList;
    private Context context;

    public NotificationAdapter(List<Bill> billList, Context context) {
        this.billList = billList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMaDonHangNotification;
        private TextView tvNamePhoneNotification;
        private TextView tvAddressNotification;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMaDonHangNotification = itemView.findViewById(R.id.tvMaDonHangNotification);
            tvNamePhoneNotification = itemView.findViewById(R.id.tvNamePhoneNotification);
            tvAddressNotification = itemView.findViewById(R.id.tvAddressNotification);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.notification_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Bill bill = billList.get(position);
        String maDonHang = "Mã Đơn Hàng - " +  bill.getId();
        String namePhone =bill.getFullName()+ " - " + bill.getPhone();
        String address = bill.getAddress();

        holder.tvMaDonHangNotification.setText(maDonHang);
        holder.tvNamePhoneNotification.setText(namePhone);
        holder.tvAddressNotification.setText(address);
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }


}
