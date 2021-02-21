package com.example.redballtoy.basicandroid_hw_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class SecondLayout extends AppCompatActivity {

    private final String STR = "Полученный адрес email: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_layout);

        String email = getIntent().getStringExtra("email");
        EditText editTextEmail = findViewById(R.id.et_textEmailAddres);
        editTextEmail.setTextColor(getResources().getColor(R.color.purple_700));
        editTextEmail.setText(STR + email);

    }
}