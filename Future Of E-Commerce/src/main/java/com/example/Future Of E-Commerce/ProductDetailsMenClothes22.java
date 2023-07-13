package com.example.e_commercefuture;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeItem;
import com.example.e_commercefuture.ReviewAndComment.UserReadReviewAndComments;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailsMenClothes22 extends AppCompatActivity {


    private Button addToCard;
    private FloatingActionButton floatingActionButton;
    private ImageView productImage;
    private TextView productPrice, productDescription, productName , textView1 ;
    private String price,description ;
    private String itemId = "", status = "Normal";
    private String image = "";
    private ProgressBar progressBar;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details_men_clothes22);

         itemId=getIntent().getStringExtra("itemId");
         image=getIntent().getStringExtra("image");

//        addToCard = (Button) findViewById(R.id.product_add_to_card_btn);
        productImage = (ImageView) findViewById(R.id.product_image_details);
        productPrice = (TextView) findViewById(R.id.product_price_details);
        productName = (TextView) findViewById(R.id.product_name_details);
        productDescription = (TextView) findViewById(R.id.product_description_details);
        floatingActionButton=findViewById(R.id.fab_product_details);
        textView1=findViewById(R.id.product_description_details1);
        progressBar=findViewById(R.id.product_page_rating_progress_bar);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ProductDetailsMenClothes22.this, UserReadReviewAndComments.class);

                startActivity(intent);
            }
        });

        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsMenClothes22.this,UserReadReviewAndComments.class);
                startActivity(intent);
            }
        });





        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (status.equals("Order Placed") || status.equals("Order Shipped"))
                {
                    Toast.makeText(ProductDetailsMenClothes22.this, "you can add purchase more items, once your order is shipped or confirmed.", Toast.LENGTH_LONG).show();
                }

                addingToCartList();

            }
        });



        getProductDetails(itemId);


//        addToCard.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//
//                if (status.equals("Order Placed") || status.equals("Order Shipped"))
//                {
//                    Toast.makeText(ProductDetailsMenClothes22.this, "you can add purchase more items, once your order is shipped or confirmed.", Toast.LENGTH_LONG).show();
//                }
//               addingToCartList();
//            }
//        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        //CheckOrderState();
    }


    private void addingToCartList() {

        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calForDate.getTime());


        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String, Object> cart = new HashMap<>();
        cart.put("itemId", itemId);
        cart.put("itemName", productName.getText().toString());
        cart.put("description", description);
        cart.put("image", image);

        cart.put("price", price);
        cart.put("date", saveCurrentDate);
        cart.put("time", saveCurrentTime);


        cartListRef.child("cartUsers").child( FirebaseAuth.getInstance().getCurrentUser().getUid() )
                .child("Items").child(itemId)
                .updateChildren(cart)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task)
                                           {

                                               if (task.isSuccessful())
                                               {
                                                   Toast.makeText(ProductDetailsMenClothes22.this, "Added to Cart List.", Toast.LENGTH_SHORT).show();

//                                                   Intent intent = new Intent(ProductDetailsMenClothes22.this, CartPage.class);
//                                                   startActivity(intent);
                                               }

                                           }
                                       }
                );
    }




    protected void getProductDetails(String key){

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Items");



        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.exists())
                {
                    DataTypeItem dataTypeItem= snapshot.getValue(DataTypeItem.class);

                    productName.setText(dataTypeItem.getItemName());
                    price =dataTypeItem.getPrice();
                    description = dataTypeItem.getDescription();
                    productPrice.setText( dataTypeItem.getPrice());
                    productDescription.setText(dataTypeItem.getDescription() + " " +dataTypeItem.getDetail());
                    Picasso.get().load(dataTypeItem.getImage()).into(productImage);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void CheckOrderState()
    {
        DatabaseReference ordersRef;
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        ordersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    String shippingState = dataSnapshot.child("status").getValue().toString();

                    if (shippingState.equals("shipped"))
                    {
                        status = "Order Shipped";
                    }
                    else if(shippingState.equals("not shipped"))
                    {
                        status = "Order Placed";
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.buttonshare,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){


            case R.id.app_bar_btn_share:

                Intent intent6 = new Intent(Intent.ACTION_SEND);
                intent6.setType("text/plain");
                String shareBody = "Your body here";
                String shareSub = "Your Subject here";
                intent6.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                intent6.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(intent6,"Sharing using"));
                return true;




        }
        return super.onOptionsItemSelected(item);
    }
}