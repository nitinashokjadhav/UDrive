package nitin.com.u_drive;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Places extends AppCompatActivity {
DbHelper dbHelper;
ImageView imageView;
TextView  textView,textView1;
Button  button;
String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        int id;
        String ID;
        Cursor cursor;
        dbHelper    =   new DbHelper(getApplicationContext());
        imageView   =   (ImageView) findViewById(R.id.imageView);
        textView    =   (TextView) findViewById(R.id.pname);
        textView1   =   (TextView)findViewById(R.id.pDesc);
        button      =   (Button)findViewById(R.id.button);
        ID          =   getIntent().getStringExtra("id");
        id          =   Integer.parseInt(ID);
//        Toast.makeText(this, "id is " + id, Toast.LENGTH_LONG).show();
//        imageView.setImageBitmap(dbHelper.getImage(id));

        cursor      =    dbHelper.getPlace(id);
        if (cursor != null && cursor.getColumnCount() > 0)
        {
            cursor.moveToFirst();
            Toast.makeText(getApplicationContext(),"COunt is"+cursor.getColumnCount(),Toast.LENGTH_LONG).show();
            name    = cursor.getString(1);
            textView.setText(name);
            byte[] img =cursor.getBlob(2);
            if(img.length>0)
            {
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(img, 0, img.length));
            }
            textView1.setText(cursor.getString(3));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent   = new Intent(Places.this,Tour_Info.class);
                intent.putExtra("location",name);
                startActivity(intent);
            }
        });
    }
}
