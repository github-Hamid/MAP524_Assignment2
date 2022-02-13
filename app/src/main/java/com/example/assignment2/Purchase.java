package com.example.assignment2;

import java.util.Date;

public class Purchase {
    int quantity;
    String name;
    double price;
    Date date;

    public Purchase()
    {
        quantity = 0;
        name = null;
        price = 0;
        date = null;
    }

    public Purchase(int quantity, String name, double price, Date date)
    {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.date = date;
    }

    public String toString()
    {
        return (this.name + "            " + this.quantity + "\n" + this.price);
    }

    public static String print(Purchase p)
    {
        return ("Product: " + p.name + "\n" + "Price: " + p.price + "\n" + "Purchase Date: " + p.date);
    }
}
