package com.example.sportsdetect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity2 extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hom2);

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance();

        // Find the views
        CardView logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        // Add click listener for "Map" card
        findViewById(R.id.idReview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for "Map" card
                startActivity(new Intent(HomeActivity2.this, ActivityMap.class));
            }
        });

        // Add click listener for "Rating" card
        findViewById(R.id.ratingId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for "Rating" card
                startActivity(new Intent(HomeActivity2.this, Rating.class));
            }
        });
        //change 1

        // Add click listener for "Profile" card
        findViewById(R.id.profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for "Profile" card
                startActivity(new Intent(HomeActivity2.this, ProfileActivity.class));
            }
        });

        // Add click listener for "Event" card
        findViewById(R.id.event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for "Event" card
                startActivity(new Intent(HomeActivity2.this,AboutActivity.class));
            }
        });
        findViewById(R.id.aboutUs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for "About Us" card
                startActivity(new Intent(HomeActivity2.this, AboutUsActivity.class));
            }
        });


        // Add click listener for "About Us" card
        // Similarly, add click listeners for other cards as needed
    }

    // Method to sign out
    private void signOut() {
        auth.signOut();
        Toast.makeText(HomeActivity2.this, "Signed Out", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(HomeActivity2.this, LoginActivity.class);
        // Clear all stacks
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Close HomeActivity2
    }
}
