package uo.sdm.mapintegrationapp.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.sdm.mapintegrationapp.model.Ruin;
import uo.sdm.mapintegrationapp.model.types.RuinType;
import uo.sdm.mapintegrationapp.persistence.tables.RuinTable;

/**
 * Created by Hans on 25/06/2015.
 */
public class RuinsDataSource {
    private static final String LOG_TAG = "RuinsDS";

    private SQLiteDatabase db;
    private WorldDatabaseHelper dbHelper;

    private final String[] columns = {
            RuinTable.COLUMN_ID,
            RuinTable.COLUMN_LATITUDE,
            RuinTable.COLUMN_LONGITUDE,
            RuinTable.COLUMN_TYPE,
            RuinTable.COLUMN_RESEARCH_END
    };

    public RuinsDataSource(Context context) {
        dbHelper = new WorldDatabaseHelper(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Ruin createRuin(double lat, double lng, RuinType type, long research_end) {
        final ContentValues values = new ContentValues();

        values.put(RuinTable.COLUMN_LATITUDE, lat);
        values.put(RuinTable.COLUMN_LONGITUDE, lng);
        values.put(RuinTable.COLUMN_TYPE, type.toString());
        values.put(RuinTable.COLUMN_RESEARCH_END, research_end);

        long ruinId = db.insert(RuinTable.NAME, null, values);
        Cursor cursor = db.query(RuinTable.NAME, columns, RuinTable.COLUMN_ID + " = " + ruinId,
                null, null, null, null);
        cursor.moveToFirst();
        Ruin ruin = cursorToRuin(cursor);
        cursor.close();
        return ruin;
    }

    public void deleteRuin(Ruin ruin) {
        final ContentValues values = new ContentValues();
        long id = ruin.getId();
        db.delete(RuinTable.NAME, RuinTable.COLUMN_ID + " = " + id, null);
        Log.w(LOG_TAG, "Ruin deleted with id: " + id);
    }

    public void deleteAllRuins() {
        db.delete(RuinTable.NAME, null, null);
        Log.w(LOG_TAG, "All ruins deleted");
    }

    public long getRuinResearchEnd(long id) {
        long research_end = -1;

        Cursor cursor = db.query(RuinTable.NAME, new String[]{RuinTable.COLUMN_LONGITUDE}, RuinTable.COLUMN_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        research_end = cursor.getLong(0);
        cursor.close();

        return research_end;
    }

    public List<Ruin> getAllRuins() {
        List<Ruin> ruins = new ArrayList<Ruin>();

        Cursor cursor = db.query(RuinTable.NAME, columns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ruin ruin = cursorToRuin(cursor);
            ruins.add(ruin);
            cursor.moveToNext();
        }

        return ruins;
    }

    public int update(Ruin ruin) {
        int rowsUpdated = 0;
        final ContentValues values = new ContentValues();

        values.put(RuinTable.COLUMN_LATITUDE, ruin.getLat());
        values.put(RuinTable.COLUMN_LONGITUDE, ruin.getLng());
        values.put(RuinTable.COLUMN_TYPE, ruin.getType().toString());
        values.put(RuinTable.COLUMN_RESEARCH_END, ruin.getResearch_end());

        rowsUpdated = db.update(RuinTable.NAME, values, RuinTable.COLUMN_ID + " = " + ruin.getId(), null);

        return rowsUpdated;
    }

    public int updateResearchTime(long id, long research_end) {
        int rowsUpdated = 0;
        final ContentValues values = new ContentValues();

        values.put(RuinTable.COLUMN_RESEARCH_END, research_end);

        rowsUpdated = db.update(RuinTable.NAME, values, RuinTable.COLUMN_ID + " = " + id, null);

        return rowsUpdated;
    }

    private Ruin cursorToRuin(Cursor cursor) {
        Ruin ruin = new Ruin(cursor.getLong(0));
        ruin.setLat(cursor.getDouble(1));
        ruin.setLng(cursor.getDouble(2));
        ruin.setType(RuinType.valueOf(cursor.getString(3)));
        ruin.setResearch_end(cursor.getLong(4));
        return ruin;
    }
}
