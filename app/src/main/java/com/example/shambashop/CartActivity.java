package com.example.shambashop;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private Button checkoutButton;
    private CartAdapter cartAdapter;
    private ArrayList<CartItem> cartItems = new ArrayList<>();
    private FirebaseAuth mAuth;
    private DatabaseReference cartRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Initialize Firebase and views
        mAuth = FirebaseAuth.getInstance();
        checkoutButton = findViewById(R.id.checkoutButton);
        cartListView = findViewById(R.id.cartListView);

        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            cartRef = FirebaseDatabase.getInstance().getReference("carts").child(userId);

            // Load items in the cart
            loadCartItems();

            // Handle checkout button click
            checkoutButton.setOnClickListener(v -> {
                if (!cartItems.isEmpty()) {
                    startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
                } else {
                    Toast.makeText(CartActivity.this, "Your cart is empty.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "User not logged in.", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadCartItems() {
        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                cartItems.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    CartItem item = itemSnapshot.getValue(CartItem.class);
                    if (item != null) {
                        cartItems.add(item);
                    }
                }

                // Set adapter for the cart list view
                cartAdapter = new CartAdapter(CartActivity.this, cartItems);
                cartListView.setAdapter(cartAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(CartActivity.this, "Failed to load cart items.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}