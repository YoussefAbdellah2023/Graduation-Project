package com.example.e_commercefuture.Revision;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.e_commercefuture.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivityUserDataUpload extends AppCompatActivity {

    EditText name , phone;
    ImageView image;
    Button btn;

    final int IMAGE_REQUEST_CODE=1;
    Uri imageUri;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    ConstraintLayout constraintLayout;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user_data_upload);

        initialization();
        initializationFirebaseTool();

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getImageFromGallery();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                UserUploadData();


//                Intent intent = new Intent (MainActivityUserDataUpload.this,MainActivityPresentYourDataFireBase.class);
//                startActivity(intent);
            }
        });
    }

//    Explicit Intent ----> Transfer From Activity To Other Activity
//    Implicit Intent ----> Transfer From Activity To Gallery , Contact , Camera...,..etc


    protected void initializationFirebaseTool(){
        storageReference = FirebaseStorage.getInstance().getReference("Images");
        databaseReference= FirebaseDatabase.getInstance().getReference();
    }


    protected void getImageFromGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");     //  (*)   Means ----> Get All Images Resources Weather PNG,JPG,...,etc.
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent,IMAGE_REQUEST_CODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data !=null){
            if (requestCode==IMAGE_REQUEST_CODE){
                imageUri=data.getData();
                image.setImageURI(imageUri);
            }
        }
    }


//
//    protected void UserUploadData(){
//        String username=name.getText().toString().trim();
//         String userphone=phone.getText().toString().trim();
//
//        if (username.isEmpty() || userphone.isEmpty() || imageUri==null){
//            Toast.makeText(this, "Your Data Is Empty", Toast.LENGTH_SHORT).show();
//        }
//
//        else {
//
//            progressBar.setVisibility(View.VISIBLE);
//            constraintLayout.setVisibility(View.GONE);
//
//                final StorageReference reference = storageReference.child(System.currentTimeMillis()+"image");
//                reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                String imageUrl=uri.toString();
//
//                                String id = databaseReference.push().getKey();
//                                databaseReference.child("Contacts").child(id).setValue(new ListViewDataType(username,userphone,id,imageUrl));
//
//
//                                progressBar.setVisibility(View.GONE);
//                                Toast.makeText(MainActivityUserDataUpload.this, "Your Data Upload", Toast.LENGTH_SHORT).show();
//                                constraintLayout.setVisibility(View.VISIBLE);
//                            }
//                        });
//                    }
//                });
//
//        }
//    }



    protected void initialization(){
        image=findViewById(R.id.image_user_upload);
        name=findViewById(R.id.user_name_upload);
        phone=findViewById(R.id.user_phone_upload);
        btn=findViewById(R.id.btn_user_upload);
        constraintLayout=findViewById(R.id.parent_layout);
        progressBar=findViewById(R.id.prog_bar);

    }
}