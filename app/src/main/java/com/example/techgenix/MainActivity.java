package com.example.techgenix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference reff;
    String dist,cunt;
    ImageView lable_up,lavel_lable_up,gauge_lable_up,button_lable_up;
    EditText lable1,lable2,lable3,lable4,lable5,lable6;//display
    EditText lable7,lable8,maxvalue1,maxvalue2;//level
    EditText G1,G2,G3,maxvalue3,maxvalue4,maxvalue5;//gauge
    EditText B1,B2,B3,B4;//Buttons

    Button save,save2,save3,save4;

    ProgressBar pro1,pro2,pro3;//gauge
    TextView progresstext1,progresstext2,progresstext3;
    TextView lable_name_1,lable_name_2,lable_name_3,lable_name_4,lable_name_5,lable_name_6;
    TextView Lable_value_1,Lable_value_2,Lable_value_3,Lable_value_4,Lable_value_5,Lable_value_6;//lable of display lable
    TextView lable_name_7,lable_name_8;//
    TextView Lable_value_7,Lable_value_8;//lable of leval lable
    TextView lable_name_9,lable_name_10,lable_name_11;//gauge lable names

    TextView B1_Lable_name,B2_Lable_name,B3_Lable_name,B4_Lable_name;//lable of buttons

    ProgressBar progress_vertical_Bar1,progress_vertical_Bar2;

    String maxonevalue,maxtwovalue;//lavel max
    String G_maxonevalue,G_maxtwovalue,G_maxthreevalue;//gauge max

    String s="50";
   String firebase_DB_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences firenames=getSharedPreferences("DB_name", Context.MODE_PRIVATE);
        firebase_DB_name =firenames.getString("dbname","user1");

        System.out.println("firebase name:::in main::::"+firebase_DB_name);



        lable_name_1=findViewById(R.id.labal_name1);
        lable_name_2=findViewById(R.id.labal_name2);
        lable_name_3=findViewById(R.id.labal_name3);
        lable_name_4=findViewById(R.id.labal_name4);
        lable_name_5=findViewById(R.id.labal_name5);
        lable_name_6=findViewById(R.id.labal_name6);
     //UPDATE LABLE NAME PREVIOUS STATUS
        SharedPreferences sharedPreferences=this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        lable_name_1.setText(sharedPreferences.getString("lable_name_1","lable1"));
        lable_name_2.setText(sharedPreferences.getString("lable_name_2","lable2"));
        lable_name_3.setText(sharedPreferences.getString("lable_name_3","lable3"));
        lable_name_4.setText(sharedPreferences.getString("lable_name_4","lable4"));
        lable_name_5.setText(sharedPreferences.getString("lable_name_5","lable5"));
        lable_name_6.setText(sharedPreferences.getString("lable_name_6","lable6"));




//level lables

        lable_name_7=findViewById(R.id.labal_name7);
        lable_name_8=findViewById(R.id.labal_name8);


        Lable_value_7=findViewById(R.id.labal_name7);
        Lable_value_8=findViewById(R.id.labal_name8);
        SharedPreferences sharedPreferences1=this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        lable_name_7.setText(sharedPreferences1.getString("lable_name_7","lable7"));
        lable_name_8.setText(sharedPreferences1.getString("lable_name_8","lable8"));

        progress_vertical_Bar1=findViewById(R.id.progressverticalBar1);
        progress_vertical_Bar1.setMax(Integer.parseInt(sharedPreferences1.getString("max","100")));

        progress_vertical_Bar2=findViewById(R.id.progressverticalBar2);
        progress_vertical_Bar2.setMax(Integer.parseInt(sharedPreferences1.getString("maxtwo","100")));

//gauge lable
        lable_name_9=findViewById(R.id.labal_name9);
        lable_name_10=findViewById(R.id.labal_name10);
        lable_name_11=findViewById(R.id.labal_name11);

        SharedPreferences sharedPreferences2=this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        lable_name_9.setText(sharedPreferences2.getString("G1","G1"));
        lable_name_10.setText(sharedPreferences2.getString("G2","G1"));
        lable_name_11.setText(sharedPreferences2.getString("G3","G1"));

        pro1=findViewById(R.id.progress_bar);
        progresstext1=findViewById(R.id.textView_progress);
        progresstext1.setText(50+"%");
        pro1.setProgress(Integer.valueOf(50));
        pro1.setMax(Integer.parseInt(sharedPreferences2.getString("G_max1","100")));

        pro2=findViewById(R.id.progress_bar2);
        progresstext2=findViewById(R.id.textView_progress2);
        progresstext2.setText(100+"%");
        pro2.setProgress(Integer.valueOf(100));
        pro2.setMax(Integer.parseInt(sharedPreferences2.getString("G_max2","100")));

        pro3=findViewById(R.id.progress_bar3);
        progresstext3=findViewById(R.id.textView_progress3);
        progresstext3.setText(200+"%");
        pro3.setProgress(Integer.valueOf(50));
        pro3.setMax(Integer.parseInt(sharedPreferences2.getString("G_max3","100")));



