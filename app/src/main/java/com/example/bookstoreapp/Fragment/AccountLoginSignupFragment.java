package com.example.bookstoreapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookstoreapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountLoginSignupFragment extends Fragment {
    private Button btnLogin, btnSignup;
    private AccountLoginFragment accountLoginFragment;
    private AccountSignupFragment accountSignupFragment;
    public AccountLoginSignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_account_login_signup , container , false );

        accountLoginFragment = new AccountLoginFragment();
        accountSignupFragment = new AccountSignupFragment();
        btnLogin = view.findViewById(R.id.btnLogin);
        btnSignup = view.findViewById(R.id.btnSignup);
        setFragment(accountLoginFragment);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(accountLoginFragment);

            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(accountSignupFragment);
            }
        });
        return view;
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.login_singup_frame, fragment);
        fragmentTransaction.commit();
    }
}
