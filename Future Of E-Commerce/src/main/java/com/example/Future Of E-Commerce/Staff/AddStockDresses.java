package com.example.e_commercefuture.Staff;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeListView;
import com.example.e_commercefuture.R;
import com.example.e_commercefuture.Revision.Dresses;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddStockDresses extends AppCompatActivity {

    EditText txt1, txt2, txt3 ;
    Button btn ;
    ImageView imageView;

    final int IMAGE_REQUEST_CODE = 1;
    Uri imageUri;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    ConstraintLayout constraintLayout;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_dresses);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference("Images Add Stock Dresses");

        initialization();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromGallery();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUploadData();

                Intent intent = new Intent(AddStockDresses.this, Dresses.class);
                startActivity(intent);

            }
        });
    }

    void initialization() {
        txt1 = findViewById(R.id.edit_text1_add_stock_dresses);
        txt2 = findViewById(R.id.edit_text2_add_stock_dresses);
        txt3 = findViewById(R.id.edit_text3_add_stock_dresses);

        btn = findViewById(R.id.btn_add_stock_dresses);

        imageView=findViewById(R.id.image_view_add_stock_dresses);

        constraintLayout = findViewById(R.id.parent_layout_add_stock_dresses);
        progressBar = findViewById(R.id.prog_bar_add_stock_dresses);
    }

    protected void getImageFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");     //  (*)   Means ----> Get All Images Resources Weather PNG,JPG,...,etc.
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, IMAGE_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == IMAGE_REQUEST_CODE) {
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
            }
        }
    }

    protected void UserUploadData() {


        String name = txt1.getText().toString();
        String description = txt2.getText().toString();
        String price = txt3.getText().toString();

        if (name.isEmpty() || description.isEmpty() || price.isEmpty() || imageUri == null) {
            Toast.makeText(this, "Your Data Is Empty", Toast.LENGTH_SHORT).show();
        } else {

            progressBar.setVisibility(View.VISIBLE);
            constraintLayout.setVisibility(View.GONE);

            final StorageReference reference = storageReference.child(System.currentTimeMillis() + "image");
            reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String imageUrl = uri.toString();


                            String id = databaseReference.push().getKey();
                            databaseReference.child("Add Stock Dresses").child(id).setValue(new DataTypeListView(name, description, price, imageUrl, id));

                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(AddStockDresses.this, "Your Data Is Upload", Toast.LENGTH_SHORT).show();
                            constraintLayout.setVisibility(View.VISIBLE);

                        }
                    });
                }
            });

        }
    }
}