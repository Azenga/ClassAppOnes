package com.shadow.classappones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shadow.classappones.models.User;

import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends AppCompatActivity {

    private RecyclerView studentsRV;
    private FloatingActionButton addStudentBtn;

    private List<User> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Students");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initComponents();

        addStudentBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddUserActivity.class);
            intent.putExtra(AddUserActivity.PARAM_USER_GROUP, "Student");
            startActivity(intent);
            finish();
        });
    }

    private void initComponents() {
        studentsRV = findViewById(R.id.users_rv);
        addStudentBtn = findViewById(R.id.add_student_fab);

        students = new ArrayList<>();
    }


    @Override
    protected void onStart() {
        super.onStart();

        readStudents();
    }

    private void readStudents() {

    }
}
