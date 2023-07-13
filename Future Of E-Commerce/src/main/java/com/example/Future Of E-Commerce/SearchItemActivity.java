package com.example.e_commercefuture;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class SearchItemActivity extends AppCompatActivity {

    private Button SearchBtn;
    private EditText inputText;
    private RecyclerView searchList;
    private String SearchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);


        inputText = findViewById(R.id.search_product_name);
        SearchBtn = findViewById(R.id.search_btn);
        searchList = findViewById(R.id.search_list);
        searchList.setLayoutManager(new LinearLayoutManager(SearchItemActivity.this));

        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                SearchInput = inputText.getText().toString();

                onStart();
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Items");

        FirebaseRecyclerOptions<DataTypeItem> options =
                new FirebaseRecyclerOptions.Builder<DataTypeItem>()
                        .setQuery(reference.orderByChild("itemName").startAt(SearchInput), DataTypeItem.class)
                        .build();

        FirebaseRecyclerAdapter<DataTypeItem, MyViewHolder> adapter =
                new FirebaseRecyclerAdapter<DataTypeItem, MyViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull final DataTypeItem model)
                    {
                        holder.txt1.setText(model.getItemName());
                        holder.txt2.setText(model.getDescription());
                        holder.txt3.setText("Price = " + model.getPrice() + "$");
                        Picasso.get().load(model.getImage()).into(holder.image1);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                Intent intent = new Intent(SearchItemActivity.this, ProductDetailsMenClothes22.class);
                                intent.putExtra("itemId", model.getItemId());
                                startActivity(intent);
                            }
                        });
                    }

                    @NonNull
                    @Override
                    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
                        MyViewHolder holder = new MyViewHolder(view);
                        return holder;
                    }
                };

        searchList.setAdapter(adapter);
        adapter.startListening();
    }


}