package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class GameScreen extends AppCompatActivity {

    Button completeButton;
    Button failButton;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        completeButton = (Button) findViewById(R.id.completeButton);
        failButton = (Button) findViewById(R.id.failButton);
        textView = (TextView) findViewById(R.id.tempTextView);

        //retrieve puzzle
        //Had to use Gson to send the puzzle through activities.
        //Either something is not right with my classes, or I am just bad at this
        Intent intent = getIntent();
        Crossword puzzle;
        puzzle = (Crossword) (new Gson().fromJson(intent.getStringExtra("puzzle"), Crossword.class));
        final int level = (int) intent.getIntExtra("lvl",1);

        String text = textView.getText().toString();
        String name = puzzle.getName();
        text += "\n" + name;
        text += "\nWords in puzzle:\n";
        for(Word word : puzzle.getWords()) {
            text += word.getWord() + " ";
        }
        text += "\nSolved puzzle:\n";
        char[][] data = puzzle.getData();
        text += "---------------\n";
        for(int i = 0; i < data.length; i++) {

            for(int j = 0; j < data[i].length; j++)
            {
                text += " | " + data[i][j];
            }
            text += " |\n---------------\n";
        }

        textView.setText(text);

        completeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LevelSelection.class);
                intent.putExtra("complete",true);
                int complete = level + 1;
                intent.putExtra("lvl",complete);
                Toast toast = Toast.makeText(getApplicationContext(),"Level complete!" + complete, Toast.LENGTH_SHORT);
                toast.show();
                startActivity(intent);
            }
        });

        failButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), LevelSelection.class);
                intent.putExtra("complete",true);
                intent.putExtra("lvl",level);
                Toast toast = Toast.makeText(getApplicationContext(),"Level failed :(", Toast.LENGTH_SHORT);
                toast.show();
                startActivity(intent);
            }
        });
    }
}