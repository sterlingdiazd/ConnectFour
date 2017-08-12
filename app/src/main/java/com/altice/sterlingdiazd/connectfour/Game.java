package com.altice.sterlingdiazd.connectfour;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sterling on 8/10/2017.
 */

class Game
{
    private static final Game ourInstance = new Game();
    private List<Move> moveList;
    static int columnSize = 7;
    static int rowSize    = 6;
    public static int timeToPlayAgain    = 1000;

    private int positionOfView;

    private Game()
    {
        moveList = new ArrayList<Move>();
    }

    public static Game getInstance()
    {
        return ourInstance;
    }

    public List<Move> getMoveList()
    {
        return moveList;
    }

    public void setMoveList(List<Move> moveList)
    {
        this.moveList = moveList;
    }

    public int getPositionOfView()
    {
        return positionOfView;
    }

    public void setPositionOfView(int positionOfView)
    {
        this.positionOfView = positionOfView;
    }

    static void cleanBoard(Context context, GridView gridViewBoard, SquareAdapter squareAdapter)
    {
        MediaPlayer.create(context, R.raw.clean_board).start();
        Game.getInstance().getMoveList().clear();
        gridViewBoard.setAdapter(squareAdapter);
        gridViewBoard.refreshDrawableState();
        gridViewBoard.setEnabled(true);
    }
}
