package com.example.crossword;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.logging.Handler;

public class LoadingDialog {

    Activity activity;
    AlertDialog dialog;
    AnimationDrawable animation;
    IndicatingView loading;
    private ProgressBar progression;
    private TextView progressText;
    private int progr = 0;
    ProgressBarAnimation progressBarAnimation;

    LoadingDialog(Activity myActivity) {
        activity = myActivity;
    }

    public void Increment() {
        if(progr <= 90) {
            progr += 10;
            updateProgressBar(progr);
        }
    }

    public void Decrease() {
        if(progr >= 10){
            progr -= 10;
            updateProgressBar(progr);
        }
    }

    public void updateProgressBar(int progress) {
        progressBarAnimation.setProgress(progress);
    }

    void startLoadingAnimation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View myView = inflater.inflate(R.layout.loading_dialog, null);
        loading = myView.findViewById(R.id.imageView);

        progression = (ProgressBar) myView.findViewById(R.id.progressBar);
        progressText = (TextView) myView.findViewById(R.id.text_view_progress);
        animation = (AnimationDrawable) loading.getBackground();

        progressBarAnimation = new ProgressBarAnimation(progression,progressText,1);
        updateProgressBar(1);
        animation.start();


        builder.setView(myView);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

    public void setIndicatorStatus(final int status) {
        loading.setState(status);
        loading.invalidate();
    }

    void dismissLoading() {

        animation.stop();
        progressBarAnimation.cancel();
        dialog.dismiss();
    }
}
