package com.example.shambashop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FarmerDashboardActivity extends AppCompatActivity {

    private Button listProductsButton, viewReportsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_farmer);

        // Initialize buttons
        listProductsButton = findViewById(R.id.listProductsButton);
        viewReportsButton = findViewById(R.id.viewReportsButton);

        // Set button click listeners
        listProductsButton.setOnClickListener(v -> {
            // Navigate to List New Products activity
            Intent intent = new Intent(FarmerDashboardActivity.this, ProductListingActivity.class);
            startActivity(intent);
        });

        viewReportsButton.setOnClickListener(v -> {
            // Navigate to View Reports activity
            Intent intent = new Intent(FarmerDashboardActivity.this, ReportsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        // Optionally, you can override the back button behavior here (e.g., prompt to log out)
        super.onBackPressed();
    }
}