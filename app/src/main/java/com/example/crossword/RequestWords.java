package com.example.crossword;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

public class RequestWords extends Thread {
    public interface RequestOperatorListener{
        void success (List<Word> publication);
        void failed (int responseCode);
    }

    LoadingDialog loadingDialog;
    private RequestWords.RequestOperatorListener listener;
    private int responseCode;
    String[] splits;
    String[] splits2;
    String wordsString;

    public void setListener (RequestWords.RequestOperatorListener listener) {
        this.listener = listener;
        wordsString = "";
    }

    @Override
    public void run() {
        super.run();
        try {
            List<Word> publication = request();

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
        catch (Exception e){
            failed(-3);
        }
    }

    public List<Word> request() throws IOException, JSONException, Exception {
        List<URL> objects = new ArrayList<>();
        List<Word> words = new ArrayList<>();
        if(wordsString != "")
        {
            String[] wordsArray = wordsString.split("\n");
            Log.e("Words", wordsArray.toString());
            Random gen = new Random();
            int max = wordsArray.length; // Maximum Random value to generate

            ArrayList<String> picked = new ArrayList<String>(); // List of picked numbers

            for(int i = 0; i < 1200; i++) {
                int index = gen.nextInt(max);
                if(!picked.contains(wordsArray[index])) // If the number isn't already picked
                    picked.add(wordsArray[index]); // Add it to the "picked" list
            }
            int index = 0;
            Collections.sort(picked);
            for(String word : picked){

                if(word.length() < 1)
                    continue;
                 word = word.substring(0,1).toUpperCase() + word.substring(1);
                URL object = new URL("https://www.lietuviuzodynas.lt/zodynas/" + word);

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
                String received = responseStringBuffer.toString();
                if (responseCode == 200) {
                    splits2 = received.split("<h1 class=\"box-header font-museo green\" style=\"margin-top: 20px\">");
                    if(splits2.length <=1){
                        continue;
                    }
                    splits2 = splits2[1].split("</h1>");
                    if(splits2.length <=1){
                        continue;
                    }
                    received = splits2[0];
                    splits = splits2[1].split("<div class='word-values'>");
                    if(splits.length > 1){
                        splits = splits[1].split("</span><span class='span-bg'>");
                        if(splits.length <= 1)
                            continue;
                        splits = splits[1].split("</span>");
                        if(splits.length <= 1)
                            continue;
                        Word temp = new Word();
                        temp.setLength(received.length());
                        temp.setWord(received);
                        temp.setDescription(splits[0].substring(3));
                        words.add(temp);
                    }

                }
                index++;
                loadingDialog.updateProgressBar(index/12);
            }
        }
        return words;
    }

    private void failed(int code) {
        if(listener!=null)
            listener.failed(code);
    }
    public void setWords(String words){
        wordsString = words;
    }

    public void success(List<Word> publication) {
        if(listener!=null)
            listener.success(publication);
    }
    public void setLoadingDialog(LoadingDialog loadingDialog) { this.loadingDialog = loadingDialog; }
}
