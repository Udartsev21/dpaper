package com.example.dpaper_shop;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class CreateProductActivity extends AppCompatActivity {

    private String categoryName, description, price, pname, saveCurrentDate, saveCurrentTime, ProductRandomKey;

    private String downloadIcoUrl;
    private ImageView selectIcon;
    private EditText productName, productDescription, productPrice;
    private Button saveBNT;

    private static final int GALLERYPICK = 1;
    private Uri ImageUri;

    private StorageReference selectIconRef;

    private DatabaseReference productsRef;

    private ProgressDialog loadingBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
init();

        Toast.makeText(this, "Выбрана категория " + categoryName, Toast.LENGTH_SHORT).show();

        selectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });
        saveBNT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProductData();
            }
        });

    }

    private void OpenGallery() {

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERYPICK);

    }


    private void ProductData() {
        description = productDescription.getText().toString();
        price = productPrice.getText().toString();
        pname = productName.getText().toString();
         if(TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Добавьте описание товара", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(price)) {
            Toast.makeText(this, "Добавьте стоимость", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(pname)) {
            Toast.makeText(this, "Добавьте название товара", Toast.LENGTH_SHORT).show();
        }
        else{
            StoreProductInf();
        }
    }

    private void StoreProductInf() {

        loadingBar.setTitle("Загрузка данных");
        loadingBar.setMessage("Пожалуйста, подождите");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("ddmmyyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
        saveCurrentTime = currentTime.format(calendar.getTime());

        ProductRandomKey = saveCurrentDate + saveCurrentTime;

        StorageReference filePath = selectIconRef.child(ImageUri.getLastPathSegment() + ProductRandomKey + ".jbg");
        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(CreateProductActivity.this, "Ошибка:" + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(CreateProductActivity.this, "Успешная загрузка", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                    throw task.getException();

                        }
                        downloadIcoUrl = filePath.getDownloadUrl().toString();
                         return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {


                        if(task.isSuccessful()){

                           SaveProductInfotoDatabase();
                            Toast.makeText(CreateProductActivity.this, "Изображение сохранено", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }

    private void SaveProductInfotoDatabase() {
        HashMap<String, Object> productMap = new HashMap<>();

        productMap.put("pid", ProductRandomKey);
        productMap.put("data", saveCurrentDate);
        productMap.put("time", saveCurrentTime);
        productMap.put("description", description);
        productMap.put("image", downloadIcoUrl);
        productMap.put("category", categoryName);
        productMap.put("price", price);
        productMap.put("pname", pname);

        productsRef.child(ProductRandomKey).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    loadingBar.dismiss();
                    Toast.makeText(CreateProductActivity.this, "Товар добавлен", Toast.LENGTH_SHORT).show();
                    Intent xIntent = new Intent(CreateProductActivity.this,CategoryActivity.class);
                    startActivity(xIntent);

                }
else {
                    String message = task.getException().toString();
                    Toast.makeText(CreateProductActivity.this, "Ошибка:" + message, Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERYPICK && resultCode == RESULT_OK && data != null){


            ImageUri = data.getData();
            selectIcon.setImageURI(ImageUri);


        }
    }

    private void init() {
        categoryName = getIntent().getExtras().get("category").toString();
        selectIcon = findViewById(R.id.select_icon);
        productName = findViewById(R.id.product_name);
        productDescription = findViewById(R.id.description_name);
        productPrice = findViewById(R.id.product_price);
        saveBNT = findViewById(R.id.save);
        selectIconRef = FirebaseStorage.getInstance().getReference().child("ProductImages");
        productsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        loadingBar = new ProgressDialog(this);
    }
}