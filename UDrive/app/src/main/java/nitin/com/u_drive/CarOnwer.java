package nitin.com.u_drive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class CarOnwer extends AppCompatActivity {
    private static final String TAG = "MainActivity" ;

    CardView card1,card2;
    Button post_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_onwer);
        card1=(CardView)findViewById(R.id.card1);
        card2=(CardView)findViewById(R.id.card2);

        post_now=(Button)findViewById(R.id.post_now);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CarOnwer.this,
                        AddPhoto.class);
                startActivity(i);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CarOnwer.this,
                        YourItem.class);
                startActivity(i);

            }
        });



    }
}

