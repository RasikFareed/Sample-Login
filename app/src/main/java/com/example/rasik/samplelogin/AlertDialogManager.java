
package com.example.rasik.samplelogin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by rasik on 18/9/17.
 */

public class AlertDialogManager {

    public void showAlertDialog(Context context,String title,String message, Boolean status){
        AlertDialog alertDialog=new AlertDialog.Builder(context).create();

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);

        if (status !=null){
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            alertDialog.show();
        }
    }
}
