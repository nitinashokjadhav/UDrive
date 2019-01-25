package nitin.com.u_drive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegisterAsActivity extends AppCompatActivity {
    TextView User;
    TextView Driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_as);
        User = (TextView)findViewById(R.id.as_user);
        Driver = (TextView)findViewById(R.id.as_driver);
        User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserRegActivity.class));
            }
        });
    }
}
