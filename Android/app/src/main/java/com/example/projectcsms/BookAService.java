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


public class BookAService extends AppCompatActivity {

    private TextView mTextView;
    private ProgressDialog progress;
    private SharedPreferences sharedPreferences;
    public static final String MyPreferences = "CSMSMyPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_a_service);

        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        final Spinner model = findViewById(R.id.activity_bac_mdl2);
        final RadioGroup type = findViewById(R.id.ser_type);
        final RadioGroup Book = findViewById(R.id.ser_des);

        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setMessage("Verification in progress");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(false);
        progress.setProgressStyle(20);

        Button btn_BookAService1=findViewById(R.id.service_submit);
        btn_BookAService1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username1 = sharedPreferences.getString("CSMSUserName",null);
                String Spin=model.getSelectedItem().toString();
                String radio=((RadioButton)findViewById(type.getCheckedRadioButtonId())).getText().toString();
                String radio2=((RadioButton)findViewById(Book.getCheckedRadioButtonId())).getText().toString();

                if (username1.equals(""))
                {
                    Toast.makeText(BookAService.this, "Fill all required details.", Toast.LENGTH_LONG).show();
                }
                else
                {
                    final String quser="Username="+username1;
                    final String qmodel="&carmodel="+Spin;
                    final String qsertype="&serpaytype="+radio;
                    final String qserdes="&serdescri="+radio2;

                    StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://projectcsms.000webhostapp.com/CSMS/BookAService.php?"+quser+qmodel+qsertype+qserdes, new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            progress.setProgress(80);
                            //Toast.makeText(BookAService.this, response, Toast.LENGTH_LONG).show();
                            if(response.trim().equalsIgnoreCase("Failure"))
                            {
                                progress.dismiss();
                                Toast.makeText(BookAService.this,"Imformation Is Not Valid",Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                try{
                                    Toast.makeText(BookAService.this,response.toString(),Toast.LENGTH_SHORT).show();

                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray result = jsonObject.getJSONArray("result");
                                    JSONObject data = result.getJSONObject(0);

                                    if(data.getString("error").equalsIgnoreCase("false"))
                                    {
                                        String email = data.getString("email").trim();
                                        SendEmail sendEmail=null;
                                        sendEmail = new SendEmail(email,"service");
                                        Toast.makeText(BookAService.this,email,Toast.LENGTH_LONG).show();
                                        sendEmail.makeRequest(getApplicationContext());

                                        Intent intent = new Intent(BookAService.this, Main_Page.class);
                                        BookAService.this.startActivity(intent);
                                        BookAService.this.finish();
                                        progress.dismiss();
                                    }
                                    else
                                    {
                                        progress.dismiss();
                                        Toast.makeText(BookAService.this,"Booking Failed",Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(BookAService.this, "Service Booked", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(BookAService.this,Main_Page.class));
                                }
                            }){
                        @Override
                        protected Map<String,String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("Username", username1);
                            params.put("carmodel", Spin);
                            params.put("serpaytype", radio);
                            params.put("serdescri", radio2);
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(BookAService.this);
                    requestQueue.add(stringRequest);
                    progress.show();
                    progress.setProgress(60);
                }
            }
        });
    }
}