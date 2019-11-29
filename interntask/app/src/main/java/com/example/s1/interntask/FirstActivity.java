package com.example.s1.interntask;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {
    Button bt1,bt2;
    EditText et1,et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        bt1=findViewById(R.id.button2);
        bt2=findViewById(R.id.button3);
        et1=findViewById(R.id.editText2);
        et2=findViewById(R.id.editText3);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it4=new Intent(FirstActivity.this,ThirdActivity.class);
                startActivity(it4);

            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it2=new Intent(FirstActivity.this,SecondActivity.class);
                startActivity(it2);
            }
        });

    }
}
