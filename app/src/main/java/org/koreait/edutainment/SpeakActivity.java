package org.koreait.edutainment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SpeakActivity extends AppCompatActivity {
    TextView sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);
    }
    protected void onPostCreate(Bundle saveInstanceState) {
        super.onPostCreate(saveInstanceState);
        setContentView(R.layout.activity_speak);

        sign = findViewById(R.id.ProgressButton);
        sign.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProgressActivity.class);
            startActivity(intent);
        });

        sign = findViewById(R.id.StartButton);
        sign.setOnClickListener(v -> {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
        });
    }

}