package newproject.com.mymessages;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import newproject.com.mymessages.SmsHelper;

/**
 * A broadcast receiver who listens for incoming SMS
 */
public class SmsBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "SmsBroadcastReceiver";
    public String smsSender = "";
    public String smsBody = "";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {

            for (SmsMessage smsMessage : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                smsBody += smsMessage.getMessageBody();
                smsSender =smsMessage.getOriginatingAddress();
            }

//            if (smsBody.startsWith(SmsHelper.SMS_CONDITION)) {
//                Log.d(TAG, "Sms with condition detected");
//                Toast.makeText(context, "BroadcastReceiver caught conditional SMS: " + smsBody, Toast.LENGTH_LONG).show();
//            }
            Toast.makeText(context,"SMS detected: From " + smsSender + " With text " + smsBody,Toast.LENGTH_SHORT).show();
            Log.d(TAG, "SMS detected: From " + smsSender + " With text " + smsBody);

        }
        Intent smsIntent=new Intent(context,MainActivity.class);
      smsIntent.putExtra("Body",smsBody);
      smsIntent.putExtra("Address",smsSender);
      context.startActivity(smsIntent);
    }
}
