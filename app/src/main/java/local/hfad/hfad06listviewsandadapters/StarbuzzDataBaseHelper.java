package local.hfad.hfad06listviewsandadapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

public class StarbuzzDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz";   // the name of out database
    private static final int DB_VERSION = 1;            // the version of the database

    private static final String CREATE_TABLE_DRINK = "CREATE TABLE DRINK ("
                                                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    + "NAME TEXT, "
                                                    + "DESCRIPTION TEXT, "
                                                    + "IMAGE_RESOURCE_ID INTEGER);";

    private String insertIntoDrink(String name, String description, String imageResourceId) {
        return "INSERT INTO DRINK (NAME, DESCRIPTION, IMAGE_REAOURCE_ID)"
                + "VALUES"
                + "('" + name + "', '" + description + "', '" + imageResourceId + "')";
    }


    public StarbuzzDataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE DRINK ("
                                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                + "NAME TEXT, "
                                + "DESCRIPTION TEXT, "
                                + "IMAGE_REAOURCE_ID INTEGER);");

//        sqLiteDatabase.execSQL(insertIntoDrink("Latte", "A couple of espresso with streamed milk", R.drawable.latte));
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", "latte");
        drinkValues.put("DESCRIPTION", "Espresso and steamed milk);");
        drinkValues.put("IMAGE_RESOURCE_ID", "R.drawable.latte");
        sqLiteDatabase.insert("DRINK", null, drinkValues);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
