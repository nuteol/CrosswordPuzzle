package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    Button _button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        _button = (Button) findViewById(R.id.button5);
        textView = (TextView) findViewById(R.id.textView4);
        textView.setText("help help help help\nhelp help help help\nhelp help help help" +
                "\nhelp help help help\nhelp help help help\nhelp help help help\n" +
                "help help help help\nhelp help help help\nhelp help help help\n" +
                "help help help help\nhelp help help help\nhelp help help help\n");

        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}