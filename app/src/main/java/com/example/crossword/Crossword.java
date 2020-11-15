package com.example.crossword;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Entity
@TypeConverters(CharConverters.class)
public class Crossword implements Parcelable, Comparable<Crossword> {
    @NonNull
    @ColumnInfo(name = "data")
    private char[][] data;
    @NonNull
    @ColumnInfo(name = "name")
    private String name;
    @NonNull
    @ColumnInfo(name = "body")
    private String body;
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    private int id;
    @NonNull
    @ColumnInfo(name = "userId")
    private int userId;

    @NonNull
    @ColumnInfo(name = "size")
    private int size;
    @NonNull
    @ColumnInfo(name = "words")
    private ArrayList<Word> words;
    @NonNull
    @ColumnInfo(name = "object")
    private JSONObject object;


    private boolean isEnabled;

    public Crossword() {}

    @Ignore
    public Crossword(char[][] data, String name, int size, ArrayList<Word> words) {
        this.data = data;
        this.name = name;
        this.size = size;
        this.words = words;
        this.isEnabled = true;
    }

    @Ignore
    protected Crossword(Parcel in) {
        this.data = (char[][]) in.readArray(char[][].class.getClassLoader());
        this.name = in.readString();
        this.size = in.readInt();
        this.words = (ArrayList<Word>) in.readArrayList(ArrayList.class.getClassLoader());
    }

    @Ignore
    public static final Creator<Crossword> CREATOR = new Creator<Crossword>() {
        @Override
        public Crossword createFromParcel(Parcel parcel) {
            return new Crossword(parcel);
        }

        @Override
        public Crossword[] newArray(int i) {
            return new Crossword[i];
        }
    };

    public boolean isEnabled()
    {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    public char[][] getData() {
        return data;
    }

    public void setData(@NonNull char[][] newData) {
        this.data = newData;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(@NonNull int size) {
        this.size = size;
    }

    public void resetSize() {
        this.size = data.length;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(@NonNull ArrayList<Word> words) {
        this.words = words;
    }

    public String getBody() { return body; }

    public void setBody(@NonNull String body) { this.body = body; }

    public int getId() { return id; }

    public void setId(@NonNull int id) { this.id = id; }

    public int getUserId() { return userId; }

    public void setUserId(@NonNull int userId) { this.userId = userId; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeArray(data);
        parcel.writeString(name);
        parcel.writeInt(size);
        parcel.writeValue(words);
    }

    @Override
    public int compareTo(Crossword crossword) {
        return this.getName().compareTo(crossword.getName());
    }

    public JSONObject getObject() { return this.object; }

    public void setObject(@NonNull JSONObject object) {
        this.object = object;
    }
}
