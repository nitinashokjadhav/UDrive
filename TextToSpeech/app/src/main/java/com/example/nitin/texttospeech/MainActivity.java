package com.example.nitin.texttospeech;


import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    TextToSpeech toSpeech;
    int result;
    String str="";
    private int simp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView lv = (ListView) findViewById(R.id.lv);
        String[] items = new String[] {
                "Chocolate cake                             ",
                "Black forest cake"
        };

        //Creating list that is to be displayed in listView
        //convert array in linked list
        final List<String> item_list = new ArrayList<String>(Arrays.asList(items));
        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,item_list);

        // DataBind ListView with items from ArrayAdapter
        lv.setAdapter(arrayAdapter);
        //adding elements to linked list directly
                /*
                    notifyDataSetChanged ()
                        Notifies the attached observers that the underlying
                        data has been changed and any View reflecting th
                        data set should refresh itself.
                 */
        arrayAdapter.notifyDataSetChanged();

        //Allowing the element in list so that they can be clickable
        lv.setClickable(true);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object o = lv.getItemAtPosition(i);
                str=(String)o;
                speak(str);
            }
        });
        toSpeech=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                    result=toSpeech.setLanguage(Locale.ENGLISH);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (toSpeech!=null)
        {
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }

    private void speak(String str)
    {
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                {
                    Toast.makeText(getApplicationContext(),"Feature not supported in your device",Toast.LENGTH_SHORT).show();
                }
            else
                {
                    toSpeech.setSpeechRate(0.75f);//setting the speak rate of android
                    toSpeech.speak(str, TextToSpeech.QUEUE_FLUSH, null, null);

                }

    }


}
