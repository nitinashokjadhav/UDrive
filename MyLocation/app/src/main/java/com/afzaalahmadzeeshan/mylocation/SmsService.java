package com.afzaalahmadzeeshan.mylocation;

import android.content.Context;
import android.telephony.SmsManager;

import java.util.ArrayList;

public class SmsService {
    public static boolean sendMessage(Context context, String location) {
        boolean result = false;
        try {
            SmsManager manager = SmsManager.getDefault();
            String message;

            // Get the string
            message = "[AUTOMATIC MESSAGE]\n" + "I am currently at " + location + " (approximately; accuracy within 100 meters).";

            ArrayList<Number> numbers = new ContentManager(context).getNumbers();
            if(numbers != null && numbers.size() > 0) {
                for (Number number : numbers) {
                    String telNumber = number.getNumber();
                    manager.sendTextMessage(telNumber, null, message, null, null);
                }
                result = true;
            }
        } catch (Exception ignored) {

        }
        return result;
    }
}
