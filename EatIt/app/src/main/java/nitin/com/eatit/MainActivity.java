package nitin.com.eatit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.graphics.*;

public class MainActivity extends AppCompatActivity {
    public Button btnSignUp,btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignUp= (Button)findViewById(R.id.btnSign);
        btnSignIn=(Button)findViewById(R.id.btnSignIn);
//        txtSlogan = (TextView)findViewById(R.id.txtSlogan);

//        Typeface face= Typeface.createFromAsset(getAssets(),"font/KaushanScript-Regular.otf");
//        txtSlogan.setTypeface(face);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignIn.class);
                startActivity(intent);
            }
        });
    }
}
