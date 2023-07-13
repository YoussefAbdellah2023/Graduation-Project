package com.example.e_commercefuture;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;
    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        intialization();

        firebaseAuth=FirebaseAuth.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ResetPassword();

            }
        });
    }


    void intialization(){
        textView=findViewById(R.id.text_view_forget_password);
        editText=findViewById(R.id.edit_text_forget_password);
        button=findViewById(R.id.btn_forget_password);
        progressBar=findViewById(R.id.prog_bar_forget_password);
    }

    void ResetPassword(){
        String email = editText.getText().toString().trim();

        if (email.isEmpty()){
            editText.setError("Email Is Required!");
            editText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText.setError("Please provide valid email");
            editText.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgetPassword.this, "Check Email To Reset Your Password", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ForgetPassword.this, "Try Again! Something wrong happened", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}