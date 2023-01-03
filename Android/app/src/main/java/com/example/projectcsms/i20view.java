package com.example.projectcsms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class i20view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i20view);

        ImageView Next, Previous;
        ViewFlipper viewflipper1 =  findViewById(R.id.elitei20_viewflipper);
        Previous =  findViewById(R.id.eliteprevious);
        Next =  findViewById(R.id.elitenext);

        TextView book = findViewById(R.id.bookcartextview);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(i20view.this,BookACar.class));
            }
        });

        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewflipper1.showPrevious();
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewflipper1.showNext();
            }
        });
    }
}