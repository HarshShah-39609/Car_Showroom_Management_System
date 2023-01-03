package com.example.projectcsms;

import android.app.ProgressDialog;
import android.content.ContentProvider;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity{

    private TextView mTextView;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final String message="Passwords Must Be Same";

        final TextView username = findViewById(R.id.inputusername1);
        final TextView email = findViewById(R.id.Inputemail);
        final TextView password = findViewById(R.id.inputpassword1);
        final TextView ConformPassword = findViewById(R.id.Inputconfirmpassword);
        final TextView phonenumber = findViewById(R.id.inputphonenumber);
        final TextView Address = findViewById(R.id.inputaddress);
        final TextView gender = findViewById(R.id.Inputgender);


        TextView text_account=findViewById(R.id.AlreadyHaveAccount);
        text_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,MainActivity.class));
            }
        });

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Verification in progress");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgressStyle(20);

        Button btn_register1=findViewById(R.id.btn_register);
        btn_register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = username.getText().toString();
                String email1 = email.getText().toString();
                String password1 = password.getText().toString();
                String conformpassword1 = ConformPassword.getText().toString();
                String phonenumber1 = phonenumber.getText().toString();
                String address1 = Address.getText().toString();
                String gender1 = gender.getText().toString();


                if (username1.equals("") || password1.equals("") || email1.equals("") || phonenumber1.equals("") || address1.equals("") || !password1.equals(conformpassword1))
                {
                    if(!password1.equals(conformpassword1))
                    {
                        Toast.makeText(Register.this, "Password Must Be Same", Toast.LENGTH_LONG).show();
                    }
                    /*else if()
                    {

                    }*/
                    else
                    {
                        Toast.makeText(Register.this, "Fill all required details.", Toast.LENGTH_LONG).show();
                    }
                }
                else
                    {
                       final String quser="Username="+username1;
                       final String qemail="&Email="+email1;
                       final String qpassword="&Password="+password1;
                       final String qcon="&Phonenumber="+phonenumber1;
                       final String qadd="&Address="+address1;
                       final String qgen="&Gender="+gender1;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://projectcsms.000webhostapp.com/CSMS/register.php?"+quser+qemail+qpassword+qcon+qadd+qgen , new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            progress.setProgress(80);
                            //Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();
                            if(response.trim().equalsIgnoreCase("Failure"))
                            {
                                progress.dismiss();
                                Toast.makeText(Register.this,"Imformation Is Not Valid",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                try{
                                    Toast.makeText(Register.this,response.toString(),Toast.LENGTH_SHORT).show();

                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray result = jsonObject.getJSONArray("result");
                                    JSONObject data = result.getJSONObject(0);

                                    if(data.getString("error").equalsIgnoreCase("false"))
                                    {
                                        Intent intent = new Intent(Register.this, Main_Page.class);
                                        Register.this.startActivity(intent);
                                        Register.this.finish();
                                        progress.dismiss();
                                    }
                                    else
                                    {
                                        progress.dismiss();
                                        Toast.makeText(Register.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e)
                                {
                                    progress.dismiss();
                                }
                            }
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progress.dismiss();
                                    Toast.makeText(Register.this, error.toString(), Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("Username", username1);
                            params.put("Email", email1);
                            params.put("Password", password1);
                            params.put("Phonenumber", phonenumber1);
                            params.put("Address", address1);
                            params.put("Gender", gender1);
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
                    requestQueue.add(stringRequest);
                    progress.show();
                    progress.setProgress(60);
                }
            }
        });
    }
}