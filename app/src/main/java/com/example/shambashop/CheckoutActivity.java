package com.example.shambashop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class CheckoutActivity extends AppCompatActivity {

    private TextView totalAmountTextView;
    private Button confirmOrderButton;
    private FirebaseAuth mAuth;
    private DatabaseReference ordersRef, cartRef;
    private double totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Initialize Firebase and views
        mAuth = FirebaseAuth.getInstance();
        totalAmountTextView = findViewById(R.id.totalAmountTextView);
        confirmOrderButton = findViewById(R.id.confirmOrderButton);

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            ordersRef = FirebaseDatabase.getInstance().getReference("orders").child(userId);
            cartRef = FirebaseDatabase.getInstance().getReference("carts").child(userId);

            // For simplicity, let's assume totalAmount is passed via Intent
            totalAmount = getIntent().getDoubleExtra("totalAmount", 0.0);
            totalAmountTextView.setText("Total: $" + totalAmount);

            // Handle confirm order button click
            confirmOrderButton.setOnClickListener(v -> confirmOrder(userId));
        } else {
            Toast.makeText(this, "User not logged in.", Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmOrder(String userId) {
        // Create a new order and save it in the Firebase Realtime Database
        HashMap<String, Object> orderDetails = new HashMap<>();
        orderDetails.put("totalAmount", totalAmount);
        orderDetails.put("status", "pending");

        ordersRef.push().setValue(orderDetails)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Clear the cart after confirming the order
                        cartRef.removeValue().addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Toast.makeText(CheckoutActivity.this, "Order confirmed! Thank you for your purchase.", Toast.LENGTH_SHORT).show();
                                finish(); // Close the activity after checkout
                            } else {
                                Toast.makeText(CheckoutActivity.this, "Failed to clear cart.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(CheckoutActivity.this, "Failed to confirm order.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}