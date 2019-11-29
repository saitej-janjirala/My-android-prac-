package com.example.s1.interntask;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Button bt;
    EditText et1,et2,et3,et4,et5;
    SQLiteDatabase sdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i("program","executed");
        bt=findViewById(R.id.button4);
        et1=findViewById(R.id.editText4);
        et2=findViewById(R.id.editText5);
        et3=findViewById(R.id.editText6);
        et4=findViewById(R.id.editText7);
        et5=findViewById(R.id.editText8);
        sdb=openOrCreateDatabase("sree",MODE_PRIVATE,null);
        sdb.execSQL("create table if not exists CSE(name varchar(15),password varchar(20),rollno varchar(10),phone varchar(10),mail varchar(40))");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=et1.getText().toString();
                String b=et2.getText().toString();
                String c=et3.getText().toString();
                String d=et4.getText().toString();
                String e=et5.getText().toString();
               sdb.execSQL("insert into CSE values('"+a+"','"+e+"','"+b+"','"+c+"','"+d+"')");
                Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
                Intent it1=new Intent(SecondActivity.this,FirstActivity.class);
                startActivity(it1);


            }
        });

    }
}
