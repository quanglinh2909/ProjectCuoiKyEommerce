package com.example.projectcuoikyeommerce.activity.admin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.admin.OrderDetailAdminAdapter;
import com.example.projectcuoikyeommerce.component.FormatPrice;
import com.example.projectcuoikyeommerce.data_local.DataLocalManager;
import com.example.projectcuoikyeommerce.dto.OrderAdminDto;
import com.example.projectcuoikyeommerce.dto.OrderDetailDto;
import com.example.projectcuoikyeommerce.model.User;
import com.example.projectcuoikyeommerce.presenter.OrderPresenter;

import java.util.ArrayList;
import java.util.List;

public class DetailOrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
    private OrderDetailAdminAdapter adminAdapter;
    private OrderPresenter orderPresenter = new OrderPresenter();
    private String idOrder;
    private TextView txtLocation, txtStatus, txtTotalProduct, txtTotalPrice;
    private ImageButton btnBackAddAddress;
    private Button btnDuyet, btnHuy;
    private RelativeLayout boxProgressBar;
    String TAG = "AAA";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_order);
        Intent intent = getIntent();
        idOrder = intent.getStringExtra("idOrder");
        initUI();
        getData();
        handleAction();
    }

    private void handleAction() {
        btnBackAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDetailDto orderAdminDto = orderDetailDtoList.get(0);
                if (orderAdminDto.getOrderEntityStatus() == 0) {
                    onCreateDialog2("bạn có chắc chắn duyệt đơn hàng này?").show();

                }
                if (orderAdminDto.getOrderEntityStatus() == 1) {
                    onCreateDialog2("Bạn có chắc chắn giao đơn hàng này").show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = onCreateDialog();
                dialog.show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getData() {
        orderDetailDtoList = orderPresenter.getOrderById(idOrder);
        adminAdapter = new OrderDetailAdminAdapter(orderDetailDtoList);
        recyclerView.setAdapter(adminAdapter);
        OrderDetailDto orderDetailDto = orderDetailDtoList.get(0);
        txtLocation.setText(orderDetailDto.getUserInfoEntityFullName() + "| (+84) "
                + orderDetailDto.getUserInfoEntityAddress() + ", " + orderDetailDto.getUserInfoEntityWard()
                + ", " + orderDetailDto.getUserInfoEntityDistrict() + ", " + orderDetailDto.getUserInfoEntityProvince());
        txtStatus.setText(getTrangThai(orderDetailDto.getOrderEntityStatus()));
        txtTotalProduct.setText("Tổng sản phẩm: " + orderDetailDto.getOrderEntityTotal());
        int totalPrice = 0;
        int totalSp = 0;
        for (OrderDetailDto detailDto : orderDetailDtoList) {
            totalPrice += detailDto.getOrderDetailEntityPrice() * detailDto.getOrderDetailEntityQuantity();
            totalSp += detailDto.getOrderDetailEntityQuantity();
        }
        txtTotalProduct.setText("Tổng sản phẩm: " + totalSp);
        txtTotalPrice.setText(FormatPrice.formatPrice(totalPrice));
        User user = DataLocalManager.getInstance().getUser();
        if(user.getRole() == 0){
            btnDuyet.setVisibility(View.GONE);
        }
        if(user.getRole() == 0 &&  orderDetailDto.getOrderEntityStatus() != 0){
            btnDuyet.setVisibility(View.GONE);
            btnHuy.setVisibility(View.GONE);
        }

        if (orderDetailDto.getOrderEntityStatus() == 1) {
            btnDuyet.setText("Giao hàng");

        }
        if (orderDetailDto.getOrderEntityStatus() == 2) {
            btnDuyet.setVisibility(View.GONE);
        }
        if (orderDetailDto.getOrderEntityStatus() == 3) {
            btnDuyet.setVisibility(View.GONE);
            btnHuy.setVisibility(View.GONE);
        }
    }

    private void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView = findViewById(R.id.recyclerViewOrder);
        txtLocation = findViewById(R.id.txtLocation);
        txtStatus = findViewById(R.id.txtStatus);
        txtTotalProduct = findViewById(R.id.txtTotalProduct);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        btnBackAddAddress = findViewById(R.id.btnBackAddAddress);
        btnHuy = findViewById(R.id.btnHuy);
        btnDuyet = findViewById(R.id.btnDuyet);
        boxProgressBar = findViewById(R.id.boxProgressBar);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    public String getTrangThai(int status) {
        switch (status) {
            case 1:
                return "Đang giao hàng";
            case 2:
                return "Đã giao hàng";
            case 3:
                return "Đã hủy";
            default:
                return "Chờ phê duyệt";
        }
    }

    public Dialog onCreateDialog() {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có chắc chắn muốn hủy đơn hàng này")
                .setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onClick(DialogInterface dialog, int id) {
                        boxProgressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                OrderDetailDto orderAdminDto = orderDetailDtoList.get(0);
                                orderPresenter.updateStatus(orderAdminDto.getOrderEntityId(), 3,orderAdminDto.getUserEntityId());
                                txtStatus.setText("Đã hủy");
                                btnDuyet.setVisibility(View.GONE);
                                btnHuy.setVisibility(View.GONE);
                                boxProgressBar.setVisibility(View.GONE);
                                orderDetailDtoList.get(0).setOrderEntityStatus(3);

                            }
                        }, 1000);
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

    public Dialog onCreateDialog2(String mess) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mess)
                .setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    public void onClick(DialogInterface dialog, int id) {
                        boxProgressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                OrderDetailDto orderAdminDto = orderDetailDtoList.get(0);
                                if (orderAdminDto.getOrderEntityStatus() == 1) {
                                    orderPresenter.updateStatus(orderAdminDto.getOrderEntityId(), 2,orderAdminDto.getUserEntityId());
                                    txtStatus.setText("Đã Giao");
                                    btnDuyet.setVisibility(View.GONE);
                                    orderDetailDtoList.get(0).setOrderEntityStatus(2);

                                }
                                if (orderAdminDto.getOrderEntityStatus() == 0) {
                                    orderPresenter.updateStatus(orderAdminDto.getOrderEntityId(), 1,orderAdminDto.getUserEntityId());
                                    txtStatus.setText("Đang Giao");
                                    btnDuyet.setText("Giao hàng");
                                    orderDetailDtoList.get(0).setOrderEntityStatus(1);

                                }
                                boxProgressBar.setVisibility(View.GONE);

                            }
                        }, 1000);
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