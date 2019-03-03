package nitin.com.u_drive;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ScaleDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import static android.text.TextUtils.isEmpty;

public class LoginActivity extends AppCompatActivity {
    CheckBox checkBox;
    EditText password,email;
    Button   login;
    FirebaseAuth mAuth;
    private Button btnVisible,btnInVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkBox = (CheckBox)findViewById(R.id.checkbox);
        password = (EditText)findViewById(R.id.et_password);
        email    = (EditText)findViewById(R.id.et_email);
        login    =  (Button) findViewById(R.id.btn_login);
        btnVisible =findViewById(R.id.visible);
        btnInVisible = findViewById(R.id.inVisible);
        btnVisible.setVisibility(View.INVISIBLE);
        mAuth=FirebaseAuth.getInstance();

        btnInVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnVisible.setVisibility(View.VISIBLE);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                btnInVisible.setVisibility(View.INVISIBLE);
            }
        });
    btnVisible.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                btnInVisible.setVisibility(View.VISIBLE);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String e,p;
                e=email.getText().toString();
                p=password.getText().toString();

                    mAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e("auth", "success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                DatabaseReference current= null;
                                String typ = null;
//                                 current = FirebaseDatabase.getInstance().getReference().child(user.getUid()).getRoot();
//                                 type = current.toString();
//                                Log.e("Parent",""+type);

                                 //Checking the type of user for changing activity
                                DbHelper dbHelper = new DbHelper(getApplicationContext());
                                Cursor cursor = dbHelper.getUser(e);

                                if (cursor != null && cursor.moveToFirst()) {
                                    typ = cursor.getString(2);
                                    Log.e("User", typ);
                                    cursor.close();
                                }

                                if(!typ.isEmpty())
                                {
                                    if(typ.contains("customer"))
                                    {
                                        Log.e("check","customer");
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("user_type", "customer"));
                                        finish();
                                    }
                                    else if(typ.contains("owner"))
                                    {
                                        Log.e("check","owner");
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class).putExtra("user_type", "owner"));
                                        finish();
                                    }
                                    else
                                    {
                                        Log.e("check","fail");
                                    }
                                }


                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e("auth", "fail "+task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }

        });

    }

    private void validation()
    {
        String Email,Password;
        Email = email.getText().toString();
        Password=password.getText().toString();
        if( isEmpty(Email) &&Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            email.setError("Enter registered email");
            if (isEmpty(Password))
            {
                password.setError("Enter Password");
            }
        }


    }
}
