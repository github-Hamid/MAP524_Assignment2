package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
ListView list;
ArrayList purchasedProducts;
PurchaseBaseAdaptor adaptor;
ArrayList<Purchase> purchasedArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //assigning fields
        list = findViewById(R.id.purchasedProducts);
        purchasedProducts = ((MyApp)getApplication()).purchase;

        // assigning adaptor for list view
        adaptor = new PurchaseBaseAdaptor(purchasedProducts, this);
        list.setAdapter(adaptor);

        //defining intent for detailed activity
        Intent intent = new Intent(this, Detailed.class);
        // set on click listener for list view
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                intent.putExtra("detailedInfo", Purchase.print((Purchase)purchasedProducts.get(i)));
                startActivity(intent);
            }
        });
    }
}