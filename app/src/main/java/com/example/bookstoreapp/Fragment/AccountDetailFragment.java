package com.example.bookstoreapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookstoreapp.Activity.AddressManagerActivity;
import com.example.bookstoreapp.Activity.ProfileDetailActivity;
import com.example.bookstoreapp.Activity.ShoppingHistoryActivity;
import com.example.bookstoreapp.Model.User;
import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.UserService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountDetailFragment extends Fragment {
    private TextView tvchinhsua, tvnamedetail, tvemaildetail, tvquanlydaichi, tvlichsumuahang;
    private Button btndangxuat;

    private AccountLoginSignupFragment accountLoginSignupFragment;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private UserService userservice;
    private User user;

    public AccountDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_account_detail , container , false );

        tvchinhsua = view.findViewById(R.id.tvchinhsua);
        tvemaildetail = view.findViewById(R.id.tvEmailDetail);
        tvnamedetail = view.findViewById(R.id.tvNameDetail);
        tvquanlydaichi = view.findViewById(R.id.tvaddress);
        tvlichsumuahang = view.findViewById(R.id.tvhistoryshopping);
        btndangxuat = view.findViewById(R.id.btnLogout);

        accountLoginSignupFragment = new AccountLoginSignupFragment();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getdata();

        tvchinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileDetailActivity.class);
                getActivity().startActivity(intent);
            }
        });

        tvquanlydaichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddressManagerActivity.class);
                getActivity().startActivity(intent);
            }
        });

        tvlichsumuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShoppingHistoryActivity.class);
                getActivity().startActivity(intent);
            }
        });

        btndangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                setFragment(accountLoginSignupFragment);
            }
        });
        return view;
    }



    private void getdata() {
        String userID = firebaseAuth.getCurrentUser().getUid();
        DocumentReference documentReference = firebaseFirestore.collection( "users" ).document( userID );
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.getResult().exists()){
                    String name = task.getResult().getString( "fullname" );
                    String email = task.getResult().getString("email");
                    tvnamedetail.setText( name );
                    tvemaildetail.setText(email);
                }
                else {
                    Toast.makeText(getActivity(), "ko có profile", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.AcccountFrame, fragment);
        fragmentTransaction.commit();
    }
}
