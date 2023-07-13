package com.example.e_commercefuture.Staff;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeImageSlider;
import com.example.e_commercefuture.HomePage2;
import com.example.e_commercefuture.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddStockImageSlider extends AppCompatActivity {

    EditText txt1;
    Button btn1 , btn2;
    ImageView imageView;
    TextView textView;

    final int IMAGE_REQUEST_CODE = 1;
    Uri imageUri;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    ConstraintLayout constraintLayout;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock_image_slider);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference= FirebaseStorage.getInstance().getReference("Image Slider");

        initialization();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromGallery();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImageSlider();

                Intent intent = new Intent(AddStockImageSlider.this, HomePage2.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletData();
            }
        });






    }


    void initialization(){
        textView=findViewById(R.id.text_view_add_stock_image_slider);
        txt1=findViewById(R.id.edit_text_add_stock_image_slider);

        btn1=findViewById(R.id.btn_upload_add_stock_image_slider);
        btn2=findViewById(R.id.btn_delet_data_image_slider);

        imageView=findViewById(R.id.add_image_slider2);

        constraintLayout = findViewById(R.id.parent_layout_add_stock_image_slider);
        progressBar = findViewById(R.id.prog_bar_add_stock_image_slider);
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


    protected void UploadImageSlider(){
        String price = txt1.getText().toString();

        if (price==null || imageUri==null){
            Toast.makeText(this, "Your Data Is Empty", Toast.LENGTH_SHORT).show();
        }else {


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
                            databaseReference.child("Image Slider").child(id).setValue(new DataTypeImageSlider(imageUrl, price, id));


                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(AddStockImageSlider.this, "Your Data Is Upload", Toast.LENGTH_SHORT).show();
                            constraintLayout.setVisibility(View.VISIBLE);


                        }
                    });
                }
            });
        }
    }

    protected void DeletData(){
        databaseReference.child("Image Slider").setValue(null);
        Toast.makeText(this, "Your Data Is Empty", Toast.LENGTH_SHORT).show();
    }


}