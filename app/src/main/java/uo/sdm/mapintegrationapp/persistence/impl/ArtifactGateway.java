package uo.sdm.mapintegrationapp.persistence.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import uo.sdm.mapintegrationapp.persistence.ZoneDB;

/**
 * Created by Adrián on 11/01/2015.
 */
public class ArtifactGateway {

    private SQLiteDatabase database;
    private final ZoneDB dbHelper;

    private final String[] colums = {
            ZoneDB.TABLE_ARTIFACTS.COLUMN_ID,
            ZoneDB.TABLE_ARTIFACTS.COLUMN_AMOUNT,
            ZoneDB.TABLE_ARTIFACTS.COLUMN_IMAGE_URI,
            ZoneDB.TABLE_ARTIFACTS.COLUMN_SET
    };

    public ArtifactGateway(final Context context){
        dbHelper = new ZoneDB(context);
    }

    public void setAmount(final long id, final int amount){
        String[] queryArgs = {
                String.valueOf(id)
        };
        ContentValues values = new ContentValues();
        values.put(ZoneDB.TABLE_ARTIFACTS.COLUMN_AMOUNT,amount);
        database.update(ZoneDB.TABLE_ARTIFACTS.NAME,
                values,
                ZoneDB.TABLE_ARTIFACTS.COLUMN_ID + "= ?",
                queryArgs);
    }


}
