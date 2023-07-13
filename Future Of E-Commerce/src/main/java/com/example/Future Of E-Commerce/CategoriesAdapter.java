package com.example.e_commercefuture;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_commercefuture.Revision.Dresses;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {



    private Context mContext;
    private ArrayList<String> mCategoryList;
    private Integer [] images = {R.drawable.imgsplash,R.drawable.t_shirts_sport,R.drawable.shirt,R.drawable.imgsplash,R.drawable.shirt};

    public CategoriesAdapter(Context mContext, ArrayList<String> mCategoryList) {
        this.mContext = mContext;
        this.mCategoryList = mCategoryList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_single_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int imgPos = images[position];

        holder.categoryTitle.setText(mCategoryList.get(position));
        Glide.with(mContext).load(imgPos).into(holder.categoryImage);
        holder.categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0){
                    mContext.startActivity(new Intent(mContext, MainActivityMenClothes.class));
                }else if(position == 1) {
                    mContext.startActivity(new Intent(mContext, Dresses.class));
                }
//                }else if(position == 2){
//                    mContext.startActivity(new Intent(mContext, ShoesActivity.class));
//                }else if(position == 3){
//                    mContext.startActivity(new Intent(mContext, KidsActivity.class));
//                }else{
//                    mContext.startActivity(new Intent(mContext, LivingActivity.class));
//                }
            }
        });

    }



    @Override
    public int getItemCount() {
        return mCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTitle;
        private ImageView categoryImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.categoryNameTv);
            categoryImage = itemView.findViewById(R.id.categoryImageIv);

        }

    }
        }