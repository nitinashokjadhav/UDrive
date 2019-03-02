package nitin.com.u_drive;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static nitin.com.u_drive.validator.EmailValidator;
import static nitin.com.u_drive.validator.NameValidator;
import static nitin.com.u_drive.validator.PasswordValidator;
import static nitin.com.u_drive.validator.PhoneNumberValidator;

public class UserRegActivity extends AppCompatActivity {
  public EditText name, phone, email, password,password2;

    private Button reg;
    private DbHelper db;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);
        mAuth       =   FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user  =  FirebaseAuth.getInstance().getCurrentUser();

                if(user!=null)
                {     Log.e("USer ","");
                    Intent intent = new Intent(UserRegActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };
        db=new DbHelper(getApplicationContext());
        name    = (EditText)findViewById(R.id.et_name);
        phone   = (EditText)findViewById(R.id.et_phone);
        email   =  (EditText)findViewById(R.id.et_email);
        password=  (EditText)findViewById(R.id.et_password);
        password2=(EditText)findViewById(R.id.et_password2);
        reg     =   (Button)findViewById(R.id.btn_reg);
//


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()) {
                    register();
                }
            }
        });

    }
    public void register() {
        final String Email,pass,Name,PhnNo,pass2;
        Email=email.getText().toString();
        Log.e("e",Email);
        pass=password.getText().toString();
        Name=name.getText().toString();
        PhnNo=phone.getText().toString();
        pass2 = password2.getText().toString();
        try {

            mAuth.createUserWithEmailAndPassword(Email, pass).addOnCompleteListener(UserRegActivity.this,
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                displayToast("User registered successfully");
                                String user_id = mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("user").child("customer").child(user_id);
                                Log.e("user_id", user_id);
                                String email = task.getResult().getUser().getEmail();
                                User user = new User(Name, PhnNo, email);
                                current_user_db.setValue(user);
                                DbHelper dbHelper = new DbHelper(getApplicationContext());
                                dbHelper.addUser(email,"customer");
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(Name).build();
                                FirebaseUser firebaseUser =mAuth.getCurrentUser();
                                firebaseUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d("name", "User profile updated.");
                                        }
                                    }
                                });

                                startActivity(new Intent(UserRegActivity.this, MainActivity.class).putExtra("user_type", "customer"));
                            } else {
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Log.e("login", "" + e);
                                Toast.makeText(UserRegActivity.this,""+e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
        catch (IllegalArgumentException e)
        {
            Toast.makeText(UserRegActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }

    private void displayToast(String text)
    {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }
    private boolean validateForm() {
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
        if(PasswordValidator(p))
        {
            password.setError("atleast 6 characters");
            result = false;
            return false;
        }
        return result;
    }
}
