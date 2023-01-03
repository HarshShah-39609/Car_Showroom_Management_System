package com.example.projectcsms;

import android.content.Context;
import android.content.Intent;
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

public class SendEmail {
     String email;
     String purpose;
     String url = "https://projectcsms.000webhostapp.com/CSMS/smtp/index.php?";
     String reqEmail  = "email=";
     String reqPurpose = "&purpose=";

    public SendEmail(String email, String purpose) {
        this.email = email;
        this.purpose = purpose;
    }

    public void makeRequest(Context context)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url+reqEmail+email+reqPurpose+purpose, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(BookACar.this, response, Toast.LENGTH_LONG).show();
                if(response.trim().equalsIgnoreCase("Failure"))
                {
                    Toast.makeText(context,"Information Is Not Valid",Toast.LENGTH_LONG).show();
                }
                else
                {
                    try{
                        //Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show();

                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray result = jsonObject.getJSONArray("result");
                        JSONObject data = result.getJSONObject(0);

                        if(data.getString("error").equalsIgnoreCase("false"))
                        {
                            //Toast.makeText(context,data.getString("msg"),Toast.LENGTH_LONG).show();
                            String msg = data.getString("msg");
                            if(msg.length()==4)
                            {
                                forgotpassword.myotp = Integer.valueOf(msg);
                                forgotpassword.progress.dismiss();
                            }

                        }
                        else
                        {
                            //Toast.makeText(context,"Booking Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e)
                    {

                        //Toast.makeText(context,"Booking Completed",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
}
