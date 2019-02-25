package com.mapview;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;


import android.R.string;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

public class BalloonOverlayView extends FrameLayout {	
	private static String SOAP_ACTION;
    private static String METHOD_NAME;
    private static String NAMESPACE;
    private static String URL;
	
	private RelativeLayout layout;
	private TextView title;
	private TextView snippet;
	private TextView regno;
	private TextView cabno;
	private TextView expr;
	private ImageView img;
	public String CabNumber;
	public String BusinessName;
	public String RegistrationNumber;
	public String Rating;
	public String Expired;
	public String id;
	public String ContactNumber;
	public String BusinessName1;
	public String RegisteredAddress;
	public String RegistrationType;
	
	public BalloonOverlayView(Context context, int balloonBottomOffset) {

		super(context);		
		setPadding(10, 0, 10, balloonBottomOffset);
		layout = new RelativeLayout(context);
		layout.setVisibility(VISIBLE);		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.cabdetailactivity, layout);
		title = (TextView) v.findViewById(R.id.TextView01);
		snippet = (TextView) v.findViewById(R.id.TextView02);
		regno = (TextView)v.findViewById(R.id.TextView03);
		cabno = (TextView)v.findViewById(R.id.TextView04);
		
		//img = (ImageView)v.findViewById(R.id.ImageView01);
		/*ImageView close = (ImageView) v.findViewById(R.id.close_img_button);
		close.setOnClickListener(new OnClickListener() {		

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				layout.setVisibility(GONE);
			}
		});*/

		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.NO_GRAVITY;

		addView(layout, params);

	}
	
	public void setData(OverlayItem item) {		
		try {			
			
				
			if(item.getTitle().equals("0")){
				
				SOAP_ACTION = "http://www.safecab.com/GetCabCompanyDetail";
				METHOD_NAME = "GetCabCompanyDetail";
				NAMESPACE = "http://www.safecab.com/";
				URL = "http://www.safecab.com/ClientInterface.asmx";
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	        	PropertyInfo pi = new PropertyInfo();
	        	//PropertyInfo pi1 = new PropertyInfo();
	        	//PropertyInfo pi2 = new PropertyInfo();
	        	pi.type = PropertyInfo.STRING_CLASS;
	        	pi.name = "BusinessId";
	        	pi.namespace = NAMESPACE;
	        	
	        	//request.addProperty(pi, "1");
	        	request.addProperty(pi, item.getSnippet());  	
	            SoapSerializationEnvelope envelope = 
	                new SoapSerializationEnvelope(SoapEnvelope.VER11);
	            envelope.dotNet = true;           
	            envelope.setOutputSoapObject(request);						
	            AndroidHttpTransport1 androidHttpTransport = new AndroidHttpTransport1(URL,"Company");
	            androidHttpTransport.call(SOAP_ACTION, envelope);
	            ContactNumber = "Contact Number:"+  androidHttpTransport.ContactNumber1;
	            BusinessName1 = "Business Name:"+ androidHttpTransport.BusinessName1;
	            RegisteredAddress ="Registered Address:"+  androidHttpTransport.RegisteredAddress;
	            RegistrationType="Registration Type:" + androidHttpTransport.RegistrationType;
	            layout.setVisibility(VISIBLE);
	    		//if (item.getTitle() != null) {
	    			title.setVisibility(VISIBLE);
	    			title.setText(ContactNumber);
	    			
	    		//} else {
	    			//title.setVisibility(GONE);
	    		//}
	    		//if (item.getSnippet() != null) {
	    			snippet.setVisibility(VISIBLE);
	    			snippet.setText(BusinessName1);
	    		//} else {
	    			//snippet.setVisibility(GONE);
	    		//}
	    			regno.setVisibility(VISIBLE);
	    			regno.setText(RegistrationType);	
			}
			else
			{
				SOAP_ACTION = "http://www.safecab.com/GetCabDetail";
				METHOD_NAME = "GetCabDetail";
				NAMESPACE = "http://www.safecab.com/";
				URL = "http://www.safecab.com/ClientInterface.asmx";
        	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        	PropertyInfo pi = new PropertyInfo();
        	PropertyInfo pi1 = new PropertyInfo();
        	//PropertyInfo pi2 = new PropertyInfo();
        	pi.type = PropertyInfo.STRING_CLASS;
        	pi.name = "DriverId";
        	pi.namespace = NAMESPACE;
        	
        	pi1.type = PropertyInfo.MULTI_REF;
        	pi1.name = "BusinessId";
        	pi1.namespace = NAMESPACE;
        	//request.addProperty(pi, "1");
        	request.addProperty(pi, item.getTitle());
        	request.addProperty(pi1, item.getSnippet());    
        	
        	//request.addProperty(pi,item.getSnippet());
        	//request.addProperty(pi1, item.getTitle());   
            SoapSerializationEnvelope envelope = 
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;           
            envelope.setOutputSoapObject(request);						
            AndroidHttpTransport1 androidHttpTransport = new AndroidHttpTransport1(URL,"Driver");
            androidHttpTransport.call(SOAP_ACTION, envelope);
            CabNumber = "CAB Number:"+  androidHttpTransport.CabNumber;
            BusinessName = "Business Name:"+ androidHttpTransport.BusinessName;
            RegistrationNumber ="Registration Number:"+  androidHttpTransport.RegistrationNumber;
            Expired = "Expired:" + androidHttpTransport.VehicleExpires;
            layout.setVisibility(VISIBLE);
    		//if (item.getTitle() != null) {
    			title.setVisibility(VISIBLE);
    			title.setText(CabNumber);
    			
    		//} else {
    			//title.setVisibility(GONE);
    		//}
    		//if (item.getSnippet() != null) {
    			snippet.setVisibility(VISIBLE);
    			snippet.setText(BusinessName);
    		//} else {
    			//snippet.setVisibility(GONE);
    		//}
    			regno.setVisibility(VISIBLE);
    			regno.setText(RegistrationNumber);
			}
		}
        catch(Exception e){
        	@SuppressWarnings("unused")
        	String asd = e.toString();
        	}
	}
}
