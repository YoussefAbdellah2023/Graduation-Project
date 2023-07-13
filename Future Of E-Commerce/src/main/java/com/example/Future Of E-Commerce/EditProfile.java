package com.example.e_commercefuture;

import android.content.Intent;
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
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity {

    CircleImageView profileimageview;
    TextView textView;
    EditText editText1 ,editText2 , editText3 , editText4;
    Button btnupdateprofile , btnchangeimage;
    ProgressBar progressBar;
    ConstraintLayout constraintLayout;

    FirebaseAuth auth;

    DatabaseReference reference;
    FirebaseUser user;

    String userID;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);






        intialization();





        btnupdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editProfile();



//
//                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                    user.updateEmail(editText2.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Toast.makeText(EditProfile.this, "Your Email Is Changed", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });


//                user.updatePassword(editText3.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(EditProfile.this, "Your Password Is Changed", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


            }
        });






    }




    void intialization(){
        profileimageview=findViewById(R.id.edit_image_profile);
        textView=findViewById(R.id.edit_change_photo);
        editText1=findViewById(R.id.edit_username_profile);
        editText2=findViewById(R.id.edit_email_profile);
        editText3=findViewById(R.id.edit_password_profile);
        editText4=findViewById(R.id.edit_confirmpassword_profile);
        btnupdateprofile=findViewById(R.id.btn_save_update_profile);
//        btnchangeimage=findViewById(R.id.button_update_image_profile);
        progressBar=findViewById(R.id.prog_bar_update_profile);
        constraintLayout = findViewById(R.id.constrain_layout_update_profile);

    }


    void editProfile(){

        String username = editText1.getText().toString();
        String email = editText2.getText().toString();
        String password = editText3.getText().toString();
        String ConfirmPassword = editText4.getText().toString();

        if (username.isEmpty() || username.length() < 7){
            editText1.setError("Your UserName Must Be 7 Characters!");
            editText1.requestFocus();
            return;
        }


        if (email.isEmpty()){
            editText2.setError("Email Is Required!");
            editText2.requestFocus();
            return;
        }



        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText2.setError("Please Provide Valid Email!");
            editText2.requestFocus();
            return;
        }

        else {


            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user.updateEmail(editText2.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(EditProfile.this, "Your Email Is Changed", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


        if (password.isEmpty() || password.length() < 7){
            editText3.setError("Password Must Be 7 Characters");
            editText3.requestFocus();
            return;
        }


        if (ConfirmPassword.isEmpty()){
            editText4.setError("ConfirmPassword Is Required!");
            editText4.requestFocus();
            return;
        }


        if (!ConfirmPassword.equals(password)){
            editText4.setError("Password Not Match!");
            editText4.requestFocus();
            return;
        }



        else {

            user.updatePassword(editText3.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(EditProfile.this, "Your Password Is Changed", Toast.LENGTH_SHORT).show();
                    }
                }
            });



            DataTypeRegisterAccount2 registerAccount2 = new DataTypeRegisterAccount2(username,email,password,ConfirmPassword);

            FirebaseDatabase.getInstance().getReference("RegisterAccount")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(registerAccount2).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(EditProfile.this, "Your Data Is Update", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfile.this,UserProfile.class);
                        startActivity(intent);

                    }
                }
            });
        }

    }





    }




