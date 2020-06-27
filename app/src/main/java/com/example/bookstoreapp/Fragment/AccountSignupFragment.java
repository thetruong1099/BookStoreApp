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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.UserService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountSignupFragment extends Fragment {
    private EditText edtemailsignup, edtpasswordsignup, edtconfirmpassword, edtfullname;
    private Button btnsignup;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore dbfirestore;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private String userID, email, fullname, password, confirmpassword;
    public AccountSignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_account_signup , container , false );

        edtemailsignup = view.findViewById(R.id.edtEmailPhoneSignup);
        edtpasswordsignup = view.findViewById(R.id.edtPasswordSignup);
        edtconfirmpassword = view.findViewById(R.id.edtConfirmPasswordSignup);
        edtfullname = view.findViewById(R.id.edtFullnameSignup);
        btnsignup = view.findViewById(R.id.btnSingup2);


        firebaseAuth = FirebaseAuth.getInstance();
        dbfirestore = FirebaseFirestore.getInstance();

        edtemailsignup.addTextChangedListener(new TextWatcher() {
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
        edtpasswordsignup.addTextChangedListener(new TextWatcher() {
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
        edtconfirmpassword.addTextChangedListener(new TextWatcher() {
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
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkEmailandPassword();
            }
        });

        return view;
    }

    private void checkInput() {
        if (!TextUtils.isEmpty(edtemailsignup.getText().toString().trim())) {
            if (!TextUtils.isEmpty(edtfullname.getText().toString().trim())) {
                if (!TextUtils.isEmpty(edtpasswordsignup.getText().toString().trim()) && edtpasswordsignup.length() >= 8) {
                    if (!TextUtils.isEmpty(edtconfirmpassword.getText().toString().trim())) {
                        btnsignup.setEnabled(true);
                        btnsignup.setBackground(getResources().getDrawable(R.drawable.duongvien_botron_button2, getActivity().getTheme()));
                        btnsignup.setTextColor( Color.rgb(255, 255, 255));

                    }
                }
            }
        }
    }

    private void checkEmailandPassword(){
        email = edtemailsignup.getText().toString();
        fullname = edtfullname.getText().toString();
        password = edtpasswordsignup.getText().toString();
        confirmpassword = edtconfirmpassword.getText().toString();
        if (email.matches(emailPattern)) {
            if (password.equals(confirmpassword) && edtpasswordsignup.length() >= 8) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    userID = firebaseAuth.getCurrentUser().getUid();
                                    UserService userservice = new UserService();
                                    userservice.addUserInSignup( userID, email, fullname );
                                    setFragment(new AccountDetailFragment());
                                }else{
                                    String error = task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }else {
                edtpasswordsignup.setError( "Password doesn't matched!" );
            }
        }else {
            edtemailsignup.setError( "Invalid Email!!" );
        }

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.AcccountFrame, fragment);
        fragmentTransaction.commit();
    }
}
