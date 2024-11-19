package com.example.shambashop;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class BrowsePurchaseActivity extends AppCompatActivity {

    private RecyclerView productRecyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_purchase);

        // Initialize RecyclerView
        productRecyclerView = findViewById(R.id.productRecyclerView);
        productRecyclerView.setHasFixedSize(true);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load some dummy products (In a real app, fetch from a database)
        productList = new ArrayList<>();
        productList.add(new Product("Tomatoes", "Fresh farm tomatoes", 2.99, R.drawable.tomato_image));
        productList.add(new Product("Carrots", "Organic carrots", 1.99, R.drawable.carrots_image));
        productList.add(new Product("Potatoes", "Farm fresh potatoes", 3.49, R.drawable.potatoes_image));

        // Set up adapter
        productAdapter = new ProductAdapter(productList, this);
        productRecyclerView.setAdapter(productAdapter);
    }
}