package com.mpapps.myapplication.Models;

import android.graphics.Color;

public enum CardColor
{
    RED(Color.RED),
    GREEN(Color.GREEN),
    YELLOW(Color.YELLOW),
    WHITE(Color.WHITE);

    private int color;

    CardColor(int c)
    {
        color = c;
    }

    public int getColor()
    {
        return color;
    }
}
