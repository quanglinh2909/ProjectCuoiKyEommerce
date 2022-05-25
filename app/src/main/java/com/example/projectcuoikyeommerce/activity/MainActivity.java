package com.example.projectcuoikyeommerce.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.component.CartBottomSheet;
import com.example.projectcuoikyeommerce.component.MenuBottomSheet;
import com.example.projectcuoikyeommerce.constant.FragmentID;
import com.example.projectcuoikyeommerce.event.MenuEvent;
import com.example.projectcuoikyeommerce.event.home.ProductHomeEvent;
import com.example.projectcuoikyeommerce.fragment.CartFragment;
import com.example.projectcuoikyeommerce.fragment.CategoryFragment;
import com.example.projectcuoikyeommerce.fragment.HomeFragment;
import com.example.projectcuoikyeommerce.fragment.MyFragment;
import com.example.projectcuoikyeommerce.fragment.NotificationFragment;
import com.example.projectcuoikyeommerce.fragment.ProductDetailFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements MenuEvent, ProductHomeEvent {
    private int currentFragment = FragmentID.FRAGMENT_HOME;
    private ImageButton btnMenu, btnShoppingBag;
    private MenuBottomSheet menuBottomSheet;
    private CartBottomSheet cartBottomSheet;
    private ImageView btnBackHome;
    private RelativeLayout header;
    private BottomNavigationView bottomNavigationView;
    private LinearLayout footer;
    private long backTime;
    private Toast mToast;
    private String TAG = "AAA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        handleAction();
        replaceFragment(new HomeFragment(MainActivity.this));
//        replaceFragment(new CategoryFragment());

        menuBottomSheet = new MenuBottomSheet(MainActivity.this);
        cartBottomSheet = new CartBottomSheet();


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

    private void initUi() {
        btnMenu = findViewById(R.id.btnMenu);
        btnBackHome = findViewById(R.id.btnBackHome);
        header = findViewById(R.id.header);
        btnShoppingBag = findViewById(R.id.btnShoppingBag);
        bottomNavigationView = findViewById(R.id.nav_view);
        footer = findViewById(R.id.footer);
    }

    private void handleAction() {
        btnMenu.setOnClickListener(v -> {

            menuBottomSheet.show(getSupportFragmentManager(), menuBottomSheet.getTag());

        });
        btnShoppingBag.setOnClickListener(v -> {

            cartBottomSheet.show(getSupportFragmentManager(), cartBottomSheet.getTag());
//            cartBottomSheet.setCancelable(false);

        });
        btnBackHome.setOnClickListener(v -> {
            if (currentFragment != FragmentID.FRAGMENT_HOME) {
                replaceFragment(new HomeFragment(MainActivity.this));
                currentFragment = FragmentID.FRAGMENT_HOME;
                header.setBackgroundResource(R.drawable.header);
                header.setVisibility(View.VISIBLE);
                footer.setVisibility(View.VISIBLE);
            }

        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if (currentFragment != FragmentID.FRAGMENT_HOME) {
                            replaceFragment(new HomeFragment(MainActivity.this));
                            currentFragment = FragmentID.FRAGMENT_HOME;
                            header.setBackgroundResource(R.drawable.header);
                            header.setVisibility(View.VISIBLE);
                            footer.setVisibility(View.VISIBLE);

                        }
                        break;
                    case R.id.navigation_notifications:
                        if (currentFragment != FragmentID.FRAGMENT_NOTIFICATION) {
                            replaceFragment(new NotificationFragment());
                            currentFragment = FragmentID.FRAGMENT_NOTIFICATION;
                            header.setBackgroundColor(getResources().getColor(R.color.white));
                            header.setVisibility(View.VISIBLE);
                            footer.setVisibility(View.VISIBLE);

                        }

                        break;
                    case R.id.navigation_my:
                        if (currentFragment != FragmentID.FRAGMENT_MY) {
                            replaceFragment(new MyFragment());
                            currentFragment = FragmentID.FRAGMENT_MY;
                            header.setBackgroundColor(getResources().getColor(R.color.white));
                            header.setVisibility(View.GONE);
                            footer.setVisibility(View.GONE);

                        }
                        break;
                }
                return true;
            }
        });
//        btnShoppingBag.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentFragment != FragmentID.FRAGMENT_CARD) {
//                    replaceFragment(new CartFragment());
//                    currentFragment = FragmentID.FRAGMENT_CARD;
//                    header.setBackgroundResource(R.drawable.header);
//                }
//            }
//        });
    }


    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void clickItem() {
        replaceFragment(new CategoryFragment());
        currentFragment = FragmentID.FRAGMENT_CATEGORY;
        menuBottomSheet.dismiss();
        header.setBackgroundColor(getResources().getColor(R.color.white));
        header.setVisibility(View.VISIBLE);
        footer.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClickItem() {
        replaceFragment(new ProductDetailFragment());
        currentFragment = FragmentID.FRAGMENT_PRODUCT_DETAIL;
        header.setBackgroundColor(getResources().getColor(R.color.white));
        header.setVisibility(View.VISIBLE);
        footer.setVisibility(View.VISIBLE);


    }
}