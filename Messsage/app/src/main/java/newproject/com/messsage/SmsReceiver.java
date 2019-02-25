package newproject.com.messsage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {
private static SmsListener mListener;


@Override
public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();
        String format = data.getString("format");
        Object[] pdus = (Object[]) data.get("pdus");

        for(int i=0;i<pdus.length;i++){
        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i],format);

        String sender = smsMessage.getDisplayOriginatingAddress();
        //Check the sender to filter messages which we require to read

        //if (sender.equals("GADGETSAINT"))
        //{

        String messageBody = smsMessage.getMessageBody();

        //Pass the message text to interface
        mListener.messageReceived(messageBody);


        }

        }

public static void bindListener(SmsListener listener) {
        mListener = listener;
        }
        }
