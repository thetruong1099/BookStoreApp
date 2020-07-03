package com.example.bookstoreapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstoreapp.Model.Address;
import com.example.bookstoreapp.R;

import java.util.List;

public class AddressManagerAdapter extends RecyclerView.Adapter<AddressManagerAdapter.ViewHolder> {

    private List<Address> addressManagerAdapterList;
    private Context context;

    public AddressManagerAdapter(List<Address> addressManagerAdapterList, Context context) {
        this.addressManagerAdapterList = addressManagerAdapterList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNamePhoneAddressManager;
        private TextView tvDetailAddressManager;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDetailAddressManager = itemView.findViewById(R.id.tvDetailAddressManager);
            tvNamePhoneAddressManager = itemView.findViewById(R.id.tvNamePhoneAddressManager);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.address_manager_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Address address = addressManagerAdapterList.get(position);
        String namePhone = address.getFullname()+" - "+address.getPhone();
        final String diaChi = address.getHomeAddress() + ", "+address.getCommune() + ", " + address.getDistrict() + ", " + address.getCity() + ", " + address.getCity();

        holder.tvNamePhoneAddressManager.setText(namePhone);
        holder.tvDetailAddressManager.setText(diaChi);
    }

    @Override
    public int getItemCount() {
        return addressManagerAdapterList.size();
    }


}
