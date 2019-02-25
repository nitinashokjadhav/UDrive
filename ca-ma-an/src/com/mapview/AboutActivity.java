package com.mapview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AboutActivity extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutactivity);
        Button btnAbout=(Button)findViewById(R.id.Btnabout);
        btnAbout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(AboutActivity.this,ClickOnOverLay.class);
    			startActivity(i);
    			finish();
			}
		});
        
        Button btncab=(Button)findViewById(R.id.Button03);
        btncab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(AboutActivity.this,IntheCabActivity.class);
				startActivity(i);
				finish();
			}
		});
        
        Button btnmap=(Button)findViewById(R.id.Button02);
        btnmap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(AboutActivity.this,ClickOnOverLay.class);
				startActivity(i);
				finish();
			}
		});
        
        Button btngo=(Button)findViewById(R.id.Button04);
        btngo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(AboutActivity.this,HistoryActivity.class);
				startActivity(i);
				finish();
			}
		});
	}
}
