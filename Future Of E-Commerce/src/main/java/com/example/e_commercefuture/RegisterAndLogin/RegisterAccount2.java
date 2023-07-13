package com.example.e_commercefuture.RegisterAndLogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.DataTypeRegisterAccount2;
import com.example.e_commercefuture.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterAccount2 extends AppCompatActivity {

    EditText txt1, txt2, txt3, txt4;
    Button btn;
    TextView textView1, textView2;

    DatabaseReference ref;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account2);

        initialization();

        auth=FirebaseAuth.getInstance();



        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterAccount2.this, com.example.e_commercefuture.RegisterAndLogin.LogInPage2.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RegisterAccount2();


            }


        });
    }







    void initialization() {

        txt1 = findViewById(R.id.edit_text_email);
        txt2 = findViewById(R.id.edit_text_password);
        txt3 = findViewById(R.id.edit_text_confirm_password);
        txt4 = findViewById(R.id.edit_text_user_name);

        btn = findViewById(R.id.btn_register);

        textView1 = findViewById(R.id.already_have_have_account);
        textView2 = findViewById(R.id.text_view_signin);


    }


    void RegisterAccount2() {
        String username = txt4.getText().toString();
        String email = txt1.getText().toString();
        String password = txt2.getText().toString();
        String ConfirmPassword = txt3.getText().toString();


      if (username.isEmpty() || username.length() < 7){
          txt4.setError("Your UserName Must Be 7 Characters!");
          txt4.requestFocus();
          return;
      }


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


        if (ConfirmPassword.isEmpty()){
            txt3.setError("ConfirmPassword Is Required!");
            txt3.requestFocus();
            return;
        }


        if (!ConfirmPassword.equals(password)){
            txt3.setError("Password Not Match!");
            txt3.requestFocus();
            return;
        }



        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            DataTypeRegisterAccount2 registerAccount2 = new DataTypeRegisterAccount2(username,email,password,ConfirmPassword);

                            FirebaseDatabase.getInstance().getReference("RegisterAccount")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(registerAccount2).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterAccount2.this, "User Has Been Registered Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(RegisterAccount2.this, com.example.e_commercefuture.RegisterAndLogin.LogInPage2.class);
                                        startActivity(intent);
                                        finish();


                                    }else {
                                        Toast.makeText(RegisterAccount2.this, "Failed to Register Try Again! ", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        }else {
                            Toast.makeText(RegisterAccount2.this, "Try Another Email ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });



    }




}