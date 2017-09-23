package com.example.rasik.samplelogin;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rasik on 18/9/17.
 */

public class LoginActivity extends Activity {
    SharedPreferences pref;
    EditText txtUserName,txtPassword;

    Button btnLogin,btnRegister;

    AlertDialogManager alertDialogManager = new AlertDialogManager();

    SessionManagement sessionManagement;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        sessionManagement = new SessionManagement(getApplicationContext());

        txtUserName = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        Toast.makeText(getApplicationContext(), "User Login Status: " + sessionManagement.isLoggedIn(), Toast.LENGTH_LONG).show();

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUserName.getText().toString().trim();
                String password =  txtPassword.getText().toString().trim();
                HashMap<String,String> user =sessionManagement.getUserDetails();
                ArrayList<UserVo> userz =sessionManagement.getUsers(getApplicationContext());
                for(int i=0;i<userz.size();i++) {
                    System.out.println("USER: " + userz.get(i).getUserName());
                    System.out.println("USER: " + userz.get(i).getPassword());
                }
                pref = getSharedPreferences("Sample", 0);
                System.out.println("Pref: "+pref.getString("Users",null));
                String savedUser = null;
                String  savedPassword = null;
               // String savedUser = user.get("name");
               // String  savedPassword = user.get("password");
                //System.out.println(username.equals(savedUser) && password.equals(savedPassword));
                for(int j=0;j<userz.size();j++) {
                    if (username.equals(userz.get(j).getUserName()) && password.equals(userz.get(j).getPassword())){
                        savedUser=userz.get(j).getUserName();
                        savedPassword=userz.get(j).getPassword();
                        System.out.println(savedUser);
                    }
                }
                    if (username.length() > 0 && password.length() > 0) {
                        if (username.equals(savedUser) && password.equals(savedPassword)) {
                            sessionManagement.createLoginSession(savedUser, savedPassword);
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            finish();
                        }

                    else {
                            alertDialogManager.showAlertDialog(LoginActivity.this, "Login Failure", "Username or Password is incorrect", false);
                        }
                    } else {
                        alertDialogManager.showAlertDialog(LoginActivity.this, "Login Failure", "Please enter Username and Password", false);

                    }

            }
        });

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
