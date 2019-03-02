package com.mapview;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpConnection;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.HttpURL;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.ksoap2.transport.ServiceConnection;

public class AndroidServiceConnection implements ServiceConnection
{
	private HttpURLConnection connection;
	//private static HttpConnectionManager connectionManager = new SimpleHttpConnectionManager();
    //private HttpConnection connection;
    //private PostMethod postMethod;
    //private java.io.ByteArrayOutputStream bufferStream = null;
    
	public AndroidServiceConnection(String url) throws IOException 
	{
		// TODO Auto-generated constructor stub
		connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		connection.setDoInput(true);
	/*	HttpURL httpURL = new HttpURL(url);
        HostConfiguration host = new HostConfiguration();
        host.setHost(httpURL.getHost(), httpURL.getPort());
        connection = connectionManager.getConnection(host);
        postMethod = new PostMethod(url);*/
	}

	@Override
	public void connect() throws IOException {
		// TODO Auto-generated method stub
		/*if (!connection.isOpen()) 
		 { 
			connection.open(); 
		 }*/
		connection.connect();
	}

	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub
		//connection.releaseConnection();
		connection.disconnect();
	}

	@Override
	public InputStream getErrorStream() {
		// TODO Auto-generated method stub
		//return null;
		return connection.getErrorStream();
	}

	@Override
	public InputStream openInputStream() throws IOException {
		// TODO Auto-generated method stub
		    /*RequestEntity re = new ByteArrayRequestEntity(bufferStream.toByteArray());
	        postMethod.setRequestEntity(re);
	        postMethod.execute(new HttpState(), connection);
	        return postMethod.getResponseBodyAsStream();*/
		return connection.getInputStream();
	}

	@Override
	public OutputStream openOutputStream() throws IOException {
		// TODO Auto-generated method stub
		/*bufferStream = new java.io.ByteArrayOutputStream();
	     return bufferStream;*/
		return connection.getOutputStream();
	}

	@Override
	public void setRequestMethod(String requestMethod) throws IOException {
		// TODO Auto-generated method stub
		/*if (!requestMethod.toLowerCase().equals("post")) 
		{
			throw(new IOException("Only POST method is supported")); 
		}*/
		connection.setRequestMethod(requestMethod);
	}

	@Override
	public void setRequestProperty(String name, String value) throws IOException {
		// TODO Auto-generated method stub
		//postMethod.setRequestHeader(name, value);
		connection.setRequestProperty(name, value);
	}

}
