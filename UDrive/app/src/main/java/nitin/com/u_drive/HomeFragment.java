package nitin.com.u_drive;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.Context.LOCATION_SERVICE;


public class HomeFragment extends Fragment implements View.OnClickListener, LocationListener {
    private static final int PERMISSION_REQUEST_CODE = 200;
    EditText locat;
    TextView user;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8, locate;
    private TextView cost_finder;
    View view1;
    double latitude, longitude;
    Location location;
    LocationManager locationManager, mLocationManager;
    public Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        FirebaseUser firebase_user = FirebaseAuth.getInstance().getCurrentUser();
        locate = (Button) view.findViewById(R.id.location);
        user = (TextView) view.findViewById(R.id.user);
        cost_finder = view.findViewById(R.id.cost_find);
        if (firebase_user == null) {
            user.setText("Guest");
        } else {
            user.setText(firebase_user.getEmail());

        }

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        Location location;


        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new MyLocationListener());

        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCurrentLocation();
                Log.e("on","c;ocvjugvibuf");
            }
        });

        cost_finder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),MapDirection.class));
            }
        });
        //you can hard-code the lat & long if you have issues with getting it
        //remove the below if-condition and use the following couple of lines
        //double latitude = 37.422005;
        //double longitude = -122.084095;

        locat = (EditText) view.findViewById(R.id.l);
        button1 = (Button) view.findViewById(R.id.btn1);
        button2 = (Button) view.findViewById(R.id.btn2);
        button3 = (Button) view.findViewById(R.id.btn3);
        button4 = (Button) view.findViewById(R.id.btn4);
        button5 = (Button) view.findViewById(R.id.btn5);
        button6 = (Button) view.findViewById(R.id.btn6);
        button7 = (Button) view.findViewById(R.id.btn7);
        button8 = (Button) view.findViewById(R.id.btn8);
        intent = new Intent(getContext(), Places.class);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);

//
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                intent.putExtra("id", "1");
                startActivity(intent);
                break;
            case R.id.btn2:
                intent.putExtra("id", "2");
                startActivity(intent);
                break;
            case R.id.btn3:
                intent.putExtra("id", "3");
                startActivity(intent);
                break;
            case R.id.btn4:
                intent.putExtra("id", "4");
                startActivity(intent);
                break;
            case R.id.btn5:
                intent.putExtra("id", "5");
                startActivity(intent);
                break;
            case R.id.btn6:
                intent.putExtra("id", "6");
                startActivity(intent);
                break;
            case R.id.btn7:
                intent.putExtra("id", "7");
                startActivity(intent);
                break;
            case R.id.btn8:
                intent.putExtra("id", "8");
                startActivity(intent);
                break;
            default:

        }

    }


    @SuppressLint("SetTextI18n")
    protected String showCurrentLocation() {

        Toast.makeText(getActivity(), "Alert sent. Location: " + latitude + " " + longitude, Toast.LENGTH_LONG).show();
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(getContext(), Locale.getDefault());
        String city = null;
        try {

            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            if (!addresses.isEmpty()) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                city = " " + addresses.get(0).getLocality() + " ";
                city += addresses.get(0).getAdminArea() + " ";
                city += addresses.get(0).getCountryName() + " ";
                city += addresses.get(0).getPostalCode() + " ";
                city += addresses.get(0).getFeatureName();
                locat.setText("city is" + city);
                Log.e("TAG", city);
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        locationManager.removeUpdates(new MyLocationListener());
        return city;
        //}
    }

    @Override
    public void onLocationChanged(Location location) {

        Log.i("Long", " " + location.getLongitude());

    }



   private class MyLocationListener implements android.location.LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Log.e("locat",""+latitude);
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
