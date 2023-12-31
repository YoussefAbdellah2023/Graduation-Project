package com.example.e_commercefuture;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeCart;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CartPage extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private Button NextProcessBtn;
    private TextView txtTotalAmount, txtMsg1;

     private String totalPrice ,  itemID , image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        NextProcessBtn = (Button) findViewById(R.id.next_btn);
        txtTotalAmount = (TextView) findViewById(R.id.total_price);
        txtMsg1 = (TextView) findViewById(R.id.msg1);


        NextProcessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //ConfirmFinalorderActivity
                Intent intent = new Intent(CartPage.this, CurrentLocation2.class);
                intent.putExtra("Total Price", String.valueOf(totalPrice));
                intent.putExtra("product Id", String.valueOf(itemID));
                intent.putExtra("Image", String.valueOf(image));
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    protected void onStart()
    {
        super.onStart();

        //CheckOrderState();


        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<DataTypeCart> options =
                new FirebaseRecyclerOptions.Builder<DataTypeCart>()
                        .setQuery(cartListRef.child("cartUsers")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("Items"), DataTypeCart.class)
                        .build();

        FirebaseRecyclerAdapter<DataTypeCart, CartViewHolder> adapter
                = new FirebaseRecyclerAdapter<DataTypeCart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final DataTypeCart model)
            {

                holder.txtItemPrice.setText("Product Price : " + model.getPrice()  );
                holder.txtItemName.setText("Product Name :" +model.getItemName() +": " + model.getDescription() );




               totalPrice = model.getPrice() ;
                 itemID = model.getItemId();
                 image=model.getImage();


                txtTotalAmount.setText("Total Price = " + String.valueOf(totalPrice));
                holder.txtItemId.setText("Product ID : " +String.valueOf(itemID));
                Picasso.get().load(model.getImage()).into(holder.imageView);



                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        CharSequence options[] = new CharSequence[]
                                {
                                        "Edit",
                                        "Delete"
                                };
                        AlertDialog.Builder builder = new AlertDialog.Builder(CartPage.this);
                        builder.setTitle("Manage Item");

                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (i == 0)
                                {
                                    Intent intent = new Intent(CartPage.this, ProductDetailsMenClothes22.class);
                                    intent.putExtra("itemId", model.getItemId());
                                    startActivity(intent);
                                }
                                if (i == 1)
                                {
                                    cartListRef.child("cartUsers")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .child("Items")
                                            .child(model.getItemId())
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task)
                                                {
                                                    if (task.isSuccessful())
                                                    {
                                                        Toast.makeText(CartPage.this, "Item removed successfully.", Toast.LENGTH_SHORT).show();

                                                        Intent intent = new Intent(CartPage.this, HomePage2.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            });
                                }
                            }
                        });
                        builder.show();
                    }
                });
//                DatabaseReference ordersRef;
//                ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//
//                ordersRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot)
//                    {
//                        if (dataSnapshot.exists())
//                        {
//                            cartListRef.child("cartUsers")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .child("Items")
//                                    .child(model.getItemId())
//                                    .removeValue()
//                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task)
//                                        {
//                                            if (task.isSuccessful())
//                                            {
//                                                Toast.makeText(CartPage.this, "Item removed successfully.", Toast.LENGTH_SHORT).show();
//
//                                                Intent intent = new Intent(CartPage.this, HomePage2.class);
//                                                startActivity(intent);
//                                            }
//                                        }
//                                    });
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//
//                    }
//                });

            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart, parent, false);
                CartViewHolder holder = new CartViewHolder(view);
                return holder;
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
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
                    String userName = dataSnapshot.child("email").getValue().toString();

                    if (shippingState.equals("shipped"))
                    {
                        txtTotalAmount.setText( userName + "\n order is shipped successfully.");
                        recyclerView.setVisibility(View.GONE);

                        txtMsg1.setVisibility(View.VISIBLE);
                        txtMsg1.setText("Congratulations, your final order has been Shipped successfully.");
                        NextProcessBtn.setVisibility(View.VISIBLE);
                        NextProcessBtn.setText("OK");
                        NextProcessBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                Intent intent = new Intent(CartPage.this, HomePage2.class);

                                startActivity(intent);
                                finish();
                            }
                        });

                        Toast.makeText(CartPage.this, "you can purchase more Items, once you received your first final order.", Toast.LENGTH_SHORT).show();
                    }
                    else if(shippingState.equals("not shipped"))
                    {
                        txtTotalAmount.setText("Status = Not Shipped");
                        recyclerView.setVisibility(View.GONE);

                        txtMsg1.setVisibility(View.VISIBLE);
                        NextProcessBtn.setVisibility(View.VISIBLE);
                        NextProcessBtn.setText("OK");
                        NextProcessBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                Intent intent = new Intent(CartPage.this, HomePage2.class);

                                startActivity(intent);
                                finish();
                            }
                        });


                        Toast.makeText(CartPage.this, "you can purchase more Items, once you received your first final order.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}