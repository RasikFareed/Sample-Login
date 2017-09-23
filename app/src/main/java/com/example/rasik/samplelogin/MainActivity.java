
package com.example.rasik.samplelogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    AlertDialogManager alertDialogManager = new AlertDialogManager();

    SessionManagement sessionManagement;

    Button btnLogout;
    TextView lblRest;

    private  static String Url="https://cdn.rawgit.com/arpitmandliya/AndroidRestJSONExample/master/countries.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManagement = new SessionManagement(getApplicationContext());
        TextView lblEmail = (TextView) findViewById(R.id.lblEmail);
        TextView lblName = (TextView) findViewById(R.id.lblName);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        Toast.makeText(getApplicationContext(),"User Login Status: "+ sessionManagement.isLoggedIn(),Toast.LENGTH_SHORT).show();

        sessionManagement.checkLogin();

        HashMap<String,String> user =sessionManagement.getUserDetails();
        //System.out.println(user);
        String name = user.get(SessionManagement.KEY_NAME);
        String email =  user.get(SessionManagement.KEY_EMAIL);

        lblEmail.setText(email);
        lblName.setText(name);

        lblRest = (TextView) findViewById(R.id.lblRest);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManagement.logoutUser();
            }
        });
        requestData(Url);
    }

    public void requestData(String uri) {

        StringRequest request = new StringRequest(uri,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        lblRest.setText(response.toString());

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

      //  RequestQueue queue = Volley.newRequestQueue(this);
        //queue.add(request);
        VolleySingleton.getInstance(this).addToRequestQueue(request);
    }
}
