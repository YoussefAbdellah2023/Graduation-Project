package com.example.e_commercefuture.Revision;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeListView;
import com.example.e_commercefuture.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class AdapterRecyclerView extends FirebaseRecyclerAdapter<DataTypeListView, AdapterRecyclerView.PostHolder> {





    public  AdapterRecyclerView(@NonNull FirebaseRecyclerOptions<DataTypeListView> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull PostHolder holder, int position, @NonNull DataTypeListView model) {


            holder.name.setText(model.getName());
            Picasso.get().load(model.getImage()).into(holder.imageView);
            holder.description.setText(model.getDescription());
            holder.price.setText(model.getPrice());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppCompatActivity activity = (AppCompatActivity)v.getContext();


                }
            });
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
       return new AdapterRecyclerView.PostHolder(v);

//       return new PostHolder(v);
    }



//    private List<DataTypeListView> dataType = new ArrayList<>();
//
//    public void setDataType(List<DataTypeListView> dataType) {
//        this.dataType = dataType;
//        notifyDataSetChanged();
//    }




    public class PostHolder extends RecyclerView.ViewHolder {

        TextView name , price , description ;
        ImageView imageView;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            imageView =itemView.findViewById(R.id.image_recycler_view);
            name=itemView.findViewById(R.id.txt1_recycler_view);
            price=itemView.findViewById(R.id.txt2_recycler_view);
            description=itemView.findViewById(R.id.txt3_recycler_view);



        }


    }
}
