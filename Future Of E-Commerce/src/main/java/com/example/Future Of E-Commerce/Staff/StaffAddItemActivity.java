package com.example.e_commercefuture.Staff;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class StaffAddItemActivity extends AppCompatActivity {

    private Spinner spinner;
    private String itemtype, description,detail, price, itemname, saveCurrentDate, saveCurrentTime;
    private Button saveBtn;
    private ImageView itemImage;
    private EditText editItemName, editDescription,editDetail ,editPrice;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String downloadImageUrl;
    private StorageReference itemImagesRef;
    private DatabaseReference itemsRef;
    private ProgressDialog loadingBar;
    private String itemid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_add_item);
        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spinner_item_category);
        //create a list of items for the spinner.
        final String[] items = new String[]{"Grocery", "Cloths and shoes", "Electronics"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);



        spinner = (Spinner) findViewById(R.id.spinner_item_category);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                itemtype =items[position];
                Toast.makeText(StaffAddItemActivity.this,itemtype, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(StaffAddItemActivity.this, "please select item type", Toast.LENGTH_SHORT).show();

            }

        });



        itemImagesRef = FirebaseStorage.getInstance().getReference().child("ItemImages");
        itemsRef = FirebaseDatabase.getInstance().getReference().child("Items");

        saveBtn = (Button) findViewById(R.id.save_item);
        itemImage = (ImageView) findViewById(R.id.chose_image);
        editItemName = (EditText) findViewById(R.id.item_name);
        editDescription = (EditText) findViewById(R.id.item_description);
        editDetail = (EditText) findViewById(R.id.item_detail);

        editPrice = (EditText) findViewById(R.id.item_price);
        loadingBar = new ProgressDialog(this);

        itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                OpenGallery();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ValidateItemInput();
            }
        });


    }

    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            itemImage.setImageURI(ImageUri);
        }
    }

    private void ValidateItemInput()
    {
        description = editDescription.getText().toString();
        price = editPrice.getText().toString();
        itemname = editItemName.getText().toString();
        detail = editDetail.getText().toString();


        if (ImageUri == null)
        {
            Toast.makeText(this, "Image required", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Description required", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(price))
        {
            Toast.makeText(this, "Price required", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(itemname))
        {
            Toast.makeText(this, "Item name required", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(detail))
        {
            Toast.makeText(this, "Item detail required", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreItemInfo();
        }

    }

    private void StoreItemInfo()
    {
        loadingBar.setTitle("Add new item");
        loadingBar.setMessage("saving...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM, dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        SimpleDateFormat dateid = new SimpleDateFormat("MMddyyyyHHmmssssss");
        itemid= String.valueOf(dateid.format(new Date()));
        itemid = itemid.replaceAll(", $", "");
        itemid = itemid.replaceAll(": $", "");


        final StorageReference filePath = itemImagesRef.child(ImageUri.getLastPathSegment() + itemid + ".jpg");
        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(StaffAddItemActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(StaffAddItemActivity.this, "Image uploaded!", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }
                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();
                            Toast.makeText(StaffAddItemActivity.this, "Image selected!", Toast.LENGTH_SHORT).show();
                            addItemToFirebase();
                        }
                    }
                });
            }
        });
    }

    private void addItemToFirebase()
    {
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("itemId", itemid);
        dataMap.put("itemName", itemname);
        dataMap.put("dateSaved", saveCurrentDate);
        dataMap.put("dateSaved", saveCurrentTime);
        dataMap.put("description", description);
        dataMap.put("detail", detail);
        dataMap.put("image", downloadImageUrl);
        dataMap.put("itemType", itemtype);
        dataMap.put("price", price);

        itemsRef.child(itemid).updateChildren(dataMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
//                            Intent intent = new Intent(StaffAddItemActivity.this, StaffHomePage2.class);
//                            startActivity(intent);
                            loadingBar.dismiss();
                            Toast.makeText(StaffAddItemActivity.this, "Item added!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(StaffAddItemActivity.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}