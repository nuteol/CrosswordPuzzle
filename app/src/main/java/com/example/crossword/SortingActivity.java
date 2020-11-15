package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingActivity extends AppCompatActivity {

    private ListView myListView;
    private  ListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);
        myListView = (ListView) findViewById(R.id.sortListView);

        Intent intent = getIntent();
        List<Word> words = intent.getParcelableArrayListExtra("words");
        Word[] sortedWords = new Word[words.size()];
        sortedWords = words.toArray(sortedWords);

        for(int i = 0; i < sortedWords.length - 1; i++){
            for(int j = 0; j < sortedWords.length - i - 1; j++) {
                if(sortedWords[j].getWord().compareTo(sortedWords[j + 1].getWord()) > 0) {
                    Word temp =  sortedWords[j];
                    sortedWords[j] = sortedWords[j+1];
                    sortedWords[j+1] = temp;
                }
            }
        }
        words = new ArrayList<>();
        for(Word w : sortedWords)
            words.add(w);
        //adapter = new ListAdapter(this, words);
        myListView.setAdapter(adapter);
    }
}