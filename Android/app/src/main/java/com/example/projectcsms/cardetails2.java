package com.example.projectcsms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class cardetails2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardetails2);

        ImageButton scorpio=findViewById(R.id.imageButton11);
        scorpio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cardetails2.this,scorpio_main.class));
            }
        });

        ImageButton Xuv=findViewById(R.id.imageButton13);
        Xuv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cardetails2.this,xuv500_main.class));
            }
        });

        ImageButton tiago=findViewById(R.id.imageButton9);
        tiago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cardetails2.this,tiago_main.class));
            }
        });

        ImageButton harrier=findViewById(R.id.imageButton10);
        harrier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cardetails2.this,harrier_main.class));
            }
        });
    }
}