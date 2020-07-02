package com.example.bookstoreapp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstoreapp.Adapter.ShoppingCartAdapter;
import com.example.bookstoreapp.Model.ShoppingCard;
import com.example.bookstoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingCartFragment extends Fragment {

    private TextView tvTongGiaTien;
    private ImageView ivReload;
    private Button btnThanhToan;


    private RecyclerView shoppingCardRecyclerView;
    private List<ShoppingCard> listBookShoppingCard;
    private ShoppingCartAdapter shoppingCartAdapter;

    int tongtien = 0;

    public ShoppingCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_shopping_cart , container , false );

        tvTongGiaTien = view.findViewById(R.id.tvTongTienShoppingCart);
        btnThanhToan = view.findViewById(R.id.btnThanhToan);

        shoppingCardRecyclerView = view.findViewById(R.id.ShoppingCardRecyclerView);
        setShoppingCardREcyclerView();

        ivReload = view.findViewById(R.id.ivReLoad);
        ivReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new ShoppingCartFragment());
            }
        });
        return view;
    }

    private void setShoppingCardREcyclerView() {
        listBookShoppingCard = new ArrayList<>();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseFirestore.getInstance().collection( "users" ).document(userID).collection("listBookSC").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ShoppingCard bookShoppingCard = new ShoppingCard();
                                bookShoppingCard.setId(document.getString("id"));
                                bookShoppingCard.setAnhBia(document.getString("anh"));
                                bookShoppingCard.setTenSach(document.getString("tenSach"));
                                bookShoppingCard.setGiaBan(document.getString("giaBan"));
                                bookShoppingCard.setSoLuong(Math.toIntExact(document.getLong("soLuong")));
                                listBookShoppingCard.add(bookShoppingCard);
                            }

                            shoppingCartAdapter = new ShoppingCartAdapter(listBookShoppingCard, getContext());
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                            shoppingCardRecyclerView.setLayoutManager(layoutManager);
                            shoppingCardRecyclerView.setHasFixedSize(true);
                            shoppingCardRecyclerView.setAdapter(shoppingCartAdapter);

                            tongtien = shoppingCartAdapter.getTongTien();
                            tvTongGiaTien.setText(String.valueOf(tongtien)+" đ");
                            if(listBookShoppingCard!=null){
                                btnThanhToan.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Bundle bundle = new Bundle();
                                        bundle.putInt("tongTien",tongtien);
                                        bundle.putSerializable("sachDaMua", (Serializable)  listBookShoppingCard);
                                        OrderFragment orderFragment = new OrderFragment();
                                        orderFragment.setArguments(bundle);
                                        setFragment(orderFragment);
                                    }
                                });
                            }else {
                                Toast.makeText(getActivity(),"Cần Mua Sách", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else {
                            Log.d(TAG, "Lỗi Không Lấy được giá trị.");
                        }
                    }
                });
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
