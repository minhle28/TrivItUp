package com.example.trivitup;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingActivity extends AppCompatActivity {

    private Button enableSoundButton;
    private Button enableDarkModeButton;
    private boolean isSoundEnabled = true;
    private boolean isDarkModeEnabled = false;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        enableSoundButton = findViewById(R.id.enableSoundButton);
        enableDarkModeButton = findViewById(R.id.enableDarkModeButton);

        mediaPlayer = MediaPlayer.create(this, R.raw.sound_tracks);

        enableSoundButton.setText(isSoundEnabled ? "Sound Enabled" : "Sound Disabled");

        enableSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSoundEnabled = !isSoundEnabled;

                playClickSound();

                enableSoundButton.setText(isSoundEnabled ? "Sound Enabled" : "Sound Disabled");

                saveSoundState(isSoundEnabled);
            }
        });

        enableDarkModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDarkModeEnabled = !isDarkModeEnabled;

                playClickSound();

                enableDarkModeButton.setText(isDarkModeEnabled ? "Dark Mode Enabled" : "Dark Mode Disabled");

                applyDarkMode();

                saveDarkModeState(isDarkModeEnabled);
            }
        });

        enableDarkModeButton.setText(isDarkModeEnabled ? "Dark Mode Enabled" : "Dark Mode Disabled");
    }

    private void applyDarkMode() {
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        recreate();
    }

    private void saveSoundState(boolean isEnabled) {
        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("soundEnabled", isEnabled);
        editor.apply();

        SoundPreferenceUtil.setSoundEnabled(this, isEnabled);
    }

    private void saveDarkModeState(boolean isEnabled) {
        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("darkMode", isEnabled);
        editor.apply();
    }

    private void playClickSound() {
        if (mediaPlayer != null) {
            if (isSoundEnabled) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.setVolume(1.0f, 1.0f);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
            } else {
                mediaPlayer.setLooping(false);
                mediaPlayer.pause();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        isSoundEnabled = preferences.getBoolean("soundEnabled", true);
        isDarkModeEnabled = preferences.getBoolean("darkMode", false);

        enableSoundButton.setText(isSoundEnabled ? "Sound Enabled" : "Sound Disabled");
        enableDarkModeButton.setText(isDarkModeEnabled ? "Dark Mode Enabled" : "Dark Mode Disabled");

        updateSoundState();
    }

    private void updateSoundState() {
        enableSoundButton.setText(isSoundEnabled ? "Sound Enabled" : "Sound Disabled");

        boolean isSoundEnabled = SoundPreferenceUtil.isSoundEnabled(this);

        if (isSoundEnabled && !mediaPlayer.isPlaying()) {
            mediaPlayer.setVolume(1.0f, 1.0f);
            mediaPlayer.start();
        } else if (!isSoundEnabled && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Save the sound state and dark mode state to SharedPreferences
        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("soundEnabled", isSoundEnabled);
        editor.putBoolean("darkMode", isDarkModeEnabled);
        editor.apply();

        // Stop the sound if the activity is paused
        stopClickSound();
    }

    private void stopClickSound() {
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(false);
            mediaPlayer.pause();
        }
    }
}
