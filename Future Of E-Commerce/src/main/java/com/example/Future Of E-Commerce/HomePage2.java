package com.example.e_commercefuture;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.e_commercefuture.RegisterAndLogin.LogInPage2;
import com.example.e_commercefuture.Revision.FragmentActivityWomanClothes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePage2 extends AppCompatActivity {

    ImageSlider mainslider;

    ImageButton btn1 , btn2;

    RecyclerView categoriesRecyclerView;

    FloatingActionButton floatingActionButton;
    private com.example.e_commercefuture.CategoriesAdapter mCategoryAdapter;
     HomePage2 mFragBindings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);



//        categoriesRecyclerView=findViewById(R.id.categoriesRecyclerView);
//
//        setCategoryAdapter();

        btn1=findViewById(R.id.image_men_clothing);
        btn2=findViewById(R.id.image_woman_clothing);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage2.this, com.example.e_commercefuture.MainActivityMenClothes.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage2.this, FragmentActivityWomanClothes.class);
                startActivity(intent);
            }
        });

        mainslider=(ImageSlider)findViewById(R.id.image_slider1);

        final List<SlideModel> remoteimages=new ArrayList<>();

        FirebaseDatabase.getInstance().getReference().child("Image Slider")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot data:snapshot.getChildren()){
                            Log.d("ttttttt", "onDataChange: "+data);
                            remoteimages.add(new SlideModel(data.child("image").getValue().toString(),data.child("price").getValue().toString(), ScaleTypes.FIT));

                           mainslider.setImageList(remoteimages,ScaleTypes.FIT);




                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



    }

    private void setCategoryAdapter() {
        ArrayList<String> categoryNames = new ArrayList<>();
        categoryNames.add("Electronics");
        categoryNames.add("Fashion");
        categoryNames.add("Shoes");
        categoryNames.add("Kids");
        categoryNames.add("Living");


     LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        mCategoryAdapter = new com.example.e_commercefuture.CategoriesAdapter(getApplicationContext(),categoryNames);

        mFragBindings.categoriesRecyclerView.setLayoutManager(manager);
        mFragBindings.categoriesRecyclerView.setAdapter(mCategoryAdapter);


    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuitems,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home_menu:
                Intent intent = new Intent(HomePage2.this,HomePage2.class);
                startActivity(intent);

                return true;

            case R.id.profile:
                Intent intent1=new Intent(HomePage2.this, com.example.e_commercefuture.UserProfile.class);
                startActivity(intent1);

                return true;
            case R.id.logout:
                Intent intent2 = new Intent(HomePage2.this, LogInPage2.class);
                startActivity(intent2);
                finish();
                return true;
            case R.id.basket:
                Intent intent3 = new Intent(HomePage2.this, com.example.e_commercefuture.CartPage.class);
                startActivity(intent3);

                return true;

            case R.id.Order:
                Intent intent4 = new Intent(HomePage2.this, com.example.e_commercefuture.UserOrderActivity.class);
                startActivity(intent4);

                return true;

            case R.id.app_bar_search:
                Intent intent5 = new Intent(HomePage2.this, com.example.e_commercefuture.SearchItemActivity.class);
                startActivity(intent5);

                return true;


            case R.id.chatbot:

                Intent intent6 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));

                startActivity(intent6);
                return true;




        }
        return super.onOptionsItemSelected(item);
    }

}