package com.example.s1.loginform;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
        Button bt1, bt2;
        Spinner spin;
        EditText et1, et2, et3, et4, et5;
        SQLiteDatabase sdb;
        AlertDialog.Builder ad;
        String a, b, c, d, e;
        String[] country = {"India", "USA", "China", "Japan", "Other"};

        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, country);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        bt1 = findViewById(R.id.button);
        bt2 = findViewById(R.id.button2);
        ad = new AlertDialog.Builder(this);
        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
        et3 = findViewById(R.id.editText5);
        et4 = findViewById(R.id.editText6);
        et4.setText("");
        et5 = findViewById(R.id.editText8);
        sdb = openOrCreateDatabase("loginform", MODE_PRIVATE, null);
        sdb.execSQL("create table if not exists LOG(name varchar(35),password varchar(35),phonenumber varchar(20),email varchar(40),address varchar(50))");

        et5 = findViewById(R.id.editText8);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ad.setMessage("do you want to save the entered data");
                ad.setCancelable(false);
                ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        a = et1.getText().toString();
                        b = et2.getText().toString();
                        c = et3.getText().toString();
                        d = et4.getText().toString();
                        e = et5.getText().toString();

                        if (a.equals("") || b.equals("") || c.equals("") || d.equals("") || e.equals("")) {
                            Toast.makeText(getApplicationContext(), "you must fill all the fields", Toast.LENGTH_LONG).show();

                        } else {
                            sdb.execSQL("insert into LOG values('" + a + "','" + b + "','" + c + "','" + d + "','" + e + "')");
                            Toast.makeText(getApplicationContext(), "successfully registered", Toast.LENGTH_LONG).show();
                        }


                    }
                });
                ad.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "the data is not saved", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alert = ad.create();
                alert.setTitle("Alert!!");
                alert.show();

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(it);

            }
        });
    }


        public void onItemSelected (AdapterView < ? > arg0, View arg1,int position, long id){

            switch (position){
                case 0:
                        et4.setText("+91");
                        break;
                case 1:
                        et4.setText("+1");
                        break;
                case 2:
                        et4.setText("+86");
                        break;
                case 3:
                        et4.setText("+81");
                        break;
                case 4:
                        et4.setText("");
                        break;
    }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
