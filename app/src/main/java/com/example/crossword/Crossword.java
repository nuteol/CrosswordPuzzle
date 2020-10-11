package com.example.crossword;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Crossword implements Parcelable {
    private char[][] data;
    private String name;
    private int size;
    List<Word> words;

    public Crossword() {}

    public Crossword(char[][] data, String name, int size, List<Word> words) {
        this.data = data;
        this.name = name;
        this.size = size;
        this.words = words;
    }

    protected Crossword(Parcel in) {
        this.data = (char[][]) in.readValue(char[][].class.getClassLoader());
        this.name = in.readString();
        this.size = in.readInt();
        this.words = (List<Word>) in.readArrayList(List.class.getClassLoader());
    }

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

    public char[][] getData() {
        return data;
    }

    public void setData(char[][] newData) {
        this.data = newData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void resetSize() {
        this.size = data.length;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(data);
        parcel.writeString(name);
        parcel.writeInt(size);
        parcel.writeValue(words);
    }
}
