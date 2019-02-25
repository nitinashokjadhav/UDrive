package com.example.nitin.singuplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private Button register;
    private EditText Email,Password;
    private TextView login;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db=new DbHelper(getApplicationContext());
        register=(Button)findViewById(R.id.btnRegister);
        Email=(EditText)findViewById(R.id.etEmail);
        Password=(EditText)findViewById(R.id.etPass);
        login=(TextView)findViewById(R.id.tvLogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
                            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    private void register()
    {
        String email,pass;
        email=Email.getText().toString();
        pass=Password.getText().toString();
        if(email.isEmpty()&& pass.isEmpty())
        {
            displayToast("UserName/Password field is empty");
        }
        else
        {
            db.addUser(email,pass);
            displayToast("User registered successfully");
            finish();
        }
    }

    private void displayToast(String text)
    {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }
}
