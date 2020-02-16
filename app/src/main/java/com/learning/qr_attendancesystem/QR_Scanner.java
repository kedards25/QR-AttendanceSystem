package com.learning.qr_attendancesystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QR_Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView scannerView;
    SharedPreferences pref;
    String primaryEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView=new ZXingScannerView(this);
        setContentView(scannerView);
        pref= getSharedPreferences("EmailShared",
                MODE_PRIVATE);
        primaryEmail=pref.getString("PrimaryEmail", null);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(QR_Scanner.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(QR_Scanner.this, new String[]{Manifest.permission.CAMERA},1);
            }
            else
            {
                scanQR();
            }
        }
        else
        {
            Toast.makeText(QR_Scanner.this,"No need of Permission",Toast.LENGTH_LONG).show();
        }
    }

    private void scanQR() {
        if (scannerView==null)
        {
            scannerView=new ZXingScannerView(this);
            setContentView(scannerView);
        }
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        scannerView.stopCamera();
//    }

    @Override
    public void handleResult(Result result) {
        String scanResult=result.getText()+"\n"+primaryEmail;

        Intent attendanceIntent=new Intent(this,MarkAttendance.class);
        Bundle b=new Bundle();
        b.putString("QR_Data",scanResult);
        attendanceIntent.putExtras(b);
        startActivity(attendanceIntent);
    }

}
