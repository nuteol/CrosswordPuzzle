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
    public ListAdapter(Context context, List<Crossword> objects) {
        super(context, R.layout.listitemdesign);
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
        ImageView image = (ImageView) view.findViewById(R.id.playImage);

        Crossword puzzle = getItem(position);

        title.setText(puzzle.getName());
        int size = puzzle.getSize();
        description.setText("Puzzle size:" + size + "x" + size);
        Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(),R.drawable.baseline_play_arrow_black_48dp);
        image.setImageBitmap(bitmap);
        return view;
    }
}
