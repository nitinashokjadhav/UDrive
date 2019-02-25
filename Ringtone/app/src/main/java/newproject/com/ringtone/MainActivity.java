package newproject.com.ringtone;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    public static AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button vibrate = (Button) findViewById(R.id.vibrate);
        Button silent = (Button)findViewById(R.id.silent);
        Button normal = (Button)findViewById(R.id.normal);

        NotificationManager notificationManager;
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted()) {

            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

            startActivity(intent);
        }
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        vibrate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (audioManager!=null) {
                    audioManager.setRingerMode(1);
                }
            }
        });

        silent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (audioManager != null) {
                    audioManager.setRingerMode(0);

                }

            }
        });

        normal.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (audioManager != null) {
                    audioManager.setRingerMode(2);
                }

            }
        });


    }
}

