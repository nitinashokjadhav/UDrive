package com.mapview;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

public class MainActivity extends MapActivity 
{
	MapView myMapView= null;	
	MapController myMC = null;	
	GeoPoint geoPoint = null;
	double	latitude1=30.682433; 
	double longitude1=76.74654;
	  @Override
   public void onCreate(Bundle savedInstanceState) {		  
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mapactivity);
	        myMapView = (MapView) findViewById(R.id.mapview);
	    	geoPoint = new GeoPoint((int) (latitude1 * 1000000), (int) (longitude1 * 1000000));
	    	myMapView.setStreetView(true);
	    	myMC = myMapView.getController();
	    	myMC.setCenter(geoPoint);
	    	myMC.setZoom(15);
	    	
	    	
	    	List<Overlay> overlays = myMapView.getOverlays();        
    		GeoPoint geoPoint1 = new GeoPoint((int) (latitude1 * 1000000), (int) (longitude1 * 1000000));
    		overlays.add(new ApplyMarkers(this, geoPoint1, R.drawable.bronzeimg));        		
    		myMapView.invalidate(); 
	  }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public class ApplyMarkers extends Overlay {
		 
		private final GeoPoint geoPoint;   
		  private final Context context;   
		  private final int drawable; 
		  
		public ApplyMarkers(Context context, GeoPoint geoPoint, int drawable) {   
		    this.context = context;   
		    this.geoPoint = geoPoint;   
		    this.drawable = drawable;   
		  }   
		@Override   
		  public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {   
		    super.draw(canvas, mapView, shadow);   
		  
		    // Convert geo coordinates to screen pixels   
		    Point screenPoint = new Point();   
		    mapView.getProjection().toPixels(geoPoint, screenPoint);   
		  
		    // Read the image   
		    Bitmap markerImage = BitmapFactory.decodeResource(context.getResources(), drawable);   
		  
		    // Draw it, centered around the given coordinates   
		    canvas.drawBitmap(markerImage,   
		        screenPoint.x - markerImage.getWidth() / 2,   
		        screenPoint.y - markerImage.getHeight() / 2, null);   
		    return true;   
		  }  
		  @Override   
		  public boolean onTap(GeoPoint p, MapView mapView) {   
		    // Handle tapping on the overlay here   
			  //showpopup();
			  return true;   
		  }
		  @Override
		  public boolean onTouchEvent(MotionEvent event, MapView mapView) 
	      {   
			  //---when user lifts his finger--- 
			  GeoPoint p = mapView.getProjection().fromPixels(
	                  (int) event.getX(),
	                  (int) event.getY());
	              Geocoder geoCoder = new Geocoder(this.context, Locale.getDefault());
	              try {
	                  List<Address> addresses = geoCoder.getFromLocation(
	                      p.getLatitudeE6()  / 1E6, 
	                      p.getLongitudeE6() / 1E6, 1);
	                  String add = "";
	                  if (addresses.size() > 0) 
	                  {
	                      for (int i=0; i<addresses.get(0).getMaxAddressLineIndex();i++)
	                         add += addresses.get(0).getAddressLine(i) + "\n";
	                  }
	                  showpopup();	                  
	                  Toast.makeText(this.context, add, Toast.LENGTH_SHORT).show();
	              }
	              catch (IOException e) {                
	                  e.printStackTrace();
	              }   
	              return true;         
	      }  
		  
	}
	private void showpopup() 
    { 
       LayoutInflater inflater = this.getLayoutInflater(); 
       View mView= inflater.inflate(R.layout.cabdetailactivity,(ViewGroup)findViewById(R.id.RelativeLayout01)); 
       PopupWindow mPopupWindow = new PopupWindow(mView,LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT, false); 
       mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog); 
       //Button btn1 = (Button)this.findViewById(R.id.Button02);
       MapView TV=(MapView)this.findViewById(R.id.mapview);       
       mPopupWindow.update(0, 0,300, 340);
       mPopupWindow.showAtLocation(TV, Gravity.BOTTOM, 0, 50);
     
     } 
	
	
}
