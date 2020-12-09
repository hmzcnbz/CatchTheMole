package com.example.moleapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    TextView time;
    TextView score;
    Button button;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    ImageView image4;
    ImageView image5;
    ImageView image6;
    ImageView image7;
    ImageView image8;
    ImageView image9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    int number;
    int highScore;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.time);
        button = findViewById(R.id.button3);
        score = findViewById(R.id.score);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);
        imageArray = new ImageView[]{image1, image2, image3, image4, image5, image6, image7, image8, image9};

        // store highest score
        sharedPreferences = getSharedPreferences("com.example.moleapp", Context.MODE_PRIVATE);
        highScore = sharedPreferences.getInt("highestEasy", 0);

        // all moles can be seen
        for (ImageView image : imageArray) {
            image.setVisibility(View.VISIBLE);
        }

    }

    @SuppressLint("SetTextI18n")
    public void start(View view) {
        // arrange button as invisible
        hideImages();
        button.setVisibility(View.INVISIBLE);
        number = 0;

        // 10 seconds to play
        new CountDownTimer(10000, 1000) {

            @Override
            // left time
            public void onTick(long millisUntilFinished) {
                time.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            // after game set button visibility
            public void onFinish() {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Game Finished");

                // print out highest score
                if (number > highScore) {
                    sharedPreferences.edit().putInt("highestEasy", number).apply();
                    highScore = sharedPreferences.getInt("highestEasy", 0);
                }
                alert.setMessage("Score: " + number + "\nHigh Score: " + highScore);

                // after alert set score=0 and stop the move
                number = 0;
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
                Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_LONG).show();
                button.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    // increase score
    public void sum(View view) {
        number++;
        score.setText("Score: " + number);
    }

    public void hideImages() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            // display mole random area
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int randomNumber = random.nextInt(9);
                imageArray[randomNumber].setVisibility(View.VISIBLE);

                handler.postDelayed(runnable, 600);
            }
        };
        handler.post(runnable);
    }
}