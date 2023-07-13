package com.example.e_commercefuture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HeaderHomePage3333 extends AppCompatActivity {

    ImageView imageView;
    TextView txt1 , txt2;
    TextView editText;
    Button button;

    DatabaseReference reference;
    FirebaseUser user;

    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_home_page3333);

       user= FirebaseAuth.getInstance().getCurrentUser();
       reference= FirebaseDatabase.getInstance().getReference("RegisterAccount");
       userID=user.getUid();


        txt1=findViewById(R.id.text_view_user_3333);
        editText=findViewById(R.id.edit_text_email_present_profile);
        imageView=findViewById(R.id.user_image_profile3333);
        button=findViewById(R.id.btn_update_profile);

        reference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataTypeRegisterAccount2 registerAccount2 = snapshot.getValue(DataTypeRegisterAccount2.class);

                if (registerAccount2 !=null ){
                    String username = registerAccount2.username;
                    String email = registerAccount2.email;


                    txt1.setText(username);
                    editText.setText(email);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(HeaderHomePage3333.this, "Something Error: Please Try Again Later!", Toast.LENGTH_SHORT).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HeaderHomePage3333.this,EditProfile.class);
                startActivity(intent);
            }
        });




    }


}