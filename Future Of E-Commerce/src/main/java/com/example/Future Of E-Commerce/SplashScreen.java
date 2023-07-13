package com.example.e_commercefuture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.RegisterAndLogin.LogInChoose;

public class SplashScreen extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);




        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                CheckUserEmail();
            }
        },3000);


    }

    void CheckUserEmail(){
        sharedPreferences=getSharedPreferences("share",MODE_PRIVATE);
//        SharedPreferences.Editor editor=sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
        String email= sharedPreferences.getString("email",null);
        String pass= sharedPreferences.getString("pass",null);

        if (email==null || pass==null) {
            Intent intent = new Intent(this, LogInChoose.class);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(this,HomePage2.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, "Your Are Login", Toast.LENGTH_SHORT).show();

            }



    }
}