package com.example.bookstoreapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.bookstoreapp.Fragment.AccountFragment;
import com.example.bookstoreapp.Fragment.HomeFragment;
import com.example.bookstoreapp.Fragment.NotificationFragment;
import com.example.bookstoreapp.Fragment.ShoppingCartFragment;
import com.example.bookstoreapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private HomeFragment homeFragment;
    private AccountFragment accountFragment;
    private NotificationFragment notificationFragment;
    private ShoppingCartFragment shoppingCartFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainNav = findViewById(R.id.main_nav);
        mMainFrame = findViewById(R.id.main_frame);

        homeFragment = new HomeFragment();
        accountFragment = new AccountFragment();
        notificationFragment = new NotificationFragment();
        shoppingCartFragment = new ShoppingCartFragment();

        setFragment(homeFragment);
        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_account:
                        setFragment(accountFragment);
                        return true;
                    case R.id.nav_notification:
                        setFragment(notificationFragment);
                        return true;
                    case R.id.nav_shoppingcart:
                        setFragment(shoppingCartFragment);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}