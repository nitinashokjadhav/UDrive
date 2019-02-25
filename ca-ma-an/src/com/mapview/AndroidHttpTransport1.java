/**
 * This class is modified from original ksoap2 - HttTransportXXXXX so that can run 
 * on android version
 * Modified by Chaiyasit T. 
 */
package com.mapview;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ksoap2.SoapEnvelope;
import org.kxml2.io.KXmlParser;
import org.kxml2.io.KXmlSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class AndroidHttpTransport1 {
	
	public String BusinessName;
	public String RegistrationNumber;
	public String CabNumber;
	public String VehicleExpires;
	public String ContactNumber;
	public String Photo;
	public String Rating;
	public String VehicleType;
	public String TimeStart;
	private String TimeEnd;
	public String BusinessName1;
	public String RegisteredAddress;
	public String ContactNumber1;
	public String Rating1;
	public String RegistrationType;
	public String TimeStart1;
	public String TimeEnd1;
	
	int count=0;
    int ArraySize = 0;
    int cnt =0;
    String id;
	String url;
	  HttpURLConnection connection;
	  OutputStream os;
	  InputStream is;
	  /** Set to true if debugging */
	  public boolean debug;
	  /** String dump of request for debugging. */
	  public String requestDump;
	  /** String dump of response for debugging */
	  public String responseDump;
	  private boolean connected;

	  public boolean isConnected() {
	    return connected;
	  }

	  public void setConnected(boolean connected) {
	    this.connected = connected;
	  }

	  /**
	   * Creates instance of HttpTransport with set url and SoapAction
	   * 
	   * @param url
	   *          the destination to POST SOAP data
	   * @param soapAction
	   *          the desired SOAP action (for HTTP headers)
	   */
//====================================================================================================
	  public AndroidHttpTransport1(String url,String id) {
	    this.url = url;
	    this.id=id;
	  }

	  /**
	   * Set the target url.
	   * 
	   * @param url
	   *          the target url.
	   */

	  public void setUrl(String url) {
	    this.url = url;
	  }

	public void setid(String id) {
		    this.id = id;
		  }
	  /**
	   * set the desired soapAction header field
	   * 
	   * @param soapAction
	   *          the desired soapAction
	 * @throws Exception 
	   */

	  public void call(String soapAction, SoapEnvelope envelope) throws IOException, XmlPullParserException, Exception {
	    if (soapAction == null)
	      soapAction = "\"\"";
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    XmlSerializer xw = new KXmlSerializer();
	    xw.setOutput(bos, null);
	    envelope.write(xw);
	    xw.flush();  	    
	    bos.write('\r');
	    bos.write('\n');
	    bos.close();
	    byte[] requestData = bos.toByteArray();
	    bos = null;
	    xw = null;
	    
	    requestDump = debug ? new String(requestData) : null;
	    responseDump = null;
	    connected = true;
	    HttpURLConnection connection = (HttpURLConnection) (new URL(url).openConnection());
	    connection.setUseCaches(false);
	    connection.setDoOutput(true);
	    connection.setDoInput(true);
	    connection.setRequestProperty("User-Agent", "kSOAP/2.0");
	    connection.setRequestProperty("SOAPAction", soapAction);
	    connection.setRequestProperty("Content-Type", "text/xml");
	    connection.setRequestProperty("Connection", "close");
	    connection.setRequestProperty("Content-Length", "" + requestData.length);
	    connection.setRequestMethod("POST");    
	    OutputStream os = connection.getOutputStream();
	    os.write(requestData, 0, requestData.length);
	    os.close();
	    requestData = null;
	    InputStream is = null;
	    try {
	      connection.connect();
	      is = connection.getInputStream();	
	      if(id.equals("Company"))
	      {
	    	  GetCompanyDetail(is);
	      }
	      else
	     {
	    	  GetCabDetail(is);
	      }
	      
	      //ParsedGetCabsNearMe(is);
	      is.close();
	    
	    }
	    catch (Exception e) {
	      Log.i("MINE", e.getMessage());
	    }
	  
	  finally
    {
		  connection.disconnect();
		  
    }
	}
	  
	  public void GetCabDetail(InputStream is)
		{
			Document doc = null;
	        DocumentBuilderFactory dbf =
	            DocumentBuilderFactory.newInstance();
	        DocumentBuilder db;
	        try {
	            db = dbf.newDocumentBuilder();
	            try {
					doc = db.parse(is);
				    
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } catch (ParserConfigurationException e) {
	            e.printStackTrace();
	        } catch (SAXException e) {
	            e.printStackTrace();
	        }
	        
	        
	        doc.getDocumentElement().normalize();
	        NodeList list=doc.getElementsByTagName("GetCabDetailResult");          
	        /*ArraySize = list.getLength();
	        BusinessName = new String[ArraySize];
		    RegistrationNumber = new String[ArraySize];
		    CabNumber = new String[ArraySize];
		    VehicleExpires = new String[ArraySize];
		    VehicleType = new String[ArraySize];
		    TimeStart = new String[ArraySize];
		    TimeEnd = new String[ArraySize];
		    VehicleExpires = new String[ArraySize];
		    ContactNumber = new double[ArraySize];*/	          
			    for(int i=0;i<list.getLength();i++)
			    {		    	 
			     Node item = list.item(i);
			     if(item.getNodeType() != Node.TEXT_NODE)
			     {
			      NodeList itemChilds = item.getChildNodes();
			      	 
		          
			         for(int j=0;j<itemChilds.getLength();j++)
			         {		          
			          Node detailNode = itemChilds.item(j);
			          if(detailNode.getNodeType() != Node.TEXT_NODE)
			          {
			        	 
			        	  if(detailNode.getNodeName().equalsIgnoreCase("BusinessName"))
			        	  {
			        		  BusinessName=getNodeValue(detailNode);		        		 
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("RegistrationNumber"))
			        	  {
			        		  RegistrationNumber=getNodeValue(detailNode);       		
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("CabNumber"))
			        	  {
			        		  CabNumber=getNodeValue(detailNode);
			        		 //longitude1[cnt]= Double.parseDouble(longitude[cnt]);
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("VehicleExpires"))
			        	  {
			        		  VehicleExpires=getNodeValue(detailNode);	        
			        		  //latitude1[cnt]= Double.parseDouble(latitude[cnt]);
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("ContactNumber"))
			        	  {
			        		  ContactNumber=getNodeValue(detailNode);		        		
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("Rating"))
			        	  {
			        		  Rating=getNodeValue(detailNode);       		
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("VehicleType"))
			        	  {
			        		  VehicleType=getNodeValue(detailNode);     		
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("TimeStart"))
			        	  {
			        		  TimeStart=getNodeValue(detailNode);
			        		  
			        	  }	
			        	  if(detailNode.getNodeName().equalsIgnoreCase("TimeEnd"))
			        	  {
			        		  TimeEnd=getNodeValue(detailNode);
			        		  cnt++;
			        	  }	
			        	  }
			         }		         
			     }
			    }
			    
			   
		}

		
		public String getNodeValue(Node node)
		{
		 NodeList nodeList = node.getChildNodes();
		 Node childNode = nodeList.item(0) ;
		 return childNode.getNodeValue();
		}
		
		
		public void GetCompanyDetail(InputStream is)
		{
			Document doc = null;
	        DocumentBuilderFactory dbf =
	            DocumentBuilderFactory.newInstance();
	        DocumentBuilder db;
	        try {
	            db = dbf.newDocumentBuilder();
	            try {
					doc = db.parse(is);
				    
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } catch (ParserConfigurationException e) {
	            e.printStackTrace();
	        } catch (SAXException e) {
	            e.printStackTrace();
	        }
	        
	        
	        doc.getDocumentElement().normalize();
	        NodeList list=doc.getElementsByTagName("GetCabCompanyDetailResult");          
	        /*ArraySize = list.getLength();
	        BusinessName = new String[ArraySize];
		    RegistrationNumber = new String[ArraySize];
		    CabNumber = new String[ArraySize];
		    VehicleExpires = new String[ArraySize];
		    VehicleType = new String[ArraySize];
		    TimeStart = new String[ArraySize];
		    TimeEnd = new String[ArraySize];
		    VehicleExpires = new String[ArraySize];
		    ContactNumber = new double[ArraySize];*/	          
			    for(int i=0;i<list.getLength();i++)
			    {		    	 
			     Node item = list.item(i);
			     if(item.getNodeType() != Node.TEXT_NODE)
			     {
			      NodeList itemChilds = item.getChildNodes();
			      	 
		          
			         for(int j=0;j<itemChilds.getLength();j++)
			         {		          
			          Node detailNode = itemChilds.item(j);
			          if(detailNode.getNodeType() != Node.TEXT_NODE)
			          {
			        	 
			        	  if(detailNode.getNodeName().equalsIgnoreCase("BusinessName"))
			        	  {
			        		  BusinessName1=getNodeValue(detailNode);		        		 
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("RegisteredAddress"))
			        	  {
			        		  RegisteredAddress=getNodeValue(detailNode);       		
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("ContactNumber"))
			        	  {
			        		  ContactNumber1=getNodeValue(detailNode);
			        		 //longitude1[cnt]= Double.parseDouble(longitude[cnt]);
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("Rating"))
			        	  {
			        		  Rating1=getNodeValue(detailNode);	        
			        		  //latitude1[cnt]= Double.parseDouble(latitude[cnt]);
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("RegistrationType"))
			        	  {
			        		  RegistrationType=getNodeValue(detailNode);		        		
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("TimeStart"))
			        	  {
			        		  TimeStart1=getNodeValue(detailNode);       		
			        	  }
			        	  if(detailNode.getNodeName().equalsIgnoreCase("TimeEnd"))
			        	  {
			        		  TimeEnd1=getNodeValue(detailNode);     		
			        	  }
			        	  }
			         }		         
			     }
			    }   
		}
}