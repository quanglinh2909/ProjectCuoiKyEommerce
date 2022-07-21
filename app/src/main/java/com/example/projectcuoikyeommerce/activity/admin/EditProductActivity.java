package com.example.projectcuoikyeommerce.activity.admin;

import androidx.annotation.NonNull;
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
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.projectcuoikyeommerce.R;
import com.example.projectcuoikyeommerce.adapter.admin.EditproductAdapter;
import com.example.projectcuoikyeommerce.adapter.admin.SpinnerBSTAdapter;
import com.example.projectcuoikyeommerce.adapter.admin.SpinnerTypeAdapter;
import com.example.projectcuoikyeommerce.component.FormatPrice;
import com.example.projectcuoikyeommerce.component.GridSpacingItemDecoration;
import com.example.projectcuoikyeommerce.component.RealPathUtil;
import com.example.projectcuoikyeommerce.constant.KeyIntent;
import com.example.projectcuoikyeommerce.dto.ImageUpload;
import com.example.projectcuoikyeommerce.dto.ProductUpload;
import com.example.projectcuoikyeommerce.model.Branch;
import com.example.projectcuoikyeommerce.model.FileUload;
import com.example.projectcuoikyeommerce.model.Image;
import com.example.projectcuoikyeommerce.model.ImageEdit;
import com.example.projectcuoikyeommerce.model.Product;
import com.example.projectcuoikyeommerce.model.TagParent;
import com.example.projectcuoikyeommerce.presenter.AddproductPresenter;
import com.example.projectcuoikyeommerce.presenter.ProductDetailPresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditProductActivity extends AppCompatActivity implements EditproductAdapter.EventAddProduct {
    private RecyclerView recyclerView;
    private ImageButton btnSelectImage, btnSave,btnBackAddAddress;
    private EditproductAdapter addproductAdapter;
    private AddproductPresenter addproductPresenter;
    private EditText edtNameProduct, edtSLS, edtSLX, edtSLM, edtSLXL, edtPrice;
    private Spinner spinerLoai, spinerBST;
    private List<TagParent> list = new ArrayList<>();
    private List<Branch> listBST = new ArrayList<>();
    private int idType = -1, idBST = -1;
    private String TAG = "AAA";
    private RelativeLayout boxProgressBar;
    private ProductDetailPresenter productDetailPresenter = new ProductDetailPresenter();
    private String idProduct = "";
    private List<Image> imageList = new ArrayList<>();
    private List<ImageEdit> imageEdits = new ArrayList<>();
    private Product mProduct;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        Intent intent = getIntent();
        if(intent.hasExtra(KeyIntent.KEY_PRODUCT_ID)){
            idProduct = intent.getStringExtra(KeyIntent.KEY_PRODUCT_ID);
        }

        addproductPresenter = new AddproductPresenter(this);
        this.mProduct = productDetailPresenter.getProductById(idProduct);
        imageList = productDetailPresenter.getListImage(idProduct);
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
        for (Image image : imageList) {
            imageEdits.add(new ImageEdit(image.getUrl(), null, 0, image.getId()));
        }
        addproductAdapter = new EditproductAdapter(imageEdits, this);
        recyclerView.setLayoutManager(new GridLayoutManager(EditProductActivity.this, 2));
        recyclerView.setAdapter(addproductAdapter);
        int spanCount = 2; // 3 columns
        int spacing = 10; // 50px
        boolean includeEdge = false;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        edtNameProduct.setText(mProduct.getName());
        edtSLS.setText(mProduct.getS() + "");
        edtSLX.setText(mProduct.getL() + "");
        edtSLM.setText(mProduct.getM() + "");
        edtSLXL.setText(mProduct.getXl() + "");
        for (int i = 0; i < list.size(); i++) {
            if (mProduct.getIdParent().getId() == list.get(i).getId()) {
                spinerLoai.setSelection(i);
                idType = mProduct.getIdParent().getId();
                break;
            }
        }
        for (int i = 0; i < listBST.size(); i++) {
            if (mProduct.getIdlocalbranch().getId() == listBST.get(i).getId()) {
                spinerBST.setSelection(i);
                idBST = mProduct.getIdlocalbranch().getId();
                break;
            }
        }

        edtPrice.setText(mProduct.getPrice() + "");
    }

    private void actionEvent() {
        btnBackAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSelectImage.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(EditProductActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(EditProductActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1);
            } else {
                selectImage();
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
                        Toast.makeText(EditProductActivity.this, "Vui lòng nhập số lớn hơn 0", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (name.isEmpty()) {
                        boxProgressBar.setVisibility(View.GONE);
                        edtNameProduct.setHintTextColor(Color.RED);
                        Toast.makeText(EditProductActivity.this, "Vui lòng nhập Tên sản phẩm", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ProductUpload product = new ProductUpload(mProduct.getId(), name, price, sizeS, sizeM, sizeX, sizeXL, idBST, idType);
                    addproductPresenter.update(product);
                    Toast.makeText(EditProductActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();
                    boxProgressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    boxProgressBar.setVisibility(View.GONE);
                    Toast.makeText(EditProductActivity.this, "Vui lòng nhập đúng định dạng", Toast.LENGTH_SHORT).show();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                if (data.getClipData() != null) {
                    int count = data.getClipData().getItemCount(); //evaluate the count before the for loop --- otherwise, the count is evaluated every loop.
                    List<Uri> listUri = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        Uri imageUri = data.getClipData().getItemAt(i).getUri();
                        imageEdits.add(new ImageEdit(null, imageUri, 1, -1));
                        listUri.add(imageUri);
                    }
                    List<FileUload> fileUloads = addproductPresenter.uploadImage(listUri);
                    List<ImageUpload> imageList = new ArrayList<>();
                    for (FileUload f : fileUloads) {
                        imageList.add(new ImageUpload( mProduct.getId(),f.getPath()));
                    }
                    addproductPresenter.createImage(imageList);
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void clickDelete(int position) {
        ImageEdit imageEdit = imageEdits.get(position);
        if (imageEdits.size() > 1) {
            if (imageEdit.getType() == 0) {
                addproductPresenter.deleteImage(imageEdit.getId() + "", imageEdit.getUrl());
            }
            imageEdits.remove(position);
            addproductAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Hình ảnh không được trống", Toast.LENGTH_SHORT).show();
        }

    }
}