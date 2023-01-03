package com.example.projectcsms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progress;

    private static boolean  flag = false;
    public static final String MyPreferences = "CSMSMyPref";
    public static final String CSMSusername = "CSMSUserName";
    public static final String CSMSpassword = "CSMSPassowrd";



    public CheckBox checkBox;

    private SharedPreferences sharedPreferences;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builder;

    private Button btn_login;


    public String user=null;
    public String password=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        checkBox= findViewById(R.id.checkBox);

        EditText username = findViewById(R.id.inputusername1);
        EditText Password = findViewById(R.id.inputpassword1);
        btn_login =findViewById(R.id.btn_login);
        TextView btn=findViewById(R.id.textviewsignup);

        builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_baseline_warning_24)
                .setTitle("Warning")
                .setMessage("You are going to login without checking the remember me.\nHence you need to login each time when you open the application.")
        .setCancelable(false)
        .setPositiveButton("Ok,Proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btn_login.callOnClick();
            }
        })
        .setNeutralButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });

        TextView btn1=findViewById(R.id.Forgotpassword);
        btn1.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,forgotpassword.class)));

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Verification in progress");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgressStyle(20);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user==null || user.equals("") || password== null) {
                    user = username.getText().toString().trim();
                    password = Password.getText().toString().trim();
                }


                if(!checkBox.isChecked() && !flag)
                {
                    flag = true;
                    alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    if (user.equals("") || password.equals("")) {
                        Toast.makeText(MainActivity.this, "Fill all required details." , Toast.LENGTH_LONG).show();
                    } else {
                        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://projectcsms.000webhostapp.com/CSMS/login.php?login=" + user + "&password=" + password, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progress.setProgress(80);
                                //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                                if (response.trim().equalsIgnoreCase("Failure")) {
                                    progress.dismiss();
                                    Toast.makeText(MainActivity.this, "Invalid userid or password/may be user is not registered.", Toast.LENGTH_LONG).show();
                                } else {
                                    try {
                                        JSONObject jsonObject = new JSONObject(response);
                                        JSONArray result = jsonObject.getJSONArray("result");
                                        JSONObject data = result.getJSONObject(0);

                                        if (data.getString("error").equalsIgnoreCase("false")) {
                                            if (checkBox.isChecked()) {


                                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                                editor.putString(CSMSusername, user.trim());
                                                editor.putString(CSMSpassword, password.trim());
                                                editor.commit();
                                            } else {

                                            }


                                            Intent intent = new Intent(MainActivity.this, Main_Page.class);
                                            MainActivity.this.startActivity(intent);
                                            MainActivity.this.finish();
                                            progress.dismiss();
                                        } else {
                                            progress.dismiss();
                                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (Exception e) {
                                        progress.dismiss();
                                        Toast.makeText(MainActivity.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        progress.dismiss();
                                        //Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("login", user);
                                params.put("password", password);
                                return params;
                            }

                        };

                        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                        requestQueue.add(stringRequest);
                        progress.show();
                        progress.setProgress(60);*/
                        Intent intent = new Intent(MainActivity.this, Main_Page.class);
                        MainActivity.this.startActivity(intent);
                        MainActivity.this.finish();
                    }
                }
            }
        });



        if(sharedPreferences!=null)
        {
            //progress.show();
            user = sharedPreferences.getString(CSMSusername,null);
            password = sharedPreferences.getString(CSMSpassword,null);


            if(user!=null && password!= null)
            {
                checkBox.setChecked(true);
                flag=false;
                btn_login.callOnClick();
                /*Intent intent = new Intent(MainActivity.this, Main_Page.class);
                MainActivity.this.startActivity(intent);
                MainActivity.this.finish();
                progress.dismiss();*/
            }
        }

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
}