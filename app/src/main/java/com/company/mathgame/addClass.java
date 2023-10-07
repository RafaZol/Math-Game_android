package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import java.util.Random;

public class addClass extends AppCompatActivity {
    TextView tfScore, tfLife, tfTime, tfQuest;
    EditText tfResp;
    Random random = new Random();
    int numberOne, numberTwo, userResp, realResp;
    int userScore = 0, userLife = 3;
    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 60000;
    Boolean timer_running;
    long time_left_milis = START_TIMER_IN_MILIS;
    private String operdor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_class);
        Intent myIntent = getIntent(); 
        operdor = myIntent.getStringExtra("operador");
        tfScore = findViewById(R.id.tfScore);
        tfLife = findViewById(R.id.tfLife);
        tfTime = findViewById(R.id.tfTime);
        tfResp = findViewById(R.id.tfResp);
        tfQuest = findViewById(R.id.tfQuestion);

        gameContinue();

        findViewById(R.id.btOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userResp = Integer.valueOf(tfResp.getText().toString());
                pauseTimer();
                if(userResp == realResp){
                    userScore += 10;
                    tfQuest.setText("Correto !!");
                    tfScore.setText(userScore + "");
                } else {
                    userLife -= 1;
                    tfQuest.setText("Incorreto !!");
                    tfLife.setText(userLife + "");
                }
            }
        });

        findViewById(R.id.btProx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userLife <= 0){
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(addClass.this, gameOverClass.class);
                    intent.putExtra("Pontuação", userScore);
                    startActivity(intent);
                    finish();
                } else {
                    tfResp.setText("");
                    resetTimer();
                    gameContinue();
                }
            }
        });

    }

    public void gameContinue(){
        numberOne = random.nextInt(100);//numero inteiro entre 0 e 100
        numberTwo = random.nextInt(100);
        if(operdor != null){
            switch (operdor){
                case "+" :
                    realResp = numberOne + numberTwo;
                    tfQuest.setText(numberOne + " + " + numberTwo + " =");
                    break;
                case "-" :
                    realResp = numberOne - numberTwo;
                    tfQuest.setText(numberOne + " - " + numberTwo + " =");
                    break;
                case "*" :
                    realResp = numberOne * numberTwo;
                    tfQuest.setText(numberOne + " X " + numberTwo + " =");
                    break;
            }
        } else {
            Toast.makeText(getApplicationContext(), "ERRO NA SOLICITAÇÃO", Toast.LENGTH_LONG).show();
        }

        startTimer();
    }

    public void startTimer(){ // contagem regressiva
        timer = new CountDownTimer(time_left_milis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_milis = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife -= 1;
                tfQuest.setText("TEMPO ESGOTADO");
            }
        }.start();

        timer_running = true;
    }

    public void updateText(){
        int seconds = (int)(time_left_milis / 1000)%60;
        String time_left = String.format(Locale.getDefault(), "%02d", seconds);
        tfTime.setText(time_left);
    }
    public void pauseTimer(){
        timer.cancel();
        timer_running = false;
    }
    public void resetTimer(){
        time_left_milis = START_TIMER_IN_MILIS;
        updateText();
    }
}