package com.example.e_commercefuture.ReviewAndComment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeReviewAndComments;
import com.example.e_commercefuture.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserReadReviewAndComments extends AppCompatActivity {


    DatabaseReference databaseReference;
    private FirebaseRecyclerOptions<DataTypeReviewAndComments> options;
    private FirebaseRecyclerAdapter<DataTypeReviewAndComments, ReviewAndCommentViewHolder> adapter;
    private RecyclerView recyclerView;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_read_review_and_comments);

        button=findViewById(R.id.btn_add_review_comment);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserReadReviewAndComments.this, ReviewAndComment.class);
                startActivity(intent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference().child("UsersReviewAndComments");
        recyclerView = findViewById(R.id.recycler_view_view_comments);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        options=new FirebaseRecyclerOptions.Builder<DataTypeReviewAndComments>().setQuery(databaseReference,DataTypeReviewAndComments.class).build();

        adapter=new FirebaseRecyclerAdapter<DataTypeReviewAndComments, ReviewAndCommentViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ReviewAndCommentViewHolder holder, int position, @NonNull DataTypeReviewAndComments model) {

                holder.textView1.setText("Review : " +model.getRating());
                holder.textView2.setText("Comment : " +model.getComment());

            }

            @NonNull
            @Override
            public ReviewAndCommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.reviewandcomments_layout,parent,false);



                return new ReviewAndCommentViewHolder(v);

            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }
}