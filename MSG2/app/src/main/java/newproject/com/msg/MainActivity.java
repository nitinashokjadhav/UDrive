package newproject.com.msg;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;

import static java.sql.DriverManager.println;
import static java.text.MessageFormat.*;
import static java.text.MessageFormat.format;

public class MainActivity extends AppCompatActivity {
    NotificationCompat.Builder notification;
    private static final int uniqueID=45612;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        // Todo : Receive Message And Number In Intent
//        println("hello there");
        Intent extras = getIntent();
      // Toast.makeText(getApplicationContext(),"MainActivity",Toast.LENGTH_LONG).show();
       if (extras != null) {
            String address = extras.getStringExtra("MessageNumber");
            String message = extras.getStringExtra("Message");
            //TextView addressField = (TextView) findViewById(R.id.etaddress);
            TextView messageField = (TextView) findViewById(R.id.message);
            //Toast.makeText(getApplicationContext(),address +" "+message,Toast.LENGTH_LONG).show();

            //addressField.setText(format("Message From : ", address));
            messageField.setText(format("Message : ", message));
       }
    }
}
