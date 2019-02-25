package nitin.com.layoutpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            URL myURL = new URL("https://2factor.in/API/V1/2cb88a6e-0762-11e9-a895-0200cd936042/SMS/+919594108026/AUTOGEN");
            HttpURLConnection httpConn = (HttpURLConnection)myURL.openConnection();
            httpConn.setRequestMethod("GET");
        }
        catch (MalformedURLException e) {
            // new URL() failed
            // ...
        }
        catch (IOException e) {
            // openConnection() failed
            // ...
        }

    }


}
