package com.example.e_commercefuture;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.cardform.view.CardForm;

public class Pay extends AppCompatActivity {

    CardForm cardForm;
    Button button , btn;
    TextView textView;
    AlertDialog.Builder alertBuilder;

    String pay = "Cash";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        initialization();
    }


    void initialization(){
        cardForm=findViewById(R.id.card_form);
        button=findViewById(R.id.btn_pay);
        btn=findViewById(R.id.btn_cash);
        textView=findViewById(R.id.text_view_pay);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(Pay.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardForm.isValid()){
                    alertBuilder=new AlertDialog.Builder(Pay.this);
                    alertBuilder.setTitle("Confirm before purchase");
                    alertBuilder.setMessage("Card number : " +cardForm.getCardNumber() + "\n" +
                                  "Card expiry date: " +cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                                  "Card CVV:" +cardForm.getCvv() + "\n" +
                                  "Postal code:" +cardForm.getPostalCode() + "\n" +
                                  "Phone number:" +cardForm.getMobileNumber());

                    alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Toast.makeText(Pay.this, "Thank you for purchase", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();

                }else {
                    Toast.makeText(Pay.this, "Please complete the form", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pay.this,UserOrderActivity.class);
                startActivity(intent);

            }
        });
    }

//    void PayCash(){
//
//
//
//        FirebaseDatabase.getInstance().getReference("Orders").child("OrderUsers").child("UserPayWay")
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("ThePayWay")
//                .setValue(pay).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//
//                    Toast.makeText(Pay.this, "You Will Pay Cash", Toast.LENGTH_SHORT).show();
//
//                }
//            }
//        });
//    }
}