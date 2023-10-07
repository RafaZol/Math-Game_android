package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
public class gameOverClass extends AppCompatActivity {

    TextView tfFinalScore;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_class);

        tfFinalScore = findViewById(R.id.tfFinalScore);

        Intent intent = getIntent();
        score = intent.getIntExtra("Pontuação", 0);
        String userScore = String.valueOf(score);
        tfFinalScore.setText("Pontuação : " + userScore);

        findViewById(R.id.btReplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gameOverClass.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.btSair).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}