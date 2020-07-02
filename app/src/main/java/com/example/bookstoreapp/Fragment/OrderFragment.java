package com.example.bookstoreapp.Fragment;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookstoreapp.Adapter.AddressPayAdapter;
import com.example.bookstoreapp.Model.Address;
import com.example.bookstoreapp.Model.Bill;
import com.example.bookstoreapp.Model.ShoppingCard;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.AddressService;
import com.example.bookstoreapp.Service.BillService;
import com.example.bookstoreapp.Service.ShoppingCartService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    private RecyclerView addressPayRecyclerView;
    private CheckBox rbGiaoHangTieuChuan;
    private CheckBox rbGiaoHangNhanh;
    private TextView tvGiaoHangTieuChuan;
    private TextView tvGiaoHangNhanh;
    private TextView tvThanhTienPay;
    private TextView tvTongTienPay;
    private TextView tvPhiVanChuyen;
    private Button btnThanhToanOrder;

    private List<ShoppingCard> listsachDaMua;
    private int thanhtien;
    private int phivanchuyenTC = 0;
    private int phivanchuyenN = 0;
    private String diaChi;
    private String userID;
    private int tongTien;
    private BillService billService;
    private ShoppingCartService shoppingCartService;
    private boolean status = false;
    private int statusPhuongThucVanChuyenTC = 0;
    private int statusPhuongThucVanChuyenN =1;

    private AddressService addressService;
    private RecyclerView orderAddressPayRecyclerView;
    private List<Address> addressList;
    private AddressPayAdapter addressPayAdapter;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        addressPayRecyclerView = view.findViewById(R.id.AcccountFrame);
        rbGiaoHangTieuChuan = view.findViewById(R.id.rbGiaoHangTieuChuan);
        rbGiaoHangNhanh = view.findViewById(R.id.rbGiaoHangNhanh);
        tvGiaoHangTieuChuan = view.findViewById(R.id.tvGiaoHangTieuChuan);
        tvGiaoHangNhanh = view.findViewById(R.id.tvGiaoHangNhanh);
        tvThanhTienPay = view.findViewById(R.id.tvThanhTienPay);
        tvPhiVanChuyen = view.findViewById(R.id.tvPhiVanChuyen);
        tvTongTienPay = view.findViewById(R.id.tvTongTienPay);
        btnThanhToanOrder = view.findViewById(R.id.btnThanhToanOrder);

        orderAddressPayRecyclerView = view.findViewById(R.id.AddressPayReceyclerView);

        getDataFragment();
        setAddressRecyclerView();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(mMessageReceiver,new IntentFilter("diaChi_intent"));


        return view;
    }


    private void setAddressRecyclerView() {
        addressService = new AddressService();
        addressList = new ArrayList<>();
        userID = FirebaseAuth.getInstance().getUid();
        addressService.getAddressData(userID);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(addressService.istatus()){
                    addressList = addressService.getAddressList();
                    addressPayAdapter = new AddressPayAdapter(addressList,getContext());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                    orderAddressPayRecyclerView.setLayoutManager(layoutManager);
                    orderAddressPayRecyclerView.setAdapter(addressPayAdapter);





                    handler.removeCallbacks(this);
                }else {
                    handler.postDelayed(this, 1);
                }
            }
        }, 1);

    }

    private void getDataFragment() {
        Bundle bundle = this.getArguments();
        listsachDaMua = new ArrayList<>();
        listsachDaMua = (List<ShoppingCard>) bundle.getSerializable("sachDaMua");
        thanhtien= bundle.getInt("tongTien");
        tvThanhTienPay.setText(String.valueOf(thanhtien) + " đ");
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            diaChi= intent.getStringExtra("diaChi");
            setPhiVanChuyenTC(diaChi);
            setrbGiaoHangTC();
            setPhiVanChuyenN(diaChi);
            setrbGiaoHangNhanh();

            String diaChiLienHe = intent.getStringExtra("diaChiLienHe");
            String fullname = intent.getStringExtra("fullname");
            String phone = intent.getStringExtra("phone");
            Bill bill = new Bill();
            bill.setFullName(fullname);
            bill.setAddress(diaChiLienHe);
            bill.setPhone(phone);
            bill.setTongTien(String.valueOf(tongTien));
            bill.setSachDaMua((ArrayList<ShoppingCard>) listsachDaMua);

            setBtnThanhToanOrder(userID,bill);

        }

    };

    private int setPhiVanChuyenTC(final String diaChi){
        if ((diaChi.equals("Hà Nội")||diaChi.equals("Hồ Chí Minh"))&& thanhtien>=140000){
            tvGiaoHangTieuChuan.setText("0 đ");
            phivanchuyenTC = 0;
        }else if((diaChi.equals("Hà Nội")||diaChi.equals("Hồ Chí Minh"))){
            tvGiaoHangTieuChuan.setText("30000 đ");
            phivanchuyenTC = 30000;
        }
        else if(thanhtien >= 250000){
            tvGiaoHangTieuChuan.setText("0 đ");
            phivanchuyenTC = 0;
        }else {
            tvGiaoHangTieuChuan.setText("50000 đ");
            phivanchuyenTC = 50000;
        }
        return phivanchuyenTC;
    }

    private int setPhiVanChuyenN(final String diaChi){
        if ((diaChi.equals("Hà Nội")||diaChi.equals("Hồ Chí Minh"))&& thanhtien>=140000){
            tvGiaoHangNhanh.setText("30000 đ");
            phivanchuyenN = 30000;
        }else if((diaChi.equals("Hà Nội")||diaChi.equals("Hồ Chí Minh"))){
            tvGiaoHangNhanh.setText("50000 đ");
            phivanchuyenN = 50000;
        }
        else if(thanhtien >= 250000){
            tvGiaoHangNhanh.setText("50000 đ");
            phivanchuyenN = 50000;
        }else {
            tvGiaoHangNhanh.setText("100000 đ");
            phivanchuyenN = 100000;
        }
        return phivanchuyenN;
    }

    private void setrbGiaoHangTC(){
        rbGiaoHangTieuChuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvPhiVanChuyen.setText(String.valueOf(phivanchuyenTC)+" đ");
                tongTien = thanhtien + phivanchuyenTC;
                tvTongTienPay.setText(String.valueOf(tongTien) + " đ");
                rbGiaoHangNhanh.setChecked(false);
                statusPhuongThucVanChuyenTC = 1;
            }
        });

    }

    private void setrbGiaoHangNhanh(){
            rbGiaoHangNhanh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tvPhiVanChuyen.setText(String.valueOf(phivanchuyenN)+" đ");
                    tongTien = thanhtien + phivanchuyenN;
                    tvTongTienPay.setText(String.valueOf(tongTien) + " đ");
                    rbGiaoHangTieuChuan.setChecked(false);
                    statusPhuongThucVanChuyenN =1;
                }
            });
    }

    private boolean checkstatus(){
        if (diaChi != null && (statusPhuongThucVanChuyenTC == 1 || statusPhuongThucVanChuyenTC == 1)){
            status = true;
        }
        return status;
    }

    private void setBtnThanhToanOrder(final String userID, final Bill donHang){
        btnThanhToanOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkstatus()){
                    showAlertDialog(userID, donHang);
                }

            }
        });
    }

    private void showAlertDialog(final String userID, final Bill donHang) {
        billService =  new BillService();
        shoppingCartService = new ShoppingCartService();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Xác nhận đơn hàng");
        builder.setCancelable(false);
        builder.setPositiveButton("Xác Nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                billService.addBill(userID,donHang);
                for (int j=0; j<listsachDaMua.size();j++){
                    String bookID = listsachDaMua.get(j).getId();
                    shoppingCartService.deleteShoppingCardData(userID, bookID);
                }
                Toast.makeText(getActivity(), "Thành Công", Toast.LENGTH_SHORT).show();
                setFragment(new HomeFragment());
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}