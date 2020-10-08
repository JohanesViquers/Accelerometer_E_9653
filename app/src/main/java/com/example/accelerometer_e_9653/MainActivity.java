package com.example.accelerometer_e_9653;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    public void  onResume(){
        super.onResume();
        mSensorManager.registerListener(this,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
    Sensor mySensor = sensorEvent.sensor;

    if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[0];
        float z = sensorEvent.values[0];

        long curTime = System.currentTimeMillis();

        if((curTime-lastUpdate)>100){
            long diffTime = (curTime-lastUpdate);
            lastUpdate = curTime;

            float speed = Math.abs(x+y+y-last_x-last_y-last_z)/diffTime*10000;

            if (speed>SHAKE_THRESHOLD){
                Toast.makeText(getApplicationContext(), "SHAKE", Toast.LENGTH_SHORT).show();
                Log.d(TAG "key", "asd ");
            }

            last_x=x;
            last_y=y;
            last_z=z;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
}
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(SEARCH_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
}
}

