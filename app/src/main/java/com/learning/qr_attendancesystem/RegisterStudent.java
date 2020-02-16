package com.learning.qr_attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class RegisterStudent extends AppCompatActivity {

    SharedPreferences pref;
    String primaryEmail;
    EditText edtEmail;
    Spinner courseSpinner,batchSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);

        courseSpinner=findViewById(R.id.spnr_Course);
        edtEmail=findViewById(R.id.edt_email);
        batchSpinner=findViewById(R.id.spnr_batch);

        pref= getSharedPreferences("EmailShared",
                MODE_PRIVATE);
        primaryEmail=pref.getString("PrimaryEmail", null);

        edtEmail.setText(primaryEmail);
        edtEmail.setEnabled(false);
        populateCourseSpinner();
        populateBatchSpinner();
    }

    private void populateCourseSpinner() {
        List<String> CoursesList=new ArrayList<>();
        CoursesList.add("FSSE");
        CoursesList.add("PAMS");
        CoursesList.add("GNIIT");

        //ArrayAdapter<String> CoursesArrayAdapter=new ArrayAdapter(this,R.layout.courses_spinner,CoursesList);
    }


    private void populateBatchSpinner() {
    }
}
