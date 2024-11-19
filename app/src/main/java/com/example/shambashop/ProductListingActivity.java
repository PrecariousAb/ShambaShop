package com.example.shambashop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProductListingActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private EditText productNameEditText, productDescriptionEditText, productPriceEditText;
    private ImageView productImageView;
    private Button uploadImageButton, submitProductButton;
    private Bitmap productImageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);

        // Initialize views
        productNameEditText = findViewById(R.id.productNameEditText);
        productDescriptionEditText = findViewById(R.id.productDescriptionEditText);
        productPriceEditText = findViewById(R.id.productPriceEditText);
        productImageView = findViewById(R.id.productImageView);
        uploadImageButton = findViewById(R.id.uploadImageButton);
        submitProductButton = findViewById(R.id.submitProductButton);

        // Set up image upload button
        uploadImageButton.setOnClickListener(v -> {
            // Open gallery to choose an image
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        // Set up submit button
        submitProductButton.setOnClickListener(v -> {
            String productName = productNameEditText.getText().toString().trim();
            String productDescription = productDescriptionEditText.getText().toString().trim();
            String productPrice = productPriceEditText.getText().toString().trim();

            if (productName.isEmpty() || productDescription.isEmpty() || productPrice.isEmpty() || productImageBitmap == null) {
                Toast.makeText(ProductListingActivity.this, "Please fill out all fields and upload an image.", Toast.LENGTH_SHORT).show();
            } else {
                // Here, you'd typically submit the product to your backend or Firebase database.
                Toast.makeText(ProductListingActivity.this, "Product added successfully!", Toast.LENGTH_SHORT).show();
                // Optionally, reset the form
                productNameEditText.setText("");
                productDescriptionEditText.setText("");
                productPriceEditText.setText("");
                productImageView.setImageResource(R.drawable.logo);
                productImageBitmap = null;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            try {
                productImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                productImageView.setImageBitmap(productImageBitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}