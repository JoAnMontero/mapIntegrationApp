package uo.sdm.mapintegrationapp.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import uo.sdm.mapintegrationapp.model.Place;
import uo.sdm.mapintegrationapp.model.types.PlaceType;
import uo.sdm.mapintegrationapp.persistence.tables.PlacesTable;

/**
 * Created by Hans on 25/06/2015.
 */
public class PlacesDataSource {
    private static final String LOG_TAG = "PlacesDS";

    private SQLiteDatabase db;
    private WorldDatabaseHelper dbHelper;

    private final String[] columns = {
            PlacesTable.COLUMN_ID,
            PlacesTable.COLUMN_LATITUDE,
            PlacesTable.COLUMN_LONGITUDE,
            PlacesTable.COLUMN_TYPE,
            PlacesTable.COLUMN_RESEARCHING,
            PlacesTable.COLUMN_RESEARCH_END
    };

    public PlacesDataSource(Context context) {
        dbHelper = new WorldDatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Place createPlace(double lat, double lng, PlaceType type,boolean researching, long research_end) {
        final ContentValues values = new ContentValues();

        values.put(PlacesTable.COLUMN_LATITUDE, lat);
        values.put(PlacesTable.COLUMN_LONGITUDE, lng);
        values.put(PlacesTable.COLUMN_TYPE, type.toString());
        values.put(PlacesTable.COLUMN_RESEARCHING, researching);
        values.put(PlacesTable.COLUMN_RESEARCH_END, research_end);

        long ruinId = db.insert(PlacesTable.TABLE_NAME, null, values);
        Cursor cursor = db.query(PlacesTable.TABLE_NAME, columns, PlacesTable.COLUMN_ID + " = " + ruinId,
                null, null, null, null);
        cursor.moveToFirst();
        Place place = cursorToPlace(cursor);
        cursor.close();
        return place;
    }

    public void deletePlace(Place place) {
        final ContentValues values = new ContentValues();
        long id = place.getId();
        db.delete(PlacesTable.TABLE_NAME, PlacesTable.COLUMN_ID + " = " + id, null);
        Log.w(LOG_TAG, "Place deleted with id: " + id);
    }

    public void deleteAllPlaces() {
        db.delete(PlacesTable.TABLE_NAME, null, null);
        Log.w(LOG_TAG, "All places deleted");
    }

    public Long getPlaceResearchEnd(long id) {
        boolean researching = false;
        long research_end = -1;

        Cursor cursor = db.query(PlacesTable.TABLE_NAME, columns, PlacesTable.COLUMN_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        researching = (cursor.getInt(4) != 0);
        research_end = cursor.getLong(5);
        cursor.close();

        return (researching == false)? null:research_end;
    }

    public List<Place> getAllPlaces() {
        List<Place> ruins = new ArrayList<Place>();

        Cursor cursor = db.query(PlacesTable.TABLE_NAME, columns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Place ruin = cursorToPlace(cursor);
            ruins.add(ruin);
            cursor.moveToNext();
        }

        return ruins;
    }

    public int update(Place place) {
        int rowsUpdated = 0;
        final ContentValues values = new ContentValues();

        values.put(PlacesTable.COLUMN_LATITUDE, place.getLat());
        values.put(PlacesTable.COLUMN_LONGITUDE, place.getLng());
        values.put(PlacesTable.COLUMN_TYPE, place.getType().toString());
        values.put(PlacesTable.COLUMN_RESEARCHING, place.isResearching());
        values.put(PlacesTable.COLUMN_RESEARCH_END, place.getResearch_end());

        rowsUpdated = db.update(PlacesTable.TABLE_NAME, values, PlacesTable.COLUMN_ID + " = " + place.getId(), null);

        return rowsUpdated;
    }

    public int updateResearchTime(long id, boolean researching, long research_end) {
        int rowsUpdated = 0;
        final ContentValues values = new ContentValues();

        values.put(PlacesTable.COLUMN_RESEARCHING, researching);
        values.put(PlacesTable.COLUMN_RESEARCH_END, research_end);

        rowsUpdated = db.update(PlacesTable.TABLE_NAME, values, PlacesTable.COLUMN_ID + " = " + id, null);

        return rowsUpdated;
    }

    private Place cursorToPlace(Cursor cursor) {
        Place place = new Place(cursor.getLong(0));
        Location location = new Location("");
        location.setLatitude(cursor.getDouble(1));
        location.setLongitude(cursor.getDouble(2));
        place.setLocation(location);
        place.setType(PlaceType.valueOf(cursor.getString(3)));
        place.setResearching(cursor.getInt(4) != 0);
        place.setResearch_end(cursor.getLong(5));
        return place;
    }
}
