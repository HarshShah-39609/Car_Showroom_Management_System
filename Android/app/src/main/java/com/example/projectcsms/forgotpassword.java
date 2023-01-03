package com.example.projectcsms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class forgotpassword extends AppCompatActivity {
static int myotp=0000;
    static ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        final String message="Email is Not Valid";
        final String message1= "OTP Is Not Valid";
        final EditText otp=findViewById(R.id.Otp);
        final Button send = findViewById(R.id.button);


        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Please Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgressStyle(40);

        TextView inputemail =findViewById(R.id.Email);
        inputemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email1 =inputemail.getText().toString();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if(inputemail.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"enter email address",Toast.LENGTH_SHORT).show();
                }else {
                    if (inputemail.getText().toString().trim().matches(emailPattern)) {
                        startActivity(new Intent(forgotpassword.this, Main_Page.class));
                    } else {
                        Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                    }
            }
        }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                SendEmail sendEmail = new SendEmail(inputemail.getText().toString().trim(),"forgotpwd");
                sendEmail.makeRequest(getApplicationContext());
            }
        });
        Button btn1=findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpinput=otp.getText().toString();

                String Otpcheck=String.valueOf(myotp);

                if(myotp==0000)
                {
                    Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();;
                }
                else if(otpinput.equals(Otpcheck))
                {
                    startActivity(new Intent(forgotpassword.this,setnewpassword.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(), message1, Toast.LENGTH_SHORT).show();;
                }
            }
        });

    }
}