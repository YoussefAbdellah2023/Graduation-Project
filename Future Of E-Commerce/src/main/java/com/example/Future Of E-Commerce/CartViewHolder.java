package com.example.e_commercefuture;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtItemName, txtItemPrice, txtItemId;
    private ItemClickListener itemClickListener;
     ImageView imageView;
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        txtItemName = itemView.findViewById(R.id.cart_item_name);
        txtItemPrice = itemView.findViewById(R.id.cart_item_price);
        txtItemId=itemView.findViewById(R.id.orderid);
       imageView = itemView.findViewById(R.id.cart_item_image);
//        txtItemQuantity = itemView.findViewById(R.id.cart_item_quantity);
    }

    @Override
    public void onClick(View v)
    {

        itemClickListener.onClick(v, getAdapterPosition(), false);
    }
    public void setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }
}
