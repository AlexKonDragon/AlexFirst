package com.company;

public class Cup
{
    private final int volume;
    private Drink drink;
    private int drinkVolume = 0;
    private boolean clean = true;
    private double price = 0;

    public Cup () {
        this.volume = 250;
    }

    public Cup (int volume) throws Exception{
        checkNegative( volume );
        this.volume = volume;
    }


    public int getVolume()
    {
        return volume;
    }


    public int getDrinkVolume()
    {
        return drinkVolume;
    }
}
