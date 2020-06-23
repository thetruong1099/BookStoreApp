package com.example.bookstoreapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookstoreapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private AccountLoginSignupFragment accountLoginSignupFragment;
    private AccountDetailFragment accountDetailFragment;
    private FirebaseAuth firebaseAuth;


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_account , container , false );

        accountLoginSignupFragment = new AccountLoginSignupFragment();
        accountDetailFragment = new AccountDetailFragment();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            setFragment(accountDetailFragment);
        } else {
            setFragment(accountLoginSignupFragment);
        }

        return view;
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.AcccountFrame, fragment);
        fragmentTransaction.commit();
    }
}
