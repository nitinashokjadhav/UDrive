package nitin.example.com.voicerecorder2;

import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaRecorder mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_2_TS);
    }
}