//button lable
        B1_Lable_name=findViewById(R.id.btn_name1);
        B2_Lable_name=findViewById(R.id.btn_name2);
        B3_Lable_name=findViewById(R.id.btn_name3);
        B4_Lable_name=findViewById(R.id.btn_name4);
        SharedPreferences sharedPreferences3=this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        B1_Lable_name.setText(sharedPreferences3.getString("B1","B1"));
        B2_Lable_name.setText(sharedPreferences3.getString("B2","B2"));
        B3_Lable_name.setText(sharedPreferences3.getString("B3","B3"));
        B4_Lable_name.setText(sharedPreferences3.getString("B4","B3"));


        reff = FirebaseDatabase.getInstance().getReference();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String G1 = dataSnapshot.child(firebase_DB_name).child("Gauge").child("G1").getValue().toString();
                progresstext1.setText(Integer.parseInt(G1)+"%");
                pro1.setProgress(Integer.valueOf(G1));
                String G2 = dataSnapshot.child(firebase_DB_name).child("Gauge").child("G2").getValue().toString();
                progresstext2.setText(Integer.parseInt(G2)+"%");
                pro2.setProgress(Integer.valueOf(G2));
                String G3 = dataSnapshot.child(firebase_DB_name).child("Gauge").child("G3").getValue().toString();
                progresstext3.setText(Integer.parseInt(G3)+"%");
                pro3.setProgress(Integer.valueOf(G3));


                //lable
                String L1 = dataSnapshot.child(firebase_DB_name).child("Lable").child("L1").getValue().toString();
                Lable_value_1=findViewById(R.id.label_one_value_id1);
                Lable_value_1.setText(L1);

                String L2 = dataSnapshot.child(firebase_DB_name).child("Lable").child("L2").getValue().toString();
                Lable_value_2=findViewById(R.id.label_one_value_id2);
                Lable_value_2.setText(L2);

                String L3 = dataSnapshot.child(firebase_DB_name).child("Lable").child("L3").getValue().toString();
                Lable_value_3=findViewById(R.id.label_one_value_id3);
                Lable_value_3.setText(L3);

                String L4 = dataSnapshot.child(firebase_DB_name).child("Lable").child("L4").getValue().toString();
                Lable_value_4=findViewById(R.id.label_one_value_id4);
                Lable_value_4.setText(L4);

                String L5 = dataSnapshot.child(firebase_DB_name).child("Lable").child("L5").getValue().toString();
                Lable_value_5=findViewById(R.id.label_one_value_id5);
                Lable_value_5.setText(L5);

                String L6 = dataSnapshot.child(firebase_DB_name).child("Lable").child("L6").getValue().toString();
                Lable_value_6=findViewById(R.id.label_one_value_id6);
                Lable_value_6.setText(L6);

                //level
                String LV1 = dataSnapshot.child(firebase_DB_name).child("Level").child("LV1").getValue().toString();
                progress_vertical_Bar1.setProgress(Integer.valueOf(LV1));

                String LV2 = dataSnapshot.child(firebase_DB_name).child("Level").child("LV2").getValue().toString();
                progress_vertical_Bar2.setProgress(Integer.valueOf(LV2));

                //button

                ToggleButton lights1=findViewById(R.id.lights1);
                lights1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(lights1.isChecked()){

                            DatabaseReference df= FirebaseDatabase.getInstance().getReference(firebase_DB_name).child("Button").child("B1");
                            df.setValue("1");



                        }else {

                            DatabaseReference df= FirebaseDatabase.getInstance().getReference(firebase_DB_name).child("Button").child("B1");
                            df.setValue("0");


                        }
                    }
                });



                ToggleButton lights2=findViewById(R.id.lights2);
                lights2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(lights2.isChecked()){

                            DatabaseReference df= FirebaseDatabase.getInstance().getReference(firebase_DB_name).child("Button").child("B2");
                            df.setValue("1");



                        }else {

                            DatabaseReference df= FirebaseDatabase.getInstance().getReference(firebase_DB_name).child("Button").child("B2");
                            df.setValue("0");


                        }
                    }
                });



                ToggleButton lights3=findViewById(R.id.lights3);
                lights3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(lights3.isChecked()){

                            DatabaseReference df= FirebaseDatabase.getInstance().getReference(firebase_DB_name).child("Button").child("B3");
                            df.setValue("1");



                        }else {

                            DatabaseReference df= FirebaseDatabase.getInstance().getReference(firebase_DB_name).child("Button").child("B3");
                            df.setValue("0");


                        }
                    }
                });


                ToggleButton lights4=findViewById(R.id.lights4);
                lights4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(lights4.isChecked()){

                            DatabaseReference df= FirebaseDatabase.getInstance().getReference(firebase_DB_name).child("Button").child("B4");
                            df.setValue("1");



                        }else {

                            DatabaseReference df= FirebaseDatabase.getInstance().getReference(firebase_DB_name).child("Button").child("B4");
                            df.setValue("0");


                        }
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("DATABASE ERROR");
            }
        });

