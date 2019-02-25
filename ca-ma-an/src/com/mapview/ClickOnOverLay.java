package com.mapview;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.kxml2.io.KXmlSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.ZoomControls;

public class ClickOnOverLay extends MapActivity {
	private static final String SOAP_ACTION = "http://www.safecab.com/GetCabsNearMe";
    private static final String METHOD_NAME = "GetCabsNearMe";
    private static final String NAMESPACE = "http://www.safecab.com/";
    private static final String URL = "http://www.safecab.com/ClientInterface.asmx"; 
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
	public int str1;
	String businessid[];
    String driverid[];
    String longitude[];
    String latitude[];
    String businessname[];
    String vehicletype[];
    String registrationtype[];
    String state[];
    int count=0;
    int ArraySize = 0;
    int cnt =0;
    private static final String TAG = null;
	private int UserID;
	private static final String MY_DEBUG_TAG = null;
	//MapView myMapView= null;	
	MapController myMC = null;	
	GeoPoint geoPoint = null;
	double latitude1[];
	double longitude1[];
	public int latitude2; 
	public int longitude2;
	MapView myMapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	Drawable drawable2;
	Drawable drawable3;
	Drawable drawable4;
	Drawable drawable5;
	MyItemizedOverlay itemizedOverlay;
	MyItemizedOverlay itemizedOverlay2;
	MyItemizedOverlay itemizedOverlay3;
	MyItemizedOverlay itemizedOverlay4;
	MyItemizedOverlay itemizedOverlay5;
	//Double lat;
	//Double lng;
	

    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapactivity);
        Button btngo=(Button)findViewById(R.id.Btngo);
        btngo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(ClickOnOverLay.this,ClickOnOverLay.class);
				startActivity(i);
				finish();
			}
		});
        
        Button btnmap=(Button)findViewById(R.id.Button02);
        btnmap.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(ClickOnOverLay.this,ClickOnOverLay.class);
				startActivity(i);
				finish();
			}
		});
        
        
        Button btncab=(Button)findViewById(R.id.Button03);
        btncab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*Intent i=new Intent(ClickOnOverLay.this,IntheCabActivity.class);
				startActivity(i);
				finish();*/
				try 
				{
					
					 EditText edit=(EditText)findViewById(R.id.EditText01);
					 String placename=edit.getText().toString();
					 Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
				     List<Address> addresses=geocoder.getFromLocationName(edit.getEditableText().toString(), 5);
				     if (addresses.size() > 0) { 
				    	 //mapView = (MapView) findViewById(R.id.mapview); 
				    	 myMapView = (MapView) findViewById(R.id.mapview);
				    	 //myMapView.setBuiltInZoomControls(true);
				    	 myMC = myMapView.getController(); 
				    	 double lat= addresses.get(0).getLatitude(); 
				    	 double lng = addresses.get(0).getLongitude(); 
				    	 int latitude2 = (int)(lat * 1E6);
				    	 int longitude2 = (int)(lng * 1E6);
				    	 //geoPoint = new GeoPoint(lat.intValue(), lng.intValue());
					     //myMC.animateTo(geoPoint);
					     //myMC.setZoom(17); 
					    // myMapView.invalidate();
					     //MapOverlay mapOverlay = new MapOverlay();
					     //List<Overlay> listOfOverlays = myMapView.getOverlays();
					     //listOfOverlays.clear();
					     //listOfOverlays.add(mapOverlay);
				    	 int strvalue=1;
				    	 Intent abc =  new Intent(ClickOnOverLay.this,ClickOnOverLay.class);
				    	 abc.putExtra("com.mapview.str1",strvalue);
				    	 abc.putExtra("com.mapview.latitude2",latitude2);
				    	 abc.putExtra("com.mapview.longitude2",longitude2);
				    	 ClickOnOverLay.this.startActivity(abc);
				     }
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        
        
       Button btnhistory =(Button)findViewById(R.id.Button04);
       btnhistory.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		    Intent i=new Intent(ClickOnOverLay.this,HistoryActivity.class);
		    startActivity(i);
		    finish();
		}
	});
        
        Button btnfind=(Button)findViewById(R.id.Button01);
        btnfind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*try 
				{
					
					 EditText edit=(EditText)findViewById(R.id.EditText01);
					 String placename=edit.getText().toString();
					 Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
				     List<Address> addresses=geocoder.getFromLocationName(edit.getEditableText().toString(), 5);
				     if (addresses.size() > 0) { 
				    	 //mapView = (MapView) findViewById(R.id.mapview); 
				    	 myMapView = (MapView) findViewById(R.id.mapview);
				    	 //myMapView.setBuiltInZoomControls(true);
				    	 myMC = myMapView.getController(); 
				    	 Double lat= addresses.get(0).getLatitude()*1E6; 
				    	 Double lng = addresses.get(0).getLongitude()*1E6;    
				    	 //geoPoint = new GeoPoint(lat.intValue(), lng.intValue());
					     //myMC.animateTo(geoPoint);
					     //myMC.setZoom(17); 
					    // myMapView.invalidate();
					     //MapOverlay mapOverlay = new MapOverlay();
					     //List<Overlay> listOfOverlays = myMapView.getOverlays();
					     //listOfOverlays.clear();
					     //listOfOverlays.add(mapOverlay);
				    	 String strvalue="Find";
				    	 Intent abc =  new Intent(ClickOnOverLay.this,ClickOnOverLay.class);
				    	 abc.putExtra("com.mapview.str1",strvalue);
				    	 abc.putExtra("com.mapview.latitude2",lat);
				    	 abc.putExtra("com.mapview.longitude2",lng);
				    	 ClickOnOverLay.this.startActivity(abc);
				     }
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				
				
				
				/*
		     geoPoint = new GeoPoint(lat.intValue(), lng.intValue());
		     myMC.animateTo(geoPoint);
		     myMC.setZoom(17); 
		     myMapView.invalidate();
		     MapOverlay mapOverlay = new MapOverlay();
		     List<Overlay> listOfOverlays = myMapView.getOverlays();
		     listOfOverlays.clear();
		     listOfOverlays.add(mapOverlay);   
		     */
			}
		});
        
        

        try {
        	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        	PropertyInfo pi = new PropertyInfo();
        	PropertyInfo pi1 = new PropertyInfo();
        	PropertyInfo pi2 = new PropertyInfo();
        	pi.type = PropertyInfo.STRING_CLASS;
        	pi.name = "Longitude";
        	pi.namespace = NAMESPACE;
        	
        	pi1.type = PropertyInfo.MULTI_REF;
        	pi1.name = "Latitude";
        	pi1.namespace = NAMESPACE;
        	
        	pi2.type = PropertyInfo.MULTI_REF;
        	pi2.name = "Area";
        	pi2.namespace = NAMESPACE;
        	//request.addProperty(pi, "1");
        	request.addProperty(pi, "-0.212341000000000");
        	request.addProperty(pi1, "51.520138000000000");
        	request.addProperty(pi2, "1");
            SoapSerializationEnvelope envelope = 
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;           
            envelope.setOutputSoapObject(request);

            AndroidHttpTransport androidHttpTransport = new AndroidHttpTransport(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            }
        catch(Exception e){
        	@SuppressWarnings("unused")
        	String asd = e.toString();
        	}
        
        myMapView = (MapView) findViewById(R.id.mapview);
		mapOverlays = myMapView.getOverlays();	
		drawable = getResources().getDrawable(R.drawable.bronzeimg);
		drawable2 = getResources().getDrawable(R.drawable.goldimg);
		drawable3 = getResources().getDrawable(R.drawable.silverimg);
		drawable4 = getResources().getDrawable(R.drawable.yellowcabimg);
		drawable5 = getResources().getDrawable(R.drawable.mappin_red);
		itemizedOverlay5 = new MyItemizedOverlay(drawable5, myMapView);
		itemizedOverlay = new MyItemizedOverlay(drawable, myMapView);
		itemizedOverlay2 = new MyItemizedOverlay(drawable2, myMapView);
		itemizedOverlay3 = new MyItemizedOverlay(drawable3, myMapView);
		itemizedOverlay4 = new MyItemizedOverlay(drawable4, myMapView);
    	//geoPoint = new GeoPoint((int) (latitude1[0] * 1000000), (int) (longitude1[0] * 1000000));
    	//myMapView.setStreetView(true);
    	//myMC = myMapView.getController();
    	//myMC.setCenter(geoPoint);
    	//myMC.setZoom(15);
		
		
            for(int l=0;l<1;l++){      
        	if((driverid[l].toString().equals("0"))&& registrationtype[l].toString().equals("Gold")) {
        		//List<Overlay> overlays = myMapView.getOverlays();
        		GeoPoint geoPoint1 = new GeoPoint((int) (latitude1[l] * 1000000), (int) (longitude1[l] * 1000000));
        		OverlayItem overlayItem = new OverlayItem(geoPoint1,driverid[l].toString(),businessid[l].toString());        		
        		itemizedOverlay2.addOverlay(overlayItem);
        		mapOverlays.add(itemizedOverlay2);
        	}
        	else
        
        
       
        	if((driverid[l].toString().equals("0"))&& registrationtype[l].toString().equals("Silver"))
        	{
        		//List<Overlay> overlays = myMapView.getOverlays();
        		
        		GeoPoint geoPoint1 = new GeoPoint((int) (latitude1[l] * 1000000), (int) (longitude1[l] * 1000000));
        		OverlayItem overlayItem = new OverlayItem(geoPoint1,driverid[l].toString(),businessid[l].toString());
        		itemizedOverlay3.addOverlay(overlayItem);
        		mapOverlays.add(itemizedOverlay3);
        	}
        
        
        	else
        	if(driverid[l].toString()!="0")
            {
            		//List<Overlay> overlays = myMapView.getOverlays();        
            		GeoPoint geoPoint1 = new GeoPoint((int) (latitude1[l] * 1000000), (int) (longitude1[l] * 1000000));
            		OverlayItem overlayItem = new OverlayItem(geoPoint1, driverid[l].toString() ,businessid[l].toString());
            		itemizedOverlay4.addOverlay(overlayItem);
            		mapOverlays.add(itemizedOverlay4);
            }
        
        
        	else
        	if((driverid[l].toString().equals("0"))&& registrationtype[l].toString().equals("Bronze"))
        	{
        		//List<Overlay> overlays = myMapView.getOverlays();  
        		//drawable2 = getResources().getDrawable(R.drawable.goldimg);
        		GeoPoint geoPoint1 = new GeoPoint((int) (latitude1[l] * 1000000), (int) (longitude1[l] * 1000000));
        		OverlayItem overlayItem = new OverlayItem(geoPoint1,driverid[l].toString() ,businessid[l].toString());
        		itemizedOverlay.addOverlay(overlayItem);
        		mapOverlays.add(itemizedOverlay);
        	}
        	
            }  	  
        
           
        	//myMC = myMapView.getController();
        	//myMC.setCenter(geoPoint);
        	//myMC.setZoom(15);
        final MapController mc = myMapView.getController();
        
        Bundle extras = getIntent().getExtras();
        str1= extras.getInt("com.mapview.str1", 0);        
      	 if(str1==1){
      		Bundle extras1 = getIntent().getExtras();
        	latitude2= extras1.getInt("com.mapview.latitude2", 0);
        	longitude2= extras1.getInt("com.mapview.longitude2", 0);
        GeoPoint geoPoint = new GeoPoint(latitude2, longitude2);
		OverlayItem overlayItem1 = new OverlayItem(geoPoint,"","");        		
		itemizedOverlay5.addOverlay(overlayItem1);
		mapOverlays.add(itemizedOverlay5);
		mc.setCenter(geoPoint);
    	mc.setZoom(15);
        }
        else {
        	latitude2 = (int) 51.754771;
        	longitude2 = (int)-1.256046;
                GeoPoint geoPoint = new GeoPoint((int) (latitude2 * 1000000), (int) (longitude2 * 1000000));
        		OverlayItem overlayItem1 = new OverlayItem(geoPoint,"","");        		
        		itemizedOverlay5.addOverlay(overlayItem1);
        		mapOverlays.add(itemizedOverlay5);
        		mc.setCenter(geoPoint);
            	mc.setZoom(15);
        }
        
        ZoomControls controls=(ZoomControls)findViewById(R.id.ZoomControls01);
        controls.setOnZoomInClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View v) { 
            	mc.zoomIn(); 
            } 
         }); 
        controls.setOnZoomOutClickListener(new View.OnClickListener() { 
            @Override 
            public void onClick(View v) { 
            	mc.zoomOut(); 
            } 
    }); 
		//mc.animateTo(point2);
		mc.setZoom(10);
        
    }
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}	
	public String getNodeValue(Node node)
	{
	 NodeList nodeList = node.getChildNodes();
	 Node childNode = nodeList.item(0) ;
	 return childNode.getNodeValue();
	}
	public class AndroidHttpTransport {
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
  	  public AndroidHttpTransport(String url) {
  	    this.url = url;
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
  	      ParsedGetCabsNearMe(is);
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
  	public void ParsedGetCabsNearMe(InputStream is)
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
        NodeList list=doc.getElementsByTagName("Cabs");          
        ArraySize = list.getLength();
        businessid = new String[ArraySize];
	          driverid = new String[ArraySize];
	          longitude = new String[ArraySize];
	          latitude = new String[ArraySize];
	          businessname = new String[ArraySize];
	          vehicletype = new String[ArraySize];
	          registrationtype = new String[ArraySize];
	          state = new String[ArraySize];
	          longitude1 = new double[ArraySize];
	          latitude1 = new double[ArraySize];
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
		        	 
		        	  if(detailNode.getNodeName().equalsIgnoreCase("BusinessId"))
		        	  {
		        		 businessid[cnt]=getNodeValue(detailNode);		        		 
		        	  }
		        	  if(detailNode.getNodeName().equalsIgnoreCase("DriverId"))
		        	  {
		        		 driverid[cnt]=getNodeValue(detailNode);       		
		        	  }
		        	  if(detailNode.getNodeName().equalsIgnoreCase("Longitude"))
		        	  {
		        		 longitude[cnt]=getNodeValue(detailNode);
		        		 longitude1[cnt]= Double.parseDouble(longitude[cnt]);
		        	  }
		        	  if(detailNode.getNodeName().equalsIgnoreCase("Latitude"))
		        	  {
		        		  latitude[cnt]=getNodeValue(detailNode);	        
		        		  latitude1[cnt]= Double.parseDouble(latitude[cnt]);
		        	  }
		        	  if(detailNode.getNodeName().equalsIgnoreCase("BusinessName"))
		        	  {
		        		  businessname[cnt]=getNodeValue(detailNode);		        		
		        	  }
		        	  if(detailNode.getNodeName().equalsIgnoreCase("VehicleType"))
		        	  {
		        		  vehicletype[cnt]=getNodeValue(detailNode);       		
		        	  }
		        	  if(detailNode.getNodeName().equalsIgnoreCase("RegistrationType"))
		        	  {
		        		  registrationtype[cnt]=getNodeValue(detailNode);     		
		        	  }
		        	  if(detailNode.getNodeName().equalsIgnoreCase("State"))
		        	  {
		        		  state[cnt]=getNodeValue(detailNode);
		        		  cnt++;
		        	  }		        	  
		        	  }
		         }		         
		     }
		    }
		    
		    @SuppressWarnings("unused")
			String str13 = businessid[59].toString();
		  	String str23 = driverid[59].toString();
		  	@SuppressWarnings("unused")
			String aaa = str23;
	}
  	
  	
 	}
	
	class MapOverlay extends com.google.android.maps.Overlay
    {
	  @Override
        public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) 
        {
            super.draw(canvas, mapView, shadow);                   
 
            //---translate the GeoPoint to screen pixels---
            Point screenPts = new Point();
            mapView.getProjection().toPixels(geoPoint, screenPts);
            if(shadow)
            {
            	Bitmap bmp=BitmapFactory.decodeResource(getResources(),R.drawable.shadow);
            	canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null); 
            }
            else
            {
            //---add the marker---
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.mappin_red);
            canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null); 
            }         
            return true;
        }
	  
	  @Override
		public boolean onTap(GeoPoint p,MapView mapView)
	    {
              return true; 
	    }
    } 

	
	 /*public void showpopup() 
     { 
       LayoutInflater inflater = this.getLayoutInflater(); 
        View mView= inflater.inflate(R.layout.cabdetailactivity,(ViewGroup)findViewById(R.id.RelativeLayout01)); 
        PopupWindow mPopupWindow = new PopupWindow(mView,LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT, false); 
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog); 
        //Button btn1 = (Button)this.findViewById(R.id.Button02);
        MapView TV=(MapView)this.findViewById(R.id.mapview);           
        //TableLayout L1 = (TableLayout)findViewById(R.id.tblntarialview); 
        
        //mPopupWindow.showAtLocation(TV, Gravity.CENTER, 0, 0); 
        //mPopupWindow.showAsDropDown(TV, 12, 150);
        mPopupWindow.update(0, 0,300, 340);
        mPopupWindow.showAtLocation(TV, Gravity.BOTTOM, 0, 50);
      
      } */
	}