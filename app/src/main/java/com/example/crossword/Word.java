package com.example.crossword;

import android.os.Parcel;
import android.os.Parcelable;

public class Word implements Parcelable {
    String word;
    String description;
    int startX, startY;
    int length;
    boolean horizontal;

    public Word() {}

    public Word(String word, String description, int startX, int startY, int length, boolean horizontal) {
        this.word = word;
        this.description = description;
        this.startX = startX;
        this.startY = startY;
        this.length = length;
        this.horizontal = horizontal;
    }

    public Word(Parcel parcel) {
        this.word = parcel.readString();
        this.description = parcel.readString();
        this.startX = parcel.readInt();
        this.startY = parcel.readInt();
        this.length = parcel.readInt();
        this.horizontal = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Word> CREATOR = new Creator<Word>() {
        @Override
        public Word createFromParcel(Parcel parcel) {
            return new Word(parcel);
        }

        @Override
        public Word[] newArray(int i) {
            return new Word[i];
        }
    };

    public void setWord(String word) {  this.word = word;    }
    public String getWord() { return word;  }

    public void setDescription(String description) { this.description = description;    }
    public String getDescription() {    return description; }

    public void setStart(int x, int y) {
        this.startX = x;
        this.startY = y;
    }
    public int getStartX() {   return this.startX; }
    public int getStartY() {   return this.startY; }

    public void setLength(int length) { this.length = length;}
    public int getLength() {    return length; }

    public boolean isHorizontal() {return horizontal;}
    public void setHorizontal(boolean horizontal) { this.horizontal = horizontal; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(word);
        parcel.writeString(description);
        parcel.writeInt(startX);
        parcel.writeInt(startY);
        parcel.writeInt(length);
        parcel.writeValue(horizontal);
    }
}
