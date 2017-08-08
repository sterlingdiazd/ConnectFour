package com.altice.sterlingdiazd.connectfour;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sterling on 7/15/2017.
 */

public class SquareAdapter extends ArrayAdapter<Position>
{
    private Position boardPosition;

    public SquareAdapter(Context context, ArrayList<Position> board)
    {
        super(context, 0, board);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent)
    {

        boardPosition = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.square_item, parent, false);
            changeBackGround(convertView, R.drawable.button_square);
        }

        return convertView;
    }

    public void changeBackGround(View view, int drawableResource)
    {
        Drawable drawable = view.getContext().getResources().getDrawable(drawableResource);
        view.setBackground(drawable);

    }
}
