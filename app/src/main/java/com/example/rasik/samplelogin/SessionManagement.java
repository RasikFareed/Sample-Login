
package com.example.rasik.samplelogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rasik on 18/9/17.
 */

public class SessionManagement {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    SharedPreferences.Editor saveEditor;
    Context con;

    int PRIVATE_MODE=0;

    private static final String PREF_NAME="Sample";
    private static final String IS_LOGIn="IsLoggedIn";
    public static  final String KEY_NAME="name";
    public static final  String KEY_EMAIL="email";
    public static final  String KEY_PASSWORD="password";


    public SessionManagement(Context context){
        this.con=context;
        pref = con.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = pref.edit();
        saveEditor=pref.edit();
    }

    public  void saveUsers(String name,String email,String password){
        saveEditor.putBoolean("Register",true);
        saveEditor.putString(KEY_NAME,name);
        saveEditor.putString(KEY_EMAIL,email);
        saveEditor.putString(KEY_PASSWORD,password);
        saveEditor.commit();
        Toast.makeText(con,"Save Sucess",Toast.LENGTH_SHORT).show();
    }

    public  void createLoginSession(String name,String password){
        editor.putBoolean("IsLoggedIn",true);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_PASSWORD,password);
        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String,String> user= new HashMap<String, String>();
        user.put(KEY_NAME,pref.getString(KEY_NAME,null));
        user.put(KEY_PASSWORD,pref.getString(KEY_PASSWORD,null));
        user.put(KEY_EMAIL,pref.getString(KEY_EMAIL,null));
        return user;
    }

    public  void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(con,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            con.startActivity(i);

        }
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIn, false);
    }

    public void logoutUser(){
       // editor.clear();
     //   editor.commit();

        Intent i = new Intent(con, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        con.startActivity(i);
    }


    public void saveUser(Context context, List<UserVo> userVos){
        pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        saveEditor = pref.edit();
        Gson gson = new Gson();
        String users = gson.toJson(userVos);
        saveEditor.putString("Users",users);
        saveEditor.commit();
    }

    public  void addUser(Context context,UserVo userVo){
        List<UserVo> userVos = getUsers(context);
        if (userVos==null)
            userVos = new ArrayList<UserVo>();
        userVos.add(userVo);
        saveUser(context,userVos);
    }

    public ArrayList<UserVo> getUsers(Context context){
        List<UserVo> userVos;

        pref = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);

        if(pref.contains("Users")){
            String users = pref.getString("Users",null);
            Gson gson = new Gson();
            UserVo[] allUsers = gson.fromJson(users,UserVo[].class);
            userVos = Arrays.asList(allUsers);
            userVos = new ArrayList<UserVo>(userVos);
            System.out.println("getUsers: "+userVos.size());
        }else
            return null;
        return (ArrayList<UserVo>)userVos;
    }
}
