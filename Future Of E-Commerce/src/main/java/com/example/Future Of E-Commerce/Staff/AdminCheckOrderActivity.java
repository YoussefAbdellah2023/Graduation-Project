package com.example.e_commercefuture.Staff;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeUserOrder;
import com.example.e_commercefuture.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AdminCheckOrderActivity extends AppCompatActivity {

     RecyclerView ordersList;
     DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_check_order);

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

        FirebaseRecyclerAdapter<DataTypeUserOrder, AdminOrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<DataTypeUserOrder, AdminOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminOrdersViewHolder holder, final int position, @NonNull final DataTypeUserOrder model)
                    {
                        holder.email.setText("Email: " + model.getEmail());
                        holder.totalPrice.setText("Total price: " + model.getTotalAmount());
                        holder.date.setText("order date =  " + model.getDate());
                        holder.time.setText("time: " + model.getTime() + "  " + model.getTime());
                        holder.productid.setText("Order ID:" +model.getProductid());
                        holder.status.setText("Status: " + model.getStatus());
                        holder.countryname.setText(model.getCountryname());
                        holder.locality.setText(model.getLocality());
                        holder.address.setText(model.getAddress());
                        Picasso.get().load(model.getImage()).into(holder.image);



                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                CharSequence options[] = new CharSequence[]
                                        {
                                                "Picked",
                                                "Shipped to location",
                                                "Arrived to location"
                                        };

                                AlertDialog.Builder builder = new AlertDialog.Builder(AdminCheckOrderActivity.this);
                                builder.setTitle("Order status");

                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        if (i == 0)
                                        {
                                            String uID = getRef(position).getKey();

                                            RemoverOrder(uID);
                                        }
                                        if(i== 1)
                                        {
                                            String uID = getRef(position).getKey();

                                            ordersRef.child(uID).child("status").setValue("Shipped to location");
                                        }
                                        else{
                                            String uID = getRef(position).getKey();

                                            ordersRef.child(uID).child("status").setValue("Arrived to location");
                                        }

                                    }

                                });
                                builder.show();
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
                        return new AdminOrdersViewHolder(view);
                    }
                };

        ordersList.setAdapter(adapter);
        adapter.startListening();
    }

    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder
    {
        public TextView email, totalPrice, date, time,status , productid , countryname , locality , address ;
        public ImageView image;
        public Button ShowOrdersBtn;


        public AdminOrdersViewHolder(View itemView)
        {
            super(itemView);


            email = itemView.findViewById(R.id.order_email);
            totalPrice = itemView.findViewById(R.id.order_total_price);
            date = itemView.findViewById(R.id.order_date);
            time = itemView.findViewById(R.id.order_time);
            productid=itemView.findViewById(R.id.order_id);
            status = itemView.findViewById(R.id.order_satus);
            image=itemView.findViewById(R.id.order_image);
            countryname=itemView.findViewById(R.id.order_country_name);
            locality=itemView.findViewById(R.id.order_locality);
            address=itemView.findViewById(R.id.order_address);

        }
    }




    private void RemoverOrder(String uID)
    {
        ordersRef.removeValue();
    }

}