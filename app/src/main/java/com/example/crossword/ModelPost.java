package com.example.crossword;

import org.json.JSONObject;

public class ModelPost {
    int id;
    int userId;
    String title;
    String bodyText;
    JSONObject object;

    public ModelPost() {}

    public ModelPost(int id, int userId, String title, String bodyText, JSONObject object) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.bodyText = bodyText;
        this.object = object;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText)
    {
        this.bodyText = bodyText;
    }

    public JSONObject getJSONObject() { return object; }

    public void setJSONObject(JSONObject object) { this.object = object; }
}
