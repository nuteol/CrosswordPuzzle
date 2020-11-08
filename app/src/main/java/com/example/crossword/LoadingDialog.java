package com.example.crossword;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import java.util.logging.Handler;

public class LoadingDialog {

    Activity activity;
    AlertDialog dialog;
    AnimationDrawable animation;
    ImageView loading;

    LoadingDialog(Activity myActivity) {
        activity = myActivity;

    }

    void startLoadingAnimation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View myView = inflater.inflate(R.layout.loading_dialog, null);
        ImageView imageView = myView.findViewById(R.id.imageView);
        animation = (AnimationDrawable) imageView.getBackground();
        animation.start();

        builder.setView(myView);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

    void dismissLoading() {

        animation.stop();
        dialog.dismiss();
    }
}
