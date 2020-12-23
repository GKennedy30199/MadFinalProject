package com.gavankennedy.realfinalprojectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Gameplay extends AppCompatActivity {
    private final int B=1;
    private final int RR=2;
    private final int Y=3;
    private final int G=4;
    Button Red,Blue,Yellow,Green,b;
    int sequencecnt=4 , Num=0, Gameplay=4;
    int[] Gseq=new int[120];
    int arrayIndex=0;
    CountDownTimer count=new CountDownTimer(6000,1500) {
        @Override
        public void onTick(long millisUntilFinished) {
            ButtonFlash();
        }

        @Override
        public void onFinish() {
            for (int i = 0; i < arrayIndex; i++) {
                Log.d("game", String.valueOf(Gseq[i]));
                Gameplay++;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        Red=findViewById(R.id.Redbtn);
        Blue=findViewById(R.id.Bluebtn);
        Yellow=findViewById(R.id.Yellowbtn);
        Green=findViewById(R.id.Greenbtn);
    }
    public void ButtonFlash()
    {
        Num=getRandom(sequencecnt);
        Toast.makeText(this,"Number ="+Num,Toast.LENGTH_SHORT).show();
        switch (Num)
        {
            case 1:
                flashButt(Blue);
                Gseq[arrayIndex++]=B;
                break;
            case 2:
                flashButt(Red);
                Gseq[arrayIndex++]=RR;
                break;
            case 3:
                flashButt(Yellow);
                Gseq[arrayIndex++]=Y;
                break;
            case 4:
                flashButt(Green);
                Gseq[arrayIndex++]=G;
                break;
            default:
                break;
        }
    }
    public int getRandom(int MaxV)
    {
        return ((int) ((Math.random() * MaxV) + 1));
    }
    public void flashButt(Button button)
    {
        b=button;
        Handler handler=new Handler();
        Runnable r = ()->{
            b.setPressed(true);
            b.invalidate();
            b.performClick();
            Handler handler1=new Handler();
            Runnable r1= ()->{
                b.setPressed(false);
                b.invalidate();
            };
            handler1.postDelayed(r1,600);
        };
        handler.postDelayed(r,600);
    }
    public void Play(View view)
    {
        Reset();
        if(arrayIndex>=Gameplay)
        {
            count.cancel();
        }
        else {
            count.start();
        }

    }
    public void Reset(){
        arrayIndex=0;
    }

}