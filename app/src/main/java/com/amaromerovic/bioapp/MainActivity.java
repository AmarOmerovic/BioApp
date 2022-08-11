package com.amaromerovic.bioapp;

import android.content.Context;
import android.net.PlatformVpnProfile;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.amaromerovic.bioapp.data.Bio;
import com.amaromerovic.bioapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private Button exitButton;
    private Button continueButton;
    private ActivityMainBinding mainBinding;
    private final Bio bio = new Bio();
    boolean isFirstEntry = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        exitButton = findViewById(R.id.exitButton);
        continueButton = findViewById(R.id.continueButton);

        exitButton.setOnClickListener(view -> {
            finish();
        });

        continueButton.setOnClickListener(view -> {
            setContentView(R.layout.activity_main);
            mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
            bio.setName("Amar Omerović");
            mainBinding.setBio(bio);
            mainBinding.doneButton.setOnClickListener(this::addHobbies);
        });

    }

    public void addHobbies(View view) {

        String help = mainBinding.enterHobbies.getText().toString().trim();
        if (help.isEmpty()){
            return;
        }

        if (isFirstEntry){
            bio.setHobbies("*" + help + "\n\n");
            isFirstEntry = false;
        } else {
            bio.setHobbies(bio.getHobbies() + "*" + help + "\n\n");
        }

        mainBinding.setBio(bio);

        mainBinding.invalidateAll();

        mainBinding.popUpText2.setVisibility(View.VISIBLE);
        mainBinding.popUpText.setVisibility(View.VISIBLE);
        mainBinding.spaceWidget.setVisibility(View.VISIBLE);

        mainBinding.setBio(bio);

        //hide keyboard
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        mainBinding.enterHobbies.setText("");

    }
}