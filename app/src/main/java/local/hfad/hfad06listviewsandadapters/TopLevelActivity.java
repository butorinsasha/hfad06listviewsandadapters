package local.hfad.hfad06listviewsandadapters;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TopLevelActivity extends Activity {

    private SQLiteDatabase db;
    private Cursor favoritesCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        //Create an OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)  {
                if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);

                    Log.i(this.getClass().getName(), "item id position " + position);
                    Toast.makeText(TopLevelActivity.this, "item id position " + position, Toast.LENGTH_SHORT).show();

                    TopLevelActivity.this.startActivity(intent);
                }
            }
        };

        //Add the listener to the list view
        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);

        ListView listFavorites = findViewById(R.id.list_favorites);

        try {
            SQLiteOpenHelper starbuzzDataBaseHelper = new StarbuzzDataBaseHelper(this);
            db = starbuzzDataBaseHelper.getReadableDatabase();
            favoritesCursor = db.query(
                    "DRINK",
                    new String[] {"_id", "NAME"},
                    "FAVORITE = 1",
                    null,
                    null,
                    null,
                    null
            );

            ListAdapter favoritesAdapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    favoritesCursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0
            );
            listFavorites.setAdapter(favoritesAdapter);
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        }

        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINK_NUMBER, (int)id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        ListView listFavorites = findViewById(R.id.list_favorites);
        try {
            SQLiteOpenHelper starbuzzDataBaseHelper = new StarbuzzDataBaseHelper(this);
            db = starbuzzDataBaseHelper.getReadableDatabase();
            Cursor newFavoritesCursor = db.query(
                    "DRINK",
                    new String[] {"_id", "NAME"},
                    "FAVORITE = 1",
                    null,
                    null,
                    null,
                    null
            );
            CursorAdapter favoritesAdapter = (CursorAdapter) listFavorites.getAdapter();
            favoritesAdapter.changeCursor(newFavoritesCursor); // Change the cursor in cursor adapter to the new one
            favoritesCursor = newFavoritesCursor;              // Change the cursor to the new one
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        favoritesCursor.close();
        db.close();
    }
}