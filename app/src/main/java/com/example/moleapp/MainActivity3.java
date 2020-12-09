package com.example.moleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

    }

    public void easyMode(View view) {
        Intent intent = new Intent(MainActivity3.this, MainActivity.class);
        startActivity(intent);
    }

    public void mediumMode(View view) {
        Intent intent2 = new Intent(MainActivity3.this, MainActivity2.class);
        startActivity(intent2);
    }

    public void hardMode(View view) {
        Intent intent3 = new Intent(MainActivity3.this, MainActivity4.class);
        startActivity(intent3);
    }
}