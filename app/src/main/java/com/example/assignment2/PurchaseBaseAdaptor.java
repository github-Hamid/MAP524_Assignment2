package com.example.assignment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PurchaseBaseAdaptor extends BaseAdapter {
    ArrayList<Purchase> purchaseList;
    Context context;

    public PurchaseBaseAdaptor(ArrayList<Purchase> purchaseList, Context context) {
        this.purchaseList = purchaseList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return purchaseList.size();
    }

    @Override
    public Object getItem(int i) {
        return purchaseList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.product_row,null);
        TextView purchase_name = view.findViewById(R.id.productRow_1);
        TextView purchase_price = view.findViewById(R.id.productRow_2);
        purchase_name.setText(purchaseList.get(i).name + "\n" + purchaseList.get(i).quantity);
        purchase_price.setText(Double.toString(purchaseList.get(i).price) + "\n");
        return view;
    }
}
