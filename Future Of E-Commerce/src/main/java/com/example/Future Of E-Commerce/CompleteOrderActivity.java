package com.example.e_commercefuture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeUserOrder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class CompleteOrderActivity extends AppCompatActivity {

    private Spinner spinner;
    private Button confirmOrderBtn;

    private String totalAmount = "",itemtype;
    private String productid = "";
    private String image = "";


    DataTypeUserOrder dataTypeUserOrder;


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);

            totalAmount = getIntent().getStringExtra("Total Price");
            productid = getIntent().getStringExtra("product Id");
            image = getIntent().getStringExtra("Image");
            Toast.makeText(this, "Total Price =   " + totalAmount, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "Product ID =   " + productID, Toast.LENGTH_SHORT).show();


            confirmOrderBtn = (Button) findViewById(R.id.confirm_final_order_btn);

            //get the spinner from the xml.
            Spinner dropdown = findViewById(R.id.spinner_pickup_loc);
            //create a list of items for the spinner.
            final String[] items = new String[]{"Cairo", "Suez", "Ismailia","Menoufia"};
            //create an adapter to describe how the items are displayed, adapters are used in several places in android.
            //There are multiple variations of this, but this is the basic variant.
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
            //set the spinners adapter to the previously created one.
            dropdown.setAdapter(adapter);



            spinner = (Spinner) findViewById(R.id.spinner_pickup_loc);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    itemtype =items[position];
                    Toast.makeText(CompleteOrderActivity.this,itemtype, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    Toast.makeText(CompleteOrderActivity.this, "please select item type", Toast.LENGTH_SHORT).show();

                }

            });




            confirmOrderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    Intent intent = new Intent(CompleteOrderActivity.this,UserOrderActivity.class);
                    intent.putExtra("productId2", String.valueOf(productid));
                    intent.putExtra("Image2", String.valueOf(image));
                    startActivity(intent);
                    finish();
                    ConfirmOrder();
                    Toast.makeText(CompleteOrderActivity.this, ""+productid, Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void ConfirmOrder()
    {
        final String saveCurrentDate, saveCurrentTime;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference()
                .child("Orders")
                .child("OrderUsers")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        HashMap<String, Object> ordersMap = new HashMap<>();
        ordersMap.put("email",FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());
        ordersMap.put("totalAmount", totalAmount);
        ordersMap.put("date", saveCurrentDate);
        ordersMap.put("time", saveCurrentTime);
        ordersMap.put("status", "not shipped");
        ordersMap.put("productid", productid);
        ordersMap.put("image", image);



        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if (task.isSuccessful())
                {
                    FirebaseDatabase.getInstance().getReference()
                            .child("Cart List")
                            .child("CartUsers")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(CompleteOrderActivity.this, "your final order has been placed successfully.", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(CompleteOrderActivity.this, HomePage2.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }
}