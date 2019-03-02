package nitin.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                //Do any action here. Now we are moving to next page
//                Fragment fragment = new HomeFragment();
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
//                /* This 'finish()' is for exiting the app when back button pressed
//                 *  from Home page which is ActivityHome
//                 */
//                finish();
//            }
//        }, SPLASH_TIME);
    }
}
