package com.example.saitejjanjirala.giving_it_a_try;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class selectandstart extends AppCompatActivity {
    EditText editText;
    ArrayList<String> listofnumbers;
    SQLiteDatabase sdb;
    private long mintime = 5000;
    private long mindist = 0;
    private LocationManager locationManager;
    private LocationListener locationListener;
    SmsManager smsManager;
    String SENT = "SMS_SENT";
    String DELIVERED = "SMS_DELIVERED";
    String x;
    int y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectandstart);
        editText = findViewById(R.id.numbertext);
    }
    @SuppressLint("MissingPermission")
    public void savenumber(View view) {
        x = editText.getText().toString();
        if (!TextUtils.isEmpty(x)) {

            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    try {
                        String mylatitude = String.valueOf(location.getLatitude());
                        String mylongitude = String.valueOf(location.getLongitude());
                        String uri = "http://maps.google.com/maps?saddr=" + mylatitude + "," + mylongitude;
                        StringBuffer smsBody = new StringBuffer();
                        //long number = Long.parseLong(get_number);
                        smsBody.append(Uri.parse(uri));
                        smsManager = SmsManager.getDefault();
                        Log.i("hey", "" + smsManager);
                        smsManager.sendTextMessage(x,null,smsBody.toString(),null,null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {
                }
                @Override
                public void onProviderEnabled(String s) {
                }
                @Override
                public void onProviderDisabled(String s) {
                }
            };
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            try {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, mintime, mindist, locationListener);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,mintime,mindist,locationListener);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
         else {
            Toast.makeText(getApplicationContext(), "you must enter the number", Toast.LENGTH_LONG).show();
        }
    }
}
