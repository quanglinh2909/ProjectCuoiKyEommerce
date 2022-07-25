package com.example.projectcuoikyeommerce.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.admin.OrderAdminAdapter;
import com.example.projectcuoikyeommerce.component.PaginationScrollListener;
import com.example.projectcuoikyeommerce.dto.OrderAdminDto;
import com.example.projectcuoikyeommerce.fragment.admin.OrderAdminFragment;
import com.example.projectcuoikyeommerce.presenter.OrderPresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements OrderAdminAdapter.OrderAdminEvent{
    private RecyclerView recyclerViewOrder;
    private List<OrderAdminDto> oderList = new ArrayList<>();
    private OrderAdminAdapter adminAdapter;
    private int status = 0;
    private OrderPresenter orderPresenter = new OrderPresenter();
    private boolean isLoading, isLastPage;
    private int totalPage = 0;
    private int currentPage = 0;
    private BottomNavigationView bottomNavigationView;
    private ImageButton back_button;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initUi();
        getData();
        handleEvent();
    }
    private void handleEvent() {
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                oderList.clear();
                switch (item.getItemId()) {
                    case R.id.choXacNhan:
                        status = 0;
                        break;
                    case R.id.dangGiao:
                        status = 1;
                        break;
                    case R.id.daGiao:
                        status = 2;
                        break;
                    case R.id.daHuy:
                        status = 3;
                        break;
                }
                currentPage = 0;
                isLoading = false;
                isLastPage = false;
                totalPage = orderPresenter.getTotalPage(status);
                if (currentPage <= totalPage) {
                    adminAdapter.addFooterLoading();
                } else {
                    isLastPage = true;
                }
                loadNextPage();
                return true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getData() {
        adminAdapter = new OrderAdminAdapter(oderList, this);
        totalPage = orderPresenter.getTotalPage(status);
        if (currentPage <= totalPage) {
            adminAdapter.addFooterLoading();
        } else {
            isLastPage = true;
        }
        recyclerViewOrder.setAdapter(adminAdapter);

    }

    private void initUi() {
        recyclerViewOrder = findViewById(R.id.recyclerViewOrder);
        bottomNavigationView = findViewById(R.id.nav_view);
        back_button = findViewById(R.id.back_button);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewOrder.setLayoutManager(linearLayoutManager);

        recyclerViewOrder.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void loadMoreItem() {
                isLoading = true;
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadNextPage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adminAdapter.removeFooterLoading();
                if(currentPage <= 1 ) oderList.clear();
                List<OrderAdminDto> list = null;
                if(currentPage > 0 ){
                    list=   orderPresenter.getByStatus(status, (currentPage * 10) - 10);
                }
                if(list != null){
                    oderList.addAll(list);
                }
                adminAdapter = new OrderAdminAdapter(oderList, OrderActivity.this);
                recyclerViewOrder.setAdapter(adminAdapter);
                isLoading = false;
                if (currentPage < totalPage) {
                    adminAdapter.addFooterLoading();
                } else {
                    isLastPage = true;
                }
            }
        }, 500);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void clickHuy(int position) {
        Dialog dialog = onCreateDialog(position);
        dialog.show();


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void clickDuyet(int position) {
        OrderAdminDto orderAdminDto = oderList.get(position);
        if(orderAdminDto.getOrderEntityStatus() == 0){
            Dialog dialog = onCreateDialog2(position,"bạn có chắc chắn duyệt đơn hàng này?");
            dialog.show();

        }
        if(orderAdminDto.getOrderEntityStatus() == 1){
            Dialog dialog = onCreateDialog2(position,"Bạn có chắc chắn giao đơn hàng này");
            dialog.show();
        }

    }
    public Dialog onCreateDialog(int position) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage("Bạn có chắc chắn muốn xóa đơn hàng này")
                .setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onClick(DialogInterface dialog, int id) {
                        OrderAdminDto orderAdminDto = oderList.get(position);
                        orderPresenter.updateStatus(orderAdminDto.getOrderEntityId(),3,orderAdminDto.getUserEntityId());
                        oderList.remove(position);
                        adminAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
    public Dialog onCreateDialog2(int position,String mess) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        builder.setMessage(mess)
                .setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onClick(DialogInterface dialog, int id) {
                        OrderAdminDto orderAdminDto = oderList.get(position);
                        if(orderAdminDto.getOrderEntityStatus() == 0){
                            orderPresenter.updateStatus(orderAdminDto.getOrderEntityId(),1,orderAdminDto.getUserEntityId());
                            oderList.remove(position);
                            adminAdapter.notifyDataSetChanged();
                        }
                        if(orderAdminDto.getOrderEntityStatus() == 1){
                            orderPresenter.updateStatus(orderAdminDto.getOrderEntityId(),2,orderAdminDto.getUserEntityId());
                            oderList.remove(position);
                            adminAdapter.notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}