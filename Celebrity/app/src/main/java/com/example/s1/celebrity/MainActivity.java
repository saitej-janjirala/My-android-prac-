package com.example.s1.celebrity;

        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.AsyncTask;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Toast;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLStreamHandler;
        import java.util.ArrayList;
        import java.util.Random;
        import java.util.concurrent.ExecutionException;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> celeburls=new ArrayList<>();
    ArrayList<String> celebnames=new ArrayList<>();
    int chosen=0;
    ImageView iv;
    int correct=0;
    String answers[]=new String[4];
    Button b0,b1,b2,b3;
    public void  celebChosen(View view){
    if(view.getTag().toString().equals(Integer.toString(correct))){
        Toast.makeText(getApplicationContext(),"CORRECT",Toast.LENGTH_LONG).show();
    }
    else{
        Toast.makeText(getApplicationContext(),"Wrong its "+celebnames.get(chosen),Toast.LENGTH_LONG).show();
    }
    change();

    }
    public class Imagedownloader extends AsyncTask<String,Void,Bitmap>{
        HttpURLConnection connection;
        URL url;
        Bitmap bitmap;
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                url = new URL(urls[0]);
                connection=(HttpURLConnection)url.openConnection();
                connection.connect();
                InputStream is=connection.getInputStream();
                bitmap=BitmapFactory.decodeStream(is);
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
    public class DownloadTask extends AsyncTask<String,Void,String>{
        HttpURLConnection connection;
        URL url;
        @Override
        protected String doInBackground(String... urls) {
            URL url;
            String result=null;
            try {
                url = new URL(urls[0]);
                connection= (HttpURLConnection) url.openConnection();
                InputStream in=connection.getInputStream();
                InputStreamReader inr=new InputStreamReader(in);
                int data=inr.read();
                while (data!=-1){
                    char current=(char)data;
                    result +=current;
                    data=inr.read();

                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DownloadTask task=new DownloadTask();
        iv=findViewById(R.id.imageView);
        b0=findViewById(R.id.button1);
        b1=findViewById(R.id.button2);
        b2=findViewById(R.id.button3);
        b3=findViewById(R.id.button4);

        String result=null;
        try {
            result=task.execute("http://www.posh24.se/kandisar").get();
            String[] splitString=result.split("<div class=\"sidebarContainer\">");
            Pattern p=Pattern.compile("<img src=\"(.*?)\"");
            Matcher m=p.matcher(splitString[0]);
            while(m.find()){
                celeburls.add(m.group(1));
            }
            p=Pattern.compile("alt=\"(.*?)\"");
            m=p.matcher(splitString[0]);
            while(m.find()){
                celebnames.add(m.group(1));
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        change();
    }
    public void change(){
        Random random=new Random();
        chosen=random.nextInt(celeburls.size());
        Imagedownloader image=new Imagedownloader();
        Bitmap bitimage = null;
        try {
            bitimage = image.execute(celeburls.get(chosen)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        iv.setImageBitmap(bitimage);

        correct=random.nextInt(4);
        int incorrect;
        for(int i=0;i<4;i++){
            if(i==correct){
                answers[i]=celebnames.get(chosen);
            }
            else{
                incorrect=random.nextInt(celeburls.size());
                while(incorrect==chosen){
                    incorrect=random.nextInt(celeburls.size());
                }
                answers[i]=celebnames.get(incorrect);
            }
        }
        b0.setText(answers[0]);
        b1.setText(answers[1]);
        b2.setText(answers[2]);
        b3.setText(answers[3]);
    }
}
