package uo.sdm.mapintegrationapp.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import uo.sdm.mapintegrationapp.model.Collectible;
import uo.sdm.mapintegrationapp.persistence.tables.CollectibleTable;

/**
 * Created by Adrián on 26/06/2015.
 */
public class CollectibleDataSource {

    private static final String LOG_TAG = "CollectibleDS";

    private SQLiteDatabase db;
    private WorldDatabaseHelper dbHelper;

    private final String[] columns = {
            CollectibleTable.COLUMN_ID,
            CollectibleTable.COLUMN_TYPE,
            CollectibleTable.COLUMN_CATEGORY,
            CollectibleTable.COLUMN_AMOUNT
    };

    public CollectibleDataSource(Context context){
        dbHelper = new WorldDatabaseHelper(context);
    }

    public void open() { db = dbHelper.getWritableDatabase(); }

    public void close() { dbHelper.close(); }

    /**
     * This function insert a new collectible type in database, if already exist throws an exception
     * @param collectible Object Collectible without id attribute. (will be ignored).
     * @return collectible Object equal to collectible object envoy by parameter but with id.
     */
    public Collectible addCollectible(Collectible collectible){
        if(getCollectibleByType(collectible.getType()) != null) {
            Log.v(LOG_TAG,"This collectible type already exists");
            throw new IllegalStateException("This collectible type already exists");
        }
        final ContentValues values = new ContentValues();
        values.put(CollectibleTable.COLUMN_TYPE,collectible.getType());
        values.put(CollectibleTable.COLUMN_CATEGORY,collectible.getCategory());
        values.put(CollectibleTable.COLUMN_AMOUNT, collectible.getAmount());
        db.insert(CollectibleTable.NAME, null, values);
        Log.v(LOG_TAG, "New collectible has been added");
        return getCollectibleByType(collectible.getType());
    }

    /**
     * This function get an object Collectible from an identifier.
     * @param type Integer
     * @return Collectible object or null if doesn't exists.
     */
    public Collectible getCollectibleByType(Integer type){
        Collectible c = null;
        Cursor cursor = db.query(
                CollectibleTable.NAME,
                columns,
                CollectibleTable.COLUMN_TYPE + " = " + type,
                null,null,null,null);
        if(cursor.moveToFirst()){ //Si existe..
            c =  new Collectible(
                    cursor.getLong(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
        }
        cursor.close();
        return c;
    }
    /**
     * This function get an object Collectible from an identifier.
     * @param id Long
     * @return Collectible object or null if doesn't exists.
     */
    public Collectible getCollectibleById(Long id){
        Collectible c = null;
        Cursor cursor = db.query(
                CollectibleTable.NAME,
                columns,
                CollectibleTable.COLUMN_ID + " = " + id,
                null,null,null,null);
        if(cursor.moveToFirst()){ //Si existe..
            c =  new Collectible(
                    cursor.getLong(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            );
        }
        cursor.close();
        return c;
    }

    /**
     * This function update attribute 'amount' of collectible object.
     * @param collectible Collectible
     * @return The same object envoy by parameter. (fluid interfaces).
     */
    public Collectible updateCollectible(Collectible collectible){
        Collectible c = getCollectibleByType(collectible.getType());
        if(c == null) {
            Log.v(LOG_TAG,"You can't update a collectible that doesn't exists.");
            throw new IllegalStateException("You can't update a collectible that doesn't exists.");
        }
        final ContentValues values = new ContentValues();
        values.put(CollectibleTable.COLUMN_AMOUNT,collectible.getAmount());
        db.update(
                CollectibleTable.NAME,
                values,
                CollectibleTable.COLUMN_TYPE + " = " + c.getType(),
                null);
        Log.v(LOG_TAG,"Attribute 'amount' from collectible object type: "
                + c.getType()
                +" has been updated");
        return collectible;
    }

    /**
     * this function allow obtain a list with all collectibles owned by user.
     * @return list with collectibles.
     */
    public List<Collectible> listCollectibles(){
        List<Collectible> list = new ArrayList<>();
        Cursor cursor = db.query(
                CollectibleTable.NAME,
                columns,
                null,null,null,null,null);
        if(cursor.moveToFirst()) {
            do {
                list.add(new Collectible(
                        cursor.getLong(0),
                        cursor.getInt(1),
                        cursor.getString(2),
                        cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.v(LOG_TAG, "List obtained with +" + list.size() + " elements.");
        return list;
    }
}
