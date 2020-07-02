package com.example.bookstoreapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookstoreapp.Model.ShoppingCard;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.ShoppingCartService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {

    private List<ShoppingCard> listBookShoppingCard;
    private Context context;
    private int tongTien = 0;



    private ShoppingCartService shoppingCartService;

    int soLuong = 1;

    public ShoppingCartAdapter(List<ShoppingCard> listBookShoppingCard, Context context) {
        this.listBookShoppingCard = listBookShoppingCard;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivAnhBiaShoppingCard;
        private TextView tvTenSachShoppingCard;
        private TextView tvGiaBanShoppingCard;
        private TextView tvSoLuongShoppingCard;
        private ImageView ivGiamShoppingCard;
        private ImageView ivTangShoppingCard;
        private ImageView ivXoaShoppingCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAnhBiaShoppingCard = itemView.findViewById(R.id.ivAnhBiaShoppingCard);
            tvTenSachShoppingCard = itemView.findViewById(R.id.tvTenSachShoppingCard);
            tvGiaBanShoppingCard = itemView.findViewById(R.id.tvGiaBanShoppingCard);
            tvSoLuongShoppingCard = itemView.findViewById(R.id.tvSoLuongShoppingCard);
            ivGiamShoppingCard = itemView.findViewById(R.id.ivGiamShoppingCard);
            ivTangShoppingCard = itemView.findViewById(R.id.ivTangShoppingCard);
            ivXoaShoppingCard = itemView.findViewById(R.id.ivXoaShoppingCard);


        }
    }


    @NonNull
    @Override
    public ShoppingCartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.shopping_card_item_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final ShoppingCartAdapter.ViewHolder holder, final int position) {
        final ShoppingCard bookShoppingCard = listBookShoppingCard.get(position);
        final String resource = bookShoppingCard.getAnhBia();
        final String tenSach = bookShoppingCard.getTenSach();
        final String giaBan = bookShoppingCard.getGiaBan();
        soLuong  = bookShoppingCard.getSoLuong();

        final String userID = FirebaseAuth.getInstance().getUid();
        final String bookID = bookShoppingCard.getId();
        shoppingCartService = new ShoppingCartService();

        Glide.with(context).load(resource).into(holder.ivAnhBiaShoppingCard);
        holder.tvTenSachShoppingCard.setText(tenSach);
        holder.tvGiaBanShoppingCard.setText(giaBan + " đ");
        holder.tvSoLuongShoppingCard.setText(String.valueOf(soLuong));
        holder.ivGiamShoppingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                giamSoLuong(holder);
                shoppingCartService.updateShoppingCardData(userID, bookID, soLuong);
                listBookShoppingCard.get(position).setSoLuong(soLuong);
                notifyDataSetChanged();
            }
        });
        holder.ivTangShoppingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tangSoLuong(holder);
                shoppingCartService.updateShoppingCardData(userID, bookID, soLuong);
                listBookShoppingCard.get(position).setSoLuong(soLuong);
                notifyDataSetChanged();
            }
        });
        holder.ivXoaShoppingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shoppingCartService.deleteShoppingCardData(userID,bookID);
                listBookShoppingCard.remove(position);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listBookShoppingCard.size();
    }

    private void tangSoLuong(ShoppingCartAdapter.ViewHolder holder){
        soLuong++;
        holder.tvSoLuongShoppingCard.setText(String.valueOf(soLuong));
    }
    private void giamSoLuong(ShoppingCartAdapter.ViewHolder holder){
        soLuong--;
        if(soLuong>0){
            holder.tvSoLuongShoppingCard.setText(String.valueOf(soLuong));
        }else {
            soLuong = 1;
            holder.tvSoLuongShoppingCard.setText(String.valueOf(soLuong));
        }

    }


    public int getTongTien(){
        for (int i=0;i<listBookShoppingCard.size();i++){
            tongTien += Integer.parseInt(listBookShoppingCard.get(i).getGiaBan())*listBookShoppingCard.get(i).getSoLuong();
        }
        return tongTien;
    }

}
