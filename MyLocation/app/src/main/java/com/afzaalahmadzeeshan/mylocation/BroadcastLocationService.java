package com.afzaalahmadzeeshan.mylocation;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class BroadcastLocationService extends IntentService {
    public static boolean enabled = false;

    public BroadcastLocationService() {
        super("BroadcastLocationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendMessage(getApplicationContext(), new LocationService(getApplicationContext()).getLocation());
    }

    private void sendMessage(Context mContext, Location location) {
        String city = "[UNKNOWN]";

        Geocoder coder = new Geocoder(mContext, Locale.getDefault());
        try {
            List<Address> addresses = coder.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1);
            if(addresses.size() > 0) {
                // Has an address
                city = addresses.get(0).getLocality();
            }
        } catch (IOException ignored) {}

        if(enabled) {
            // Send the SMS
            SmsService.sendMessage(mContext, city);
        }
    }
}
