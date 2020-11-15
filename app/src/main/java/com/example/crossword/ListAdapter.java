package com.example.crossword;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Crossword> {
    List<Crossword> objects;
    public ListAdapter(Context context, List<Crossword> objects) {
        super(context, R.layout.listitemdesign, objects);
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater =
                    (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listitemdesign, null);
        }

        TextView title = (TextView) view.findViewById(R.id.levelTitle);
        TextView description = (TextView) view.findViewById(R.id.levelDescription);

        Crossword crossword = getItem(position);

        title.setText(crossword.getName().substring(0,10));
        description.setText(crossword.getBody().substring(0,50));
        //Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(),R.drawable.baseline_play_arrow_black_48dp);
        //image.setImageBitmap(bitmap);
        return view;
    }




}
