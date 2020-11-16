package com.example.crossword;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LevelSelection extends AppCompatActivity implements RequestOperator.RequestOperatorListener {

    Button back;
    ListView puzzleList;
    Button sort;
    Button query;
    Button animation;
    Button delete;
    int maxLevelUnlocked = 1;
    private ListAdapter adapter;
    List<Crossword> publication;
    LoadingDialog loading;
    private  AppDatabase db;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);
        db = AppActivity.getDatabase();


        back = (Button) findViewById(R.id.button6);
        sort = (Button) findViewById(R.id.sortingbutton);
        query = (Button) findViewById(R.id.query_activity);
        delete = (Button) findViewById(R.id.delete_button);
        puzzleList = (ListView) findViewById(R.id.puzzleList);
        loading = new LoadingDialog(this);
        context = this;

        //reads data from file
        Type pubType = new TypeToken<List<Crossword>>() {}.getType();
        String data = readFromFile(this);
        if(data != "") {
            publication = new Gson().fromJson(data, pubType);
            setAdapter(puzzleList, publication);
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });



        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(publication);
                setAdapter(puzzleList,publication);
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });

        puzzleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Crossword selected = (Crossword) puzzleList.getItemAtPosition(i);
                Intent intent = new Intent(getBaseContext(), GameScreen.class);
                intent.putExtra("puzzle", (new Gson()).toJson(selected));
                intent.putExtra("lvl",1);
                startActivity(intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeToFile("", context);
            }
        });
    }

    void setAdapter(ListView listView, List<Crossword> crosswords)
    {
        adapter = new ListAdapter(this,crosswords);
        listView.setClickable(true);
        listView.setAdapter(adapter);
    }

    private void sendRequest(){
        RequestOperator ro = new RequestOperator();
        ro.setListener(this);
        //setIndicatorStatus(IndicatingView.LOADING);


        loading.startLoadingAnimation();
        ro.setLoadingDialog(loading);
        ro.start();
    }

    public void updateProgress(int progress) {
        loading.updateProgressBar(progress);
    }

    public void updatePublication(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(publication!=null){
                    setAdapter(puzzleList, publication);
                    puzzleList.setClickable(true);
                } else {
                    setAdapter(puzzleList,null);
                    puzzleList.setClickable(true);
                }
            }
        });
    }

    @Override
    public void success(List<Crossword> publication) {
        this.publication = publication;
        //addToDb(publication);
        Type pubType = new TypeToken<List<Crossword>>() {}.getType();
        writeToFile(new Gson().toJson(publication, pubType),this);
        updatePublication();
        loading.setIndicatorStatus(IndicatingView.SUCCESS);
        loading.dismissLoading();
    }

    @Override
    public void failed(int responseCode) {
        this.publication = null;
        updatePublication();
        loading.setIndicatorStatus(IndicatingView.FAILED);
        loading.dismissLoading();
    }

    private void addToDb(List<Crossword> crosswords) {
        for(Crossword crossword : crosswords) {
            db.crosswordDAO().insert(crossword);
        }
        Toast.makeText(getApplicationContext(),
                "Kryžiažodžiai išsaugoti sėkmingai",
                Toast.LENGTH_LONG).show();
    }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("crosswordData.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("crosswordData.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}