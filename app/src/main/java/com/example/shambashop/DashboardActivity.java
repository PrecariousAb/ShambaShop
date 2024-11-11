package com.example.shambashop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button listProductsButton, viewCartButton, viewReportsButton;
    private Button viewOrderHistoryButton, browsePurchaseButton, checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        welcomeText = findViewById(R.id.welcomeText);
        listProductsButton = findViewById(R.id.listProductsButton);
        viewCartButton = findViewById(R.id.viewCartButton);
        viewReportsButton = findViewById(R.id.viewReportsButton);
        viewOrderHistoryButton = findViewById(R.id.viewOrderHistoryButton);
        browsePurchaseButton = findViewById(R.id.browsePurchaseButton);
        checkoutButton = findViewById(R.id.checkoutButton);

        // Navigate to Product Listing Activity
        listProductsButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, ProductListingActivity.class))
        );

        // Navigate to Cart Activity
        viewCartButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, CartActivity.class))
        );

        // Navigate to Reports Activity
        viewReportsButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, ReportsActivity.class))
        );

        // Navigate to Order History Activity
        viewOrderHistoryButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, OrderHistoryActivity.class))
        );

        // Navigate to Browse & Purchase Activity
        browsePurchaseButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, BrowsePurchaseActivity.class))
        );

        // Navigate to Checkout Activity
        checkoutButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, CheckoutActivity.class))
        );
    }
}
