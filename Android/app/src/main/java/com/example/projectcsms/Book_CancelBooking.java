package com.example.projectcsms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

public class Book_CancelBooking extends AppCompatActivity {

    private TextView mTextView;
    private ProgressDialog progress;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__cancel_booking);

        final TextView username = findViewById(R.id.cancelcarid);
        final TextView email = findViewById(R.id.Caneclemail);
        Spinner s1=findViewById(R.id.activity_bac_mdl);
        RadioGroup radio1=findViewById(R.id.radioGroup3);

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Verification in progress");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgressStyle(20);

        Button btn_Book_CancelBooking1=findViewById(R.id.btn_caneclbooking);
        btn_Book_CancelBooking1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = username.getText().toString();
                String email1 = email.getText().toString();
                String Spin=s1.getSelectedItem().toString();
                String radio=((RadioButton)findViewById(radio1.getCheckedRadioButtonId())).getText().toString();

                if (username1.equals("") ||  email1.equals("") || Spin.equals("") || radio.equals(""))
                {
                    Toast.makeText(Book_CancelBooking.this, "Fill all required details.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    final String quser="Username="+username1;
                    final String qemail="&Email="+email1;
                    final String model="&model="+Spin;
                    final String type="&Type="+radio;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://projectcsms.000webhostapp.com/CSMS/Book_CancelBooking.php?"+quser+qemail+model+type , new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            progress.setProgress(80);
                            //Toast.makeText(Book_CancelBooking.this, response, Toast.LENGTH_LONG).show();
                            if(response.trim().equalsIgnoreCase("Failure"))
                            {
                                progress.dismiss();
                                Toast.makeText(Book_CancelBooking.this,"Imformation Is Not Valid",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                try{
                                    Toast.makeText(Book_CancelBooking.this,response.toString(),Toast.LENGTH_SHORT).show();

                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray result = jsonObject.getJSONArray("result");
                                    JSONObject data = result.getJSONObject(0);

                                    if(data.getString("error").equalsIgnoreCase("false"))
                                    {
                                        Intent intent = new Intent(Book_CancelBooking.this, Main_Page.class);
                                        Book_CancelBooking.this.startActivity(intent);
                                        Book_CancelBooking.this.finish();
                                        progress.dismiss();
                                    }
                                    else
                                    {
                                        progress.dismiss();
                                        Toast.makeText(Book_CancelBooking.this,"Cancel Booking Failed",Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(Book_CancelBooking.this, "Booking Canceled", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Book_CancelBooking.this,Main_Page.class));
                                }
                            }){
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("Username", username1);
                            params.put("Email", email1);
                            params.put("model",Spin);
                            params.put("Type",radio);
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(Book_CancelBooking.this);
                    requestQueue.add(stringRequest);
                    progress.show();
                    progress.setProgress(60);
                }
            }
        });
    }
}