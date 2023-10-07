package com.company.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addClass.class);
                intent.putExtra("operador", "+");
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.bdSub).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addClass.class);
                intent.putExtra("operador", "-");
                startActivity(intent);
                finish();
            }
        });
        findViewById(R.id.btMult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addClass.class);
                intent.putExtra("operador", "*");
                startActivity(intent);
                finish();
            }
        });

    }
}