//setting of display lable
        lable_up=(ImageView) findViewById(R.id.settingbtn);
        lable_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Dialog mydialog=new Dialog(MainActivity.this);
                mydialog.setContentView(R.layout.setting_pop);

                lable1= mydialog.findViewById(R.id.lable1);
                lable1.setText(sharedPreferences.getString("lable_name_1","lable1"));
                lable2= mydialog.findViewById(R.id.lable2);
                lable2.setText(sharedPreferences.getString("lable_name_2","lable2"));
                lable3= mydialog.findViewById(R.id.lable3);
                lable3.setText(sharedPreferences.getString("lable_name_3","lable3"));
                lable4= mydialog.findViewById(R.id.lable4);
                lable4.setText(sharedPreferences.getString("lable_name_4","lable4"));
                lable5= mydialog.findViewById(R.id.lable5);
                lable5.setText(sharedPreferences.getString("lable_name_5","lable5"));
                lable6= mydialog.findViewById(R.id.lable6);
                lable6.setText(sharedPreferences.getString("lable_name_6","lable6"));



                save= mydialog.findViewById(R.id.save);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lable_name_1.setText(lable1.getText());
                        lable_name_2.setText(lable2.getText());
                        lable_name_3.setText(lable3.getText());
                        lable_name_4.setText(lable4.getText());
                        lable_name_5.setText(lable5.getText());
                        lable_name_6.setText(lable6.getText());

                        save_status();
                    }
                });

                mydialog.show();

            }
        });

//setting of lavel lable
        lavel_lable_up=(ImageView) findViewById(R.id.settingbtn2);
        lavel_lable_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog mydialog=new Dialog(MainActivity.this);
                mydialog.setContentView(R.layout.setting_pop_level);

                lable7= mydialog.findViewById(R.id.lable7);
                lable7.setText(sharedPreferences1.getString("lable_name_7","lable7"));
                lable8= mydialog.findViewById(R.id.lable8);
                lable8.setText(sharedPreferences1.getString("lable_name_8","lable8"));
                maxvalue1=mydialog.findViewById(R.id.maxvalue1);
                maxvalue1.setText(sharedPreferences1.getString("max","100"));
                maxvalue2=mydialog.findViewById(R.id.maxvalue2);
                maxvalue2.setText(sharedPreferences1.getString("maxtwo","100"));



                save2= mydialog.findViewById(R.id.save2);
                save2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lable_name_7.setText(lable7.getText());
                        lable_name_8.setText(lable8.getText());
                        maxonevalue=maxvalue1.getText().toString();
                        progress_vertical_Bar1.setMax(Integer.parseInt(maxonevalue));

                        maxtwovalue=maxvalue2.getText().toString();
                        progress_vertical_Bar2.setMax(Integer.parseInt(maxtwovalue));
                        save_status_leve();
                    }
                });

                mydialog.show();

            }
        });
//Setting of gauge_lable_up
        gauge_lable_up=(ImageView) findViewById(R.id.settingbtn3);
        gauge_lable_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog mydialog=new Dialog(MainActivity.this);
                mydialog.setContentView(R.layout.setting_pop_gauge);

                G1= mydialog.findViewById(R.id.G1);
                G1.setText(sharedPreferences2.getString("G1","G1"));
                G2= mydialog.findViewById(R.id.G2);
                G2.setText(sharedPreferences2.getString("G2","G2"));
                G3=mydialog.findViewById(R.id.G3);
                G3.setText(sharedPreferences2.getString("G3","G3"));

                maxvalue3=mydialog.findViewById(R.id.G1_max);
                maxvalue3.setText(sharedPreferences2.getString("G_max1","100"));
                maxvalue4=mydialog.findViewById(R.id.G2_max);
                maxvalue4.setText(sharedPreferences2.getString("G_max2","100"));
                maxvalue5=mydialog.findViewById(R.id.G3_max);
                maxvalue5.setText(sharedPreferences2.getString("G_max3","100"));


                save3= mydialog.findViewById(R.id.save3);
                save3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lable_name_9.setText(G1.getText());
                        lable_name_10.setText(G2.getText());
                        lable_name_11.setText(G3.getText());

                        G_maxonevalue=maxvalue3.getText().toString();
                        pro1.setMax(Integer.parseInt(G_maxonevalue));
                        G_maxtwovalue=maxvalue4.getText().toString();
                        pro2.setMax(Integer.parseInt(G_maxtwovalue));
                        G_maxthreevalue=maxvalue5.getText().toString();
                        pro3.setMax(Integer.parseInt(G_maxthreevalue));

                        save_status_gauge();
                    }
                });

                mydialog.show();

            }
        });


