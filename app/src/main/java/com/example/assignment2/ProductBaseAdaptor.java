package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductBaseAdaptor extends BaseAdapter {
    ArrayList<Product> products;
    Context context;

    public ProductBaseAdaptor(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.product_row,null);
        TextView product_name = view.findViewById(R.id.productRow_1);
        TextView product_quantity = view.findViewById(R.id.productRow_2);
        product_name.setText(products.get(i).name + "\n" + products.get(i).price);
        product_quantity.setText(Integer.toString(products.get(i).available_quantity) + "\n");
        return view;
    }
}
