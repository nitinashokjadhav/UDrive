package nitin.com.otp;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity    {

    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    TextView textView;
    String surl,otp,p;
    EditText phn;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(policy);

        btn = (Button)findViewById(R.id.button) ;
        phn = (EditText)findViewById(R.id.editText); //9920929694
        textView=(TextView)findViewById(R.id.textView);
        otp =OTP();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p =phn.getText().toString();
                Toast.makeText(MainActivity.this,p,Toast.LENGTH_SHORT);

                textView.setText(p);
                Send(p,otp);


            }
        });

    }

    protected void Send(String p,String o)
    {
        InputStream stream = null;
        try {
            URL url = new URL("https://smsapi.engineeringtgr.com/send/?Mobile=8779895394&Password=nitin777&Message="+o+"&To="+p+"&Key=popst2GWV9vD1sQclEKioMtu6R");
            URLConnection urlcon = url.openConnection();
            stream = urlcon.getInputStream();
            int i;
            String response = "";
            while ((i = stream.read()) != -1) {
                response += (char) i;
            }
            System.out.print(response);
            if (response.contains("success")) {
                System.out.println("Successfully send SMS");
                Log.e("inside on click",p);
                Toast.makeText(this, "OTP send ", Toast.LENGTH_SHORT).show();
            } else {
                System.out.println(response);
                Toast.makeText(this, "Error in sending OTP to "+p, Toast.LENGTH_LONG).show();
            }
            stream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    protected String OTP()
    {
        String otp="";
        String numbers = "0123456789";

        // Using random method
        Random rndm_method = new Random();



        for (int i = 0; i < 5; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp +=numbers.charAt(rndm_method.nextInt(numbers.length()));
        }

        return otp;
    }
}

