package com.example.btgki_ltdd;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroupThemes;
    private RadioButton radioButtonLight, radioButtonDark, radioButtonBlue;
    private Button btnSaveTheme;

    // Constants for theme selection
    private static final String SHARED_PREFS = "sharedPrefs";
    private static final String THEME_KEY = "themeKey";
    private static final int THEME_LIGHT = 1;
    private static final int THEME_DARK = 2;
    private static final int THEME_BLUE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load the saved theme before setting the content view
        int savedTheme = getSavedTheme();
        applyTheme(savedTheme);  // Apply the saved theme

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupThemes = findViewById(R.id.radioGroupThemes);
        radioButtonLight = findViewById(R.id.radioButtonLight);
        radioButtonDark = findViewById(R.id.radioButtonDark);
        radioButtonBlue = findViewById(R.id.radioButtonBlue);
        btnSaveTheme = findViewById(R.id.btnSaveTheme);

        // Set the saved theme as selected
        setSavedTheme(savedTheme);

        btnSaveTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTheme();
            }
        });
    }

    // Function to save the selected theme
    private void saveTheme() {
        int selectedTheme = THEME_LIGHT;  // Default

        // Thay thế switch bằng if-else
        if (radioGroupThemes.getCheckedRadioButtonId() == R.id.radioButtonDark) {
            selectedTheme = THEME_DARK;
        } else if (radioGroupThemes.getCheckedRadioButtonId() == R.id.radioButtonBlue) {
            selectedTheme = THEME_BLUE;
        }

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(THEME_KEY, selectedTheme);
        editor.apply();

        // Restart activity to apply the theme change
        restartActivity();
    }

    // Function to load and apply the saved theme
    private int getSavedTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        return sharedPreferences.getInt(THEME_KEY, THEME_LIGHT); // Default to Light Theme
    }

    private void applyTheme(int theme) {
        switch (theme) {
            case THEME_DARK:
                setTheme(R.style.DarkTheme);
                break;
            case THEME_BLUE:
                setTheme(R.style.BlueTheme);
                break;
            default:
                setTheme(R.style.LightTheme);
                break;
        }
    }

    // Function to set the radio button of the saved theme as checked
    private void setSavedTheme(int savedTheme) {
        switch (savedTheme) {
            case THEME_DARK:
                radioButtonDark.setChecked(true);
                break;
            case THEME_BLUE:
                radioButtonBlue.setChecked(true);
                break;
            default:
                radioButtonLight.setChecked(true);
                break;
        }
    }

    // Function to restart activity and apply the new theme
    private void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
