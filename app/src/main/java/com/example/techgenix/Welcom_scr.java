package com.example.techgenix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Welcom_scr extends AppCompatActivity {
LottieAnimationView lottieAnimationView;
ImageView name,splashimg;
Button nextbtn;
EditText getdatabase_name;
    CheckBox status;

    DatabaseReference reff;
    String dist,cunt;
    String firebase_DB_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom_scr);

        splashimg=findViewById(R.id.imgs);
        splashimg.animate().translationY(-3000).setDuration(1000).setStartDelay(4000);
        name=findViewById(R.id.appname);
        name.animate().translationY(2500).setDuration(1000).setStartDelay(4000);
        lottieAnimationView=findViewById(R.id.lottie);
        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);

        SharedPreferences sharedPreferences1=getSharedPreferences("state", Context.MODE_PRIVATE);

        boolean s=sharedPreferences1.getBoolean("status",false);
        System.out.println("............status...............");
        System.out.println(s);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Dialog mydialog=new Dialog(Welcom_scr.this);
                mydialog.setContentView(R.layout.update_firebase);
                getdatabase_name=mydialog.findViewById(R.id.databasename);
                nextbtn= mydialog.findViewById(R.id.next);
                status=mydialog.findViewById(R.id.statuschk);
                status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            nextbtn.setVisibility(View.VISIBLE);
                        }else{
                            nextbtn.setVisibility(View.GONE);
                        }

                    }
                });

                SharedPreferences sharedPreferences=getSharedPreferences("state", Context.MODE_PRIVATE);
                status.setChecked(sharedPreferences.getBoolean("status",false));


                SharedPreferences sharedPreferences1=getSharedPreferences("DB_name", Context.MODE_PRIVATE);
               firebase_DB_name =sharedPreferences1.getString("dbname","user1");

                System.out.println("firebase name:::::::"+firebase_DB_name);
                nextbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean ck=status.isChecked();
                        if(ck==true){
                            nextbtn.setVisibility(View.VISIBLE);
                            String DB_name=getdatabase_name.getText().toString();
                            save_db_name(DB_name);

                            if (DB_name.equals("")){
                                Toast.makeText(getApplicationContext(),"please enter database name",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            updatefirebasevalues(DB_name);
                        }
                        Intent myIntent = new Intent(Welcom_scr.this, MainActivity.class);
                        startActivity(myIntent);

                        okbtn();
                        finish();

                    }
                });

                if (s==true){

                    System.out.println(".........don");
                    Intent myIntent = new Intent(Welcom_scr.this, MainActivity.class);
                    startActivity(myIntent);
                    okbtn();
                    finish();
                }else {
                    mydialog.show();

                }


            }
        },6000);




    }

    void okbtn() {
        SharedPreferences sharedPreferences=getSharedPreferences("state", Context.MODE_PRIVATE);

        sharedPreferences.edit().putBoolean("status",status.isChecked()).apply();

    }


    void  updatefirebasevalues(String DB_name){

        System.out.println("update firebase values");
        reff = FirebaseDatabase.getInstance().getReference();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


//                dist = dataSnapshot.child("c").getValue().toString();
//                cunt = dataSnapshot.child("d").getValue().toString();
                reff.child(DB_name).child("Gauge").child("G1").setValue(0);
                reff.child(DB_name).child("Gauge").child("G2").setValue(0);
                reff.child(DB_name).child("Gauge").child("G3").setValue(0);

                reff.child(DB_name).child("Lable").child("L1").setValue(0);
                reff.child(DB_name).child("Lable").child("L2").setValue(0);
                reff.child(DB_name).child("Lable").child("L3").setValue(0);
                reff.child(DB_name).child("Lable").child("L4").setValue(0);
                reff.child(DB_name).child("Lable").child("L5").setValue(0);
                reff.child(DB_name).child("Lable").child("L6").setValue(0);

                reff.child(DB_name).child("Level").child("LV1").setValue(0);
                reff.child(DB_name).child("Level").child("LV2").setValue(0);

                reff.child(DB_name).child("Button").child("B1").setValue(0);
                reff.child(DB_name).child("Button").child("B2").setValue(0);
                reff.child(DB_name).child("Button").child("B3").setValue(0);
                reff.child(DB_name).child("Button").child("B4").setValue(0);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("DATABASE ERROR");
            }
        });
    }

    void save_db_name(String name) {
        SharedPreferences sharedPreferences=getSharedPreferences("DB_name", Context.MODE_PRIVATE);

        sharedPreferences.edit().putString("dbname",name).apply();

    }

}
