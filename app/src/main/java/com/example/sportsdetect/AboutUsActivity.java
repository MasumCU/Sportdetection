// AboutUsActivity.java
package com.example.sportsdetect;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutUsActivity extends AppCompatActivity {

    private int currentPage = 1; // Tracks the current page number

    private TextView pageNumberTextView;
    private TextView contentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        pageNumberTextView = findViewById(R.id.pageNumberTextView);
        contentTextView = findViewById(R.id.contentTextView);

        updateContent(); // Initially update content
    }

    // Method to update content based on current page number
    private void updateContent() {
        switch (currentPage) {
            case 1:
                pageNumberTextView.setText("Page 1");
                contentTextView.setText("Hello folks i am Masum Hasan,student  of computer science and Engineering Department ,university of chittagong.I am going to development an app project name\"SportsDetection\" using Java language . My app name is \"SportsDetection\".It is an android app which is build up using java language.Its main function is detect the exact sports name when a verifyed user select a photo of sports");
                break;
            case 2:
                pageNumberTextView.setText("Page 2");
                contentTextView.setText("This app is developing by using java language and use varous tools name as Android studio");
                break;
            default:
                break;
        }
    }

    // Method to handle Next button click
    public void onNextButtonClick(View view) {
        currentPage++;
        updateContent();
    }

    // Method to handle Previous button click
    public void onPreviousButtonClick(View view) {
        if (currentPage > 1) {
            currentPage--;
            updateContent();
        }
    }
}
