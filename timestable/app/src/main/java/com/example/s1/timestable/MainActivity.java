package com.example.s1.timestable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list;
    SeekBar seek;
    public void tables(int timetable){
        ArrayList<String> array=new ArrayList<String>();
        for(int i=1;i<=10;i++){
            array.add(Integer.toString(i*timetable));
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,array);
        list.setAdapter(adapter);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        list=findViewById(R.id.list);
        seek=findViewById(R.id.seekBar);
        seek.setMax(20);
        seek.setProgress(10);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min=1;
                int timetable=0;
                if(progress<min){
                    timetable=min;
                    seek.setProgress(min);
                }
                else{
                    timetable=progress;
                }
                tables(timetable);
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
