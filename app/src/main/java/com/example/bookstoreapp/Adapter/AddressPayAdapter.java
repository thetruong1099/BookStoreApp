package com.example.bookstoreapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstoreapp.Model.Address;
import com.example.bookstoreapp.R;

import java.util.List;

public class AddressPayAdapter extends RecyclerView.Adapter<AddressPayAdapter.ViewHolder> {

    private List<Address> addressPayAdapterList;
    private Context context;

    private String city = "Hà Nội aaaaa";

    public AddressPayAdapter(List<Address> addressPayAdapterList, Context context) {
        this.addressPayAdapterList = addressPayAdapterList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox rbAddressPaySelect;
        private TextView tvNamePhoneAddressPay;
        private TextView tvDetailAddressPay;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rbAddressPaySelect = itemView.findViewById(R.id.rbAddressPaySelect);
            tvDetailAddressPay = itemView.findViewById(R.id.tvDetailAddressPay);
            tvNamePhoneAddressPay = itemView.findViewById(R.id.tvNamePhoneAddressPay);

        }
    }



    @NonNull
    @Override
    public AddressPayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.address_pay_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddressPayAdapter.ViewHolder holder, final int position) {
        final Address address = addressPayAdapterList.get(position);
        String namePhone = address.getFullname()+" - "+address.getPhone();
        final String diaChi = address.getHomeAddress() + ", "+address.getCommune() + ", " + address.getDistrict() + ", " + address.getCity() + ", " + address.getCity();

        holder.tvNamePhoneAddressPay.setText(namePhone);
        holder.tvDetailAddressPay.setText(diaChi);



        holder.rbAddressPaySelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    city = addressPayAdapterList.get(position).getCity();
                    Intent intent = new Intent("diaChi_intent");
                    intent.putExtra("diaChi",city);
                    intent.putExtra("diaChiLienHe",diaChi);
                    intent.putExtra("fullname", address.getFullname());
                    intent.putExtra("phone", address.getPhone());
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }else {
                    city = null;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return addressPayAdapterList.size();
    }


}
