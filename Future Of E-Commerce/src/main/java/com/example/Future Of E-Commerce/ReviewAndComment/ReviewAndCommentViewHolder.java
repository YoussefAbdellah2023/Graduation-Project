package com.example.e_commercefuture.ReviewAndComment;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercefuture.R;

public class ReviewAndCommentViewHolder extends RecyclerView.ViewHolder {

    TextView textView1 ,textView2 ,textView3;

    View view;
    public ReviewAndCommentViewHolder(@NonNull View itemView) {
        super(itemView);

        textView1=itemView.findViewById(R.id.review);
        textView2=itemView.findViewById(R.id.comment);
        textView3=itemView.findViewById(R.id.text_view_review_comments);





        view=itemView;


    }
}
