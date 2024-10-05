package com.example.sportsdetect;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_PICK = 1;

    private EditText nameEditText;
    private Spinner districtSpinner;
    private Button saveButton;
    private Button cancelButton;
    private Button deleteButton;
    private Button changePhotoButton;
    private ImageView profilePhoto;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

        // Find views
        nameEditText = findViewById(R.id.nameEditText);
        districtSpinner = findViewById(R.id.districtSpinner);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);
        deleteButton = findViewById(R.id.deleteButton);
        changePhotoButton = findViewById(R.id.changePhotoButton);
        profilePhoto = findViewById(R.id.profilePhoto);

        // Populate spinner with districts
        adapter = ArrayAdapter.createFromResource(this,
                R.array.districts_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(adapter);

        // Set onClickListeners for buttons
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear EditText and Spinner
                nameEditText.setText("");
                districtSpinner.setSelection(0);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder for deletion functionality
                Toast.makeText(ProfileActivity.this, "Profile deleted", Toast.LENGTH_SHORT).show();
                // Add your delete profile logic here
            }
        });

        changePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch image picker
                openImagePicker();
            }
        });

        // Load saved profile data
        loadProfile();
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                // Set the selected image to the ImageView
                profilePhoto.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadProfile() {
        // Load saved profile data from SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String savedName = preferences.getString("name", "");
        String savedDistrict = preferences.getString("district", "");
        // Set loaded profile data to views
        nameEditText.setText(savedName);
        int districtIndex = adapter.getPosition(savedDistrict);
        districtSpinner.setSelection(districtIndex);
    }

    private void saveProfile() {
        // Retrieve name and district from EditText and Spinner
        String name = nameEditText.getText().toString();
        String district = districtSpinner.getSelectedItem().toString();

        // Save profile data to SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", name);
        editor.putString("district", district);
        editor.apply();

        // Placeholder for saving functionality
        Toast.makeText(this, "Profile saved:\nName: " + name + "\nDistrict: " + district, Toast.LENGTH_SHORT).show();
        // Add your save profile logic here
    }
}
