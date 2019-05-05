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

public class TeachersActivity extends AppCompatActivity {
    private static final String TAG = "TeachersActivity";
    private RecyclerView teachersRV;
    private FloatingActionButton addTeacherBtn;

    private List<User> teachers;


    private FirebaseFirestore mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Teachers");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initComponents();

        mDb = FirebaseFirestore.getInstance();

        addTeacherBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddUserActivity.class);
            intent.putExtra(AddUserActivity.PARAM_USER_GROUP, "Teacher");
            startActivity(intent);
            finish();
        });
    }

    private void initComponents() {
        teachersRV = findViewById(R.id.users_rv);
        teachersRV.setLayoutManager(new LinearLayoutManager(this));
        teachersRV.setHasFixedSize(true);

        addTeacherBtn = findViewById(R.id.add_teacher_fab);

        teachers = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        readTeachers();
    }

    private void readTeachers() {
        mDb.collection("users")
                .addSnapshotListener(
                        (queryDocumentSnapshots, e) -> {
                            if (e != null) {
                                Log.e(TAG, "readTeachers: Failed", e);
                                return;
                            }

                            if (queryDocumentSnapshots.isEmpty()) {
                                Toast.makeText(this, "No teachers yet", Toast.LENGTH_SHORT).show();
                            } else {
                                teachers.clear();
                                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                                    User user = snapshot.toObject(User.class);
                                    teachers.add(user);
                                }

                                UserAdapter adapter = new UserAdapter(this, teachers);
                                teachersRV.setAdapter(adapter);
                            }
                        }
                );
    }
}
