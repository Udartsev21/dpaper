package com.example.dpaper_shop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dpaper_shop.Model.Users;
import com.example.dpaper_shop.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity
{

    private Button logButton;

    private EditText username;
    private EditText password;
    private EditText phoneNumber;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";

    private TextView adminLink, clientLink, forget_passBTN;
    private CheckBox rememberMe;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logButton = (Button)findViewById(R.id.main_login);
        password = (EditText)findViewById(R.id.input_password);
        phoneNumber = (EditText)findViewById(R.id.input_login);

        loadingBar = new ProgressDialog(this);
         rememberMe = (CheckBox)findViewById(R.id.checkbox);

        forget_passBTN = (TextView) findViewById(R.id.forget_pass);
        adminLink = (TextView)findViewById(R.id.admin);
        clientLink = (TextView)findViewById(R.id.client);

        Paper.init(this);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        adminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adminLink.setVisibility(View.INVISIBLE);
                clientLink.setVisibility(View.VISIBLE);
                logButton.setText("Вход для сотрудника");
                parentDbName = "Admin";
            }
        });

        forget_passBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forget_passBTN.setText("Если вы забыли свой логин или пароль, пожалуйста напишите нам на почту dpaper@mail.ru");
            }
        });

        clientLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adminLink.setVisibility(View.VISIBLE);
                clientLink.setVisibility(View.INVISIBLE);
                logButton.setText("Войти");
                parentDbName = "Users";
            }
        });
    }

    private void loginUser() {
        String pass = password.getText().toString();
        String phone = phoneNumber.getText().toString();

        if(TextUtils.isEmpty(pass)){

            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(phone)){

            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show();
        }
        else {

            loadingBar.setTitle("Вход в приложение");
            loadingBar.setMessage("Пожалуйста, подождите");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            ValidateUser(pass,phone);
        }
    }



    private void ValidateUser(String pass, String phone) {

        if(rememberMe.isChecked()){
            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, pass);

        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.child(parentDbName).child(phone).exists())
                {
                    Users usersData = dataSnapshot.child(parentDbName).child(phone).getValue(Users.class);


                    if(usersData.getPhone().equals(phone))
                    {

                        if(usersData.getPass().equals(pass)){

                           if(parentDbName.equals("Users")){

                               loadingBar.dismiss();
                               Toast.makeText(LoginActivity.this, "Успешный вход!", Toast.LENGTH_SHORT).show();

                               Intent homeIntent = new Intent(LoginActivity.this,HomeActivity.class);
                               startActivity(homeIntent);

                           }
                           else if (parentDbName.equals("Admin")){
                               loadingBar.dismiss();
                               Toast.makeText(LoginActivity.this, "Успешный вход!", Toast.LENGTH_SHORT).show();

                               Intent homeIntent = new Intent(LoginActivity.this,CategoryActivity.class);
                               startActivity(homeIntent);

                           }
                        }
                        else {

                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Неверный пароль", Toast.LENGTH_SHORT).show();
                        }


                        }

                    }



                    else {
                        loadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "Аккаунт с номером  " + phone + "  не существует", Toast.LENGTH_SHORT).show();

                    Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(registerIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}