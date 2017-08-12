package com.altice.sterlingdiazd.connectfour;

/**
 * Created by Sterling on 8/10/2017.
 */

public class WinningMove extends Move
{
    private String  wonMessage = null;
    private             StringBuilder wonMessageBuilder = new StringBuilder();
    private             boolean       isWon             = false;
    public final static String        horizontally      = "horizontally";
    public final static String        vertically        = "vertically";
    public final static String        diagonally        = "diagonally";


    public WinningMove()
    {
    }

    public StringBuilder getWonMessageBuilder()
    {
        return wonMessageBuilder;
    }

    public void setWonMessageBuilder(StringBuilder wonMessageBuilder)
    {
        this.wonMessageBuilder = wonMessageBuilder;
    }

    public WinningMove(String wonMessage, boolean youWon)
    {
        this.wonMessage = wonMessage;
        this.isWon = youWon;
    }

    public String getWonMessage()
    {
        return wonMessage;
    }

    public void setWonMessage(Move move, String typeOfWinning)
    {
        String player = null;
        if(move.isFirstPlayer())
        {
            player = "Yellow ";
        } else {
            player = "Red ";
        }
        wonMessageBuilder.append(player);
        wonMessageBuilder.append("won ");
        wonMessageBuilder.append(typeOfWinning);

        this.wonMessage = wonMessageBuilder.toString();
    }

    public boolean isWon()
    {
        return isWon;
    }

    public void setWon(boolean won)
    {
        this.isWon = won;
    }
}
