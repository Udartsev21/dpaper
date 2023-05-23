package com.example.dpaper_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.canhub.cropper.CropImage;
import com.canhub.cropper.CropImageView;
import com.example.dpaper_shop.Prevalent.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {

    private CropImageView settings_image_IV;
    private EditText settings_name_ET, settings_phone_ET, settings_adress_ET;
    private TextView close_settings_TV, save_settings_TV;

    private String chacker = "";
    private String imageUri;
    private StorageReference storageProfilePictureRef;
    private StorageTask uploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings_image_IV = (CropImageView)findViewById(R.id.settings_image);
        settings_name_ET = (EditText)findViewById(R.id.settings_name);
        settings_phone_ET = (EditText)findViewById(R.id.settings_phone);
        settings_adress_ET = (EditText)findViewById(R.id.settings_adress);
        close_settings_TV = (TextView)findViewById(R.id.close_settings);
        save_settings_TV = (TextView)findViewById(R.id.save_settings);
        storageProfilePictureRef = FirebaseStorage.getInstance().getReference().child("Profile pictures");

        userInfDisplay(settings_image_IV, settings_name_ET, settings_phone_ET, settings_adress_ET);


        close_settings_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(SettingsActivity.this, HomeActivity.class);
                startActivity(loginIntent);
            }
        });

        save_settings_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chacker.equals("pushed")){

                        userInfSaved();
                }
                else{

                        updateOnlyUserInf();
                }
            }
        });


        settings_image_IV.setOnCropImageCompleteListener(new CropImageView.OnCropImageCompleteListener() {
            @Override
            public void onCropImageComplete(@NonNull CropImageView cropImageView, @NonNull CropImageView.CropResult cropResult) {
                chacker = "pushed";

                cropImageView.setImageUriAsync(Uri.parse(imageUri));
                settings_image_IV.getCroppedImage();



            }
        });
        
    }

    private void userInfDisplay(CropImageView settings_image_iv, EditText settings_name_et, EditText settings_phone_et, EditText settings_adress_et) {



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            CropImage.ActivityResult.Companion result = CropImage.ActivityResult.Companion;

//            imageUri = result.getUri();

            settings_image_IV.setImageUriAsync(Uri.parse(imageUri));
        }
        else
        {
            Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(SettingsActivity.this, SettingsActivity.class));
            finish();
        }
    }


    private void userInfSaved() {

        if(TextUtils.isEmpty(settings_name_ET.getText().toString())){
            Toast.makeText(this, "Заполните ФИО", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(settings_adress_ET.getText().toString())){
            Toast.makeText(this, "Заполните адрес доставки", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(settings_phone_ET.getText().toString())){
            Toast.makeText(this, "Заполните номер телефона", Toast.LENGTH_SHORT).show();
        }
        else if(chacker.equals("pushed")){

            uploadImage();

        }
    }

    private void uploadImage() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Обновляемся..");
        progressDialog.setMessage("Пожалуйста, подождите");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        if (imageUri != null)
        {
            final StorageReference fileRef = storageProfilePictureRef
                    .child(Prevalent.currentOnlineUser.getPhone() + ".jpg");

            uploadTask = fileRef.putFile(Uri.parse(imageUri));

            uploadTask.continueWithTask(new Continuation() {
                        @Override
                        public Object then(@NonNull Task task) throws Exception
                        {
                            if (!task.isSuccessful())
                            {
                                throw task.getException();
                            }

                            return fileRef.getDownloadUrl();
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task)
                        {
                            if (task.isSuccessful())
                            {
                                Uri downloadUrl = task.getResult();
                                String myUrl = downloadUrl.toString();

                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

                                HashMap<String, Object> userMap = new HashMap<>();
                                userMap. put("name", settings_name_ET.getText().toString());
                                userMap. put("address", settings_adress_ET.getText().toString());
                                userMap. put("phone", settings_phone_ET.getText().toString());
                                userMap. put("image", myUrl);
                                ref.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);

                                progressDialog.dismiss();

                                startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
                                Toast.makeText(SettingsActivity.this, "Информация успешно сохранена", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(SettingsActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else
        {
            Toast.makeText(this, "Изображение не выбрано.", Toast.LENGTH_SHORT).show();
        }
    }



    private void updateOnlyUserInf() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users");

        HashMap<String, Object> userMap = new HashMap<>();
        userMap. put("name", settings_name_ET.getText().toString());
        userMap. put("name", settings_phone_ET.getText().toString());
        userMap. put("name", settings_adress_ET.getText().toString());
        ref.child(Prevalent.currentOnlineUser.getPhone()).updateChildren(userMap);



        startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
        Toast.makeText(SettingsActivity.this, "Сохранение успешно", Toast.LENGTH_SHORT).show();
        finish();
    }
}