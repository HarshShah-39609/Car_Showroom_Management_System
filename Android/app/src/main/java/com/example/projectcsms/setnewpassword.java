package com.example.projectcsms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class setnewpassword extends AppCompatActivity {

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setnewpassword);

        final TextView username = findViewById(R.id.editTextTextPersonName);
        final TextView password = findViewById(R.id.newpassword1);
        final TextView ConformPassword = findViewById(R.id.newpassword2);


        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Verification in progress");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgressStyle(20);


        Button setpassword1=findViewById(R.id.setpassword);
        setpassword1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = username.getText().toString();
                String password1 = password.getText().toString();
                String conformpassword1 = ConformPassword.getText().toString();


                if (username1.equals("") || password1.equals("")  || !password1.equals(conformpassword1))
                {
                    if(!password1.equals(conformpassword1))
                    {
                        Toast.makeText(setnewpassword.this, "Password Must Be Same", Toast.LENGTH_LONG).show();
                    }
                    /*else if()
                    {

                    }*/
                    else
                    {
                        Toast.makeText(setnewpassword.this, "Fill all required details.", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    final String quser="Username="+username1;
                    final String qpassword="&Password="+password1;


                    StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://projectcsms.000webhostapp.com/CSMS/changepassword.php?"+quser+qpassword , new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            progress.setProgress(80);
                            //Toast.makeText(setnewpassword.this, response, Toast.LENGTH_LONG).show();
                            if(response.trim().equalsIgnoreCase("Failure"))
                            {
                                progress.dismiss();
                                Toast.makeText(setnewpassword.this,"Imformation Is Not Valid",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                try{
                                    Toast.makeText(setnewpassword.this,response.toString(),Toast.LENGTH_SHORT).show();

                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray result = jsonObject.getJSONArray("result");
                                    JSONObject data = result.getJSONObject(0);

                                    if(data.getString("error").equalsIgnoreCase("false"))
                                    {
                                        Intent intent = new Intent(setnewpassword.this, Main_Page.class);
                                        setnewpassword.this.startActivity(intent);
                                        setnewpassword.this.finish();
                                        progress.dismiss();
                                    }
                                    else
                                    {
                                        progress.dismiss();
                                        Toast.makeText(setnewpassword.this,"Password Change Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e)
                                {
                                    progress.dismiss();
                                    Toast.makeText(setnewpassword.this,"Password Set SuccessFully", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(setnewpassword.this,Main_Page.class));
                                }
                            }
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progress.dismiss();
                                    Toast.makeText(setnewpassword.this, error.toString(), Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("Username", username1);
                            params.put("Password", password1);
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(setnewpassword.this);
                    requestQueue.add(stringRequest);
                    progress.show();
                    progress.setProgress(60);
                }
            }
            });
        }
    }