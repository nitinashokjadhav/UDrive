package com.afzaalahmadzeeshan.mylocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Objects;

public class ContentManager {
    private static Context mContext;

    public long getMinutes() {
        return new SettingsManager(mContext).get(true);
    }

    public long getMeters() {
        return new SettingsManager(mContext).get(false);
    }

    public ContentManager(Context context) {
        mContext = context;
    }

    // Settings
    public int setupSettings(Context context, long meters, long minutes) {
        return new SettingsManager(context).setup(minutes, meters);
    }

    // Cities
    public ArrayList<City> getCities () {
        return new CityManager(mContext).get();
    }
    public int addCity(String name, String lon, String lat, boolean enabled) {
        return new CityManager(mContext).insert(name, lon, lat, enabled);
    }
    public int updateCity(int id, String name, String lon, String lat, boolean enabled) {
        return new CityManager(mContext).update(id, name, lon, lat, enabled);
    }
    public boolean deleteCity(int id) {
        return new CityManager(mContext).delete(id);
    }

    // Numbers
    public ArrayList<Number> getNumbers () {
        return new NumberManager(mContext).get();
    }
    public int addNumber(String name, String number, boolean enabled) {
        for (Number number1 : getNumbers()) {
            if(number1.getNumber().equals(number)) {
                return -1;
            }
        }
        return new NumberManager(mContext).insert(name, number, enabled);
    }
    public int updateNumber(int id, String name, String number, boolean enabled) {
        return new NumberManager(mContext).update(id, name, number, enabled);
    }
    public boolean deleteNumber(int id) {
        return new NumberManager(mContext).delete(id);
    }

    // Classes below
    public class SettingsManager extends SQLiteOpenHelper implements BaseColumns {
        private final static String DATABASE_NAME = "settings_database.db";
        private final static String TABLE_NAME = "settings";
        private final static String COLUMN_MINUTES = "minutes";
        private final static String COLUMN_METERS = "meters";

        public SettingsManager(Context mContext) {
            super(mContext, DATABASE_NAME, null, 1);
        }

        public final static String CREATE_COMMAND =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        COLUMN_METERS + " TEXT, " +
                        COLUMN_MINUTES + " TEXT" +
                ")";

        public int setup(long minutes, long meters) {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); // Drop
            onCreate(db);

            ContentValues values = new ContentValues();
            values.put(COLUMN_METERS, meters);
            values.put(COLUMN_MINUTES, minutes);

            return (int)db.insert(TABLE_NAME, null, values);
        }

        public long get(boolean minutes) {
            String[] columns = {COLUMN_METERS, COLUMN_MINUTES};
            SQLiteDatabase db = getReadableDatabase();

            Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
            if(cursor.moveToNext()) {
                // A column is present
                if(minutes) {
                    return Long.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_MINUTES)));
                } else {
                    return Long.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_METERS)));
                }
            } else {
                return (minutes) ? 10 : 1000;
            }
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_COMMAND);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public class CityManager extends SQLiteOpenHelper implements BaseColumns {
        private final static String DATABASE_NAME = "cities_database.db";
        public static final String TABLE_NAME = "cities";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_ENABLED = "enabled";

        private static final String CREATE_COMMAND =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_LONGITUDE + " TEXT, " +
                        COLUMN_LATITUDE + " TEXT, " +
                        COLUMN_ENABLED + " TEXT" +
                ")";

        private static final String DELETE_COMMAND =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public CityManager(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        public int insert(String name, String lon, String lat, boolean enabled) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_ENABLED, String.valueOf(enabled));
            values.put(COLUMN_LATITUDE, lat);
            values.put(COLUMN_LONGITUDE, lon);

            return (int)db.insert(TABLE_NAME, null, values);
        }

        public boolean delete(int id) {
            String selection = _ID + " LIKE ?";
            String arguments[] = { String.valueOf(id) };

            SQLiteDatabase db = getWritableDatabase();
            db.delete(TABLE_NAME, selection, arguments);
            return true;
        }

        public int update(int id, String name, String lon, String lat, boolean enabled) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_ENABLED, String.valueOf(enabled));
            values.put(COLUMN_LATITUDE, lat);
            values.put(COLUMN_LONGITUDE, lon);

            String selection = _ID + " LIKE ?";
            String arguments[] = { String.valueOf(id) };

            return db.update(TABLE_NAME, values, selection, arguments);
        }

        public ArrayList<City> get() {
            SQLiteDatabase db = getReadableDatabase();
            String columns[] = {
                    COLUMN_NAME, COLUMN_LATITUDE, COLUMN_LONGITUDE, COLUMN_ENABLED
            };

            String order = _ID + " DESC";

            Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, order);

            ArrayList<City> cities = new ArrayList<City>();
            if(cursor.moveToFirst()) {
                do {
                    City city = new City();
                    city.setActive(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_ENABLED))));
                    city.setLatitude(cursor.getString(cursor.getColumnIndex(COLUMN_LATITUDE)));
                    city.setLongitude(cursor.getString(cursor.getColumnIndex(COLUMN_LONGITUDE)));
                    city.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));

                    cities.add(city);
                } while (cursor.moveToNext());
            }

            return cities;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_COMMAND);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public class NumberManager extends SQLiteOpenHelper implements BaseColumns {
        private final static String DATABASE_NAME = "numbers_database.db";
        public static final String TABLE_NAME = "numbers";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_NUMBER = "number";
        public static final String COLUMN_ENABLED = "enabled";

        private static final String CREATE_COMMAND =
                "CREATE TABLE " + TABLE_NAME + "(" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_NUMBER + " TEXT, " +
                        COLUMN_ENABLED + " TEXT" +
                        ")";

        private static final String DELETE_COMMAND =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

        public NumberManager(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        public int insert(String name, String number, boolean enabled) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_ENABLED, String.valueOf(enabled));
            values.put(COLUMN_NUMBER, number);

            return (int)db.insert(TABLE_NAME, null, values);
        }

        public boolean delete(int id) {
            String selection = _ID + " LIKE ?";
            String arguments[] = { String.valueOf(id) };

            SQLiteDatabase db = getWritableDatabase();
            db.delete(TABLE_NAME, selection, arguments);
            return true;
        }

        public int update(int id, String name, String number, boolean enabled) {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_ENABLED, String.valueOf(enabled));
            values.put(COLUMN_NUMBER, number);

            String selection = _ID + " LIKE ?";
            String arguments[] = { String.valueOf(id) };

            return db.update(TABLE_NAME, values, selection, arguments);
        }

        public ArrayList<Number> get() {
            SQLiteDatabase db = getReadableDatabase();
            String columns[] = {
                    COLUMN_NAME, COLUMN_NUMBER, COLUMN_ENABLED
            };

            String order = _ID + " DESC";

            Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, order);

            ArrayList<Number> numbers = new ArrayList<Number>();
            if(cursor.moveToFirst()) {
                do {
                    Number number = new Number();
                    number.setActive(Boolean.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_ENABLED))));
                    number.setNumber(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER)));
                    number.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));

                    numbers.add(number);
                } while (cursor.moveToNext());
            }

            return numbers;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_COMMAND);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
