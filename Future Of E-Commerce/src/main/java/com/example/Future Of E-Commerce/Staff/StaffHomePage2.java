package com.example.e_commercefuture.Staff;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.R;
import com.google.firebase.auth.FirebaseAuth;

public class StaffHomePage2 extends AppCompatActivity {

    private Button signOut;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private Button btn_add_item, btn_check_order;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_home_page2);

        imageView=findViewById(R.id.admin_image);
        btn_add_item = (Button) findViewById(R.id.add_btn);
        btn_check_order = (Button) findViewById(R.id.check_orders_btn);
        signOut = (Button) findViewById(R.id.sign_out_btn);

        btn_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(StaffHomePage2.this, StaffAddItemActivity.class);
                startActivity(intent);
            }
        });

        btn_check_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(StaffHomePage2.this, AdminCheckOrderActivity.class);
                startActivity(intent);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StaffHomePage2.this,StaffLogIn.class);
                startActivity(intent);
                finish();
            }
        });

    }
}