package com.example.e_commercefuture.Staff;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeListView;
import com.example.e_commercefuture.MainActivityMenClothes;
import com.example.e_commercefuture.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddStock extends AppCompatActivity {

    EditText txt1, txt2, txt3 ,txt4;
    Button btn , btn2 , btn3;
    ImageView btnimage ;





    final int IMAGE_REQUEST_CODE = 1;
    Uri imageUri;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    ConstraintLayout constraintLayout;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_stock);


        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference = FirebaseStorage.getInstance().getReference("Images Men Clothes");
        initialization();



        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromGallery();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUploadData();

                Intent intent = new Intent(AddStock.this, MainActivityMenClothes.class);
                startActivity(intent);


            }
        });





    }


    void initialization() {
        txt1 = findViewById(R.id.add_stock_name_upload);
        txt2 = findViewById(R.id.add_stock_description_upload);
        txt3 = findViewById(R.id.add_stock_price_upload);


        btn = findViewById(R.id.btn_add_stock_upload);
        btn3=findViewById(R.id.btn_delet_data_image_slider);
        btnimage = findViewById(R.id.add_Stock_image_upload);

        constraintLayout = findViewById(R.id.parent_layout);
        progressBar = findViewById(R.id.prog_bar);
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
                btnimage.setImageURI(imageUri);
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
                            databaseReference.child("Add Stock Men Clothes").child(id).setValue(new DataTypeListView(name, description, price,imageUrl, id));


                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(AddStock.this, "Your Data Is Upload", Toast.LENGTH_SHORT).show();
                            constraintLayout.setVisibility(View.VISIBLE);

                        }
                    });
                }
            });

        }
    }



    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuitemsaddstock,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_stock_dresses_woman:
                Intent intent = new Intent(this, AddStockDresses.class);
                startActivity(intent);
                break;
           

            case R.id.add_stock_image_slider:
                Intent intent5 = new Intent(this, AddStockImageSlider.class);
                startActivity(intent5);
                break;




        }
        return super.onOptionsItemSelected(item);
    }
}





