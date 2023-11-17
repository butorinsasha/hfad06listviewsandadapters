package local.hfad.hfad06listviewsandadapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDataBaseHelper extends SQLiteOpenHelper {

    // Class fields
    private static final String DB_NAME = "starbuzz";   // the name of the database
    private static final int DB_VERSION = 1;            // the version of the database

    private static final String CREATE_TABLE_DRINK = "CREATE TABLE DRINK ("
                                                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    + "NAME TEXT, "
                                                    + "DESCRIPTION TEXT, "
                                                    + "IMAGE_RESOURCE_ID INTEGER);";
    // Constructor
    public StarbuzzDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Overriden class methods
    @Override
    public void onCreate(SQLiteDatabase sqlLiteDatabase) {
        sqlLiteDatabase.execSQL(CREATE_TABLE_DRINK);

        insertDrink(sqlLiteDatabase, "Latte", "A couple of espresso with streamed milk", R.drawable.latte);
        insertDrink(sqlLiteDatabase, "Cuppuccino", "Espresso, hot milk, and a steamed milk foam", R.drawable.cappuccino);
        insertDrink(sqlLiteDatabase, "Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter);

        insertDrink2(sqlLiteDatabase, "Americano", "No milk foam, just coffee and milk if you wish", R.drawable.americano);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }


    // Private class methods
    private static void insertDrink(SQLiteDatabase sqlLiteDatabase, String name, String description, int imageResourceId) {
        ContentValues drinkValues = new ContentValues();

        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_REAOURCE_ID", imageResourceId);

        sqlLiteDatabase.insert("DRINK", null, drinkValues);
    }

    private static void insertDrink2(SQLiteDatabase sqlLiteDatabase, String name, String description, int imageResourceId) {
        sqlLiteDatabase.execSQL("INSERT INTO (NAME, DESCRIPTION, IMAGE_REAOURCE_ID)"
                + "VALUES ('" + name + "', '" + description + "', '" + imageResourceId + "')");
    }
}
