package com.example.rasik.samplelogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by rasik on 18/9/17.
 */

public class RegisterActivity extends Activity {

    EditText txtUserName1,txtPassword1,txtEmail1;
    Button btnRegister1;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        sessionManagement = new SessionManagement(this);
        txtUserName1 = findViewById(R.id.txtUsername1);
        txtPassword1  = findViewById(R.id.txtPassword1);
        txtEmail1 = findViewById(R.id.txtEmail1);

        btnRegister1 = findViewById(R.id.btnRegistr1);

 /*       btnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUserName1.getText().toString().trim();
                String email = txtEmail1.getText().toString().trim();
                String password =  txtPassword1.getText().toString().trim();
                sessionManagement.saveUsers(username,email,password);
                Intent i =new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });*/

           btnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ;
                String userName = txtUserName1.getText().toString().trim();
                String email = txtEmail1.getText().toString().trim();
                String password =  txtPassword1.getText().toString().trim();
                UserVo u = new UserVo(userName,email,password);
                List<UserVo> UserList = new ArrayList<UserVo>();
                UserList.add(u);
                System.out.println(UserList);
               // sessionManagement.saveUser(getApplicationContext(),UserList);
                sessionManagement.addUser(getApplicationContext(),u);
                Intent i =new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
