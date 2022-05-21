package com.example.projectcuoikyeommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.component.MenuBottomSheet;
import com.example.projectcuoikyeommerce.constant.FragmentID;
import com.example.projectcuoikyeommerce.event.MenuEvent;
import com.example.projectcuoikyeommerce.fragment.CategoryFragment;
import com.example.projectcuoikyeommerce.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements MenuEvent {
   private int currentFragment = FragmentID.FRAGMENT_HOME;
   private ImageButton btnMenu;
    private MenuBottomSheet menuBottomSheet;
    private ImageView btnBackHome;
    private RelativeLayout header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        handleAction();
        replaceFragment(new HomeFragment());
//        replaceFragment(new CategoryFragment());

        menuBottomSheet = new MenuBottomSheet(MainActivity.this);



    }


    private void initUi() {
        btnMenu = findViewById(R.id.btnMenu);
        btnBackHome = findViewById(R.id.btnBackHome);
        header = findViewById(R.id.header);
    }
    private void handleAction() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                menuBottomSheet.show(getSupportFragmentManager(),menuBottomSheet.getTag());
                menuBottomSheet.setCancelable(false);

            }
        });
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentFragment != FragmentID.FRAGMENT_HOME){
                    replaceFragment(new HomeFragment());
                    currentFragment = FragmentID.FRAGMENT_HOME;
                    header.setBackgroundResource(R.drawable.header);
                }

            }
        });
    }


    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void clickItem() {
        replaceFragment(new CategoryFragment());
        currentFragment = FragmentID.FRAGMENT_CATEGORY;
        menuBottomSheet.dismiss();
        header.setBackgroundColor(getResources().getColor(R.color.white));

    }
}