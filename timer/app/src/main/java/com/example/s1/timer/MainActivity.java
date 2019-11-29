package com.example.s1.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekbar;
    TextView text;
    Button bt;

    CountDownTimer countdown;
    boolean counter=false;
    public void reset(){
        text.setText("0:30");
        seekbar.setProgress(30);
        seekbar.setEnabled(true);
        countdown.cancel();
        bt.setText("start");
        counter=false;


    }
    public void controller(View view){
        if(counter==false){
            counter=true;
            seekbar.setEnabled(false);
            bt.setText("stop");
           countdown= new CountDownTimer(seekbar.getProgress()*1000+100,1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    updater((int)millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    reset();
                    MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.horn);
                    mp.start();
                }
            }.start();
        }
        else{
            reset();

        }

    }
    public void updater(int secondch){
        int minutes=secondch/60;
        int seconds=secondch-minutes*60;
        String s=Integer.toString(seconds);
        if(seconds<=9){
            s="0"+s;
        }
        text.setText(Integer.toString(minutes)+":"+s);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar=findViewById(R.id.seekBar2);
        bt=findViewById(R.id.button);
        text=findViewById(R.id.textView4);
        seekbar.setMax(600);
        seekbar.setProgress(30);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updater(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
