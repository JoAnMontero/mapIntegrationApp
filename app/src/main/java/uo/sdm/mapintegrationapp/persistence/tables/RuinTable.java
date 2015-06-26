package uo.sdm.mapintegrationapp.persistence.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Hans on 25/06/2015.
 */
public class RuinTable {
    public static final String NAME = "ruins";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_RESEARCH_END = "research_end";

    public static final String CREATE = "CREATE TABLE "
            + NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LATITUDE + " REAL NOT NULL, "
            + COLUMN_LONGITUDE + " REAL NOT NULL, "
            + COLUMN_TYPE + " TEXT NOT NULL, "
            + COLUMN_RESEARCH_END + " INT"
            + ");";

    public static final String DROP = "DROP TABLE IF EXISTS " + NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP);
        onCreate(db);
    }
}
