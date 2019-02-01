package nitin.com.u_drive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Tour_Info extends AppCompatActivity {
    EditText location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_info);
        location    =(EditText)findViewById(R.id.tx_location);
        location.setText(getIntent().getStringExtra("location"));
    }
}
