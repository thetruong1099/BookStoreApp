package com.example.bookstoreapp.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookstoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ChangeAccountFragment extends Fragment {

    private EditText edtEmailForgotPassWord;
    private Button btnForgotPassWord;
    private TextView tvBackToSignIn;

    public ChangeAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_change_account, container, false);

        edtEmailForgotPassWord= view.findViewById(R.id.edtEmailForgotPassWord);
        btnForgotPassWord = view.findViewById(R.id.btnForgotPassWord);
        tvBackToSignIn = view.findViewById(R.id.tvBackToSignInFormForgotPasswordFragment);

        btnForgotPassWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().sendPasswordResetEmail(edtEmailForgotPassWord.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    showAlerDialogSuccess();
                                }
                                else {
                                    showAlerDialogFail();
                                }
                            }
                        });
            }
        });


        tvBackToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new AccountFragment());
            }
        });

        return view;
    }

    private void showAlerDialogFail() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Gửi email xác nhận thất bại");
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAlerDialogSuccess() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("Gửi email xác nhận thành công");
        builder.setCancelable(true);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}