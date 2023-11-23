package local.hfad.hfad06listviewsandadapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StarbuzzDataBaseHelper extends SQLiteOpenHelper {

    // Class fields
    private static final String DB_NAME = "starbuzz";   // the name of the database
    private static final int DB_VERSION = 2;            // the version of the database

    private static final String CREATE_TABLE_DRINK = "CREATE TABLE DRINK ("
                                                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    + "NAME TEXT, "
                                                    + "DESCRIPTION TEXT, "
                                                    + "IMAGE_RESOURCE_ID INTEGER);";

    private static final String ADD_COLUMN_FAVORITE_NUMERIC = "ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;";

    // Constructor
    public StarbuzzDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Overriden class methods
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        updateMyDatabase(sqLiteDatabase, oldVersion, newVersion);
    }


    // Private class methods
    private static void insertDrink(SQLiteDatabase sqlLiteDatabase, String name, String description, int imageResourceId) {
        ContentValues drinkValues = new ContentValues();

        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", imageResourceId);

        sqlLiteDatabase.insert("DRINK", null, drinkValues);
    }

    private static void insertDrink2(SQLiteDatabase sqlLiteDatabase, String name, String description, int imageResourceId) {
        sqlLiteDatabase.execSQL("INSERT INTO DRINK (NAME, DESCRIPTION, IMAGE_RESOURCE_ID)"
                + " VALUES ('" + name + "', '" + description + "', '" + imageResourceId + "')");
    }

    private static void updateMyDatabase(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            sqLiteDatabase.execSQL(CREATE_TABLE_DRINK);

            insertDrink(sqLiteDatabase, "Latte", "A couple of espresso with streamed milk", R.drawable.latte);
            insertDrink(sqLiteDatabase, "Cuppuccino", "Espresso, hot milk, and a steamed milk foam", R.drawable.cappuccino);
            insertDrink(sqLiteDatabase, "Filter", "Highest quality beans roasted and brewed fresh", R.drawable.filter);

            insertDrink2(sqLiteDatabase, "Americano", "No milk foam, just coffee and milk if you wish", R.drawable.americano);
        }
        if (oldVersion < 2) {
            sqLiteDatabase.execSQL(ADD_COLUMN_FAVORITE_NUMERIC);
        }
    }
}
