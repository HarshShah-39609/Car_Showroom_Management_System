package com.example.projectcsms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Feedback extends AppCompatActivity {

    private ProgressDialog progress;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final EditText feedback=findViewById(R.id.feedbacktext);

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Verification in progress");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgressStyle(20);
        
        Button Feedback1=findViewById(R.id.Submitfeed);
        Feedback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feeds = feedback.getText().toString();

                if (feeds.equals(""))
                {
                    Toast.makeText(Feedback.this, "Give Some Feedback", Toast.LENGTH_LONG).show();
                }
                else
                {
                    final String qfeed="feeds="+feeds;
                    StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://projectcsms.000webhostapp.com/CSMS/feedback.php?"+qfeed, new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            progress.setProgress(80);
                            //Toast.makeText(Feedback.this, response, Toast.LENGTH_LONG).show();
                            if(response.trim().equalsIgnoreCase("Failure"))
                            {
                                progress.dismiss();
                                Toast.makeText(Feedback.this,"Imformation Is Not Valid",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                try{
                                    Toast.makeText(Feedback.this,response.toString(),Toast.LENGTH_SHORT).show();

                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray result = jsonObject.getJSONArray("result");
                                    JSONObject data = result.getJSONObject(0);

                                    if(data.getString("error").equalsIgnoreCase("false"))
                                    {
                                        Intent intent = new Intent(Feedback.this, Main_Page.class);
                                        Feedback.this.startActivity(intent);
                                        Feedback.this.finish();
                                        progress.dismiss();
                                    }
                                    else
                                    {
                                        progress.dismiss();
                                        Toast.makeText(Feedback.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                catch (Exception e)
                                {
                                    progress.dismiss();
                                    String Message=e.getMessage();
                                    Toast.makeText(Feedback.this,"Feedback Submitted",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progress.dismiss();
                                    Toast.makeText(Feedback.this, error.toString(), Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("feeds",feeds);
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(Feedback.this);
                    requestQueue.add(stringRequest);
                    progress.show();
                    progress.setProgress(60);
                }
            }
        });
    }
}