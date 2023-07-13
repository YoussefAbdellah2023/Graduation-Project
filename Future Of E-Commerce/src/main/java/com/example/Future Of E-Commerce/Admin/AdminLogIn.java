package com.example.e_commercefuture.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.R;

public class AdminLogIn extends AppCompatActivity {

    EditText txt1 , txt2;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log_in);

        initialization();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        CheckCredential();

            }
        });
    }

    void initialization(){
        txt1=findViewById(R.id.admin_email);
        txt2=findViewById(R.id.admin_pass);
        btn=findViewById(R.id.btn_admin2);
    }

    void CheckCredential() {

        String email = txt1.getText().toString();
        String password = txt2.getText().toString();


        if (email.isEmpty()){
            txt1.setError("Email Is Required!");
            txt1.requestFocus();
            return;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txt1.setError("Please Provide Valid Email!");
            txt1.requestFocus();
            return;
        }


        if (password.isEmpty() || password.length() < 7){
            txt2.setError("Password Must Be 7 Characters");
            txt2.requestFocus();
            return;
        }

        if (email.equals("admin@gmail.com") && password.equals("abc1234")){
            Intent intent = new Intent(AdminLogIn.this,AdminHomePage.class);
            startActivity(intent);
            finish();
        }


    }
}