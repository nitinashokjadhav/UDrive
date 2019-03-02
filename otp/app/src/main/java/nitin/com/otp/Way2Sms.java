package nitin.com.otp;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
public class Way2Sms {
    // required variables
    static String url = "http://www.way2sms.com";

    /**
     * @paramtoken
     * @param phone    10 digit mobile number
     * @param message
     * @param senderId
     */
    public Way2Sms(String apiKey, String secretKey, String useType, String phone, String message, String senderId) {
        StringBuilder content = null;
        try {
            // construct data
            JSONObject urlParameters = new JSONObject();
            urlParameters.put("apikey", apiKey);//NNFA5DA8E8M9TM102AE2JQV6LASX32BJ
            urlParameters.put("secret", secretKey);//T0E2VO2L9LNT16FY
            urlParameters.put("usetype", useType);
            urlParameters.put("phone", phone);
            urlParameters.put("message", URLEncoder.encode(message, "UTF-8"));
            urlParameters.put("senderid", senderId);
            URL obj = new URL(url + "/api/v1/sendCampaign");
            // send data
            HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.write(urlParameters.toString().getBytes());
            // get the response
            Log.i("SMS","Inside constructor");
            BufferedReader bufferedReader = null;
            if (httpConnection.getResponseCode() == 200) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }
            content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception ex) {
            System.out.println("Exception at:" + ex);
        }

    }
}