package com.example.dpaper_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

private Button regButton;
private EditText username;
private EditText password;
private EditText phoneNumber;

private TextView alreadyBTN;

 private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regButton = (Button)findViewById(R.id.register_button1);
        username = (EditText)findViewById(R.id.register_input_username);
        password = (EditText)findViewById(R.id.register_input_password);
        phoneNumber = (EditText)findViewById(R.id.register_phone_number);
        alreadyBTN = (TextView) findViewById(R.id.alreadyHave);
        loadingBar = new ProgressDialog(this);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });

        alreadyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }
    private void CreateAccount() {

        String usernameS = username.getText().toString();
        String passwordS = password.getText().toString();
        String phoneNumberS = phoneNumber.getText().toString();

        if(TextUtils.isEmpty(usernameS)){

            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(passwordS)){

            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(phoneNumberS)){

            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show();
        }
        else {

            loadingBar.setTitle("Создание аккаунта");
            loadingBar.setMessage("Пожалуйста, подождите");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidatePhone(usernameS,passwordS,phoneNumberS);



        }
    }

    private void ValidatePhone(String usernameS, String passwordS, String phoneNumberS) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("Users").child(phoneNumberS).exists())) {
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("phone", phoneNumberS);
                    userDataMap.put("name", usernameS);
                    userDataMap.put("pass", passwordS);

                    RootRef.child("Users").child(phoneNumberS).updateChildren(userDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {


                                    loadingBar.dismiss();
                                    Toast.makeText(RegisterActivity.this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();

                                    if (task.isSuccessful()) {
                                        Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(LoginIntent);
                                    }

                                    else
                                    {
                                        loadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                }

                else
                {
                    loadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Номер  " + phoneNumberS + "  уже зарегистрирован", Toast.LENGTH_SHORT).show();

                    Intent LoginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(LoginIntent);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}