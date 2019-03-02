package com.mapview;

import com.google.android.maps.MapActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HistoryActivity extends MapActivity 
{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historydetailactivity);
        Button btngo=(Button)findViewById(R.id.Btngo);
        btngo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(HistoryActivity.this,AboutActivity.class);
				startActivity(i);
				finish();
			}
		});
        Button btncab=(Button)findViewById(R.id.Button03);
        btncab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(HistoryActivity.this,IntheCabActivity.class);
				startActivity(i);
				finish();
			}
		});
        
        Button btnmap=(Button)findViewById(R.id.Button02);
        btnmap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(HistoryActivity.this,ClickOnOverLay.class);
				startActivity(i);
				finish();
			}
		});
        
        Button btnhistory=(Button)findViewById(R.id.Button04);
        btnhistory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(HistoryActivity.this,HistoryActivity.class);
				startActivity(i);
				finish();
			}
		});
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
