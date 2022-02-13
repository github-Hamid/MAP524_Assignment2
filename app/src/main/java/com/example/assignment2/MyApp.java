package com.example.assignment2;

import android.app.Application;

import java.util.ArrayList;

public class MyApp extends Application {
 ArrayList<Product> product_list = new ArrayList<Product>() {
  {
   add(new Product(10, "Pants", 20.44));
   add(new Product(100, "Shoes", 10.44));
   add(new Product(30, "Hats", 5.9));
  }
 };

 ArrayList<Purchase> purchase = new ArrayList<Purchase>(0);

}
