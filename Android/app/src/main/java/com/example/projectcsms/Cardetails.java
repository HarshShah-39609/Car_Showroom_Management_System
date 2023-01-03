package com.example.projectcsms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Cardetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardetails);

        ImageButton Creta=findViewById(R.id.cretaimage);
        TextView next =findViewById(R.id.nextpage);
        Creta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cardetails.this,Cretamain.class));
            }
        });

        ImageButton i20=findViewById(R.id.i20image);
        i20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cardetails.this,i20main.class));
            }
        });

        ImageButton duster=findViewById(R.id.imageButton7);
        duster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cardetails.this,dustermain.class));
            }
        });

        ImageButton kwid=findViewById(R.id.imageButton8);
        kwid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cardetails.this,kwidmain.class));
            }
        });

        ImageButton swift=findViewById(R.id.imageButton12);
        swift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cardetails.this,swift_main.class));
            }
        });

        ImageButton alto=findViewById(R.id.imageButton14);
        alto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cardetails.this,alto_main.class));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Cardetails.this,cardetails2.class));
            }
        });
    }
}