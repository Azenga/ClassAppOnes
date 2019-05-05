package com.shadow.classappones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shadow.classappones.models.User;

public class AddUserActivity extends AppCompatActivity {

    private static final String TAG = "AddUserActivity";
    public static final String PARAM_USER_GROUP = "user-group";

    private String mUserGroup = null;

    private TextInputEditText nameTIET, ageTIET, locationTIET;
    private Button button;

    private FirebaseFirestore mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        mUserGroup = getIntent().getStringExtra(PARAM_USER_GROUP);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Add " + mUserGroup);

        initComponents();
        mDb = FirebaseFirestore.getInstance();

        button.setOnClickListener(view -> addUser());
    }

    private void initComponents() {
        nameTIET = findViewById(R.id.name_txt);
        ageTIET = findViewById(R.id.age_txt);
        locationTIET = findViewById(R.id.location_txt);

        button = findViewById(R.id.button);
    }

    private void addUser() {

        if (TextUtils.isEmpty(nameTIET.getText())) {
            nameTIET.setError("Name is required");
            nameTIET.requestFocus();
            return;
        } else if (TextUtils.isEmpty(ageTIET.getText())) {
            ageTIET.setError("Age is required");
            ageTIET.requestFocus();
            return;
        } else if (TextUtils.isEmpty(locationTIET.getText())) {
            locationTIET.setError("Location is required");
            locationTIET.requestFocus();
            return;
        }

        String name = String.valueOf(nameTIET.getText());
        String location = String.valueOf(locationTIET.getText());
        int age = Integer.parseInt(String.valueOf(ageTIET.getText()));

        User user = new User(name, location, mUserGroup, age);

        ProgressDialog.show(this, "Adding " + mUserGroup, "Please wait...");

        mDb.collection("users")
                .add(user)
                .addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(this, mUserGroup + " added", Toast.LENGTH_SHORT).show();

                                if (mUserGroup.equalsIgnoreCase("Student")) {
                                    startActivity(new Intent(this, StudentsActivity.class));
                                    finish();
                                } else {
                                    startActivity(new Intent(this, TeachersActivity.class));
                                    finish();
                                }
                            } else {
                                Log.e(TAG, "addUser: Failed", task.getException());
                                Toast.makeText(this, "Try Again Later", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

    }

}
