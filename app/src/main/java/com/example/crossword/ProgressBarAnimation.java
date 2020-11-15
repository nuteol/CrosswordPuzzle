package com.example.crossword;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressBarAnimation extends Animation {
    private ProgressBar progressBar;
    private TextView textView;
    private int from;
    private int  to;
    private long stepDuration;

    public ProgressBarAnimation(ProgressBar progressBar, TextView textView, long fullDuration) {
        super();
        this.progressBar = progressBar;
        this.textView = textView;
        stepDuration = fullDuration / progressBar.getMax();
    }

    public void setProgress(int progress) {
        if(progress < 0) {
            progress = 0;
        }
        if(progress > progressBar.getMax()) {
            progress = progressBar.getMax();
        }

        to = progress;
        from = progressBar.getProgress();

        setDuration(Math.abs(to - from) * stepDuration);
        progressBar.startAnimation(this);
    }
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
        textView.setText(String.valueOf((int) value) + "%");
    }

}