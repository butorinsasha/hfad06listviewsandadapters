package local.hfad.hfad06listviewsandadapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class StarbuzzDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz";   // the name of the database
    private static final int DB_VERSION = 1;            // the version of the database

    private static final String CREATE_TABLE_DRINK = "CREATE TABLE DRINK ("
                                                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    + "NAME TEXT, "
                                                    + "DESCRIPTION TEXT, "
                                                    + "IMAGE_REAOURCE_ID INTEGER);";

    private static final String INSERT_AMERICANO = "INSERT INTO DRINK "
                                                    +        "( NAME, DESCRIPTION_TEXT,IMAGE_REAOURCE_ID)"
                                                    + "VALUES ('Americano', 'No milk foam, just coffee and milk if you wish', null);";

    public StarbuzzDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlLiteDatabase) {
        sqlLiteDatabase.execSQL(CREATE_TABLE_DRINK);
        insertDrink(sqlLiteDatabase, "Latte", "A couple of espresso with streamed milk", R.drawable.latte);
        insertDrink(sqlLiteDatabase, "Cuppuccino", "Espresso, hot milk, and a steamed milk foam", R.drawable.cappuccino);
        insertDrink(sqlLiteDatabase, "Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter);
        sqlLiteDatabase.execSQL(INSERT_AMERICANO);
    }

    private static void insertDrink(SQLiteDatabase sqlLiteDatabase, String name, String description, int imageResourceId) {
        ContentValues drinkValues = new ContentValues();

        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_REAOURCE_ID", imageResourceId);

        sqlLiteDatabase.insert("DRINK", null, drinkValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
