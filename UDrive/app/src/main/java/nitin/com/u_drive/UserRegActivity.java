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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegActivity extends AppCompatActivity {
    EditText name, phone, email, password,password2;

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
               // if(validateForm()) {
                    register();
                //}
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
                                current_user_db.setValue(true);
                                startActivity(new Intent(UserRegActivity.this, MainActivity.class).putExtra("user_type", "customer"));
                            } else {
                                FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                Log.e("login", "" + e);
                                Toast.makeText(UserRegActivity.this, e.getLocalizedMessage(), Toast.LENGTH_LONG);
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
//    private boolean validateForm() {
//        boolean result = true;
//        String e = Email.trim();
//        if (e.isEmpty())
//        {
//            email.setError("Empty");
//            result = false;
//        }
//        if (Name.equals(""))
//        {
//            name.setError("Empty");
//            result = false;
//        }
//        if (PhnNo.equals(""))
//        {
//            phone.setError("Empty");
//            result = false;
//        }
//        if (password.length()<7)
//        {
//            password.setError("atleast 6");
//            result = false;
//        }
//
//        return result;
//    }
}
