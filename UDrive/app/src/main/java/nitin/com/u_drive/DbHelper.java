package nitin.com.u_drive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

//import java.lang.reflect.Constructor;

public  class DbHelper extends SQLiteOpenHelper {
    public static final String TAG=DbHelper.class.getSimpleName();
    public static final String DB_NAME="myapp_db";
    public static final int DB_VERSION=1;
    public static final String USER_TABLE="users";
    public static final  String COLUMN_ID="_id";
    public static final String COLUMN_PASS="password";
    public static final String COLUMN_EMAIL="email";


    public static final String CREATE_TABLE_USERS = "CREATE TABLE "+USER_TABLE+" ("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMAIL+" TEXT,"
            + COLUMN_PASS+" TEXT);";

    public DbHelper(Context context) {
        super(context, DB_NAME , null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE);
        onCreate(db);
    }

    public void addUser(String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_EMAIL,email);
        values.put(COLUMN_PASS,password);
        long id =db.insert(USER_TABLE,null,values);
        db.close();
        Log.d(TAG,"user inserted "+id);
    }

    public boolean getUser(String email,String pass)
    {
        String selectQuery = "select * from "+ USER_TABLE + " where "+ COLUMN_EMAIL +" = "+"'"+email+"'"+" and "+ COLUMN_PASS+" = "+"'"+pass+"'";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }

//    public Bitmap getImage(String id)
//    {
//        Bitmap bitmap = null;
//        int pId = Integer.parseInt(id);
//        String selectQuery = "select pImage from place where pId="+pId;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery,null);
//        cursor.moveToFirst();
//        if (cursor.getCount()>0)
//        {
//            byte[] img =cursor.getBlob(0);
//            if(img.length>0) {
//                bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
//            }
//        }
//        cursor.close();
//        db.close();
//        return bitmap;
//    }

    public Cursor getPlace(int id)
    {
        Cursor cursor = null;
        String selectQuery = "select * from place where pId="+id;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(selectQuery,null);
        return cursor;
    }
    public Cursor getAllPlace()
    {
        Cursor cursor = null;
        String selectQuery = "select pId,pName from place" ;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery(selectQuery,null);
        return cursor;
    }
}

