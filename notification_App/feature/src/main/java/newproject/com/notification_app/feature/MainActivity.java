package newproject.com.notification_app.feature;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    NotificationCompat.Builder notification;
    private static final int uniqueID=45612;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
    }
    public void buckysButtonClicked(View view){
        //Customizing notification
        notification.setSmallIcon(R.mipmap.ic_launcher);
        notification.setTicker("this is ticker");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("notification for kola");
        notification.setContentText("tere bhai ne dunda ye");

        //whenever user will click notificatiohe will auto matically moved to app
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        //Build notification and issue it
        NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());
    }
}
