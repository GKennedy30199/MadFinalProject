package com.gavankennedy.realfinalprojectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TiltTest extends AppCompatActivity implements SensorEventListener {

    Button N,S,E,W;
    Button BTM;

    private final double North_Move_Forward=6.0;
    private final double North_Move_Backward=9.0;
    private final double South_Move_Backward=5.0;
    private final double South_Move_forward=9.0;
    private final double East_Move_Right=8.0;
    private final double East_Move_Center=9.0;
    private final double West_Move_Left=7.0;
    private final double West_Move_Center=9.0;
    boolean highlimit=false;
    int counter=0;
    TextView x,y,z,Steps;
    private SensorManager SensorMang;
    private Sensor mSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tilt_test);
        BTM=findViewById(R.id.MenuButton);
        N=findViewById(R.id.NorthButton);
        S=findViewById(R.id.SouthButton);
        E=findViewById(R.id.EastButton);
        W=findViewById(R.id.WestButton);
        x=findViewById(R.id.X);
        y=findViewById(R.id.Y);
        z=findViewById(R.id.Z);
        Steps=findViewById(R.id.Xnum);
        SensorMang=(SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor=SensorMang.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        BTM.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                BackToMenu();
            }
        });
    }
    public void BackToMenu()
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    protected void onResume()
    {
        super.onResume();
        SensorMang.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause()
    {
        super.onPause();
        SensorMang.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        float X=event.values[0];
        float Y=event.values[1];
        float Z=event.values[2];
        x.setText(String.valueOf(X));
        y.setText(String.valueOf(Y));
        z.setText(String.valueOf(Z));
        if((X>North_Move_Forward)&&(highlimit==false)){
            highlimit=true;
        }
        if((X<North_Move_Backward)&&(highlimit==true)){
            counter++;
            Steps.setText(String.valueOf(counter));
            N.performClick();
            highlimit=false;
        }
        if((Y>South_Move_Backward)&&(highlimit==false)){
            highlimit=true;
        }
        if((Y<South_Move_forward)&&(highlimit==true)){
            counter++;
            Steps.setText(String.valueOf(counter));
            S.performClick();
            highlimit=false;
        }
        if((X>East_Move_Right)&&(highlimit==false)){
            highlimit=true;
        }
        if((X<East_Move_Center)&&(highlimit==true)){
            counter++;
            Steps.setText(String.valueOf(counter));
            E.performClick();
            highlimit=false;
        }
        if((X>West_Move_Left)&&(highlimit==false)){
            highlimit=true;
        }
        if((X<West_Move_Center)&&(highlimit==true)){
            counter++;
            Steps.setText(String.valueOf(counter));
            W.performClick();
            highlimit=false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
    public static double round(double value,int places)
    {
        if(places<0) throw new IllegalArgumentException();

        long factor=(long)Math.pow(10,places);
        value=value*factor;
        long tmp=Math.round(value);
        return (double) tmp/factor;
    }
}