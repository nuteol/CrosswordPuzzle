package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
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
    final float[] from = new float[3],
                    to = new float[3];

    ValueAnimator colorAnim;
    ArgbEvaluator colorEvaluator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_animation);

        imageView = (ImageView) findViewById(R.id.animationView);
        start = (Button) findViewById(R.id.start_animation);
        stop = (Button) findViewById(R.id.stop_animation);
        final int red = getResources().getColor(R.color.red);
        final float[] hsv  = new float[3];                  // transition color
        colorAnim = ObjectAnimator.ofFloat(0f, 1f);
        colorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float mul = (Float) animation.getAnimatedValue();
                int alphaRed = adjustAlpha(red, mul);
                imageView.setColorFilter(alphaRed);
                imageView.setBackground(getResources().getDrawable(R.color.blue));
                if (mul == 0.0) {
                    imageView.setColorFilter(alphaRed);
                }
            }
        });
        colorAnim.setDuration(1500);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.setRepeatCount(-1);
        colorAnim.start();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorAnim.start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                colorAnim.cancel();
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