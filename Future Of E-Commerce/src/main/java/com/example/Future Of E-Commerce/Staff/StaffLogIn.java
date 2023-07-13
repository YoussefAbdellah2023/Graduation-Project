package com.example.e_commercefuture.Staff;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StaffLogIn extends AppCompatActivity {

    EditText txt1 , txt2;
    Button btn;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_log_in);

        intialization();
        firebaseAuth=FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          CheckCredential();
            }
        });
    }

    void intialization(){
        txt1=findViewById(R.id.login_staff_email);
        txt2=findViewById(R.id.login_staff_pass);
        btn=findViewById(R.id.btn_login_staff);
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

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Intent intent = new Intent(StaffLogIn.this, StaffHomePage2.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(StaffLogIn.this, "Failed LogIn! Check Your Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}