package com.changeringingmode;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button vibrate = (Button) findViewById(R.id.vibrate);
		Button silent = (Button) findViewById(R.id.silent);
		Button normal = (Button) findViewById(R.id.normal);

		final AudioManager audioManager = 
				(AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);

		vibrate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
				
			}
		});
		
		silent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
				
			}
		});
		
		normal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
				
			}
		});
		
		
	}
}
