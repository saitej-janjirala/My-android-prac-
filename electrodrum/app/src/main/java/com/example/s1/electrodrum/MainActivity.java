package com.example.s1.electrodrum;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    MediaRecorder recorder;
    File audiofile = null;
    AlertDialog.Builder builder;
    Button bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11,bt12,record,stop;
    SoundPool sp;
    int p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        builder = new AlertDialog.Builder(this);
        record=findViewById(R.id.record);


        stop.setVisibility(View.INVISIBLE);
        bt1=findViewById(R.id.button3);
        bt2=findViewById(R.id.button4);
        bt3=findViewById(R.id.button5);
        bt4=findViewById(R.id.button6);
        bt5=findViewById(R.id.button7);
        bt6=findViewById(R.id.button8);
        bt7=findViewById(R.id.button9);
        bt8=findViewById(R.id.button10);
        bt9=findViewById(R.id.button11);
        bt10=findViewById(R.id.button12);
        bt11=findViewById(R.id.button13);
        bt12=findViewById(R.id.button14);
       sp=new SoundPool(200, AudioManager.STREAM_MUSIC,0);
       p1=sp.load(getApplicationContext(),R.raw.drum1,1);
        p2=sp.load(getApplicationContext(),R.raw.drum2,1);
        p3=sp.load(getApplicationContext(),R.raw.drum3,1);
        p4=sp.load(getApplicationContext(),R.raw.drum4,1);
        p5=sp.load(getApplicationContext(),R.raw.drum5,1);
        p6=sp.load(getApplicationContext(),R.raw.drum6,1);
        p7=sp.load(getApplicationContext(),R.raw.drum7,1);
        p8=sp.load(getApplicationContext(),R.raw.drum9,1);
        p9=sp.load(getApplicationContext(),R.raw.drum12,1);
        p10=sp.load(getApplicationContext(),R.raw.drum15,1);
        p11=sp.load(getApplicationContext(),R.raw.drum8,1);
        p12=sp.load(getApplicationContext(),R.raw.drum10,1);



    }
    public void recording(View view) throws IOException {
        stop.setVisibility(View.VISIBLE);
        record.setEnabled(false);
        stop.setEnabled(true);
        //Creating file
        File dir = Environment.getExternalStorageDirectory();
        try {
            audiofile = File.createTempFile("sound", ".3gp", dir);
        } catch (IOException e) {
            Log.e("tag", "external storage access error");
            return;
        }
        //Creating MediaRecorder and specifying audio source, output format, encoder & output format
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(audiofile.getAbsolutePath());
        recorder.prepare();
        recorder.start();
    }
    public void stopping(View view) {

        //stopping recorder

        //Setting message manually and performing action on button click
        builder.setMessage("Do you want to close this application ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        addRecordingToMediaLibrary();
                        record.setEnabled(true);
                        stop.setEnabled(false);
                        recorder.stop();
                        recorder.release();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"your audio file is not saved",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("AlertDialogExample");
        alert.show();
        //after stopping the recorder, create the sound file and add it to media library.

    }
    protected void addRecordingToMediaLibrary() {
        //creating content values of size 4
        ContentValues values = new ContentValues(4);
        long current = System.currentTimeMillis();
        values.put(MediaStore.Audio.Media.TITLE, "audio" + audiofile.getName());
        values.put(MediaStore.Audio.Media.DATE_ADDED, (int) (current / 1000));
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/3gpp");
        values.put(MediaStore.Audio.Media.DATA, audiofile.getAbsolutePath());

        //creating content resolver and storing it in the external content uri
        ContentResolver contentResolver = getContentResolver();
        Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Uri newUri = contentResolver.insert(base, values);

        //sending broadcast message to scan the media file so that it can be available
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));
        Toast.makeText(this, "Added File " + newUri, Toast.LENGTH_LONG).show();
    }



    public void play1(View view){
        sp.play(p1,1.0f,1.0f,0,0,10f);
    }
    public void play2(View view){
        sp.play(p2,1.0f,1.0f,0,0,10f);
    }

    public void play3(View view){
        sp.play(p3,1.0f,1.0f,0,0,10f);

    }
    public void play4(View view){
        sp.play(p4,1.0f,1.0f,0,0,10f);

    }
    public void play5(View view){
        sp.play(p5,1.0f,1.0f,0,0,10f);

    }
    public void play6(View view){
        sp.play(p6,1.0f,1.0f,0,0,10f);

    }
    public void play7(View view){
        sp.play(p7,1.0f,1.0f,0,0,10f);
    }
    public void play8(View view){
        sp.play(p8,1.0f,1.0f,0,0,10f);
    }
    public void play9(View view){
        sp.play(p9,1.0f,1.0f,0,0,10f);
    }
    public void play10(View view){
        sp.play(p10,1.0f,1.0f,0,0,10f);
    }
    public void play11(View view){
        sp.play(p11,1.0f,1.0f,0,0,10f);
    }
    public void play12(View view){
        sp.play(p12,1.0f,1.0f,0,0,10f);
    }

}
