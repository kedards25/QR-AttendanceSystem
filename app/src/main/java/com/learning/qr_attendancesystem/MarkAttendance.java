package com.learning.qr_attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MarkAttendance extends AppCompatActivity {

    TextView txtAttendanceDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendance);
        txtAttendanceDetails=findViewById(R.id.textView);
        showDetails();
    }

    private void showDetails() {
        Intent intent=getIntent();
        Bundle recvdBundle= intent.getExtras();
        String details=recvdBundle.getString("QR_Data");
        txtAttendanceDetails.setText(details);
    }
}
