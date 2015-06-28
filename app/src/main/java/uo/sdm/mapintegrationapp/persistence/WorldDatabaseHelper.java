package uo.sdm.mapintegrationapp.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import uo.sdm.mapintegrationapp.persistence.tables.CollectibleTable;
import uo.sdm.mapintegrationapp.persistence.tables.PlacesTable;

/**
 * Created by Hans on 25/06/2015.
 */
public class WorldDatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = "WorldDB";

    private static final String DATABASE_NAME = "world.db";
    private static final int DATABASE_VERSION = 6;

    public static final String[] DEPRECATED_TABLES_DROPS = {
            "DROP TABLE IF EXISTS ruins",
            "DROP TABLE IF EXISTS artifacts",
            "DROP TABLE IF EXISTS zones"
    };
    public WorldDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO add initial data
        PlacesTable.onCreate(db);
        CollectibleTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion +
                " to version " + newVersion + ", all old data will be destroyed.");
        for(String drop : DEPRECATED_TABLES_DROPS)
            db.execSQL(drop);
        PlacesTable.onUpgrade(db, oldVersion, newVersion);
    }
}
