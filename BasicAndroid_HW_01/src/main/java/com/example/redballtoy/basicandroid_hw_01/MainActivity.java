package com.example.redballtoy.basicandroid_hw_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etInputEmail;
    Button btGoSecondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etInputEmail = findViewById(R.id.et_et);
        btGoSecondActivity = findViewById(R.id.bt_go_second_activity);
        btGoSecondActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email = etInputEmail.getText().toString();
        if (email.equals("") || !email.contains("@") || !email.contains(".") || email.length() < 5) {
            Toast.makeText(this, "Введите корректный email", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, SecondLayout.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}