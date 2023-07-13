package com.example.e_commercefuture.RegisterAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.Admin.AdminLogIn;
import com.example.e_commercefuture.R;
import com.example.e_commercefuture.Staff.StaffLogIn;

public class LogInChoose extends AppCompatActivity {

    Button reg , login;

    TextView admin , staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_choose);

        initview();

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInChoose.this, RegisterAccount2.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInChoose.this, LogInPage2.class);
                startActivity(intent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInChoose.this, AdminLogIn.class);
                startActivity(intent);
            }
        });

        staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInChoose.this, StaffLogIn.class);
                startActivity(intent);
            }
        });


    }

    void initview(){
        reg=findViewById(R.id.btnreg);
        login=findViewById(R.id.btnlogin);
        admin=findViewById(R.id.btn_admin);
        staff=findViewById(R.id.btn_staff);


    }
    }