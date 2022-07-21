package com.example.projectcuoikyeommerce.activity.admin;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.activity.MainActivity;
import com.example.projectcuoikyeommerce.adapter.admin.AddproductAdapter;
import com.example.projectcuoikyeommerce.adapter.admin.SpinnerBSTAdapter;
import com.example.projectcuoikyeommerce.adapter.admin.SpinnerTypeAdapter;
import com.example.projectcuoikyeommerce.api.config.ApiUtils;
import com.example.projectcuoikyeommerce.component.GridSpacingItemDecoration;
import com.example.projectcuoikyeommerce.component.RealPathUtil;
import com.example.projectcuoikyeommerce.dto.DataUpload;
import com.example.projectcuoikyeommerce.dto.ProductUpload;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.FileUload;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.Product;
import com.example.projectcuoikyeommerce.model.Province;
import com.example.projectcuoikyeommerce.model.TagParent;
import com.example.projectcuoikyeommerce.presenter.AddproductPresenter;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductActivity extends AppCompatActivity implements AddproductAdapter.EventAddProduct {
    private RecyclerView recyclerView;
    private ImageButton btnSelectImage, btnSave, btnBackAddAddress;
    private List<Uri> uriList = new ArrayList<>();
    private AddproductAdapter addproductAdapter;
    private AddproductPresenter addproductPresenter;
    private EditText edtNameProduct, edtSLS, edtSLX, edtSLM, edtSLXL, edtPrice;
    private Spinner spinerLoai, spinerBST;
    private List<TagParent> list = new ArrayList<>();
    private List<Branch> listBST = new ArrayList<>();
    private int idType = -1, idBST = -1;
    private String TAG = "AAA";
    private RelativeLayout boxProgressBar;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        addproductPresenter = new AddproductPresenter(this);
        initUi();
        actionEvent();
        initData();
        initSpinner();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initSpinner() {
        list = addproductPresenter.getListType();
        SpinnerTypeAdapter spinnerTypeAdapter = new SpinnerTypeAdapter(list);
        spinerLoai.setAdapter(spinnerTypeAdapter);

        listBST = addproductPresenter.getListBST();
        SpinnerBSTAdapter spinnerBSTAdapter = new SpinnerBSTAdapter(listBST);
        spinerBST.setAdapter(spinnerBSTAdapter);

        spinerLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idType = list.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinerBST.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idBST = listBST.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initData() {
        addproductAdapter = new AddproductAdapter(uriList, this);
        recyclerView.setLayoutManager(new GridLayoutManager(AddProductActivity.this, 2));
        recyclerView.setAdapter(addproductAdapter);
        int spanCount = 2; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = false;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
    }

    private void actionEvent() {

        btnSelectImage.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(AddProductActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AddProductActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            } else {
                selectImage();
            }

        });
        btnBackAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                boxProgressBar.setVisibility(View.VISIBLE);
                String name = edtNameProduct.getText().toString().trim();
                String s = edtSLS.getText().toString().trim();
                String x = edtSLX.getText().toString().trim();
                String m = edtSLM.getText().toString().trim();
                String xl = edtSLXL.getText().toString().trim();
                String c = edtPrice.getText().toString().trim();

                try {
                    int sizeS = Integer.parseInt(s);
                    int sizeX = Integer.parseInt(x);
                    int sizeM = Integer.parseInt(m);
                    int sizeXL = Integer.parseInt(xl);
                    int price = Integer.parseInt(c);

                    if (sizeS < 0 || sizeM < 0 || sizeX < 0 || sizeXL < 0 || price < 0) {
                        boxProgressBar.setVisibility(View.GONE);
                        Toast.makeText(AddProductActivity.this, "Vui lòng nhập số lớn hơn 0", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (name.isEmpty()) {
                        boxProgressBar.setVisibility(View.GONE);
                        edtNameProduct.setHintTextColor(Color.RED);
                        Toast.makeText(AddProductActivity.this, "Vui lòng nhập Tên sản phẩm", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (uriList.size() <= 0) {
                        boxProgressBar.setVisibility(View.GONE);
                        Toast.makeText(AddProductActivity.this, "Vui lòng chọn hình ảnh", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    ProductUpload product = new ProductUpload(name, price, sizeS, sizeM, sizeX, sizeXL, idBST, idType);
                    List<FileUload> fileUloads = addproductPresenter.uploadImage(uriList);
                    List<Image> imageList = new ArrayList<>();
                    for (FileUload f : fileUloads) {
                        imageList.add(new Image(f.getPath()));
                    }
                    DataUpload dataUpload = new DataUpload(product, imageList);
                    Product p = addproductPresenter.create(dataUpload);
                    Toast.makeText(AddProductActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                    boxProgressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    boxProgressBar.setVisibility(View.GONE);
                    Toast.makeText(AddProductActivity.this, "Vui lòng nhập đúng định dạng", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private MultipartBody.Part[] requestUploadSurvey(List<Uri> uriList) {
        MultipartBody.Part[] surveyImagesParts = new MultipartBody.Part[uriList.size()];
        for (int index = 0; index < uriList.size(); index++) {
            String path = RealPathUtil.getRealPath(getApplicationContext(), uriList.get(index));
            File file = new File(path);
            RequestBody surveyBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            surveyImagesParts[index] = MultipartBody.Part.createFormData("files", file.getName(),
                    surveyBody);
        }
        return surveyImagesParts;

    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*"); //allows any image file type. Change * to specific extension to limit it
//**The following line is the important one!
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
    }

    private void initUi() {
        recyclerView = findViewById(R.id.recyclerViewAddProduct);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnSave = findViewById(R.id.btnSave);
        edtNameProduct = findViewById(R.id.edtNameProduct);
        edtSLS = findViewById(R.id.edtSLS);
        edtSLX = findViewById(R.id.edtSLX);
        edtSLM = findViewById(R.id.edtSLM);
        edtSLXL = findViewById(R.id.edtSLXL);
        spinerLoai = findViewById(R.id.spinerLoai);
        spinerBST = findViewById(R.id.spinerBST);
        edtPrice = findViewById(R.id.edtPrice);
        boxProgressBar = findViewById(R.id.boxProgressBar);
        btnBackAddAddress = findViewById(R.id.btnBackAddAddress);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectImage();
            } else {
                Toast.makeText(this, "PERMISSION fail", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                    for (int i = 0; i < count; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        uriList.add(imageUri);
                    }
                    addproductAdapter.notifyDataSetChanged();
                }
            }
//            else if (data.getData() != null) {
//                Uri selectImageUri =  data.getData();
//                Log.d("AAA", "onActivityResult: " + selectImageUri);
//                uriList.add(selectImageUri);
//                addproductAdapter.notifyDataSetChanged();
//
//            }
        }
    }

    @Override
    public void clickDelete(int position) {
        uriList.remove(position);
        addproductAdapter.notifyDataSetChanged();

    }
}