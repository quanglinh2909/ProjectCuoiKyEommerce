package com.example.projectcuoikyeommerce.activity.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.MainActivity;
import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.component.SearchBottomSheet;
import com.example.projectcuoikyeommerce.constant.FragmentID;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.fcm.Data;
import com.example.projectcuoikyeommerce.fcm.RequestNotification;
import com.example.projectcuoikyeommerce.fcm.ResponseNotification;
import com.example.projectcuoikyeommerce.fragment.HomeFragment;
import com.example.projectcuoikyeommerce.fragment.MyFragment;
import com.example.projectcuoikyeommerce.fragment.NotificationFragment;
import com.example.projectcuoikyeommerce.fragment.admin.DashboardFragment;
import com.example.projectcuoikyeommerce.fragment.admin.NotifycationFragment;
import com.example.projectcuoikyeommerce.fragment.admin.OrderAdminFragment;
import com.example.projectcuoikyeommerce.fragment.admin.ProductAdminFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardActivity extends AppCompatActivity {
    private ImageButton btnAddProduct, btnSearch;
    private long backTime;
    private Toast mToast;
    private int currentFragment = FragmentID.FRAGMENT_HOME;
    private BottomNavigationView bottomNavigationView;
    private TextView txtTitle;
    private BadgeDrawable badgeDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        replaceFragment(new DashboardFragment());
        initUi();
        handleEvent();

        initNotification();
        ;

    }

    private void initNotification() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        badgeDrawable =
                bottomNavigationView.getOrCreateBadge(R.id.navigation_notifications);
        badgeDrawable.setBackgroundColor(Color.RED);
        badgeDrawable.setBadgeTextColor(Color.WHITE);
        DatabaseReference myRef = database.getReference(DataLocalManager.getInstance().getUser().getId()+ "");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer value = dataSnapshot.getValue(Integer.class);
                if (value != null && value > 0) {
                    badgeDrawable.setNumber(value);
                    badgeDrawable.setVisible(true);

                }else{
                    badgeDrawable.setVisible(false);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    private void handleEvent() {
        btnAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(DashBoardActivity.this, AddProductActivity.class);
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
                            txtTitle.setText("DashBoard");

                        }
                        break;
                    case R.id.product:
                        if (currentFragment != FragmentID.PRODUCT) {
                            replaceFragment(new ProductAdminFragment());
                            currentFragment = FragmentID.PRODUCT;
                            txtTitle.setText("Sản Phẩm");
                        }
                        break;
                    case R.id.order:
                        if (currentFragment != FragmentID.ORDER) {
                            replaceFragment(new OrderAdminFragment());
                            currentFragment = FragmentID.ORDER;
                            txtTitle.setText("Hóa Đơn");
                        }
                        break;
                    case R.id.navigation_notifications:
                        if (currentFragment != FragmentID.FRAGMENT_NOTIFICATION) {
                            replaceFragment(new NotifycationFragment());
                            currentFragment = FragmentID.FRAGMENT_NOTIFICATION;
                            txtTitle.setText("Thông Báo");
                        }
                        break;
                    case R.id.navigation_my:
                        if (currentFragment != FragmentID.FRAGMENT_MY) {
                            replaceFragment(new MyFragment());
                            currentFragment = FragmentID.FRAGMENT_MY;
                            txtTitle.setText("Cài Đặt");
                        }
                        break;
                }
                return true;
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchBottomSheet searchBottomSheet = new SearchBottomSheet();
                searchBottomSheet.show(getSupportFragmentManager(), searchBottomSheet.getTag());
            }
        });

    }

    private void initUi() {
        btnAddProduct = findViewById(R.id.btnAddProduct);
        bottomNavigationView = findViewById(R.id.nav_view);
        txtTitle = findViewById(R.id.txtTitle);
        btnSearch = findViewById(R.id.btnSearch);

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