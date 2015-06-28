package uo.sdm.mapintegrationapp.persistence.tables;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Adrian on 26/06/2015.
 */
public class CollectibleTable {
    public static final String NAME = "collectible";

    public static final String COLUMN_ID = "_id";           //Id of row.
    public static final String COLUMN_TYPE = "type";        //each cards.
    public static final String COLUMN_NAME = "name";        //Name D:
    public static final String COLUMN_CATEGORY = "category";      //Category class.
    public static final String COLUMN_AMOUNT = "amount";    //Amount of this type card.

    public static final String CREATE = "CREATE TABLE "
            + NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TYPE + " INTEGER NOT NULL, "
            + COLUMN_NAME +" TEXT NOT NULL, "
            + COLUMN_CATEGORY + " TEXT NOT NULL, "
            + COLUMN_AMOUNT + " INTEGER NOT NULL"
            + ");";
    public static final String DROP = "DROP TABLE IF EXISTS " + NAME;

    public static void onCreate(SQLiteDatabase db)  {
        db.execSQL(CREATE);

        final ContentValues values = new ContentValues();
        values.put(CollectibleTable.COLUMN_TYPE,1);
        values.put(CollectibleTable.COLUMN_NAME,"name1");
        values.put(CollectibleTable.COLUMN_CATEGORY,"category1");
        values.put(CollectibleTable.COLUMN_AMOUNT,1);
        db.insert(CollectibleTable.NAME, null, values);

        values.put(CollectibleTable.COLUMN_TYPE,3);
        values.put(CollectibleTable.COLUMN_NAME,"name3");
        values.put(CollectibleTable.COLUMN_CATEGORY,"category3");
        values.put(CollectibleTable.COLUMN_AMOUNT,1);
        db.insert(CollectibleTable.NAME,null,values);

        values.put(CollectibleTable.COLUMN_TYPE,5);
        values.put(CollectibleTable.COLUMN_NAME,"name5");
        values.put(CollectibleTable.COLUMN_CATEGORY,"category5");
        values.put(CollectibleTable.COLUMN_AMOUNT,1);
        db.insert(CollectibleTable.NAME,null,values);

        values.put(CollectibleTable.COLUMN_TYPE,9);
        values.put(CollectibleTable.COLUMN_NAME,"name9");
        values.put(CollectibleTable.COLUMN_CATEGORY,"category9");
        values.put(CollectibleTable.COLUMN_AMOUNT,1);
        db.insert(CollectibleTable.NAME,null,values);

        values.put(CollectibleTable.COLUMN_TYPE,11);
        values.put(CollectibleTable.COLUMN_NAME,"name11");
        values.put(CollectibleTable.COLUMN_CATEGORY,"category11");
        values.put(CollectibleTable.COLUMN_AMOUNT,1);
        db.insert(CollectibleTable.NAME,null,values);

        values.put(CollectibleTable.COLUMN_TYPE,13);
        values.put(CollectibleTable.COLUMN_NAME,"name13");
        values.put(CollectibleTable.COLUMN_CATEGORY,"category13");
        values.put(CollectibleTable.COLUMN_AMOUNT,1);
        db.insert(CollectibleTable.NAME,null,values);

        values.put(CollectibleTable.COLUMN_TYPE,15);
        values.put(CollectibleTable.COLUMN_NAME,"name15");
        values.put(CollectibleTable.COLUMN_CATEGORY,"category15");
        values.put(CollectibleTable.COLUMN_AMOUNT,1);
        db.insert(CollectibleTable.NAME,null,values);

        values.put(CollectibleTable.COLUMN_TYPE,18);
        values.put(CollectibleTable.COLUMN_NAME,"name18");
        values.put(CollectibleTable.COLUMN_CATEGORY,"category18");
        values.put(CollectibleTable.COLUMN_AMOUNT,1);
        db.insert(CollectibleTable.NAME,null,values);

        values.put(CollectibleTable.COLUMN_TYPE,22);
        values.put(CollectibleTable.COLUMN_NAME,"name18");
        values.put(CollectibleTable.COLUMN_CATEGORY,"category18");
        values.put(CollectibleTable.COLUMN_AMOUNT,1);
        db.insert(CollectibleTable.NAME,null,values);


    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP);
        onCreate(db);
    }
}
