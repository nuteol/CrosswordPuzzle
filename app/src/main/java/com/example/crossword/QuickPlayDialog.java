package com.example.crossword;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuickPlayDialog implements AdapterView.OnItemSelectedListener {

    Context context;
    AppDatabase db;
    AlertDialog dialog;
    Spinner size;
    Spinner theme;
    Button create;
    Activity activity;
    int[] sizes = {3,4,5,6};
    int selectedSize = 0;

    QuickPlayDialog(Activity activity) { this.activity = activity; }

    public void startQuickplayDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View myView = inflater.inflate(R.layout.quickplay_dialog, null);
        db = AppActivity.getDatabase();
        size = (Spinner) myView.findViewById(R.id.size);
        theme = (Spinner) myView.findViewById(R.id.spinner3);
        create = (Button) myView.findViewById(R.id.button7);

        size.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(context,android.R.layout.simple_spinner_item);
        ArrayList<Integer> toAdapter = new ArrayList<>();
        for (int s: sizes) {
            toAdapter.add(s);
        }
        adapter.addAll(toAdapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        size.setAdapter(adapter);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedSize == 0)
                    selectedSize = 3;
                Crossword crossword = createCrossword(selectedSize);
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
    private Crossword createCrossword(int n) {
        Crossword crossword = new Crossword();
        List<Word> word = db.wordDAO().getAllWords();
        ArrayList<Word> picked = new ArrayList<Word>(); // List of picked numbers
        Random gen = new Random();
        int max = word.size();
        int longest = 0;
        for(int i = 0; i < n; i++) {
            int index = gen.nextInt(max);
            if(!picked.contains(word.get(index))){
                picked.add(word.get(index));
                if(word.get(index).getLength() > longest)
                    longest = word.get(index).getLength();
            }
        }
        crossword.setWords(picked);
        char[][] data = new char[n][longest];
        int i = 0;
        int j = 0;
        for(Word w : picked){
            for(j = 0; j < longest; j++){
                if(j < w.getLength()){
                    data[i][j] = w.getWord().charAt(j);
                }
                else data[i][j] = '-';

            }
            i++;
        }
        crossword.setName("Sugeneruotas");
        crossword.setSize(n);
        crossword.setData(data);
        crossword.setSize(n);
        return crossword;
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        selectedSize = sizes[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void setContext(Context context){
        this.context = context;
    }
}
