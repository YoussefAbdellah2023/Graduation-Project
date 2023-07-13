package com.example.e_commercefuture.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.R;
import com.example.e_commercefuture.Staff.StaffAddItemActivity;

public class AdminHomePage extends AppCompatActivity {

    Button btn1 , btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        intiview();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminHomePage.this, AddStaffPage.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomePage.this, StaffAddItemActivity.class);
                startActivity(intent);
            }
        });
    }

    void intiview(){
        btn1=findViewById(R.id.btn_add_staff);
        btn2=findViewById(R.id.btn_add_stock);

    }
}