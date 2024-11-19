package com.example.shambashop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.ArrayAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CartAdapter extends ArrayAdapter<CartItem> {

    private Context mContext;
    private ArrayList<CartItem> cartItems;
    private DatabaseReference cartRef;

    public CartAdapter(Context context, ArrayList<CartItem> items) {
        super(context, 0, items);
        this.mContext = context;
        this.cartItems = items;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            cartRef = FirebaseDatabase.getInstance().getReference("carts").child(user.getUid());
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cart_item, parent, false);
        }

        CartItem currentItem = cartItems.get(position);

        TextView itemName = convertView.findViewById(R.id.itemNameTextView);
        TextView itemPrice = convertView.findViewById(R.id.itemPriceTextView);
        Button removeButton = convertView.findViewById(R.id.removeButton);

        itemName.setText(currentItem.getName());
        itemPrice.setText("$" + currentItem.getPrice());

        // Remove item from cart
        removeButton.setOnClickListener(v -> {
            cartRef.child(currentItem.getItemId()).removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(mContext, "Item removed from cart.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Failed to remove item.", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return convertView;
    }
}