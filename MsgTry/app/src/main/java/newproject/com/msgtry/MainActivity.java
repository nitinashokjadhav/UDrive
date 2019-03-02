package newproject.com.msgtry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

//import newproject.com.msgtry.R;
public class MainActivity extends Activity {
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.welcome);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String address = extras.getString("address");
            String message = extras.getString("message");
        //    TextView addressField = (TextView) findViewById(R.id.address);
          //  TextView messageField = (TextView) findViewById(R.id.message);
            //addressField.setText(address);
            //messageField.setText(message);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       /// getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
         //   case R.id.settings:
           //     startActivity(new Intent(this, SettingsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
