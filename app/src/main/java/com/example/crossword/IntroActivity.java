package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class IntroActivity extends AppCompatActivity implements RequestWords.RequestOperatorListener {
    ImageView imageView;
    ValueAnimator anim;
    TextView received;
    List<Word> publication;
    String words;
    LoadingDialog loading;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        imageView = (ImageView) findViewById(R.id.imageView2);
        received = (TextView) findViewById(R.id.receivedTxt);
        imageView.setAlpha(0.0f);
        loading = new LoadingDialog(this);
        db = AppActivity.getDatabase();
        anim = new ValueAnimator();
        anim.setObjectValues(0.0f,1.0f);
        anim.setDuration(3000);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(1);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                imageView.setAlpha((float)animation.getAnimatedValue());
            }
        });

        //db.wordDAO().deleteAll();
        String ret = "";
        AssetManager manager = this.getAssets();
        try {
            InputStream inputStream = manager.open("lithuanian-words-list.txt");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
                words = ret;
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        //received.setText(ret);
        anim.start();
        if(db.wordDAO().getAllWords().size() == 0) {
            sendRequest();
        }
        else {
            imageView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(IntroActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }, anim.getDuration());

        }


    }

    private void sendRequest(){
        RequestWords ro = new RequestWords();
        ro.setListener(this);
        ro.setWords(words);
        //setIndicatorStatus(IndicatingView.LOADING);


        loading.startLoadingAnimation();
        ro.setLoadingDialog(loading);
        ro.start();
    }
    public void updatePublication(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(publication!=null){
                    received.setText(String.valueOf(publication.size()));
                } else {
                    received.setText("");
                }
            }
        });
    }

    @Override
    public void success(List<Word> publication) {
        this.publication = publication;
        //addToDb(publication);
        Type pubType = new TypeToken<List<Crossword>>() {}.getType();
        updatePublication();
        loading.setIndicatorStatus(IndicatingView.SUCCESS);
        insertToDb(publication);
        loading.dismissLoading();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void failed(int responseCode) {
        this.publication = null;
        received.setText(String.valueOf(responseCode));
        updatePublication();
        loading.setIndicatorStatus(IndicatingView.FAILED);
        loading.dismissLoading();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void insertToDb(List<Word> words)
    {
        for(Word word : words){
            db.wordDAO().insert(word);
        }
    }
}