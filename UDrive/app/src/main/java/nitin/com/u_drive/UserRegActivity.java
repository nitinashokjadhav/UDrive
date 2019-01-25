package nitin.com.u_drive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegActivity extends AppCompatActivity {
    EditText name;
    EditText phone;
    EditText email;
    EditText password;
    private Button reg;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);
        db=new DbHelper(getApplicationContext());
        name    = (EditText)findViewById(R.id.et_name);
        phone   = (EditText)findViewById(R.id.et_phone);
        email   =  (EditText)findViewById(R.id.et_email);
        password=  (EditText)findViewById(R.id.et_password);
        reg     =   (Button)findViewById(R.id.btn_reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }
    private void register()
    {
        String Email,pass;
        Email=email.getText().toString();
        pass=password.getText().toString();
        if(Email.isEmpty()&& pass.isEmpty())
        {
            displayToast("UserName/Password field is empty");
        }
        else
        {
            db.addUser(Email,pass);
            displayToast("User registered successfully");
            finish();
        }
    }

    private void displayToast(String text)
    {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

}
