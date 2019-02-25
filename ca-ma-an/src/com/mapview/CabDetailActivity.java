package com.mapview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CabDetailActivity extends Activity {
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.cabdetailactivity);
	        TextView txt1=(TextView)findViewById(R.id.TextView01);
	        TextView txt2=(TextView)findViewById(R.id.TextView02);
	        TextView txt3=(TextView)findViewById(R.id.TextView03);
	        TextView txt4=(TextView)findViewById(R.id.TextView04);
	        TextView txt5=(TextView)findViewById(R.id.TextView05);
	        String txt10="A1 Taxis\n" + 
	        "01582\n" + 
	        "654345\n";
	        String txt20="Name:Stephen Robinson";
	        String txt30="Registration:MK43 RWE";
	        String txt40="Can No:1234567890";
	        String txt50="Expired:31st Jan 2011";
	        txt1.setText(txt10);
	        txt2.setText(txt20);
	        txt3.setText(txt30);
	        txt4.setText(txt40);
	        txt5.setText(txt50);
	 }
}
