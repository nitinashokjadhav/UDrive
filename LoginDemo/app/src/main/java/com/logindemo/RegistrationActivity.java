package com.logindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegistrationActivity extends AppCompatActivity {

    private EditText UserName, UserPassword, UserEmail;
    private Button regButton;
    private TextView userLogin;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {

                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
    }

    private void setupUIViews() {
        UserName = (EditText) findViewById(R.id.etUserName);
        UserPassword = (EditText) findViewById(R.id.etUserPassword);
        UserEmail = (EditText) findViewById(R.id.etEmail);
        regButton = (Button) findViewById(R.id.btnRegister);
        userLogin = (TextView) findViewById(R.id.tvLogin);
    }

    private Boolean validate() {
        Boolean result = false;
        String name = UserName.getText().toString();
        String password = UserPassword.getText().toString();
        String email = UserEmail.getText().toString();
        if (name.isEmpty() && password.isEmpty() && email.isEmpty()) {
            Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }
        return result;
    }


}
