package com.example.bookstoreapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.bookstoreapp.R;
import com.example.bookstoreapp.Service.Userservice;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class UpdateProfileActivity extends AppCompatActivity {
    private EditText tvfullname, tvemail, tvphone, tvbirthday;
    private CheckBox cbman, cbwoman;
    private Button btnupdate;
    private ImageView btnback;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        tvfullname = findViewById(R.id.tvfullname);
        tvemail = findViewById(R.id.tvemail);
        tvphone = findViewById(R.id.tvphone);
        tvbirthday = findViewById(R.id.tvbirthday);
        cbman = findViewById(R.id.cbman);
        cbwoman = findViewById(R.id.cbwoman);
        btnback = findViewById(R.id.btnbackUpdateProfile);
        btnupdate = findViewById(R.id.btnupdate);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        
        getdata();

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateProfileActivity.this, ProfileDetailActivity.class);
                startActivity(intent);
            }
        });

        cbman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbwoman.setChecked(false);
            }
        });
        cbwoman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbman.setChecked(false);
            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }

    private void update() {
        String userID = firebaseAuth.getCurrentUser().getUid();
        String fullname = tvfullname.getText().toString();
        String email = tvemail.getText().toString();
        String phone = tvphone.getText().toString();
        String birtday = tvbirthday.getText().toString();
        int sex=2;
        if(cbman.isChecked()){
            sex = 1;
        }
        if (cbwoman.isChecked()){
            sex = 0;
        }
        Userservice userservice = new Userservice();
        userservice.updateUser(userID, fullname, email, phone, birtday, sex);
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
                    String phone = task.getResult().getString("phone");
                    String birthday = task.getResult().getString("birthday");
                    Long sex = task.getResult().getLong("sex");

                    tvfullname.setText(name);
                    tvemail.setText(email);
                    tvphone.setText(phone);
                    tvbirthday.setText(birthday);
                    if(sex == 1){
                        cbman.setChecked(true);
                    }else {
                        cbwoman.setChecked(true);
                    }

                }
                else {
                    Toast.makeText(UpdateProfileActivity.this, "ko có profile", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}