package com.example.s1.downloadtask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity  {

    public class Download extends AsyncTask<String,void,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String result="";
            URL url;
            HttpURLConnection urlconnection=null;
            try{
                url=new URL(strings[0])
            }
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String result="";
        Download d=new Download();
            d.execute("https:https://www.udemy.com///www.udemy.com/");
    }
}
