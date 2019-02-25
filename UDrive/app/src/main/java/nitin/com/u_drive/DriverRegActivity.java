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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class DriverRegActivity extends AppCompatActivity {
    public FirebaseAuth mAuth;
    EditText name, email, password, phone;
    Button register;
    public FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_reg);
        mAuth = FirebaseAuth.getInstance();
        name = (EditText) findViewById(R.id.rg_name);
        email = (EditText) findViewById(R.id.rg_email);
        phone = (EditText) findViewById(R.id.rg_phone);
        password = (EditText) findViewById(R.id.rg_password);
        register = (Button) findViewById(R.id.btn_register);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user  =  FirebaseAuth.getInstance().getCurrentUser();
                Log.e("USer ","Fck this shit''");
//                if(user!=null)
//                {
//                    Intent intent = new Intent(DriverRegActivity.this,MainActivity.class);
//                    startActivity(intent.putExtra("user_type","car_owner"));
//                    finish();
//                    return;
//                }
            }
        };



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("On", "Click");
                final String Email, pass, Name, PhnNo, pass2;
                Email = email.getText().toString();
                Log.e("e", Email);
                pass = password.getText().toString();
                Name = name.getText().toString();
                PhnNo = phone.getText().toString();

                try {

                    mAuth.createUserWithEmailAndPassword(Email, pass).addOnCompleteListener(DriverRegActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.e("Task", "task");
                                    if (task.isSuccessful()) {
                                        Log.e("login", "successfull");
                                        String user_id = mAuth.getCurrentUser().getUid();
                                        if (user_id != null) {
                                            Log.e("Register", user_id);
                                            DatabaseReference current_owner = FirebaseDatabase.getInstance().getReference().child("user").child("owner").child(user_id);
                                            User user = new User(Name, PhnNo, Email);
                                            current_owner.setValue(user);
                                            Toast.makeText(DriverRegActivity.this, "Logged in", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(DriverRegActivity.this, MainActivity.class).putExtra("user_type", "owner"));
                                        } else {
                                            Log.e("Register", "no user id found");
                                        }
                                    } else {
                                        FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                        Log.e("login", "" + e);
                                        Toast.makeText(DriverRegActivity.this, "Failed register", Toast.LENGTH_LONG);
                                    }
                                }
                            });
                }
                catch (Exception e)
                {

                }
            }

        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
}
