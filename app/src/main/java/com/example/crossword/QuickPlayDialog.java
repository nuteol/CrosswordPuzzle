package com.example.crossword;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;

public class QuickPlayDialog {

    AlertDialog dialog;
    Spinner size;
    Spinner theme;
    Button create;
    Activity activity;

    QuickPlayDialog(Activity activity) { this.activity = activity; }

    public void startQuickplayDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View myView = inflater.inflate(R.layout.quickplay_dialog, null);

        size = (Spinner) myView.findViewById(R.id.size);
        theme = (Spinner) myView.findViewById(R.id.spinner3);
        create = (Button) myView.findViewById(R.id.button7);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Crossword crossword = createCrossword(3,1);
                Intent intent = new Intent(activity.getApplicationContext(), GameScreen.class);
                intent.putExtra("puzzle", (new Gson()).toJson(crossword));
                intent.putExtra("lvl",1);
                dialog.dismiss();
                activity.startActivity(intent);
            }
        });
        builder.setView(myView);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

    private Crossword createCrossword(int size, int theme) {
        Crossword crossword = new Crossword();
        ArrayList<Word> words;
        words = new ArrayList<>();
        words.add(new Word("cat","Feline animal",0,0,3,true));
        words.add(new Word("pat","Repeated action of touching someone with affection",0,0,3,true));
        words.add(new Word("sat","Past tense of sit",0,0,3,true));
        words.add(new Word("art","Da Vinki",0,0,3,true));
        words.add(new Word("car","Automotive vehicle, with some sort of internal propulsion",0,0,3,false));
        words.add(new Word("tip","An end to some sort of thing.",2,0,3,false));
        words.add(new Word("rip","Tear something of",0,2,3,true));
        char[][] data = {   {'c','a','t'},
                {'a','0','i'},
                {'r','i','p'}};
        crossword.setWords(words);
        crossword.setData(data);
        crossword.setName("Sukurta greit");
        crossword.setSize(3);
        crossword.setId(11);
        return crossword;
    }
}
