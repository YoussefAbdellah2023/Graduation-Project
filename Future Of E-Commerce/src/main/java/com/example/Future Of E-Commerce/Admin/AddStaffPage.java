package com.example.e_commercefuture.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeRegisterStaff;
import com.example.e_commercefuture.R;
import com.example.e_commercefuture.Staff.StaffLogIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AddStaffPage extends AppCompatActivity {

    EditText txt1 , txt2;
    Button btn;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff_page);

        intialization();
        firebaseAuth=FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterAccount2();
            }
        });
    }

    void intialization(){
        txt1=findViewById(R.id.add_staff_email);
        txt2=findViewById(R.id.add_staff_pass);
        btn=findViewById(R.id.btn_register_staff);
    }

    void RegisterAccount2() {

        String email = txt1.getText().toString();
        String password = txt2.getText().toString();


        if (email.isEmpty()) {
            txt1.setError("Email Is Required!");
            txt1.requestFocus();
            return;
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txt1.setError("Please Provide Valid Email!");
            txt1.requestFocus();
            return;
        }


        if (password.isEmpty() || password.length() < 7) {
            txt2.setError("Password Must Be 7 Characters");
            txt2.requestFocus();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            DataTypeRegisterStaff registerStaff = new DataTypeRegisterStaff(email,password);

                            FirebaseDatabase.getInstance().getReference("RegisterAccountStaff")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(registerStaff).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(AddStaffPage.this, "Staff Has Been Registered Successfully", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(AddStaffPage.this, StaffLogIn.class);
                                        startActivity(intent);
                                        finish();


                                    }else {
                                        Toast.makeText(AddStaffPage.this, "Failed to Register Try Again! ", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        }else {
                            Toast.makeText(AddStaffPage.this, "Try Another Email ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
}