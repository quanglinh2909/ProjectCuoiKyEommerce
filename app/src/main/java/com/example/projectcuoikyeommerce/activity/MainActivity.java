package com.example.projectcuoikyeommerce.activity;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.component.CartBottomSheet;
import com.example.projectcuoikyeommerce.component.MenuBottomSheet;
import com.example.projectcuoikyeommerce.component.SearchBottomSheet;
import com.example.projectcuoikyeommerce.constant.FragmentID;
import com.example.projectcuoikyeommerce.constant.KeyIntent;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.event.ExploreMoreEvent;
import com.example.projectcuoikyeommerce.event.MenuEvent;
import com.example.projectcuoikyeommerce.fragment.HomeFragment;
import com.example.projectcuoikyeommerce.fragment.MyFragment;
import com.example.projectcuoikyeommerce.fragment.NotificationFragment;
import com.example.projectcuoikyeommerce.fragment.admin.NotifycationFragment;
import com.example.projectcuoikyeommerce.model.TagChild;
import com.example.projectcuoikyeommerce.model.TagParent;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements MenuEvent, ExploreMoreEvent {
    private int currentFragment = FragmentID.FRAGMENT_HOME;
    private ImageButton btnMenu, btnShoppingBag, btnSearch;
    private MenuBottomSheet menuBottomSheet;
    private CartBottomSheet cartBottomSheet;
    private ImageView btnBackHome;
    private RelativeLayout header;
    private BottomNavigationView bottomNavigationView;
    private LinearLayout footer;
    private long backTime;
    private Toast mToast;
    private String TAG = "AAA";
    private TagParent tagParent;
    private BadgeDrawable badgeDrawable;
    private SearchBottomSheet searchBottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        handleAction();
        replaceFragment(new HomeFragment(this));
        initNotification();
//        replaceFragment(new CategoryFragment());

        menuBottomSheet = new MenuBottomSheet(MainActivity.this);
        cartBottomSheet = new CartBottomSheet();
        searchBottomSheet = new SearchBottomSheet();


    }

    private void initNotification() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        badgeDrawable =
                bottomNavigationView.getOrCreateBadge(R.id.navigation_notifications);
        badgeDrawable.setBackgroundColor(Color.RED);
        badgeDrawable.setBadgeTextColor(Color.WHITE);
        badgeDrawable.setVisible(true);
        DatabaseReference myRef = database.getReference(DataLocalManager.getInstance().getUser().getId() + "");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer value = dataSnapshot.getValue(Integer.class);
                if (value != null && value > 0) {
                    badgeDrawable.setNumber(value);
                    badgeDrawable.setVisible(true);

                } else {
                    badgeDrawable.setVisible(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
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
        btnSearch = findViewById(R.id.btnSearch);
    }

    private void handleAction() {
        btnMenu.setOnClickListener(v -> {

            menuBottomSheet.show(getSupportFragmentManager(), menuBottomSheet.getTag());

        });
        btnSearch.setOnClickListener(v -> searchBottomSheet.show(getSupportFragmentManager(), searchBottomSheet.getTag()));
        btnShoppingBag.setOnClickListener(v -> {

            cartBottomSheet.show(getSupportFragmentManager(), cartBottomSheet.getTag());
//            cartBottomSheet.setCancelable(false);

        });
        btnBackHome.setOnClickListener(v -> {
            if (currentFragment != FragmentID.FRAGMENT_HOME) {
                replaceFragment(new HomeFragment(this));
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
                            replaceFragment(new NotifycationFragment());
                            currentFragment = FragmentID.FRAGMENT_NOTIFICATION;
                            header.setBackgroundColor(getResources().getColor(R.color.white));
                            header.setVisibility(View.VISIBLE);
                            footer.setVisibility(View.GONE);

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

    }


    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void clickItemMenu(TagParent tagParent, TagChild tagChild) {
//        CategoryFragment categoryFragment = new CategoryFragment();
//        categoryFragment.setTagParent(tagParent);
//        categoryFragment.setTagChild(tagChild);
//        replaceFragment(categoryFragment);
//        currentFragment = FragmentID.FRAGMENT_CATEGORY;
//        menuBottomSheet.dismiss();
//        header.setBackgroundColor(getResources().getColor(R.color.white));
//        header.setVisibility(View.VISIBLE);
//        footer.setVisibility(View.VISIBLE);

        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KeyIntent.KEY_TAG_PARENT, tagParent);
        bundle.putSerializable(KeyIntent.KEY_TAF_CHILD, tagChild);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    public void clickExplore() {
//        CategoryFragment categoryFragment = new CategoryFragment();
//        categoryFragment.setTagParent(tagParent);
//        categoryFragment.setTagChild(null);
//        replaceFragment(categoryFragment);
//        currentFragment = FragmentID.FRAGMENT_CATEGORY;
//        header.setBackgroundColor(getResources().getColor(R.color.white));
//        header.setVisibility(View.VISIBLE);
//        footer.setVisibility(View.VISIBLE);

        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(KeyIntent.KEY_TAG_PARENT, tagParent);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    public void setTagParent(TagParent tagParent) {
        this.tagParent = tagParent;
    }

}