package com.example.crossword;

import android.util.Log;
import android.view.Display;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RequestOperator extends Thread {

    public interface RequestOperatorListener{
        void success (ModelPost publication);
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
            ModelPost publication = request();

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

    public ModelPost request() throws IOException, JSONException {
        URL object = new URL("https://jsonplaceholder.typicode.com/posts/1");

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

        if (responseCode == 200)
            return parsingJsonObject(responseStringBuffer.toString());
        else
            return null;
    }

    public ModelPost parsingJsonObject(String response) throws JSONException {
        JSONObject object = new JSONObject(response);
        ModelPost post = new ModelPost();

        post.setJSONObject(object);
        post.setId(object.optInt("id",0));
        post.setUserId(object.optInt("userId", 0));

        post.setTitle(object.getString("title"));
        post.setBodyText(object.getString("body"));

        return post;
    }

    private void failed(int code) {
        if(listener!=null)
            listener.failed(code);
    }

    public void success(ModelPost publication) {
        if(listener!=null)
            listener.success(publication);
    }
}
