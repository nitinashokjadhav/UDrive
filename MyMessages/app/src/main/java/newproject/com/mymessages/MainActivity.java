package newproject.com.mymessages;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {
//EditText et=(EditText)findViewById(R.id.body);
//final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.sound_file_1);

    public static AudioManager audioManager;
    private static final String TAG = "MainActivity";
    private static final int SMS_PERMISSION_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationManager notificationManager;
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !notificationManager.isNotificationPolicyAccessGranted()) {

            Intent intent = new Intent(
                    android.provider.Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

            startActivity(intent);
        }
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        if (!hasReadSmsPermission()) {
            showRequestPermissionsInfoAlertDialog();
        }
        Intent intent = getIntent();
        String body="";
       body=intent.getStringExtra("Body");

      // et.setText(" ");
       // txt.setText(body.toString());
        Toast.makeText(getApplicationContext(),"Main Activity Message : "+body,Toast.LENGTH_SHORT).show();
        if(body==null)
        {

        }
         else if(body.equals("ring")||body.equals("Ring"))
            {
                audioManager.setRingerMode(2);
                audioManager.setStreamVolume(AudioManager.STREAM_RING,audioManager.getStreamMaxVolume(AudioManager.STREAM_RING),0);
                Toast.makeText(getApplicationContext(),"Normal mode activated "+body,Toast.LENGTH_SHORT).show();
           }
       else if(body.equals("silent")||body.equals("Silent"))
     {
         audioManager.setRingerMode(1);
         Toast.makeText(getApplicationContext(), "Silent mode !!!", Toast.LENGTH_SHORT).show();
     }
     else if(body.equals("Location"))
        {
            Intent intent1=new Intent(MainActivity.this,SendLocation.class);
            startActivity(intent1);
        }
        else if(body.equals("Camera"))
        {
            Intent intent2=new Intent(MainActivity.this,CameraActivity.class);
            startActivity(intent2);
        }
     else if (body.equals("vibrate")||body.equals("Vibrate"))
        {
            audioManager.setRingerMode(0);
            Toast.makeText(getApplicationContext(),"Vibrate mode ",Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Validates if the app has readSmsPermissions and the mobile phone is valid
     *
     * @return boolean validation value
     */
    private boolean hasValidPreConditions() {
        if (!hasReadSmsPermission()) {
            requestReadAndSendSmsPermission();
            return false;
        }

//        if (!SmsHelper.isValidPhoneNumber(mNumberEditText.getText().toString())) {
//            Toast.makeText(getApplicationContext(), R.string.error_invalid_phone_number, Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }
    private boolean hasReadSmsPermission() {
        return ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED;
    }
    private void requestReadAndSendSmsPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_SMS)) {
            Log.d(TAG, "shouldShowRequestPermissionRationale(), no permission requested");
            return;
        }
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},
                SMS_PERMISSION_CODE);
    }
    private void showRequestPermissionsInfoAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.permission_alert_dialog_title);
        builder.setMessage(R.string.permission_dialog_message);
        builder.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                requestReadAndSendSmsPermission();
            }
        });
        builder.show();
    }
  


}
