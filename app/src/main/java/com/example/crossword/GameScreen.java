package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class GameScreen extends AppCompatActivity {

    Button completeButton;
    Button failButton;
    GridView letterGrid;
    TextView clues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        completeButton = (Button) findViewById(R.id.completeButton);
        failButton = (Button) findViewById(R.id.failButton);
        letterGrid = (GridView) findViewById(R.id.letter_grid);
        clues = (TextView) findViewById(R.id.cluesText);

        //retrieve puzzle
        //Had to use Gson to send the puzzle through activities.
        //Either something is not right with my classes, or I am just bad at this
        Intent intent = getIntent();
        Crossword puzzle;
        puzzle = (Crossword) (new Gson().fromJson(intent.getStringExtra("puzzle"), Crossword.class));
        letterGrid.setNumColumns(puzzle.getData().length);



        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        failButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}