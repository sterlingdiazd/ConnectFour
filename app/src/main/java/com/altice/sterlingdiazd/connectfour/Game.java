package com.altice.sterlingdiazd.connectfour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sterling on 8/8/2017.
 */

public class Game
{
    List<Move> moveList;
    public static int columnSize = 7;
    public static int rowSize = 6;


    public Game()
    {
        moveList = new ArrayList<Move>();
    }

    public List<Move> getMoveList()
    {
        return moveList;
    }

    public void setMoveList(List<Move> moveList)
    {
        this.moveList = moveList;
    }
}
