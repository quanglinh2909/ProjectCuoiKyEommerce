package com.example.projectcuoikyeommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.AdapterBannerHome;
import com.example.projectcuoikyeommerce.api.TagParentController;
import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.api.config.ConfigRetrofit;
import com.example.projectcuoikyeommerce.component.MenuBottomSheet;
import com.example.projectcuoikyeommerce.constant.FragmentID;
import com.example.projectcuoikyeommerce.fragment.HomeFragment;
import com.example.projectcuoikyeommerce.model.Banner;
import com.example.projectcuoikyeommerce.model.TagChild;
import com.example.projectcuoikyeommerce.model.TagParent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
   private int currentFragment = FragmentID.FRAGMENT_HOME;
   private ImageButton btnMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        handleAction();
        replaceFragment(new HomeFragment());



    }


    private void initUi() {
        btnMenu = findViewById(R.id.btnMenu);
    }
    private void handleAction() {
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuBottomSheet menuBottomSheet = new MenuBottomSheet();
                menuBottomSheet.show(getSupportFragmentManager(),menuBottomSheet.getTag());
                menuBottomSheet.setCancelable(false);

            }
        });
    }


    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.containerFragment,fragment);
        fragmentTransaction.commit();
    }
    


}