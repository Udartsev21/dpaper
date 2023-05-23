package com.example.dpaper_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dpaper_shop.Model.Users;
import com.example.dpaper_shop.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

private Button regBtn, logBtn;
    private ProgressDialog loadingBar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logBtn = (Button) findViewById(R.id.main_login);
        regBtn =  (Button) findViewById(R.id.reg_button1);



        loadingBar = new ProgressDialog(this);

        Paper.init(this);





logBtn.setOnClickListener(new View.OnClickListener(){
    @Override

    public void onClick(View v) {

        Intent LoginIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(LoginIntent);
    }
});




regBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent RegisterIntent = new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(RegisterIntent);

    }
});

        String UserPhonekey = Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordkey = Paper.book().read(Prevalent.UserPasswordKey);

        if(UserPhonekey != "" && UserPasswordkey != "") {

                if(!TextUtils.isEmpty(UserPhonekey) && !TextUtils.isEmpty(UserPasswordkey) ){

            ValidateUser(UserPhonekey, UserPasswordkey);

                    loadingBar.setTitle("Вход в приложение");
                    loadingBar.setMessage("Пожалуйста, подождите");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();
}
        }

    }

    private void ValidateUser(final String phone, final String pass) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.child("Users").child(phone).exists())
                {
                    Users usersData = dataSnapshot.child("Users").child(phone).getValue(Users.class);


                    if(usersData.getPhone().equals(phone))
                    {

                        if(usersData.getPass().equals(pass)){

                            loadingBar.dismiss();
                            Toast.makeText(MainActivity.this, "Успешный вход!", Toast.LENGTH_SHORT).show();

                            Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(homeIntent);
                        }
                        else {

                            loadingBar.dismiss();

                        }


                    }

                }



                else {
                    loadingBar.dismiss();
                    Toast.makeText(MainActivity.this, "Аккаунт с номером  " + phone + "  не существует", Toast.LENGTH_SHORT).show();

                    Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                    startActivity(registerIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}