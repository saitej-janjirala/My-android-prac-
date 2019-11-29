package com.example.s1.interntask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button bt;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=findViewById(R.id.button);
        et=findViewById(R.id.editText);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x=et.getText().toString();
                Log.i("exchange",x);
                Intent it=new Intent(MainActivity.this,ThirdActivity.class);
                it.putExtra("exc",x);
                Intent it2=new Intent(MainActivity.this,FirstActivity.class);
                startActivity(it2);

            }
        });
    }
}


