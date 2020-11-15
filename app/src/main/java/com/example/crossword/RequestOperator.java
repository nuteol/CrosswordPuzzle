package com.example.crossword;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Display;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class RequestOperator extends Thread {
    LoadingDialog loadingDialog;
    public interface RequestOperatorListener{
        void success (List<Crossword> publication);
        void failed (int responseCode);
    }


    private RequestOperatorListener listener;
    private int responseCode;

    public void setListener (RequestOperatorListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        super.run();
        try {
            List<Crossword> publication = request();

            if(publication!=null)
                success(publication);
            else
                failed(responseCode);
        }
        catch (IOException e){
            failed(-1);
        }
        catch (JSONException e){
            failed(-2);
        }
    }

    public List<Crossword> request() throws IOException, JSONException {
        List<Crossword> crosswords = new ArrayList<>();
        List<URL> objects = new ArrayList<>();
        for(int i = 1; i < 100; i++) {
            URL object = new URL("https://jsonplaceholder.typicode.com/posts/"+String.valueOf(i));

            HttpsURLConnection connection = (HttpsURLConnection) object.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("Content-Type", "application/json");

            responseCode = connection.getResponseCode();
            Log.i("Response Code", String.valueOf(responseCode));
            InputStreamReader inputStreamReader;

            if(responseCode == 200) {
                inputStreamReader = new InputStreamReader(connection.getInputStream());
            } else {
                inputStreamReader = new InputStreamReader(connection.getErrorStream());
            }

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String inputLine;
            StringBuffer responseStringBuffer = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                responseStringBuffer.append(inputLine);
            }
            bufferedReader.close();

            Log.i("Response Result", responseStringBuffer.toString());
            Crossword temp = parsingJsonObject(responseStringBuffer.toString());
            crosswords.add(temp);
            loadingDialog.updateProgressBar(i);
        }
        if (responseCode == 200)
            return crosswords;
        else
            return null;
    }

    public Crossword parsingJsonObject(String response) throws JSONException {
        JSONObject object = new JSONObject(response);
        Crossword post = new Crossword();


        ArrayList<Word> words;
        words = new ArrayList<>();
        words.add(new Word("cat","Feline animal",0,0,3,true));
        words.add(new Word("pat","Repeated action of touching someone with affection",0,0,3,true));
        words.add(new Word("sat","Past tense of sit",0,0,3,true));
        words.add(new Word("art","Da Vinki",0,0,3,true));
        words.add(new Word("car","Automotive vehicle, with some sort of internal propulsion",0,0,3,false));
        words.add(new Word("tip","An end to some sort of thing.",2,0,3,false));
        words.add(new Word("rip","Tear something of",0,2,3,true));
        char[][] data = {   {'c','a','t'},
                {'a','0','i'},
                {'r','i','p'}};
        post.setObject(object);
        post.setId(object.optInt("id",0));
        post.setUserId(object.optInt("userId", 0));

        post.setName(object.getString("title"));
        post.setBody(object.getString("body"));
        post.setSize(3);
        post.setWords(words);
        post.setData(data);

        return post;
    }

    private void failed(int code) {
        if(listener!=null)
            listener.failed(code);
    }

    public void success(List<Crossword> publication) {
        if(listener!=null)
            listener.success(publication);
    }

    public void setLoadingDialog(LoadingDialog loadingDialog) { this.loadingDialog = loadingDialog; }
}
