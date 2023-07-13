package com.example.e_commercefuture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeUserOrder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class UserOrderActivity extends AppCompatActivity {

    private RecyclerView ordersList;
    private DatabaseReference ordersRef , user;


    String productID = "" ;
    String image = "" ;
    String pay= "Cash";

    String textview3 = "" ;
    String textview4 = "" ;
    String textview5 = "" ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);

        productID = getIntent().getStringExtra("productId2");
        image = getIntent().getStringExtra("Image2");



        textview3=getIntent().getStringExtra("textView3");
        textview4=getIntent().getStringExtra("textView4");
        textview5=getIntent().getStringExtra("textView5");


        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child("OrderUsers");






        ordersList = findViewById(R.id.orders_list);
        ordersList.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    protected void onStart()
    {
        super.onStart();


        FirebaseRecyclerOptions<DataTypeUserOrder> options =
                new FirebaseRecyclerOptions.Builder<DataTypeUserOrder>()
                        .setQuery(ordersRef, DataTypeUserOrder.class)
                        .build();

        FirebaseRecyclerAdapter<DataTypeUserOrder, UserOrderActivity.UserOrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<DataTypeUserOrder, UserOrderActivity.UserOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull UserOrderActivity.UserOrdersViewHolder holder, final int position, @NonNull final DataTypeUserOrder model)
                    {
                        if(model.getEmail().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail()) ) {
                            holder.email.setText("Email: " + model.getEmail());
                            holder.totalPrice.setText("Total price: "  + model.getTotalAmount());
                            holder.date.setText("Order Date:" + model.getDate());
                            holder.time.setText("Time: " + model.getTime());
                            holder.status.setText("Status: " + model.getStatus());
                            holder.productId.setText("Product ID :"+model.getProductid());
                            holder.countryname.setText( model.getCountryname());
                            holder.locality.setText( model.getLocality());
                            holder.address.setText(model.getAddress());
                            holder.pay.setText("The Pay Way : " +String.valueOf(pay));
                            Picasso.get().load(model.getImage()).into(holder.image);


                        }

                    }

                    @NonNull
                    @Override
                    public UserOrderActivity.UserOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
                        return new UserOrderActivity.UserOrdersViewHolder(view);
                    }
                };

        ordersList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class UserOrdersViewHolder extends RecyclerView.ViewHolder
    {
        public TextView email, totalPrice, date, time,status , productId , countryname , locality , address , pay ;
        public ImageView image;


        public UserOrdersViewHolder(View itemView)
        {
            super(itemView);


            email = itemView.findViewById(R.id.order_email);
            totalPrice = itemView.findViewById(R.id.order_total_price);
            date = itemView.findViewById(R.id.order_date);
            time = itemView.findViewById(R.id.order_time);
            status = itemView.findViewById(R.id.order_satus);
            productId=itemView.findViewById(R.id.order_id);
            image=itemView.findViewById(R.id.order_image);
            countryname=itemView.findViewById(R.id.order_country_name);
            locality=itemView.findViewById(R.id.order_locality);
            address=itemView.findViewById(R.id.order_address);
            pay=itemView.findViewById(R.id.order_pay);
//            username=itemView.findViewById(R.id.order_username);
        }
    }

}