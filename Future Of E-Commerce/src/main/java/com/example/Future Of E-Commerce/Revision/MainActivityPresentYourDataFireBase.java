package com.example.e_commercefuture.Revision;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivityPresentYourDataFireBase extends AppCompatActivity {


    DatabaseReference reference;
    FirebaseAuth firebaseAuth;

    EditText searchname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_present_your_data_fire_base);



        reference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth=FirebaseAuth.getInstance();

       searchname=findViewById(R.id.search_name);

//        initial();

        //LoginAnonymous();

      // SignUpWithEmailAndPassword("mohamed@gmail.com","omar123456");

        LoginWithEmailAndPassword("mohamed@gmail.com","omar123456");



//        LoadData();


//        searchname.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//
//
//
//
//
//
//
//            }
//        });













//        UpdateName("Hady");
//        DeletData();

        //               ----  Data Reception  ----





    }




    protected void SignUpWithEmailAndPassword(String email, String password){
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (!task.isSuccessful())
                    Toast.makeText(MainActivityPresentYourDataFireBase.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivityPresentYourDataFireBase.this, "Registered Success", Toast.LENGTH_SHORT).show();

            }
        });
    }




    protected void LoginWithEmailAndPassword(String email,String password){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful())
                    Toast.makeText(MainActivityPresentYourDataFireBase.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivityPresentYourDataFireBase.this, "LogIn Success", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void LoginAnonymous(){
        firebaseAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful())
                    Toast.makeText(MainActivityPresentYourDataFireBase.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivityPresentYourDataFireBase.this, "LogIn Success", Toast.LENGTH_SHORT).show();

            }
        });
    }



//    protected void LoadData(){
//
//
//
//        reference.child("Contacts").orderByChild("name"). // OrderBy ---> Organize Data Weather Name Or Date
//                addValueEventListener(new ValueEventListener() {  // add single value--->  Update For Data automatically
//            // ,,,,,, add listener single value---> Update For Data After Run Application Or Intent from Activity to Activity
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {   // OnDataChange ---> Change Data Automatically whether change in studio or google
//                contacts.clear();
//                Log.d("error", "onDataChange: " + snapshot);
//                if (snapshot.exists()) {
//
//                    for (DataSnapshot snapshot2 : snapshot.getChildren()) {
//
//                        //     Log.d("contact", "onDataChange: "+snapshot1);  //  Logcat in Console Application
//
//                        ListViewDataType test6 = snapshot2.getValue(ListViewDataType.class);
//
//                        contacts.add(test6);
//
//                    }
//
//                    adapter.setListViewDataTypes(contacts);
//
//
//                }
//
//
//            }


//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//                Toast.makeText(MainActivityPresentYourDataFireBase.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
//
//
//            }
//
//        });
//


    }

//    protected void UpdateName(String name){
//// When Change Id Will Present The Data But From (Default Constractor)
//        reference.child("Contact1").child("-MZV5snJc4oKqJTTXE").child("name").setValue(name);
//
//    }
//
//    protected void DeletData(){
//        reference.child("Contact1").child("-MZV5snJc4oKqJTTXE").setValue(null);
//    }
