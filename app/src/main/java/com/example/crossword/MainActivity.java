package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    AppDatabase db;
    Button _button;
    Button _helpButton;
    Button _quickPlay;
    QuickPlayDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _button = (Button) findViewById(R.id.button);
        _helpButton = (Button) findViewById(R.id.button4);
        _quickPlay = (Button) findViewById(R.id.button3);
        dialog = new QuickPlayDialog(this);
        db = AppActivity.getDatabase();

        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //on click
                Intent intent = new Intent(getBaseContext(), LevelSelection.class);
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

        _quickPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContext(MainActivity.this);
                dialog.startQuickplayDialog();
            }
        });


    }
}