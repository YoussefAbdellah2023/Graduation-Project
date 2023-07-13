package com.example.e_commercefuture;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeItem;
import com.example.e_commercefuture.DataTypeOrModel.DataTypeListView;
import com.example.e_commercefuture.RegisterAndLogin.LogInPage2;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivityMenClothes extends AppCompatActivity  {

    List<DataTypeListView> dataType ;


    RecyclerView.LayoutManager layoutManager;




   DatabaseReference databaseReference;
   private FirebaseRecyclerOptions<DataTypeItem>options;
   private FirebaseRecyclerAdapter<DataTypeItem, com.example.e_commercefuture.MyViewHolder>adapter;
   private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_men_clothes);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Items");
        recyclerView = findViewById(R.id.recycler_view_men_clothes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


       options=new FirebaseRecyclerOptions.Builder<DataTypeItem>().setQuery(databaseReference,DataTypeItem.class).build();
       adapter= new FirebaseRecyclerAdapter<DataTypeItem, com.example.e_commercefuture.MyViewHolder>(options) {
           @Override
           protected void onBindViewHolder(@NonNull com.example.e_commercefuture.MyViewHolder holder, int position, @NonNull DataTypeItem model) {




               holder.view.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      Intent intent = new Intent(getApplicationContext(), com.example.e_commercefuture.ProductDetailsMenClothes22.class);

                      intent.putExtra("itemId",model.getItemId());
                      intent.putExtra("image",model.getImage());

                      startActivity(intent);
                  }
              });

//               holder.floatingActionButton.setOnClickListener(new View.OnClickListener() {
//                   @Override
//                   public void onClick(View v) {
//                       Intent intent = new Intent(getApplicationContext(),CartPage.class);
//                       startActivity(intent);
//                   }
//               });


               holder.txt1.setText(model.getItemName());
               holder.txt2.setText(model.getDescription());
               holder.txt3.setText( model.getPrice());
               Picasso.get().load(model.getImage()).into(holder.image1);





           }

           @NonNull
           @Override
           public com.example.e_commercefuture.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

           View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);



               return new com.example.e_commercefuture.MyViewHolder(v);



           }
       };

      adapter.startListening();
      recyclerView.setAdapter(adapter);
        layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);







    }


    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuitems,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home_menu:
                Intent intent = new Intent(MainActivityMenClothes.this, com.example.e_commercefuture.HomePage2.class);
                startActivity(intent);

                return true;

            case R.id.profile:
                Intent intent1=new Intent(MainActivityMenClothes.this, com.example.e_commercefuture.UserProfile.class);
                startActivity(intent1);

                return true;
            case R.id.logout:
                Intent intent2 = new Intent(MainActivityMenClothes.this, LogInPage2.class);
                startActivity(intent2);
                finish();
                return true;
            case R.id.basket:
                Intent intent3 = new Intent(MainActivityMenClothes.this, com.example.e_commercefuture.CartPage.class);
                startActivity(intent3);

                return true;

            case R.id.Order:
                Intent intent4 = new Intent(MainActivityMenClothes.this, com.example.e_commercefuture.UserOrderActivity.class);
                startActivity(intent4);

                return true;

            case R.id.app_bar_search:
                Intent intent5 = new Intent(MainActivityMenClothes.this, com.example.e_commercefuture.SearchItemActivity.class);
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