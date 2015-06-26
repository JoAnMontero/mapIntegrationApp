package uo.sdm.mapintegrationapp.persistence;

import android.database.sqlite.SQLiteDatabase;

@Deprecated
public interface ZoneGateway {
    void setDatabase(SQLiteDatabase database);
    //long createZone(CircularZone zone);
    //CircularZone getZoneById(final long id);
}
