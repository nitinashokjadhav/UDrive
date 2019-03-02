package newproject.com.newproject.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText name,password;
    private Button login;
    private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    setupValues();
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(validate())
            {
                //Context context = getApplicationContext();
                CharSequence text = "Hello  !";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText( getApplicationContext(), text, duration);
                toast.show();
            }
        }
    });

    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intent);
        }
    });
}
private void setupValues()
{
    name=(EditText)findViewById(R.id.etName);
    password=(EditText)findViewById(R.id.etEmail);
    login=(Button)findViewById(R.id.btnLogin);
    register=(TextView)findViewById(R.id.tvRegister);
}

private Boolean validate()
{
    Boolean res=false;
    String Name=name.getText().toString();
    String pass=password.getText().toString();
    if((Name.equals("Nitin"))&&(pass.equals("1234")))
    {
        res=true;
    }
    else
    {
        Toast.makeText(getApplicationContext(),"invalid details",Toast.LENGTH_SHORT).show();
    }
    return res;
}

}