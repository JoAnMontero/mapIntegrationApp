package uo.sdm.mapintegrationapp.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import uo.sdm.mapintegrationapp.persistence.tables.RuinTable;

/**
 * Created by Hans on 25/06/2015.
 */
public class WorldDatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = "WorldDB";

    private static final String DATABASE_NAME = "world.db";
    private static final int DATABASE_VERSION = 2;

    public WorldDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO add initial data
        RuinTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database from version " + oldVersion +
                " to version " + newVersion + ", all old data will be destroyed.");
        // TODO wipe deprecated tables
        RuinTable.onUpgrade(db, oldVersion, newVersion);
    }
}
