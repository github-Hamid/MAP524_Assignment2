package com.example.assignment2;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Restock extends AppCompatActivity implements View.OnClickListener {
ArrayList<Product> product_list;
Button ok_btn;
Button cancel_btn;
EditText quantity_editText;
ListView stock;
ProductBaseAdaptor adaptor;
Product selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);
        product_list = ((MyApp)getApplication()).product_list;
        ok_btn = findViewById(R.id.ok_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        quantity_editText = findViewById(R.id.newQuantity);
        stock = findViewById(R.id.stock);
        adaptor = new ProductBaseAdaptor(product_list, this);
        //click listener for buttons
        ok_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);



        // set base adaptor for list view
        stock.setAdapter(adaptor);


        // click listener for list view
        stock.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               selected = product_list.get(i);
            }
        });

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id)
        {
            case R.id.ok_btn: {
                if(selected != null && !quantity_editText.getText().toString().isEmpty())
                {
                    selected.available_quantity += Integer.parseInt(quantity_editText.getText().toString());
                    adaptor = new ProductBaseAdaptor(product_list, this);
                    stock.setAdapter(adaptor);
                }
                else
                {
                    Toast.makeText(Restock.this, "Please select product and enter quantity!!!", Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.cancel_btn: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}