package com.example.nitin.singuplogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Session session;
    private Button Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session=new Session(getApplication());

        Logout=(Button)findViewById(R.id.btnLogout);

        if(session.loggedin())
        {
            Log.d("mainactivity","agrtg");
            logout();
        }
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }
    public void logout()
    {
        session.setLoggedin(true);
        finish();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
    }
}
