package com.example.s1.downloadimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    //https://binged.it/2SBRC9T
    public void downloadimage(View view) throws ExecutionException, InterruptedException {
        Bitmap myimage;
         Downloader download=new Downloader();
         myimage=download.execute("https://www.bing.com/th?id=OIP.0JrzCNGnt1wzQiz4Bi9OTwAAAA&w=158&h=201&c=7&o=5&pid=1.7").get();
         iv.setImageBitmap(myimage);
    }
    public class Downloader extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url= null;
            try {
                url = new URL(urls[0]);
                HttpURLConnection connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream is=connection.getInputStream();
                Bitmap bitmap=BitmapFactory.decodeStream(is);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.image);
    }
}
