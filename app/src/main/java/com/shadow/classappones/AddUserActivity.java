package com.shadow.classappones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddUserActivity extends AppCompatActivity {

    private static final String TAG = "AddUserActivity";
    public static final String PARAM_USER_GROUP = "user-group";

    private String mUserGroup = null;

    private TextInputEditText nameTIET, ageTIET, locationTIET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        mUserGroup = getIntent().getStringExtra(PARAM_USER_GROUP);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Add " + mUserGroup);

        initComponents();
    }

    private void initComponents() {
        nameTIET = findViewById(R.id.name_txt);
        ageTIET = findViewById(R.id.age_txt);
        locationTIET = findViewById(R.id.location_txt);
    }

    public void addUser(View view) {

        String name = nameTIET.getText().toString();
        String location = locationTIET.getText().toString();
        int age = Integer.parseInt(locationTIET.getText().toString());

        if (emptyFields()) return;

        Map<String, Object> userMap = new HashMap<>();

        userMap.put("name", name);
        userMap.put("location", location);
        userMap.put("age", age);
        userMap.put("group", mUserGroup.toLowerCase());
        userMap.put("timestamp", new Date());
    }

    private boolean emptyFields() {

        if (TextUtils.isEmpty(nameTIET.getText())) {
            nameTIET.setError("Name is required");
            nameTIET.requestFocus();
            return true;
        } else if (TextUtils.isEmpty(ageTIET.getText())) {
            ageTIET.setError("Age is required");
            ageTIET.requestFocus();
            return true;
        } else if (TextUtils.isEmpty(locationTIET.getText())) {
            locationTIET.setError("Location is required");
            locationTIET.requestFocus();
            return true;
        } else return false;

    }
}
