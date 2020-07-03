package com.example.bookstoreapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstoreapp.Adapter.NotificationAdapter;
import com.example.bookstoreapp.Model.Bill;
import com.example.bookstoreapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    private ImageView btnBacktoHomefromNotification;

    private RecyclerView notificationReyclerView;
    private List<Bill> billList;
    private NotificationAdapter notificationAdapter;


    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate( R.layout.fragment_notification , container , false );

        btnBacktoHomefromNotification = view.findViewById(R.id.btnBacktoHomefromNotification);
        btnBacktoHomefromNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new HomeFragment());
            }
        });

        notificationReyclerView = view.findViewById(R.id.notificationReyclerView);
        setNotificationRecyclerView();

        return view;
    }

    private void setNotificationRecyclerView() {
        billList = new ArrayList<>();
        String userID = FirebaseAuth.getInstance().getUid();

        FirebaseFirestore.getInstance()
                .collection("users")
                .document(userID)
                .collection("bill")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                Bill bill = new Bill();
                                bill.setId(documentSnapshot.getId());
                                bill.setFullName(documentSnapshot.getString("fullName"));
                                bill.setPhone(documentSnapshot.getString("phone"));
                                bill.setAddress(documentSnapshot.getString("address"));
                                billList.add(bill);
                            }
                            notificationAdapter = new NotificationAdapter(billList, getContext());
                            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
                            notificationReyclerView.setLayoutManager(layoutManager);
                            notificationReyclerView.setHasFixedSize(true);
                            notificationReyclerView.setAdapter(notificationAdapter);
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
