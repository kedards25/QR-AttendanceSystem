package com.learning.qr_attendancesystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int PERMISSION_ALL = 1;
    String[] PERMISSIONS = {
            android.Manifest.permission.READ_CONTACTS,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };


    SharedPreferences pref;
//    static boolean isEmailRegistered;
     String selVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         pref= this.getPreferences(Context.MODE_PRIVATE);
        boolean isEmailRegistered=pref.getBoolean("EmailRegistered",false);
        String selVal=pref.getString("PrimaryEmail", null);
        if (isEmailRegistered)
        {
            Toast.makeText(MainActivity.this,"Registered Email:\n"+selVal,Toast.LENGTH_LONG).show();
            Intent qrIntent=new Intent(MainActivity.this,QR_Scanner.class);
            startActivity(qrIntent);
        }
        else
        {
            Account[] accounts=getRegisteredAccounts();
            final String [] accountNames=new String[accounts.length];
            for (int i=0;i<accountNames.length;i++)
            {
                accountNames[i]=accounts[i].name;
            }
            AlertDialog.Builder builder=  new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Select Primary Email ID");
            builder.setItems(accountNames, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                     String selVal1=accountNames[which];

                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("PrimaryEmail", selVal1);
                    editor.putBoolean("EmailRegistered",true);
                      editor.commit();
                    Toast.makeText(MainActivity.this,selVal1,Toast.LENGTH_SHORT).show();
                    //isEmailRegistered=true;
                    Intent registerIntent=new Intent(MainActivity.this,RegisterStudent.class);
                    startActivity(registerIntent);
                }
            });
            builder.show();
        }



        }

    private Account[] getRegisteredAccounts() {
        Account[] accounts=null;
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        try {
            accounts= AccountManager.get(this).getAccountsByType("com.google");

        } catch (Exception e) {
            Log.i("Exception", "Exception:" + e);
        }

        return accounts;
    }


    public static boolean hasPermissions(Context context, String... permissions){
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
