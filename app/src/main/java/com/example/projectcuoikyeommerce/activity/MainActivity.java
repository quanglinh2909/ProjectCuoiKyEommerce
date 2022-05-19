package com.example.projectcuoikyeommerce.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.component.MenuBottomSheet;
import com.example.projectcuoikyeommerce.constant.FragmentID;
import com.example.projectcuoikyeommerce.fragment.HomeFragment;

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