package com.example.e_commercefuture;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CurrentLocation2 extends AppCompatActivity {

    Button btnLocation , btn;
    TextView textView1, textView2, textView3, textView4, textView5;
    FusedLocationProviderClient fusedLocationProviderClient;

    private String totalAmount = "";
    private String productid = "";
    private String image = "";
    String pay="Cash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location2);


        totalAmount = getIntent().getStringExtra("Total Price");
        productid = getIntent().getStringExtra("product Id");
        image = getIntent().getStringExtra("Image");



        btn=findViewById(R.id.btn_next_location);
        btnLocation = findViewById(R.id.btn_location);
        textView1 = findViewById(R.id.text_view_location1);
        textView2 = findViewById(R.id.text_view_location2);
        textView3 = findViewById(R.id.text_view_location3);
        textView4 = findViewById(R.id.text_view_location4);
        textView5 = findViewById(R.id.text_view_location5);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
                if (ActivityCompat.checkSelfPermission(CurrentLocation2.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                } else {
                    ActivityCompat.requestPermissions(CurrentLocation2.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConfirmOrder();
                Intent intent = new Intent(CurrentLocation2.this, Pay.class);

                intent.putExtra("productId2", String.valueOf(productid));
                intent.putExtra("Image2", String.valueOf(image));

                startActivity(intent);
            


            }
        });
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {

                Location location = task.getResult();
                if (location != null) {

                    try {
                        Geocoder geocoder = new Geocoder(CurrentLocation2.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        textView1.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Latitude :</b><br></font> "
                                        + addresses.get(0).getLatitude()
                        ));

                        textView2.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Longitude :</b><br></font> "
                                        + addresses.get(0).getLongitude()
                        ));

                        textView3.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Country Name :</b><br></font> "
                                        + addresses.get(0).getCountryName()
                        ));

                        textView4.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Locality :</b><br></font> "
                                        + addresses.get(0).getLocality()
                        ));

                        textView5.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Address :</b><br></font> "
                                        + addresses.get(0).getAddressLine(0)
                        ));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }
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
        ordersMap.put("countryname",textView3.getText().toString());
        ordersMap.put("locality",textView4.getText().toString());
        ordersMap.put("address",textView5.getText().toString());
        ordersMap.put("ThePayWay",pay);



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
                                        Toast.makeText(CurrentLocation2.this, "your final order has been placed successfully.", Toast.LENGTH_SHORT).show();

//                                        Intent intent = new Intent(CurrentLocation2.this, Pay.class);
//                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                        startActivity(intent);
//                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }

}