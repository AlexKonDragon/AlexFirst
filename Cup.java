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


    public void topUp (int value, Drink drink) throws Exception{
        if (this.drink != null && this.drinkVolume > 0 && this.drink != drink ) {
            throw new CupException(String.format("Different drinks can not be mixed: %s %s", this.drink, drink ));
        }
        if (!this.clean && this.drink != drink ) {
            throw new CupException("Please wash the cup before top up something");
        }
        updateDrinkVolume(value);
        this.clean = false;
        this.drink = drink;
        contentPrice();
    }

    public void topUp (int value) throws Exception{
        if (this.drink == null) {
            throw new CupException("Please specify a drink");
        }
        updateDrinkVolume(value);
    }

    private void updateDrinkVolume(int value) throws Exception {
        checkNegative( value );
        int newVolume = this.drinkVolume + value;
        if (this.volume < newVolume) {
            throw new CupException( String.format( "drink volume %s more than cup volume %s", newVolume, this.volume ) );
        }
        this.drinkVolume = newVolume;
        contentPrice();
    }
    public void drink (int value) throws Exception{
        checkNegative( value );
        if (this.drinkVolume < value) {
            throw new CupException( String.format( "can't drink more %s than exists in the cup %s", value, this.drinkVolume) );
        }
        this.drinkVolume -= value;
        contentPrice();
    }

    public void wash () {
        this.clean = true;
        this.drinkVolume = 0;
        this.drink = null;
        this.price = 0;
    }

    @Override
    public String toString() {
        return "Cup{" +
                "volume=" + volume +
                ", drink=" + drink +
                ", drinkVolume=" + drinkVolume +
                ", clean=" + clean +
                ", price=" + price +
                '}';
    }

    private void checkNegative (int value) throws Exception{
        if (value <= 0) {
            throw new Exception( "negative value not allowed: " + value );
        }
    }

    private void contentPrice() {
        if (this.drink == null) {
            this.price = 0;
        } else {
            this.price = drink.price() * ((double) this.drinkVolume / 100);
        }
    }
}
