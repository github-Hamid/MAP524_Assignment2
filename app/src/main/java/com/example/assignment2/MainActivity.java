package com.example.assignment2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
NumberPicker picker;
String[] pickValues;
int value_picked;
TextView productName;
Button manager;
TextView total;
TextView quantity;
Button buy;
ListView product_list;
ArrayList<Product> products;

Product selected;
double totalPrice;
AlertDialog.Builder builder;
ProductBaseAdaptor adaptor;

private static final DecimalFormat df = new DecimalFormat("0.00");
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //assigning objects
        picker = findViewById(R.id.numberPicker);
        productName = findViewById(R.id.productTextView);
        manager = findViewById(R.id.manager_btn);
        total = findViewById(R.id.totalAmount);
        quantity = findViewById(R.id.quantity);
        buy = findViewById(R.id.buy_btn);
        product_list = findViewById(R.id.productList);
        builder = new AlertDialog.Builder(this);
        //setting click listener
        buy.setOnClickListener(this);
        manager.setOnClickListener(this);


        //assigning products array list to product_list in the my app
        products = ((MyApp)getApplication()).product_list;

        //displaying product list in the list view
        adaptor = new ProductBaseAdaptor(products, this);
         product_list.setAdapter(adaptor);


        //product_list on click listener
        product_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               selected = products.get(i);
               productName.setText(selected.name);
               if(value_picked != 0)
               {
                    totalPrice = Double.parseDouble(df.format(value_picked * selected.price));

                   total.setText(Double.toString(totalPrice));
                   if(value_picked > selected.available_quantity)
                       Toast.makeText(MainActivity.this, "Not enough quantity in the stock!!!", Toast.LENGTH_LONG).show();
               }
            }
        });

        //assigning picker range and numbers
        pickValues = new String[101];
        for(int i = 0 ; i < 101; i++)
        {
            pickValues[i] = Integer.toString(i);

        }
        picker.setMinValue(0);
        picker.setMaxValue(100);
        picker.setDisplayedValues(pickValues);

         //picker change listener
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                value_picked = numberPicker.getValue();

                if(value_picked != 0)
                quantity.setText(Integer.toString(value_picked));

                if(selected != null && value_picked != 0)
                {
                    totalPrice = Double.parseDouble(df.format(value_picked * selected.price));
                    total.setText(Double.toString(totalPrice));
                    if(value_picked > selected.available_quantity)
                    Toast.makeText(MainActivity.this, "Not enough quantity in the stock!!!", Toast.LENGTH_LONG).show();
                }

                if(value_picked == 0)
                {
                    quantity.setText(R.string.quantity);
                    total.setText(R.string.total_amount);
                }


            }

        }

        );


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id)
        {
            case R.id.manager_btn: {
                // defining intent for manager activity
                Intent intent = new Intent(this, ManagerPanel.class);
                startActivity(intent);
                break;
            }

            //clicking on Buy button
            case R.id.buy_btn: {
                if(value_picked == 0 || selected == null)
                    Toast.makeText(this, "All fields are required!!!", Toast.LENGTH_LONG).show();
                else if(value_picked > selected.available_quantity)
                {
                    Toast.makeText(this, "Not enough quantity in the stock!!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    //create new object to add to purchased array list
                    Purchase purchased = new Purchase(value_picked, selected.name, totalPrice, new Date());
                  selected.available_quantity -= value_picked;
                  selected = null;
                  value_picked = 0;
                  productName.setText(R.string.product_name);
                  quantity.setText(R.string.quantity);
                  total.setText(R.string.total_amount);
                  //add to purchase array list
                  ((MyApp)getApplication()).purchase.add(purchased);

                    //updating the activity list view
                    adaptor = new ProductBaseAdaptor(products, this);
                    product_list.setAdapter(adaptor);
                    // showing builder
                    builder.setTitle("Thank You for your purchase");
                    builder.setMessage("your purchase is " + purchased.quantity + " " + purchased.name + " for " +  purchased.price);
                    builder.setCancelable(true);
                    builder.show();
                }
                break;
            }

        }
    }
}