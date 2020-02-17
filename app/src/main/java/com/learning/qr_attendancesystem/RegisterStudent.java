package com.learning.qr_attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.learning.qr_attendancesystem.API_Services.IStudentAPI;
import com.learning.qr_attendancesystem.models.Students;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterStudent extends AppCompatActivity {

    SharedPreferences pref;
    String primaryEmail;
    EditText edtName, edtEmail, edtMobNo, edtCours, edtBatch;
    IStudentAPI studentAPI;
    Button btnRegister;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);


        edtName = findViewById(R.id.editText);
        edtEmail = findViewById(R.id.edt_email);
        edtMobNo = findViewById(R.id.editText3);
        edtCours = findViewById(R.id.editText2);
        edtBatch = findViewById(R.id.editText4);
        btnRegister=findViewById(R.id.button);
        txtView=findViewById(R.id.textView2);

        pref = getSharedPreferences("EmailShared",
                MODE_PRIVATE);
        primaryEmail = pref.getString("PrimaryEmail", null);

        edtEmail.setText(primaryEmail);
        edtEmail.setEnabled(false);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });
    }

    private void addStudent() {
        Students student=new Students(edtName.getText().toString(),edtEmail .getText().toString(),edtMobNo.getText().toString(),edtCours.getText().toString(),edtBatch.getText().toString());
        Call<Students> call=studentAPI.createStudent(student);

        call.enqueue(new Callback<Students>() {
            @Override
            public void onResponse(Call<Students> call, Response<Students> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(RegisterStudent.this,"Error Occurred"+response.code(),Toast.LENGTH_LONG).show();

                }

                    txtView.setText("Registration Successful..!!");

            }

            @Override
            public void onFailure(Call<Students> call, Throwable t) {
                Toast.makeText(RegisterStudent.this,"Error Thrown"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}

