package com.example.projectcuoikyeommerce.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.MainActivity;
import com.example.projectcuoikyeommerce.constant.FragmentID;
import com.example.projectcuoikyeommerce.fragment.HomeFragment;
import com.example.projectcuoikyeommerce.fragment.MyFragment;
import com.example.projectcuoikyeommerce.fragment.NotificationFragment;
import com.example.projectcuoikyeommerce.fragment.admin.DashboardFragment;
import com.example.projectcuoikyeommerce.fragment.admin.ProductAdminFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashBoardActivity extends AppCompatActivity {
    private ImageButton btnAddProduct;
    private long backTime;
    private Toast mToast;
    private int currentFragment = FragmentID.FRAGMENT_HOME;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        replaceFragment(new DashboardFragment());
        initUi();
        handleEvent();

    }

    private void handleEvent() {
        btnAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this,AddProductActivity.class);
            startActivity(intent);
        });
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if (currentFragment != FragmentID.FRAGMENT_HOME) {
                            replaceFragment(new DashboardFragment());
                            currentFragment = FragmentID.FRAGMENT_HOME;

                        }
                        break;
                    case R.id.product:
                        if (currentFragment != FragmentID.PRODUCT) {
                            replaceFragment(new ProductAdminFragment());
                            currentFragment = FragmentID.PRODUCT;
                        }

                        break;
                    case R.id.navigation_my:
                        if (currentFragment != FragmentID.FRAGMENT_MY) {
                            replaceFragment(new MyFragment());
                            currentFragment = FragmentID.FRAGMENT_MY;
                        }
                        break;
                }
                return true;
            }
        });

    }

    private void initUi() {
        btnAddProduct = findViewById(R.id.btnAddProduct);
        bottomNavigationView = findViewById(R.id.nav_view);

    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment, fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {

        if (backTime + 2000 > System.currentTimeMillis()) {
            mToast.cancel();
            super.onBackPressed();
            return;
        } else {
            mToast = Toast.makeText(this, "Bấm 1 back một lần nữa để thoát", Toast.LENGTH_SHORT);
            mToast.show();
        }
        backTime = System.currentTimeMillis();


    }

}