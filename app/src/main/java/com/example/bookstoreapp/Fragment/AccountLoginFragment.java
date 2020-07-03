package com.example.bookstoreapp.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookstoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountLoginFragment extends Fragment {
    private EditText edtemaillogin, edtpassword;
    private Button btnlogin, btnloginbyfacebook;
    private TextView tvForgetPassword;
    private AccountDetailFragment accountDetailFragment;

    private FirebaseAuth firebaseAuth;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    public AccountLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_account_login , container , false );

        edtemaillogin = view.findViewById(R.id.edtEmailLogin);
        edtpassword = view.findViewById(R.id.edtPasswordLogin);
        btnlogin = view.findViewById(R.id.btnLogin2);
 //       btnloginbyfacebook = view.findViewById(R.id.btnLoginFacebook);

        tvForgetPassword = view.findViewById(R.id.tvForgetPassword);

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment1(new ChangeAccountFragment());
            }
        });

        accountDetailFragment = new AccountDetailFragment();

        firebaseAuth = FirebaseAuth.getInstance();

        edtemaillogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmailandPassword();
            }
        });

        return view;
    }



    private void checkEmailandPassword() {
        if (edtemaillogin.getText().toString().matches(emailPattern)) {
            if (edtpassword.length() >= 8) {
                firebaseAuth.signInWithEmailAndPassword(edtemaillogin.getText().toString().trim(), edtpassword.getText().toString().trim()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    setFragment(accountDetailFragment);
//                                    Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {

                edtpassword.setError("Kiểm tra lại email và mật khẩu!!");
            }
        } else {
            edtemaillogin.setError("Kiểm tra lại email và mật khẩu!!");
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.AcccountFrame, fragment);
        fragmentTransaction.commit();
    }

    private void checkInput() {
        if (!TextUtils.isEmpty(edtemaillogin.getText().toString().trim())) {
            if (!TextUtils.isEmpty(edtpassword.getText().toString().trim()) && edtpassword.length() >= 8) {
                btnlogin.setEnabled(true);
                btnlogin.setBackground(getResources().getDrawable(R.drawable.duongvien_botron_button2, getActivity().getTheme()));
                btnlogin.setTextColor( Color.rgb(255, 255, 255));
            }
        }
    }

    private void setFragment1(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
