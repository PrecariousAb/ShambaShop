package com.example.shambashop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView registerTextView;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerTextView = findViewById(R.id.registerTextView);
        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty()) {
                emailEditText.setError("Email Required");
                emailEditText.requestFocus();
            } else if (password.isEmpty()) {
                passwordEditText.setError("Password Required");
                passwordEditText.requestFocus();
            } else {
                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            progressBar.setVisibility(View.GONE);

                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    String userId = user.getUid();

                                    // Fetch the user data from Firebase
                                    usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            if (dataSnapshot.exists()) {
                                                User loggedInUser = dataSnapshot.getValue(User.class);
                                                if (loggedInUser != null) {
                                                    // Determine the user type and redirect accordingly
                                                    if ("consumer".equals(loggedInUser.getUserType())) {
                                                        startActivity(new Intent(LoginActivity.this, CustomerDashboardActivity.class));
                                                    } else if ("farmer".equals(loggedInUser.getUserType())) {
                                                        startActivity(new Intent(LoginActivity.this, FarmerDashboardActivity.class));
                                                    }
                                                    finish();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            Toast.makeText(LoginActivity.this, "Failed to load user data.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        registerTextView.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
        });
    }
}