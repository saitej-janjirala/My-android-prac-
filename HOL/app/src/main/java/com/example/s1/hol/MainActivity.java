package com.example.s1.hol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.*;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int n;
    public void onclick(View view){
        EditText p=findViewById(R.id.t);
        String x=p.getText().toString();
        int i=Integer.parseInt(x);
        if(i<n){

            Toast.makeText(this, "try higher", Toast.LENGTH_SHORT).show();
        }
        else if(i>n){
            Toast.makeText(this, "try lower", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "yeah that's right", Toast.LENGTH_SHORT).show();
            Random rand=new Random();
            n=rand.nextInt(20)+1;

        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand=new Random();
        n=rand.nextInt(20)+1;
    }
}
