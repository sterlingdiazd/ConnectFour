package com.altice.sterlingdiazd.connectfour;

/**
 * Created by Sterling on 8/8/2017.
 */

public class Move
{
    private boolean isFirstPlayer;
    private Position position;

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public boolean isFirstPlayer()
    {
        return isFirstPlayer;
    }

    public void setFirstPlayer(boolean firstPlayer)
    {
        isFirstPlayer = firstPlayer;
    }
}
