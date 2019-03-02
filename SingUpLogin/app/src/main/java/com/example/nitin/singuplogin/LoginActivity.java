package com.example.nitin.singuplogin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button login,register;
    private EditText Email,Password;
    private DbHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.btnLogin);
        register=(Button)findViewById(R.id.btnRegister);
        Email=(EditText)findViewById(R.id.etEmail);
        Password=(EditText)findViewById(R.id.etPass);
        db=new DbHelper(getApplicationContext());
        session=new Session(getApplicationContext());

        if(session.loggedin())
        {
            startActivity(new Intent (getApplicationContext(),MainActivity.class));
            finish();
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                getApplicationContext().startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }
    private void login()
    {
        String email,pass;
        email=Email.getText().toString();
        pass=Password.getText().toString();
        boolean login=db.getUser(email,pass);
        if(login)
        {
            session.setLoggedin(login);
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            getApplicationContext().startActivity(intent);

            displayToast("Login is success");
            finish();

        }
        else
        {
            displayToast("Login error ");
        }
    }
    private void displayToast(String text)
    {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }
}
