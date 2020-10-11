package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button _button;
    Button _settingsButton;
    Button _exitButton;
    Button _helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _button = (Button) findViewById(R.id.button);
        _settingsButton = (Button) findViewById(R.id.button2);
        _helpButton = (Button) findViewById(R.id.button4);
        _exitButton = (Button) findViewById(R.id.button3);

        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on click
                Intent intent = new Intent(getBaseContext(), LevelSelection.class);
                startActivity(intent);
            }
        });

        _settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        _helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HelpActivity.class);
                startActivity(intent);
            }
        });

        _exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //exit on click
                finish();
                System.exit(0);
            }
        });


    }
}