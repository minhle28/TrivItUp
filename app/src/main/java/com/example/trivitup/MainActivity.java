package com.example.trivitup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button logoutButton;
    private TextView welcomeTextView;
    private Button playIndividuallyButton;
    private Button selectedCategoryButton;
    private Button settingsButton;
    private MediaPlayer mediaPlayer;
    private boolean isDarkModeEnabled = false;


    // Define the categories
    String[] categories = {"Math", "Science", "General Knowledge", "Random"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeTextView = findViewById(R.id.WelcomeTV);
        playIndividuallyButton = findViewById(R.id.playIndividuallyButton);
        selectedCategoryButton = null;

        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        isDarkModeEnabled = preferences.getBoolean("darkMode", false);

        // Get the current user from FirebaseAuth and Check if the user is authenticated
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String displayName = user.getDisplayName();

            if (displayName != null && !displayName.isEmpty()) {
                welcomeTextView.setText("Welcome, " + displayName + "!");
            } else {
                welcomeTextView.setText("Welcome, User!");
            }
        }

        // Set up MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.sound_tracks);
        mediaPlayer.setLooping(true);

        // Logout button
        logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this, AuthenticationActivity.class);
                startActivity(i);
            }
        });

        // Loop through the categories and create buttons with radio button styling for each
        LinearLayout layout = findViewById(R.id.button_cat);
        for (String category : categories) {
            Button button = new Button(this);
            button.setText(category);
            button.setId(View.generateViewId());
            button.setBackground(getDrawable(R.drawable.radio_button_background));

            // Set margins to create spacing between buttons
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.button_margin_bottom));

            button.setLayoutParams(params);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCategoryButtonClicked((Button) view);
                }
            });
            layout.addView(button);
        }

        // Play Individually button
        playIndividuallyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedCategoryButton != null) {
                    openSubCategories(selectedCategoryButton.getText().toString());
                }
            }
        });

        // Settings button
        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the SettingsActivity when the settings button is clicked
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });

        // Leaderboard button
        Button leaderboardButton = findViewById(R.id.leaderboardButton);
        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LeaderboardActivity.class));
            }
        });

        // Rules button
        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Check if the sound is enabled and the MediaPlayer is not already playing
        updateSoundState();
    }

    private void updateSoundState() {
        boolean isSoundEnabled = SoundPreferenceUtil.isSoundEnabled(this);

        if (isSoundEnabled && !mediaPlayer.isPlaying()) {
            mediaPlayer.setVolume(1.0f, 1.0f);
            mediaPlayer.start();
        } else if (!isSoundEnabled && mediaPlayer.isPlaying()) {
            // If sound is disabled but the MediaPlayer is playing, pause it
            mediaPlayer.pause();
        }
    }

    // Method to handle button clicks for category selection
    void onCategoryButtonClicked(Button clickedButton) {
        if (selectedCategoryButton != null) {
            selectedCategoryButton.setBackground(getDrawable(R.drawable.radio_button_background));

            // Set text color based on dark mode state
            int textColor = isDarkModeEnabled ? getColor(R.color.white) : getColor(R.color.default_text_color);
            selectedCategoryButton.setTextColor(textColor);
        }

        clickedButton.setBackground(getDrawable(R.drawable.radio_button_selected_background));
        clickedButton.setTextColor(getColor(R.color.selected_text_color));
        selectedCategoryButton = clickedButton;
    }

    // Method to open the subcategories
    void openSubCategories(String category) {
        Intent intent = new Intent(this, SubCategoryActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
