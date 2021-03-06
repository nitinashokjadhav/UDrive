package nitin.com.u_drive;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import static nitin.com.u_drive.validator.EmailValidator;
import static nitin.com.u_drive.validator.NameValidator;
import static nitin.com.u_drive.validator.PasswordValidator;
import static nitin.com.u_drive.validator.PhoneNumberValidator;

public class DriverRegActivity extends AppCompatActivity {
    EditText name, email, password, phone;
    Button register;
    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_reg);
        mAuth = FirebaseAuth.getInstance();
        name =  findViewById(R.id.rg_name);
        email =  findViewById(R.id.rg_email);
        phone =  findViewById(R.id.rg_phone);
        password =  findViewById(R.id.rg_password);
        register =  findViewById(R.id.btn_register);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("On", "Click");
               if(validate())
               {
                    register();
               }
            }

        });
    }

    private boolean validate() {
        boolean result = true;
        String e = email.getText().toString();
        String n = name.getText().toString();
        String p = phone.getText().toString();
        String pa = password.getText().toString();
        if (!NameValidator(n))
        {
            name.setError("Name is wrong or empty");
            result = false;
            return false;
        }

        if(!PhoneNumberValidator(p))
        {
            phone.setError("Email is wrong or empty");
            result = false;
            return false;
        }
        if(!EmailValidator(e))
        {
            email.setError("Email is wrong or empty");
            result = false;
            return false;
        }
        if(pa.length()<7)
        {
            password.setError("atleast 6 characters");
            result = false;
            return false;
        }
        return result;
    }


    public void register()
    {
        final String Email, pass, Name, PhnNo, pass2;
        Email = email.getText().toString();
        Log.e("e", Email);
        pass = password.getText().toString();
        Name = name.getText().toString();
        PhnNo = phone.getText().toString();
        final DbHelper dbHelper = new DbHelper(getApplicationContext());
        try {

            mAuth.createUserWithEmailAndPassword(Email, pass).addOnCompleteListener(DriverRegActivity.this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.e("Task", "task");
                            if (task.isSuccessful()) {
                                Log.e("login", "successfull");
                                String user_id = mAuth.getCurrentUser().getUid();
                                if (user_id != null)
                                {
                                    Log.e("Register", user_id);
                                    DatabaseReference current_owner = FirebaseDatabase.getInstance().getReference().child("user").child("owner").child(user_id);
                                    User user = new User(Name, PhnNo, Email,"owner");
                                    current_owner.setValue(user);
                                    dbHelper.addUser(Email,"owner");
                                    Toast.makeText(DriverRegActivity.this, "Logged in", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(DriverRegActivity.this, CarOnwer.class));
                                } else {
                                    Log.e("Register", "no user id found");
                                }
                            }
                            else
                            {
                                FirebaseException e = (FirebaseException) task.getException();
                                Log.e("login", "" + e);
                                Toast.makeText(DriverRegActivity.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
        catch (IllegalArgumentException e)
        {
            Toast.makeText(DriverRegActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
