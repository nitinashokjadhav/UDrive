package nitin.com.u_drive;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Places extends AppCompatActivity {
DbHelper dbHelper;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        dbHelper = new DbHelper(getApplicationContext());
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(dbHelper.getImage(1));
    }
}
