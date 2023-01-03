package com.example.projectcsms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Cretamain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cretamain);

        ImageButton black=findViewById(R.id.imageButton6);

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cretamain.this,cretaview.class));
            }
        });

        TextView inte = findViewById(R.id.textView16);
        TextView exti = findViewById(R.id.textView15);

        inte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cretamain.this,creta_interer.class));
            }
        });

        exti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cretamain.this,creta_exterier.class));
            }
        });
    }
}