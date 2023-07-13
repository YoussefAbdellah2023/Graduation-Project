package com.example.e_commercefuture.RegisterAndLogin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.ForgetPassword;
import com.example.e_commercefuture.HomePage2;
import com.example.e_commercefuture.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInPage2 extends AppCompatActivity {

    EditText editText1 , editText2;
    TextView textView1 , textView2 , textView3;
    Button btn1;
    View view1 , view2;
    ImageView imageView1 , imageView2 , imageView3 , imageView4 , imageView5;

    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page2);

        initialization();
        firebaseAuth=FirebaseAuth.getInstance();



        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInPage2.this, ForgetPassword.class);
                startActivity(intent);
            }
        });


        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInPage2.this, com.example.e_commercefuture.RegisterAndLogin.RegisterAccount2.class);
                startActivity(intent);
            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckCredential();
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));

                startActivity(intent);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin"));

                startActivity(intent);
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mobile.twitter.com/login"));

                startActivity(intent);
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login"));
                startActivity(intent);
            }
        });


    }


    void initialization(){

        editText1=findViewById(R.id.edit_text_email_login);
        editText2=findViewById(R.id.edit_text_password_login);

        textView1=findViewById(R.id.forget_password);
        textView2=findViewById(R.id.dont_have_account);
        textView3=findViewById(R.id.signup);

        btn1=findViewById(R.id.btn_login);

        view1=findViewById(R.id.view_line1);
        view2=findViewById(R.id.view_line2);

        imageView1=findViewById(R.id.btn_facebook);
        imageView2=findViewById(R.id.btn_google);
        imageView3=findViewById(R.id.btn_phone);
        imageView4=findViewById(R.id.btn_twitter);
        imageView5=findViewById(R.id.btn_githup);

        progressBar=findViewById(R.id.prog_bar_login2);





    }
    void CheckCredential() {

        String email = editText1.getText().toString();
        String password = editText2.getText().toString();


        if (email.isEmpty()){
            editText1.setError("Email Is Required!");
            editText1.requestFocus();
            return;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText1.setError("Please Provide Valid Email!");
            editText1.requestFocus();
            return;
        }


        if (password.isEmpty() || password.length() < 7){
            editText2.setError("Password Must Be 7 Characters");
            editText2.requestFocus();
            return;
        }
       progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                   Intent intent = new Intent(LogInPage2.this, HomePage2.class);
                   startActivity(intent);
                   finish();
                }
                else {
                    Toast.makeText(LogInPage2.this, "Failed LogIn! Check Your Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}