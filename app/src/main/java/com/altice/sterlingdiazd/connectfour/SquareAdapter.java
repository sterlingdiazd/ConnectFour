package com.altice.sterlingdiazd.connectfour;

import android.content.Context;
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

public class SquareAdapter extends ArrayAdapter<Square> implements View.OnClickListener
{
    private Square square;

    public SquareAdapter(Context context, ArrayList<Square> board)
    {
        super(context, 0, board);
    }

    @Override public View getView(int position, View convertView, ViewGroup parent)
    {

        square = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.square_item, parent, false);
        }

        return convertView;
    }

    @Override public void onClick(View v)
    {
        Toast.makeText(v.getContext(), "Position: " + square.getRow() + "x" + square.getColumn(), Toast.LENGTH_LONG).show();

    }
}
