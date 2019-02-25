package com.mapview;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import com.mapview.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Carmap extends Activity 
{
	 private static final String SOAP_ACTION = "http://www.safecab.com/SignIn"; 
	 private static final String METHOD_NAME = "SignIn";
	 private static final String NAMESPACE = "http://www.safecab.com";
	 private static final String URL = "http://www.safecab.com/ClientInterface.asmx"; 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ImageView imageView=(ImageView)findViewById(R.id.ImageView01);
        imageView.setImageResource(R.drawable.headers);
        Button imageBtn=(Button)findViewById(R.id.Button01);
        imageBtn.setOnClickListener(new OnClickListener() 
        {	
        	@Override
			public void onClick(View v) {
        		 try {
        	        	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        	        	PropertyInfo pi = new PropertyInfo();
        	        	pi.type = PropertyInfo.STRING_CLASS;
        	        	pi.name = "MobilePhone";
        	        	pi.namespace = NAMESPACE;
        	        	request.addProperty(pi, "07887 570570");
        	            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        	            envelope.dotNet = true;           
        	            envelope.setOutputSoapObject(request);
        	            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);
        	            androidHttpTransport.call(SOAP_ACTION, envelope);
        	            SoapObject result = (SoapObject)envelope.bodyIn;
        	            Intent i=new Intent(Carmap.this,ClickOnOverLay.class);
        	            i.putExtra("com.mapview.UserId",result.toString());
            			startActivity(i);
            			finish();
        	        } 
        	        catch(Exception E) 
        	        {
        	        	String str = E.toString();
        	        }	      
			}
		});
		
    }
}