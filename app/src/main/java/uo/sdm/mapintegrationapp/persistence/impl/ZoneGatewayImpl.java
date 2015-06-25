package uo.sdm.mapintegrationapp.persistence.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import uo.sdm.mapintegrationapp.persistence.ZoneDB;
import uo.sdm.mapintegrationapp.persistence.ZoneGateway;

public class ZoneGatewayImpl implements ZoneGateway{
    public static final String TABLE = ZoneDB.TABLE_ZONES.NAME;

    private final String[] columns = {
            ZoneDB.TABLE_ZONES.COLUMN_ID,
            ZoneDB.TABLE_ZONES.COLUMN_LATITUDE,
            ZoneDB.TABLE_ZONES.COLUMN_LONGITUDE,
            ZoneDB.TABLE_ZONES.COLUMN_RADIUS,
            ZoneDB.TABLE_ZONES.COLUMN_INFLUENCE_RADIUS
    };

    private SQLiteDatabase _database;

    public void setDatabase(SQLiteDatabase database) {
        _database = database;
    }

    /*public long createZone(CircularZone zone){
        final ContentValues contentValues = new ContentValues();

        contentValues.put(ZoneDB.TABLE_ZONES.COLUMN_LATITUDE, zone.getLatitude());
        contentValues.put(ZoneDB.TABLE_ZONES.COLUMN_LONGITUDE, zone.getLongitude());
        contentValues.put(ZoneDB.TABLE_ZONES.COLUMN_RADIUS, zone.getRadius());
        contentValues.put(ZoneDB.TABLE_ZONES.COLUMN_INFLUENCE_RADIUS, zone.getInfluenceRadius());

        return _database.insert(TABLE, null, contentValues);
    }

    public CircularZone getZoneById(final long id){

        final String[] queryArgs = { String.valueOf(id) };

        final Cursor cursor = _database.query(TABLE, columns,
                ZoneDB.TABLE_ZONES.COLUMN_ID + " = ?", queryArgs,
                null, null, null);

        if (cursor.moveToFirst()) {

            return new CircularZone(
                    cursor.getLong(cursor.getColumnIndex(
                            ZoneDB.TABLE_ZONES.COLUMN_ID)),
                    cursor.getDouble(cursor.getColumnIndex(
                            ZoneDB.TABLE_ZONES.COLUMN_LATITUDE)),
                    cursor.getDouble(cursor.getColumnIndex(
                            ZoneDB.TABLE_ZONES.COLUMN_LONGITUDE)),
                    cursor.getDouble(cursor.getColumnIndex(
                            ZoneDB.TABLE_ZONES.COLUMN_RADIUS)),
                    cursor.getDouble(cursor.getColumnIndex(
                            ZoneDB.TABLE_ZONES.COLUMN_INFLUENCE_RADIUS)));
        }

        return null;
    }

    public int deleteZone(final CircularZone zone) {
        final String[] queryArgs = { String.valueOf(zone.getId()) };

        return _database.delete(TABLE, ZoneDB.TABLE_ZONES.COLUMN_ID + " = ?", queryArgs);
    }

    public int updateZone(final CircularZone zone) {
        final String[] queryArgs = { String.valueOf(zone.getId()) };

        final ContentValues contentValues = new ContentValues();

        contentValues.put(ZoneDB.TABLE_ZONES.COLUMN_LATITUDE, zone.getLatitude());
        contentValues.put(ZoneDB.TABLE_ZONES.COLUMN_LONGITUDE, zone.getLongitude());
        contentValues.put(ZoneDB.TABLE_ZONES.COLUMN_RADIUS, zone.getRadius());
        contentValues.put(ZoneDB.TABLE_ZONES.COLUMN_INFLUENCE_RADIUS, zone.getInfluenceRadius());

        return _database.update(TABLE, contentValues, ZoneDB.TABLE_ZONES.COLUMN_ID + " = ?", queryArgs);
    }

    public List<CircularZone> getNearZones(LatLng point) {
        final List<CircularZone> zones = new ArrayList<>();
        final float proximity = 0.000005f;
        final String selection = ZoneDB.TABLE_ZONES.COLUMN_LATITUDE +
                " BETWEEN ? AND ? AND " + ZoneDB.TABLE_ZONES.COLUMN_LONGITUDE +
                " BETWEEN ? AND ?";
        final String[] queryArgs = {
                String.valueOf(point.latitude + proximity),
                String.valueOf(point.latitude - proximity),
                String.valueOf(point.longitude + proximity),
                String.valueOf(point.longitude - proximity)
        };

        final Cursor cursor = _database.query(TABLE, columns, selection,
                queryArgs, null, null, null);
        if (cursor.moveToFirst()) {

            while (cursor.moveToNext()) {
                final CircularZone zone = new CircularZone(
                        cursor.getLong(cursor.getColumnIndex(
                                ZoneDB.TABLE_ZONES.COLUMN_ID)),
                        cursor.getDouble(cursor.getColumnIndex(
                                ZoneDB.TABLE_ZONES.COLUMN_LATITUDE)),
                        cursor.getDouble(cursor.getColumnIndex(
                                ZoneDB.TABLE_ZONES.COLUMN_LONGITUDE)),
                        cursor.getDouble(cursor.getColumnIndex(
                                ZoneDB.TABLE_ZONES.COLUMN_RADIUS)),
                        cursor.getDouble(cursor.getColumnIndex(
                                ZoneDB.TABLE_ZONES.COLUMN_INFLUENCE_RADIUS)));
                zones.add(zone);
            }

            cursor.close();
        }

        return zones;
    }*/
}
