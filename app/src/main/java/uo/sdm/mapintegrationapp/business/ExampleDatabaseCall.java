package uo.sdm.mapintegrationapp.business;

import android.content.ContentResolver;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import uo.sdm.mapintegrationapp.conf.PersistenceFactory;
import uo.sdm.mapintegrationapp.persistence.ZoneDB;
import uo.sdm.mapintegrationapp.persistence.ZoneGateway;

public class ExampleDatabaseCall {
    public void executeWrite() {
        Context context = null;
        SQLiteDatabase db = new ZoneDB(context).getWritableDatabase();
        ZoneGateway zoneGateway = PersistenceFactory.getZoneGateway();
        //zoneGateway.createZone(new CircularZone(0,0,0));
    }

    public void executeRead() {
        Context context = null;
        SQLiteDatabase db = new ZoneDB(context).getReadableDatabase();
        ZoneGateway zoneGateway = PersistenceFactory.getZoneGateway();
        //zoneGateway.getZoneById(0);
    }
}
