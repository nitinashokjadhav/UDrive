package com.logindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLoginViews();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });
    }

    private void setupLoginViews()
    {
        Name=(EditText)findViewById(R.id.etName);
        Password=(EditText)findViewById(R.id.etUserPassword);
        Login=(Button)findViewById(R.id.btLogin);
        register=(TextView)findViewById(R.id.Register);
    }
    private Boolean validate()
    {
       String uName,uPassword;
        Boolean result;
        result=false;
        uName=Name.getText().toString();
        uPassword=Password.getText().toString();
        if((uName=="Nitin") && (uPassword=="1234"))
        {
          Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            result=true;
        }
        else
        {
            Toast.makeText(this,"Please enter all details",Toast.LENGTH_SHORT).show();
        }
        return result;
    }
}
