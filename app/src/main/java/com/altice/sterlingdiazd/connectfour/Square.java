package com.altice.sterlingdiazd.connectfour;

import java.io.Serializable;

/**
 * Created by Sterling on 7/20/2017.
 */

public class Square implements Serializable
{
    public String id, row, column;

    public Square()
    {
    }

    public Square(String id, String row, String column)
    {
        this.id = id;
        this.row = row;
        this.column = column;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getRow()
    {
        return row;
    }

    public void setRow(String row)
    {
        this.row = row;
    }

    public String getColumn()
    {
        return column;
    }

    public void setColumn(String column)
    {
        this.column = column;
    }
}
