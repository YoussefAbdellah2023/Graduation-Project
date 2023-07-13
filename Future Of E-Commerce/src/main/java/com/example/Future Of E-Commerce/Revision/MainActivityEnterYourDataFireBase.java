package com.example.e_commercefuture.Revision;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commercefuture.R;
import com.google.firebase.database.DatabaseReference;

public class MainActivityEnterYourDataFireBase extends AppCompatActivity {

    EditText txt1 , txt2;
    Button btn;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_enter_your_data_fire_base);

        intialization();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              // SaveData();
            }

        });

    }


    void intialization(){

        txt1=findViewById(R.id.edit_text_savedata1);
        txt2=findViewById(R.id.edit_text_savedata2);
        btn=findViewById(R.id.btn_save_data);

    }

//    void SaveData(String t1 , String t2) {
//
//        t1 = txt1.getText().toString();
//        t2 = txt2.getText().toString();
//
//        if (t1.isEmpty() || t2.isEmpty()) {
//            Toast.makeText(this, "Your Data Is Empty", Toast.LENGTH_SHORT).show();
//        } else if (t1.equals("a") && t2.equals("1")) {
//            Intent intent = new Intent(this, MainActivityPresentYourDataFireBase.class);
//            startActivity(intent);
//            finish();
//        } else {
//
//            // FirebaseDatabase.getinstance ---> عبارة عن مؤشر يعبر عن البداية في الداتا بتاعتي
//            // getReference --->  هات بقي الداتا دي
//
////            ------ Upload Data ------
//
//            reference = FirebaseDatabase.getInstance().getReference();
//
//            String id = reference.child("Contact1").push().getKey();
//
//            ListViewDataType listViewDataType = new ListViewDataType(t1, t2, id);
//
//            reference.child("Contact1").child(id).setValue(listViewDataType);
//
//            Toast.makeText(this, "Success , Your Data Is Saved", Toast.LENGTH_SHORT).show();
//
//            Intent intent = new Intent(this, MainActivityPresentYourDataFireBase.class);
//            startActivity(intent);
//            finish();
//
//
//        }
//    }

}

