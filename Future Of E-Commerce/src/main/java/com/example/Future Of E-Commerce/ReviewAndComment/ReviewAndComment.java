package com.example.e_commercefuture.ReviewAndComment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeReviewAndComments;
import com.example.e_commercefuture.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewAndComment extends AppCompatActivity {

    Button gh, btn;
    EditText txt;
    RatingBar rt;
    TextView txt1;
    TextView txt2;





    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_and_comment);

        intialization();

        gh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                String rat = String.valueOf(rt.getRating());
                String commnt = String.valueOf(txt.getText());
                txt1.setText(rat);
                txt2.setText(commnt);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReviewAndComment();
            }
        });


    }


    void intialization() {
        gh =  findViewById(R.id.button);
        txt =  findViewById(R.id.editTextTextPersonName);
        rt =  findViewById(R.id.ratingBar);
        txt1 =  findViewById(R.id.textView2);
        txt2 =  findViewById(R.id.textView3);
        btn = findViewById(R.id.btn_review_comment);
    }


    void submitReviewAndComment() {

        String rating = String.valueOf(rt.getRating());
        String comment = txt.getText().toString();



        if (comment.isEmpty()) {
            txt.setError("Comment Is Required!");
            txt.requestFocus();
            return;
        }
            DataTypeReviewAndComments reviewAndComments = new DataTypeReviewAndComments(rating, comment);

            FirebaseDatabase.getInstance().getReference("UsersReviewAndComments")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(reviewAndComments).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ReviewAndComment.this, "Submit Successfully", Toast.LENGTH_SHORT).show();


                    }
                    else {
                        Toast.makeText(ReviewAndComment.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }


    }





