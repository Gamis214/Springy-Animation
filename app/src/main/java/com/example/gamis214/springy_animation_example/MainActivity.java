package com.example.gamis214.springy_animation_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;

public class MainActivity extends AppCompatActivity implements SpringListener, View.OnTouchListener,
                                                        View.OnClickListener{

    private ImageView img1;
    private Spring spring;
    private SpringSystem springSystem;

    private static double TENSION = 800;
    private static double DAMPER = 20; //friction

    private boolean mMovedUp = false;
    private float mOrigY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = (ImageView) findViewById(R.id.imageView);
        img1.setOnTouchListener(this);
        //img1.setOnClickListener(this);

        springSystem = SpringSystem.create();

        spring = springSystem.createSpring();
        spring.addListener(this);

        SpringConfig config = new SpringConfig(TENSION, DAMPER);
        spring.setSpringConfig(config);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                spring.setEndValue(1f);
                return true;
            case MotionEvent.ACTION_UP:
                spring.setEndValue(0f);
                return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (mMovedUp) {
            spring.setEndValue(mOrigY);
        } else {
            mOrigY = img1.getY();

            spring.setEndValue(mOrigY - 300f);
        }
        mMovedUp = !mMovedUp;
    }

    @Override
    public void onSpringUpdate(Spring spring) {
        float value = (float) spring.getCurrentValue();
        float scale = 1f - (value * 0.5f);

        img1.setScaleX(scale);
        img1.setScaleY(scale);

        //img1.setY(value);
    }

    @Override
    public void onSpringAtRest(Spring spring) {

    }

    @Override
    public void onSpringActivate(Spring spring) {

    }

    @Override
    public void onSpringEndStateChange(Spring spring) {

    }

}
