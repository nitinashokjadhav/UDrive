package nitin.com.u_drive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
CheckBox checkBox;
EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        checkBox = (CheckBox)findViewById(R.id.checkbox);
        password = (EditText)findViewById(R.id.et_password);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }
}
