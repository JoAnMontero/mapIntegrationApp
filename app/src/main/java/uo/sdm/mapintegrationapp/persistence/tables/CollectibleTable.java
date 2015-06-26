package uo.sdm.mapintegrationapp.persistence.tables;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Adrian on 26/06/2015.
 */
public class CollectibleTable {
    public static final String NAME = "collectible";

    public static final String COLUMN_ID = "_id";           //Id of row.
    public static final String COLUMN_TYPE = "type";        //each cards.
    public static final String COLUMN_CATEGORY = "category";      //Category class.
    public static final String COLUMN_AMOUNT = "amount";    //Amount of this type card.

    public static final String CREATE = "CREATE TABLE"
            + NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TYPE + " INTEGER NOT NULL, "
            + COLUMN_CATEGORY + " TEXT NOT NULL, "
            + COLUMN_AMOUNT + " INTEGER NOT NULL"
            + ");";
    public static final String DROP = "DROP TABLE IF EXISTS " + NAME;

    public static void onCreate(SQLiteDatabase db)  { db.execSQL(CREATE); }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP);
        onCreate(db);
    }
}
