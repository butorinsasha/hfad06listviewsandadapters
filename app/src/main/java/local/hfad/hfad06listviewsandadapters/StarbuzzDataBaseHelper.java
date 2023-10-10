package local.hfad.hfad06listviewsandadapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class StarbuzzDataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "starbuzz";   // the name of out database
    private static final int DB_VERSION = 1;            // the version of the database

    private static final String CREATE_TABLE_DRINK = "CREATE TABLE DRINK ("
                                                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                    + "NAME TEXT, "
                                                    + "DESCRIPTION TEXT, "
                                                    + "IMAGE_REAOURCE_ID INTEGER);";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
