package com.example.s1.interntask;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {
    EditText et;
    Button bt;
    TextView t1,t2,t3,t4,t5,tv;

    SQLiteDatabase sdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        sdb=openOrCreateDatabase("sree",MODE_PRIVATE,null);
        Intent it1=getIntent();
        String y=it1.getStringExtra("exc");
        et=findViewById(R.id.editText9);
        bt=findViewById(R.id.button5);
        t1=findViewById(R.id.textView3);
        t2=findViewById(R.id.textView4);
        t3=findViewById(R.id.textView5);
        t4=findViewById(R.id.textView6);
        t5=findViewById(R.id.textView7);
        tv=findViewById(R.id.textView);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String y=et.getText().toString();
                Cursor c=sdb.rawQuery("select * from CSE where name='"+y+"'",null);

                    if(c!=null){
                        c.moveToFirst();
                        do{
                            int a=c.getColumnIndex("name");
                            int b=c.getColumnIndex("password");
                            int d=c.getColumnIndex("rollno");
                            int e=c.getColumnIndex("phone");
                            int f=c.getColumnIndex("mail");
                            String m=c.getString(a);
                            String n=c.getString(b);
                            String o=c.getString(d);
                            String p=c.getString(e);
                            String q=c.getString(f);
                            t1.setText(m);
                            t2.setText(n);
                            t3.setText(o);
                            t4.setText(p);
                            t5.setText(q);

                        }while(c.moveToNext());
                    }

                    else {
                        Toast.makeText(getApplicationContext(), "not found", Toast.LENGTH_LONG).show();
                    }



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("call");
        menu.add("message");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getTitle().equals("call")){
            Intent in=new Intent(ThirdActivity.this,FourthActivity.class);
            startActivity(in);
        }
        else{
            Intent i=new Intent(ThirdActivity.this,FifthActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
