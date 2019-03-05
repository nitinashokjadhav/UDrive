package nitin.com.u_drive;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;


public class MapDirection extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {
    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    Button getDirection;
    LocationManager locationManager;
    private Polyline currentPolyline;
    double latitude,longitude,dlatitude,dlongitude;
    private Location mLastLocation,destLocation;
    private EditText place;
    private Button search;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_direction);
        place=findViewById(R.id.place) ;
        search=findViewById(R.id.sh);
        getDirection = findViewById(R.id.btnGetDirection);
        getDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchURL(MapDirection.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
                float distance = calculateDistance(mLastLocation.getLatitude(),mLastLocation.getLongitude(),dlatitude,dlongitude);
                TextView textView = (TextView) findViewById(R.id.textView);
                String s1 = String.valueOf(distance);
                textView.setText(s1);
                }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pl = place.getText().toString();
                getLatitudeAndLongitude(pl);
            }
        });

        //27.658143,85.3199503
        //27.667491,85.3208583
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        Location location;


        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new MyLocationListener());


    //    place1 = new MarkerOptions().position(new LatLng(latitude,longitude)).title("Location 1");

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapFrag);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("mylog", "Added Markers");
        //mMap.addMarker(place1);



    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key= AIzaSyDrxixpsxXHZYe9tg5Im6FJaBjDSUVQ8Wo";
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }


    public final static double AVERAGE_RADIUS_OF_EARTH = 6371;

//Getting latitude and longitude according to place name
    public boolean getLatitudeAndLongitude(String searchedAddress){
        String TAG = "Get Coordinates";
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        try {

            address = coder.getFromLocationName(searchedAddress,5);
            if (address == null) {
                Log.d(TAG, "############Address not correct #########");
            }
            Address location = address.get(0);
            place2 = new MarkerOptions().position(new LatLng(dlatitude,dlongitude)).title("Location 2");
            mMap.addMarker(place2);
            Log.d(TAG, "Address Latitude : "+ location.getLatitude()+ "Address Longitude : "+ location.getLongitude());
            dlatitude = location.getLatitude();
            dlongitude=location.getLongitude();
            Log.e(TAG,""+dlongitude);
            return true;

        }catch(Exception e){
            Log.d(TAG, "MY_ERROR : ############Address Not Found");
            return false;
        }
    }
    public float calculateDistance(double userLat, double userLng, double venueLat, double venueLng) {

        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);

        double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                (Math.cos(Math.toRadians(userLat))) *
                        (Math.cos(Math.toRadians(venueLat))) *
                        (Math.sin(lngDistance / 2)) *
                        (Math.sin(lngDistance / 2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));

    }

    private class MyLocationListener implements android.location.LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            mLastLocation = new Location(location);
            LatLng latLng = new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
            place1 = new MarkerOptions().position(new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude())).title("Location 1");
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(8));
            mMap.addMarker(new MarkerOptions().position(latLng).title("You are here"));
            Log.e("Map", "" + latitude+""+longitude);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}


