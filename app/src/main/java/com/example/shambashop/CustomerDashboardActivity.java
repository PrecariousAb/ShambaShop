package com.example.shambashop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CustomerDashboardActivity extends AppCompatActivity {

    private Button browsePurchaseButton, viewCartButton, checkoutButton, viewOrderHistoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_customer);

        // Initialize buttons
        browsePurchaseButton = findViewById(R.id.browsePurchaseButton);
        viewCartButton = findViewById(R.id.viewCartButton);
        checkoutButton = findViewById(R.id.checkoutButton);
        viewOrderHistoryButton = findViewById(R.id.viewOrderHistoryButton);

        // Set button click listeners
        browsePurchaseButton.setOnClickListener(v -> {
            // Navigate to Browse Products activity
            Intent intent = new Intent(CustomerDashboardActivity.this, BrowsePurchaseActivity.class);
            startActivity(intent);
        });

        viewCartButton.setOnClickListener(v -> {
            // Navigate to View Cart activity
            Intent intent = new Intent(CustomerDashboardActivity.this, CartActivity.class);
            startActivity(intent);
        });

        checkoutButton.setOnClickListener(v -> {
            // Navigate to Checkout activity
            Intent intent = new Intent(CustomerDashboardActivity.this, CheckoutActivity.class);
            startActivity(intent);
        });

        viewOrderHistoryButton.setOnClickListener(v -> {
            // Navigate to Order History activity
            Intent intent = new Intent(CustomerDashboardActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        // Optionally, you can override the back button behavior here (e.g., prompt to log out)
        super.onBackPressed();
    }
}