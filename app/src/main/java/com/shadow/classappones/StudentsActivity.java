package com.shadow.classappones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shadow.classappones.adapters.UserAdapter;
import com.shadow.classappones.models.User;

import java.util.ArrayList;
import java.util.List;


public class StudentsActivity extends AppCompatActivity {
    private static final String TAG = "StudentsActivity";
    private RecyclerView studentsRV;
    private FloatingActionButton addStudentBtn;

    private List<User> students;

    private FirebaseFirestore mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Students");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initComponents();

        mDb = FirebaseFirestore.getInstance();

        addStudentBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddUserActivity.class);
            intent.putExtra(AddUserActivity.PARAM_USER_GROUP, "Student");
            startActivity(intent);
            finish();
        });
    }

    private void initComponents() {

        studentsRV = findViewById(R.id.users_rv);
        studentsRV.setLayoutManager(new LinearLayoutManager(this));
        studentsRV.setHasFixedSize(true);

        addStudentBtn = findViewById(R.id.add_student_fab);

        students = new ArrayList<>();
    }


    @Override
    protected void onStart() {
        super.onStart();

        readStudents();
    }

    private void readStudents() {
        mDb.collection("users")
                .addSnapshotListener(
                        (queryDocumentSnapshots, e) -> {
                            if (e != null) {
                                Log.e(TAG, "readStudents: Failed", e);
                                return;
                            }

                            if (queryDocumentSnapshots.isEmpty()) {
                                Toast.makeText(this, "No students yet", Toast.LENGTH_SHORT).show();
                            } else {
                                students.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    User user = snapshot.toObject(User.class);
                                    students.add(user);
                                }

                                UserAdapter adapter = new UserAdapter(this, students);
                                studentsRV.setAdapter(adapter);
                            }
                        }
                );
    }
}
