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
        textView.setText("Tai yra kryžiažodžio programėlė.\n" +
                "Taigi, kas yra kryžiažodis?\n" +
                "Kryžiažodis – žaidimas, kuriame spėjami žodžiai ir įrašomi į tam tikru būdu piešiamus langelius. Dažniausiai žodžiai susikerta.\n" +
                "Žaidimo tikslas:\n" +
                "Išspresti kryžiažodį naudojantis kuo mažiau pagalbos ir surenkant kuo daugiau taškų. Naudojantis pagalba taškai nerenkami." +
                "Taip pat galimų pagalbų skaičius ribotas kiekvienam kryžiažodžiui." +
                "Kaip žaisti žaidimą:\n" +
                "Pasirinkite langelį, kuriame prasideda žodis. Žaidimo lange iššoks žodžio paaiškinimas, iš kurio jūs turite išsiaiškinti " +
                "koks tai yra žodis.\n" +
                "Nežinote žodžio?\n" +
                "Paspauskite pagalbos mygtuką, jis jums parodys po raidę, tačiau tai kainuos taškus");

        _button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}