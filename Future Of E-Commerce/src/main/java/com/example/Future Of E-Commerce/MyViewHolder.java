package com.example.e_commercefuture;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyViewHolder extends RecyclerView.ViewHolder {

  TextView txt1,txt2,txt3;
  ImageView image1;
  View view;

  FloatingActionButton floatingActionButton;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);


        txt1=itemView.findViewById(R.id.txt1_recycler_view);
        txt2=itemView.findViewById(R.id.txt2_recycler_view);
        txt3=itemView.findViewById(R.id.txt3_recycler_view);
       image1=itemView.findViewById(R.id.image_recycler_view);
//       floatingActionButton=itemView.findViewById(R.id.fab);

       view=itemView;

    }






}
