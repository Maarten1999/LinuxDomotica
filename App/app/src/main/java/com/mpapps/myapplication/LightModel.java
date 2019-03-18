package com.mpapps.myapplication;

public class LightModel
{
    int id;
    String name;
    String color;
    boolean state;

    public LightModel(int id, String name, String color, boolean state)
    {
        this.id = id;
        this.name = name;
        this.color = color;
        this.state = state;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public boolean isState()
    {
        return state;
    }

    public void setState(boolean state)
    {
        this.state = state;
    }
}
