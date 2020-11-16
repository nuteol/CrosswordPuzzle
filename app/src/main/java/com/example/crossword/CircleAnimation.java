package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CircleAnimation extends AppCompatActivity {
    ImageView imageView;
    Button start;
    Button stop;
    ValueAnimator colorAnim;
    ArgbEvaluator colorEvaluator;
    boolean started;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_animation);

        imageView = (ImageView) findViewById(R.id.animationView);
        start = (Button) findViewById(R.id.start_animation);
        stop = (Button) findViewById(R.id.stop_animation);

        int colorFrom = getResources().getColor(R.color.blue);
        int colorTo = getResources().getColor(R.color.red);
        colorAnim = ValueAnimator.ofObject(new ArgbEvaluator(),colorFrom, colorTo);
        colorAnim.setDuration(1500);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                imageView.getBackground().setColorFilter((int)animation.getAnimatedValue(), PorterDuff.Mode.MULTIPLY);

            }
        });
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(-1);
        //colorAnim.start();
        started = false;
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!started) {
                    colorAnim.start();
                    started = true;
                }
                else {
                    colorAnim.resume();
                }

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorAnim.pause();
            }
        });
    }
    public int adjustAlpha(int color, float factor) {
        int alpha = 1;
        int red = (int)(Color.red(color)*factor);
        int green = 0;
        int blue = 255-red;
        return Color.argb(alpha, red, green, blue);
    }
}