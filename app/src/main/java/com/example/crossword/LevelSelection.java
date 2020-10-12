package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class LevelSelection extends AppCompatActivity {

    Button _button;
    Button lvl1;
    Button lvl2;
    Button lvl3;
    int maxLevelUnlocked = 1;
    Crossword[] puzzles;
    private ListView listView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);
        listView = (ListView) findViewById(R.id.selectListView);


        //Puzzle creation, later I'll try to make it automatic, from a list of words that will be saved outside the game.
        //Another possibility is that I will save already premade puzzles in some sort of database, with complete word lists.
        puzzles = new Crossword[10];
        ArrayList<Crossword> croswords = new ArrayList<>();
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("cat","Feline animal",0,0,3,true));
        words.add(new Word("car","Automotive vehicle, with some sort of internal propulsion",0,0,3,false));
        words.add(new Word("tip","An end to some sort of thing.",2,0,3,false));
        words.add(new Word("rip","Tear something of",0,2,3,true));
        char[][] data = {   {'c','a','t'},
                            {'a','0','i'},
                            {'r','i','p'}};
        puzzles[0] = new Crossword(data,"Level 1", 3, words);
        croswords.add(puzzles[0]);
        croswords.add(puzzles[0]);
        croswords.add(puzzles[0]);

        _button = (Button) findViewById(R.id.button6);
        lvl1 = (Button) findViewById(R.id.lvl1);
        lvl2 = (Button) findViewById(R.id.lvl2);
        lvl3 = (Button) findViewById(R.id.lvl3);
        lvl2.setVisibility(View.GONE);
        lvl3.setVisibility(View.GONE);
        lvl2.setHighlightColor(getResources().getColor(R.color.grey));
        Button[] buttons = { lvl1, lvl2, lvl3};

        Intent intent1 = getIntent();
        if (intent1.getBooleanExtra("complete",false)) {
            maxLevelUnlocked = intent1.getIntExtra("lvl",0);
            for(int i = 1; i < maxLevelUnlocked; i++) {
                buttons[i].setVisibility(View.VISIBLE);
                buttons[i].setActivated(true);
                listView.setVisibility(View.VISIBLE);
            }
        }

        adapter = new ListAdapter(this, croswords);


        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), GameScreen.class);
                intent.putExtra("puzzle", (new Gson()).toJson(puzzles[0]));
                intent.putExtra("lvl",1);
                startActivity(intent);
            }
        });

        if(lvl2.isActivated() == true) {
            lvl2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), GameScreen.class);
                    intent.putExtra("puzzle", (new Gson()).toJson(puzzles[0]));
                    intent.putExtra("lvl",2);
                    startActivity(intent);
                }
            });
        }

        if(lvl3.isActivated() == true) {
            lvl3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), GameScreen.class);
                    intent.putExtra("puzzle", (new Gson()).toJson(puzzles[0]));
                    intent.putExtra("lvl",2);
                    startActivity(intent);
                }
            });
        }
        listView.setAdapter(adapter);
    }
}