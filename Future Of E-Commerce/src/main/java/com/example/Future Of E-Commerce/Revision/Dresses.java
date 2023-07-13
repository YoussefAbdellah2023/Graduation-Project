package com.example.e_commercefuture.Revision;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commercefuture.DataTypeOrModel.DataTypeListView;
import com.example.e_commercefuture.ItemClickListener;
import com.example.e_commercefuture.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Dresses extends Fragment  {



    ArrayList<DataTypeListView> dataType;
    TextView textView;

     ItemClickListener itemClickListener;

    RecyclerView recyclerView;
    AdapterRecyclerView adapterRecyclerView;
    RecyclerView.LayoutManager layoutManager;

    DatabaseReference databaseReference;

    public Dresses() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dresses, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_woman_clothes);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        //   databaseReference= FirebaseDatabase.getInstance().getReference().child("Add Stock Men Clothes");


        FirebaseRecyclerOptions<DataTypeListView> options = new FirebaseRecyclerOptions.Builder<DataTypeListView>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Add Stock Men Clothes"), DataTypeListView.class)
                .build();
        Log.d("dddd", "onCreateView: " + options);

        adapterRecyclerView = new AdapterRecyclerView(options);
        layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setAdapter(adapterRecyclerView);
        recyclerView.setLayoutManager(layoutManager);








        return v;
    }



    public void onStart() {

        super.onStart();
        adapterRecyclerView.startListening();
    }

    public void onStop() {

        super.onStop();
        adapterRecyclerView.stopListening();
    }




}

