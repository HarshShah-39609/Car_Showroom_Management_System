package com.example.projectcsms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

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

public class BookACar extends AppCompatActivity {

    private TextView mTextView;
    private ProgressDialog progress;
    private SharedPreferences sharedPreferences;
    public static final String MyPreferences = "CSMSMyPref";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_a_car);
        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        final Spinner model = findViewById(R.id.activity_bac_mdl1);
        final RadioGroup type = findViewById(R.id.pay_type);
        final RadioGroup Book = findViewById(R.id.booktype);

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Verification in progress");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgressStyle(20);

        Button btn_BookACar1=findViewById(R.id.Submitbook);
        btn_BookACar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = sharedPreferences.getString("CSMSUserName",null);
                String Spin=model.getSelectedItem().toString();
                String radio=((RadioButton)findViewById(type.getCheckedRadioButtonId())).getText().toString();
                String radio2=((RadioButton)findViewById(Book.getCheckedRadioButtonId())).getText().toString();

                Toast.makeText(BookACar.this, username1, Toast.LENGTH_LONG).show();

                if ( Spin.equals("") || radio.equals("") || radio2.equals(""))
                {
                    Toast.makeText(BookACar.this, "Fill all required details.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    final String quser="Username="+username1;
                    final String qmodel="&Carmodel="+Spin;
                    final String qpay="&paytype="+radio;
                    final String qbook="&booktype="+radio2;


                    StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://projectcsms.000webhostapp.com/CSMS/BookACar.php?"+quser+qmodel+qpay+qbook, new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            progress.setProgress(80);
                            //Toast.makeText(BookACar.this, response, Toast.LENGTH_LONG).show();
                            if(response.trim().equalsIgnoreCase("Failure"))
                            {
                                progress.dismiss();
                                Toast.makeText(BookACar.this,"Information Is Not Valid",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                try{
                                    Toast.makeText(BookACar.this,response.toString(),Toast.LENGTH_SHORT).show();

                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray result = jsonObject.getJSONArray("result");
                                    JSONObject data = result.getJSONObject(0);

                                    if(data.getString("error").equalsIgnoreCase("false"))
                                    {
                                        String email = data.getString("email").trim();
                                        progress.dismiss();
                                        Toast.makeText(BookACar.this,data.getString("email"),Toast.LENGTH_LONG).show();

                                        SendEmail sendEmail=null;
                                        if(radio2.equalsIgnoreCase("New Car"))
                                        {
                                            sendEmail = new SendEmail(email,"newcar");
                                        }
                                        else if(radio2.equalsIgnoreCase("Test Drive"))
                                        {
                                            sendEmail = new SendEmail(email,"testcar");
                                        }
                                        if(sendEmail!=null)
                                        {
                                            sendEmail.makeRequest(getApplicationContext());
                                        }

                                        Intent intent = new Intent(BookACar.this, Main_Page.class);
                                        BookACar.this.startActivity(intent);
                                        BookACar.this.finish();

                                    }
                                    else
                                    {
                                        progress.dismiss();
                                        Toast.makeText(BookACar.this,"Booking Failed",Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(BookACar.this, error.toString(), Toast.LENGTH_LONG).show();
                                }
                            }){
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("Username", username1);
                            params.put("Carmodel", Spin);
                            params.put("paytype", radio);
                            params.put("booktype", radio2);
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(BookACar.this);
                    requestQueue.add(stringRequest);
                    progress.show();
                    progress.setProgress(60);
                }
            }
        });
    }
}