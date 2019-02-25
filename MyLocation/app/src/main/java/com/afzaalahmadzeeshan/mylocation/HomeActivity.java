package com.afzaalahmadzeeshan.mylocation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Observer;

public class HomeActivity extends AppCompatActivity {
    private LocationService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        service = new LocationService(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CheckBox checkBox = (CheckBox) findViewById(R.id.enable_service);
        greet(checkBox.isChecked());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                BroadcastLocationService.enabled = isChecked;
                greet(isChecked);

                if(isChecked) {
                    service.getLocation();
                } else {
                    service.cancelUpdates();
                }
            }
        });

        // Set the listeners for updating the values.
        final EditText meters = (EditText) findViewById(R.id.meters_distance);
        final EditText minutes = (EditText) findViewById(R.id.time_interval);

        Button button = (Button) findViewById(R.id.update_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate the data and then store in the databases
                long m = Long.valueOf(meters.getText().toString());
                long min = Long.valueOf(minutes.getText().toString());

                if (min > 18000 && min < 5) {
                    new AlertDialog.Builder(getBaseContext())
                            .setTitle("Time not correct")
                            .setMessage("Time must be 5 minutes minimum and 18000 minutes at max. If you want to set the service to notify once a day, then you may consider enabling service again tomorrow.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    meters.setText("");
                                }
                            })
                            .create().show();
                }

                if (m < 100) {
                    new AlertDialog.Builder(getBaseContext())
                            .setTitle("Invalid distance")
                            .setMessage("Minimum value for distance is set to 100m; to save the battery timing on your device.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    minutes.setText("");
                                }
                            })
                            .create().show();
                }

                if(m > 99 && min > 4 && min < 18001) {
                    // Valid, update the database.
                    new ContentManager(getBaseContext()).setupSettings(getBaseContext(), m, min);
                }
            }
        });
    }

    public void greet(boolean isChecked) {
        TextView textView = (TextView)findViewById(R.id.welcome_information_home);
        if (isChecked) {
            ContentManager manager = new ContentManager(getBaseContext());
            String welcomeText = String.valueOf(manager.getNumbers().size()) + " contacts will be notified after "
                    + manager.getMinutes() + " minutes, only if you have walked (moved) " + String.valueOf(manager.getMeters() / 1000) + " kms.";
            textView.setText(welcomeText);
        } else {
            textView.setText(R.string.service_disabled_welcome);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            // New activity for numbers.
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.action_edit_numbers) {
            // New activity for numbers.
            Intent intent = new Intent(this, EditNumbersActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