//Setting of button_lable_up
        button_lable_up=(ImageView) findViewById(R.id.settingbtn4);
        button_lable_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog mydialog=new Dialog(MainActivity.this);
                mydialog.setContentView(R.layout.setting_pop_buttons);

                B1= mydialog.findViewById(R.id.B1);
                B1.setText(sharedPreferences3.getString("B1","B1"));
                B2= mydialog.findViewById(R.id.B2);
                B2.setText(sharedPreferences3.getString("B2","B2"));
                B3=mydialog.findViewById(R.id.B3);
                B3.setText(sharedPreferences3.getString("B3","B3"));
                B4=mydialog.findViewById(R.id.B4);
                B4.setText(sharedPreferences3.getString("B4","B4"));



                save4= mydialog.findViewById(R.id.save4);
                save4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        B1_Lable_name.setText(B1.getText());
                        B2_Lable_name.setText(B2.getText());
                        B3_Lable_name.setText(B3.getText());
                        B4_Lable_name.setText(B4.getText());

                        save_status_button();
                    }
                });

                mydialog.show();

            }
        });


    }

    public void save_status() {
        SharedPreferences sharedPreferences=this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);


        lable_name_1.setText(lable1.getText().toString());
        lable_name_2.setText(lable2.getText().toString());
        lable_name_3.setText(lable3.getText().toString());
        lable_name_4.setText(lable4.getText().toString());
        lable_name_5.setText(lable5.getText().toString());
        lable_name_6.setText(lable6.getText().toString());

        sharedPreferences.edit().putString("lable_name_1",lable_name_1.getText().toString()).apply();
        sharedPreferences.edit().putString("lable_name_2",lable_name_2.getText().toString()).apply();
        sharedPreferences.edit().putString("lable_name_3",lable_name_3.getText().toString()).apply();
        sharedPreferences.edit().putString("lable_name_4",lable_name_4.getText().toString()).apply();
        sharedPreferences.edit().putString("lable_name_5",lable_name_5.getText().toString()).apply();
        sharedPreferences.edit().putString("lable_name_6",lable_name_6.getText().toString()).apply();

    }

    public void save_status_leve(){
        SharedPreferences sharedPreferences=this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        lable_name_7.setText(lable7.getText().toString());
        lable_name_8.setText(lable8.getText().toString());

        sharedPreferences.edit().putString("lable_name_7",lable_name_7.getText().toString()).apply();
        sharedPreferences.edit().putString("lable_name_8",lable_name_8.getText().toString()).apply();

       // progress_vertical_Bar1.setMax(Integer.parseInt());
        sharedPreferences.edit().putString("max",maxonevalue).apply();
        sharedPreferences.edit().putString("maxtwo",maxtwovalue).apply();


    }

    public void save_status_gauge(){
        SharedPreferences sharedPreferences=this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        lable_name_9.setText(G1.getText().toString());
        lable_name_10.setText(G2.getText().toString());
        lable_name_11.setText(G3.getText().toString());

        sharedPreferences.edit().putString("G1",lable_name_9.getText().toString()).apply();
        sharedPreferences.edit().putString("G2",lable_name_10.getText().toString()).apply();
        sharedPreferences.edit().putString("G3",lable_name_11.getText().toString()).apply();

        sharedPreferences.edit().putString("G_max1",G_maxonevalue).apply();
        sharedPreferences.edit().putString("G_max2",G_maxtwovalue).apply();
        sharedPreferences.edit().putString("G_max3",G_maxthreevalue).apply();

    }


    public void save_status_button(){
        SharedPreferences sharedPreferences=this.getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE);
        B1_Lable_name.setText(B1.getText().toString());
        B2_Lable_name.setText(B2.getText().toString());
        B3_Lable_name.setText(B3.getText().toString());
        B4_Lable_name.setText(B4.getText().toString());
        sharedPreferences.edit().putString("B1",B1_Lable_name.getText().toString()).apply();
        sharedPreferences.edit().putString("B2",B2_Lable_name.getText().toString()).apply();
        sharedPreferences.edit().putString("B3",B3_Lable_name.getText().toString()).apply();
        sharedPreferences.edit().putString("B4",B4_Lable_name.getText().toString()).apply();

    }

}
