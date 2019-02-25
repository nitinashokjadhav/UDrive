package newproject.com.msg;
import android.content.*;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.*;
import android.util.Log;
import android.widget.Toast;

// Todo : SMS Reciever Class

public class SimpleSmsReciever extends BroadcastReceiver {

    private static final String TAG = "Message recieved";
    public String address,body;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle pudsBundle = intent.getExtras();
        Object[] pdus = (Object[]) pudsBundle.get("pdus");
        SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);
        Log.i(TAG,  messages.getMessageBody());

        // Todo : Start Application's  MainActivty activity

        // Todo : Send Message And Number In Activity

        Intent smsIntent=new Intent(context,SMS_Receive.class);
        smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        smsIntent.putExtra("MessageNumber", messages.getOriginatingAddress());
        smsIntent.putExtra("Message", messages.getMessageBody());
        context.startActivity(smsIntent);

        // Todo : Show Message In Toast

        Toast.makeText(context, "SMS Received From :"+messages.getOriginatingAddress()+"\n"+ messages.getMessageBody(), Toast.LENGTH_LONG).show();
    }
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
//            SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
//            for (SmsMessage message : smsMessages) {
//                // Do whatever you want to do with SMS.
//                body=message.getMessageBody();
//                address=message.getOriginatingAddress();
//                Intent smsIntent=new Intent(context,SMS_Receive.class);
//                smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                smsIntent.putExtra("MessageNumber", address);
//                smsIntent.putExtra("Message",body);
//                context.startActivity(smsIntent);
//                Toast.makeText(context, "SMS Received From :"+address+"\n"+ body, Toast.LENGTH_LONG).show();
//            }
//        }
//    }
}
    //
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        Bundle pudsBundle = intent.getExtras();
//        Object[] pdus = (Object[]) pudsBundle.get("pdus");
//        SmsMessage messages =SmsMessage.createFromPdu((byte[]) pdus[0]);
//        // Log.i(TAG,  messages.getMessageBody());
//
//        // Todo : Start Application's  MainActivty activity
//
//        // Todo : Send Message And Number In Activity
//
//        Intent smsIntent=new Intent(context,SMS_Receive.class);
//        smsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        smsIntent.putExtra("MessageNumber", messages.getOriginatingAddress());
//        smsIntent.putExtra("Message", messages.getMessageBody());
//        context.startActivity(smsIntent);
//
//        // Todo : Show Message In Toast
//
//        Toast.makeText(context, "SMS Received From :"+messages.getOriginatingAddress()+"\n"+ messages.getMessageBody(), Toast.LENGTH_LONG).show();
//    }


