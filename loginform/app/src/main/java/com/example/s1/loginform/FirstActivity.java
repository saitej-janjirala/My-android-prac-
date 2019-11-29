package com.example.s1.loginform;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {
    SQLiteDatabase sdb;
    TextView t1,t2,t3,t4,t5;
    EditText et;
    Button bt;
    String y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        sdb=openOrCreateDatabase("loginform",MODE_PRIVATE,null);
        et=findViewById(R.id.editText3);
        bt=findViewById(R.id.button3);
        t1=findViewById(R.id.textView);
        t2=findViewById(R.id.textView2);
        t3=findViewById(R.id.textView3);
        t4=findViewById(R.id.textView4);
        t5=findViewById(R.id.textView5);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y=et.getText().toString();
                    Cursor c=sdb.rawQuery("select * from LOG where name='"+y+"'",null);
                    if(c!=null){
                        c.moveToFirst();
                        do{
                            int a=c.getColumnIndex("name");
                            int b=c.getColumnIndex("password");
                            int d=c.getColumnIndex("phonenumber");
                            int e=c.getColumnIndex("email");
                            int f=c.getColumnIndex("address");
                            t1.setText(c.getString(a));
                            t2.setText(c.getString(b));
                            t3.setText(c.getString(d));
                            t4.setText(c.getString(e));
                            t5.setText(c.getString(f));
                        }while(c.moveToNext());
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"not found",Toast.LENGTH_LONG).show();
                    }
                }


        });



    }
}
