package local.hfad.hfad06listviewsandadapters;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DrinkCategoryActivity extends ListActivity {

    //Create a database and cursor vars
    SQLiteDatabase db = null;
    Cursor cursor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView drinksListView = getListView();

        try {

            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDataBaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();

            cursor = db.query(
                    "DRINK",                       // SELECT ... FROM DRINK
                    new String[]{"_id", "NAME"},   // ...NAME, DESCRIPTION, IMAGE_RESOURCE_ID...
                    null,                          // WHERE _id = ...
                    null,
                    null,
                    null,
                    null
            );
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0
            );
            drinksListView.setAdapter(cursorAdapter);
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        cursor.close();
        db.close();
    }

    @Override
    protected void onListItemClick(ListView listView,
                                   View itemview,
                                   int position,
                                   long id) {
        super.onListItemClick(listView, itemview, position, id);
        Intent intent = new Intent(this, DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINK_NUMBER, (int) id);
        startActivity(intent);
    }
}