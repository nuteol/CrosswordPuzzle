package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LevelSelection extends AppCompatActivity {

    Button _button;
    Button lvl1;
    Button lvl2;
    Button lvl3;
    int maxLevelUnlocked = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);
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
            }
        }

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
                Intent intent = new Intent(getBaseContext(), Level1.class);
                startActivity(intent);
            }
        });

        if(lvl2.isActivated() == true) {
            lvl2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), Level2.class);
                    startActivity(intent);
                }
            });
        }

        if(lvl3.isActivated() == true) {
            lvl3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), Level3.class);
                    startActivity(intent);
                }
            });
        }
    }
}