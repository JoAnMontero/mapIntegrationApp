package uo.sdm.mapintegrationapp.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ZoneDB extends SQLiteOpenHelper {

    private static final String LOG_TAG = "ZoneDB";
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "zones.db";

    public static class TABLE_ARTIFACTS {
        public static final String NAME = "artifacts";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_SET = "set";
        public static final String COLUMN_IMAGE_URI = "imageUri";
        public static final String COLUMN_AMOUNT = "amount";

        public static final String CREATE = "CREATE TABLE " + NAME + "(" +
                COLUMN_ID + " BIGINT PRIMARY KEY AUTOINCREMENT" + ", " +
                COLUMN_SET + " TEXT NOT NULL" + ", " +
                COLUMN_IMAGE_URI + "TEXT NOT NULL" + ", " +
                COLUMN_AMOUNT + " SMALLINT NOT NULL" + ");";

        public static final String DROP = "DROP TABLE IF EXISTS " + NAME;
    }

    public static class TABLE_RUINS {
        public static final String NAME = "ruins";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_RESEARCH_END = "research_end";

        public static final String CREATE = "CREATE TABLE " + NAME + "(" +
                COLUMN_ID + " BIGINT PRIMARY KEY AUTOINCREMENT" + ", " +
                COLUMN_LATITUDE + " DOUBLE NOT NULL" + ", " +
                COLUMN_LONGITUDE + " DOUBLE NOT NULL" + ", " +
                COLUMN_TYPE + " DOUBLE NOT NULL" + ", " +
                COLUMN_RESEARCH_END + " INT" + ");";

        public static final String DROP = "DROP TABLE IF EXISTS " + NAME;
    }

    public static class TABLE_ZONES {
        public static final String NAME = "zones";

        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_LATITUDE = "latitude";
        public static final String COLUMN_LONGITUDE = "longitude";
        public static final String COLUMN_RADIUS = "radius";
        public static final String COLUMN_INFLUENCE_RADIUS = "influence_radius";

        public static final String CREATE = "CREATE TABLE " + NAME + "(" +
                COLUMN_ID + " BIGINT PRIMARY KEY AUTOINCREMENT" + ", " +
                COLUMN_LATITUDE + " DOUBLE NOT NULL" + ", " +
                COLUMN_LONGITUDE + " DOUBLE NOT NULL" + ", " +
                COLUMN_RADIUS + " DOUBLE NOT NULL" + ", " +
                COLUMN_INFLUENCE_RADIUS + " DOUBLE NOT NULL" + ");";

        public static final String DROP = "DROP TABLE IF EXISTS " + NAME;
    }

    public static final String[] DEPRECATED_TABLES_DROPS = {
            TABLE_ARTIFACTS.DROP,
            TABLE_ZONES.DROP
    };

    public static final String[] ACTIVE_TABLES_CREATES = {
            TABLE_RUINS.CREATE
    };

    public static final String[] ACTIVE_TABLES_DROPS = {
            TABLE_RUINS.DROP
    };

    public ZoneDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String TABLE_CREATE : ACTIVE_TABLES_CREATES)
            db.execSQL(TABLE_CREATE);
        // db.execSQL(TABLE_ZONES.CREATE);
        // db.execSQL(TABLE_ARTIFACTS.CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion +
                " to version " + newVersion + ", all old data will be destroyed.");

        for (String TABLE_DROP : DEPRECATED_TABLES_DROPS)
            db.execSQL(TABLE_DROP);
        for (String TABLE_DROP : ACTIVE_TABLES_DROPS)
            db.execSQL(TABLE_DROP);
        //db.execSQL(TABLE_ZONES.DROP);
        //db.execSQL(TABLE_ARTIFACTS.DROP);

        onCreate(db);
    }
}
