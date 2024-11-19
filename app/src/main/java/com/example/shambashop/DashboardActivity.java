package com.example.shambashop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private TextView welcomeText;
    private boolean isFarmer; // Differentiation based on user type

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve user type from intent
        Intent intent = getIntent();
        isFarmer = intent.getBooleanExtra("isFarmer", false);

        // Load specific layout based on user type
        if (isFarmer) {
            setContentView(R.layout.activity_dashboard_farmer);
            setupFarmerDashboard();
        } else {
            setContentView(R.layout.activity_dashboard_customer);
            setupCustomerDashboard();
        }
    }

    private void setupFarmerDashboard() {
        welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome, Farmer!");

        // Farmer-specific actions
        Button listProductsButton = findViewById(R.id.listProductsButton);
        Button viewReportsButton = findViewById(R.id.viewReportsButton);

        listProductsButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, ProductListingActivity.class))
        );

        viewReportsButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, ReportsActivity.class))
        );
    }

    private void setupCustomerDashboard() {
        welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome, Customer!");

        // Customer-specific actions
        Button browseProductsButton = findViewById(R.id.browsePurchaseButton);
        Button viewCartButton = findViewById(R.id.viewCartButton);
        Button checkoutButton = findViewById(R.id.checkoutButton);
        Button orderHistoryButton = findViewById(R.id.viewOrderHistoryButton);

        browseProductsButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, BrowsePurchaseActivity.class))
        );

        viewCartButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, CartActivity.class))
        );

        checkoutButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, CheckoutActivity.class))
        );

        orderHistoryButton.setOnClickListener(view ->
                startActivity(new Intent(DashboardActivity.this, OrderHistoryActivity.class))
        );
    }
}
