package com.example.assignment2;

public class Product {
    int available_quantity;
    String name;
    double price;

    public Product()
    {
        available_quantity = 0;
        name = null;
        price = 0;
    }

    public Product(int q, String n, double p)
    {
        available_quantity = q;
        name = n;
        price = p;
    }

public String toString()
{
    return (this.name + "            " + this.available_quantity + "\n" + this.price);
}

}
