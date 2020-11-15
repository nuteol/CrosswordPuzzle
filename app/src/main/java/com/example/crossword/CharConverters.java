package com.example.crossword;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CharConverters {
    @TypeConverter
    public static char[][] formStringToCharArray(String value) {
        Type charType = new TypeToken<char[][]>() {}.getType();
        return new Gson().fromJson(value,charType);
    }

    @TypeConverter
    public static String fromCharArrayToString(char[][] data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        return json;
    }

    @TypeConverter
    public static ArrayList<Word> fromStringToArrayList(String value) {
        Type listType = new TypeToken<ArrayList<Word>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayListToString(ArrayList<Word> data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        return json;
    }

    @TypeConverter
    public static JSONObject fromStringToJSONObject(String value) {
        Type objectType = new TypeToken<JSONObject>() {}.getType();
        return new Gson().fromJson(value, objectType);
    }

    @TypeConverter
    public static String fromJSONObjectToString(JSONObject object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return json;
    }
}
