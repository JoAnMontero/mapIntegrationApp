package uo.sdm.mapintegrationapp.persistence.tables;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import uo.sdm.mapintegrationapp.model.Place;
import uo.sdm.mapintegrationapp.model.types.PlaceType;

/**
 * Created by Hans on 25/06/2015.
 */
public class PlacesTable {
    public static final String TABLE_NAME = "places";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_RESEARCHING = "researching";
    public static final String COLUMN_RESEARCH_END = "research_end";

    public static final String CREATE = "CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LATITUDE + " REAL NOT NULL, "
            + COLUMN_LONGITUDE + " REAL NOT NULL, "
            + COLUMN_TYPE + " TEXT NOT NULL, "
            + COLUMN_RESEARCHING + " BOOLEAN NOT NULL, "
            + COLUMN_RESEARCH_END + " INT NOT NULL"
            + ");";

    public static final String DROP = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);

        final ContentValues values = new ContentValues();
        values.put(PlacesTable.COLUMN_LATITUDE, 43.537686);
        values.put(PlacesTable.COLUMN_LONGITUDE, -5.687766);
        values.put(PlacesTable.COLUMN_TYPE, PlaceType.Cave.toString());
        values.put(PlacesTable.COLUMN_RESEARCHING, false);
        values.put(PlacesTable.COLUMN_RESEARCH_END, 0);
        db.insert(PlacesTable.TABLE_NAME, null, values);

        values.put(PlacesTable.COLUMN_LATITUDE, 43.538686);
        values.put(PlacesTable.COLUMN_LONGITUDE, -5.687766);
        values.put(PlacesTable.COLUMN_TYPE, PlaceType.Desert.toString());
        values.put(PlacesTable.COLUMN_RESEARCHING, false);
        values.put(PlacesTable.COLUMN_RESEARCH_END, 0);
        db.insert(PlacesTable.TABLE_NAME, null, values);

        values.put(PlacesTable.COLUMN_LATITUDE, 43.539686);
        values.put(PlacesTable.COLUMN_LONGITUDE, -5.687766);
        values.put(PlacesTable.COLUMN_TYPE, PlaceType.Dungeon.toString());
        values.put(PlacesTable.COLUMN_RESEARCHING, false);
        values.put(PlacesTable.COLUMN_RESEARCH_END, 0);
        db.insert(PlacesTable.TABLE_NAME, null, values);

        values.put(PlacesTable.COLUMN_LATITUDE, 43.540686);
        values.put(PlacesTable.COLUMN_LONGITUDE, -5.687766);
        values.put(PlacesTable.COLUMN_TYPE, PlaceType.Forest.toString());
        values.put(PlacesTable.COLUMN_RESEARCHING, false);
        values.put(PlacesTable.COLUMN_RESEARCH_END, 0);
        db.insert(PlacesTable.TABLE_NAME, null, values);

        values.put(PlacesTable.COLUMN_LATITUDE, 43.541686);
        values.put(PlacesTable.COLUMN_LONGITUDE, -5.687766);
        values.put(PlacesTable.COLUMN_TYPE, PlaceType.Garden.toString());
        values.put(PlacesTable.COLUMN_RESEARCHING, false);
        values.put(PlacesTable.COLUMN_RESEARCH_END, 0);
        db.insert(PlacesTable.TABLE_NAME, null, values);

        values.put(PlacesTable.COLUMN_LATITUDE, 43.542686);
        values.put(PlacesTable.COLUMN_LONGITUDE, -5.687766);
        values.put(PlacesTable.COLUMN_TYPE, PlaceType.Mountain.toString());
        values.put(PlacesTable.COLUMN_RESEARCHING, false);
        values.put(PlacesTable.COLUMN_RESEARCH_END, 0);
        db.insert(PlacesTable.TABLE_NAME, null, values);

        values.put(PlacesTable.COLUMN_LATITUDE, 43.543686);
        values.put(PlacesTable.COLUMN_LONGITUDE, -5.687766);
        values.put(PlacesTable.COLUMN_TYPE, PlaceType.River.toString());
        values.put(PlacesTable.COLUMN_RESEARCHING, false);
        values.put(PlacesTable.COLUMN_RESEARCH_END, 0);
        db.insert(PlacesTable.TABLE_NAME, null, values);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP);
        onCreate(db);
    }
}
