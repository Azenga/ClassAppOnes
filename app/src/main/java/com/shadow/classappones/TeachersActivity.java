package com.shadow.classappones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TeachersActivity extends AppCompatActivity {

    private RecyclerView teachersRV;
    private FloatingActionButton addTeacherBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Teachers");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initComponents();

        addTeacherBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddUserActivity.class);
            intent.putExtra(AddUserActivity.PARAM_USER_GROUP, "Teacher");
            startActivity(intent);
            finish();
        });
    }

    private void initComponents() {
        teachersRV = findViewById(R.id.users_rv);
        addTeacherBtn = findViewById(R.id.add_teacher_fab);
    }

    @Override
    protected void onStart() {
        super.onStart();

        readTeachers();
    }

    private void readTeachers() {

    }
}
