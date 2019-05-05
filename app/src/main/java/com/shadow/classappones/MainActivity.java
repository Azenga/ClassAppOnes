package com.shadow.classappones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

        //Changes the title in the action bar
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Class List");

        //CLicking the students CardView takes you to an activity for students
        CardView studentsCV = findViewById(R.id.students_card);
        studentsCV.setOnClickListener(view -> startActivity(new Intent(this, StudentsActivity.class)));

        //Clicking the teachers CardView takes you to teachers activity
        CardView teachersCV = findViewById(R.id.teachers_card);
        teachersCV.setOnClickListener(view -> startActivity(new Intent(this, TeachersActivity.class)));
    }
}
