package com.mapview;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.transport.ServiceConnection;
import org.ksoap2.transport.Transport;
import org.xmlpull.v1.XmlPullParserException;

public class AndroidHttpTransport extends Transport
{
	String response;
	public AndroidHttpTransport(String url) {
		// TODO Auto-generated constructor stub
		super(url);
	}
	
	@Override
	public void call(String soapAction, SoapEnvelope envelope) throws IOException,
			XmlPullParserException {
		// TODO Auto-generated method stub
		if (soapAction == null) soapAction = "\"\"";
		  byte[] requestData = createRequestData(envelope);
		  requestDump = debug ? new String(requestData) : null;
		  responseDump = null;
		  ServiceConnection connection = getServiceConnection();
		  //connection.connect();
		  try
		  {
			   connection.setRequestProperty("User-Agent", "kSOAP/2.0");
			   connection.setRequestProperty("SOAPAction", soapAction);
			   connection.setRequestProperty("Content-Type", "text/xml");
			   connection.setRequestProperty("Connection", "close");
			   connection.setRequestProperty("Content-Length", "" + requestData.length);
			   connection.setRequestMethod("POST");   
			   connection.connect();
			   OutputStream os = connection.openOutputStream();
			   os.write(requestData, 0, requestData.length);
			   os.flush();
			   os.close();
			   requestData = null;
			   InputStream is;
			   try
			   {
				   connection.connect();
				   is = connection.openInputStream();
				   int a=10;
				   int b=a;
			   }
			   catch(IOException e)
			   {
				   is = connection.getErrorStream();
				    if (is == null) {
				    connection.disconnect();
				    throw (e);
			   }
		  }
			   if(debug){
				   ByteArrayOutputStream bos = new ByteArrayOutputStream();
				   byte[] buf = new byte[256];
				   while(true){
					   int rd = is.read(buf,0,256);
					   if(rd==-1)
						   break;
					   bos.write(buf,0,rd);				   
				   }
				   bos.flush();
				   buf = bos.toByteArray();
				   responseDump = new String(buf);
				   is.close();
				   is = new ByteArrayInputStream(buf);
			   }
			   parseResponse(envelope, is);
			   /*System.out.println("----------------><"+envelope+"><<<");
			   System.out.println("|---------------->["+envelope.bodyIn +"]<<<");
			   System.out.println("DBG:request:" + requestDump);
			   System.out.println("DBG:response:" + responseDump);*/
		  }
		  finally 
		  {
			   connection.disconnect();
		  }
	}

	
	private ServiceConnection getServiceConnection() throws IOException  
	{
		// TODO Auto-generated method stub
		 return new AndroidServiceConnection(url);
	}
	

}
