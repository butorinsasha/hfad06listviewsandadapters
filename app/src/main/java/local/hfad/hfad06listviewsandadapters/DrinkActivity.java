package local.hfad.hfad06listviewsandadapters;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {

    public static final String EXTRA_DRINK_NUMBER = "drinkNumber";
    public int drinkNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the intent
        drinkNumber = (int) getIntent().getExtras().get(EXTRA_DRINK_NUMBER);

        //Create db and cursor
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            SQLiteOpenHelper startbuzzDatabaseHelper = new StarbuzzDataBaseHelper(this);
            db = startbuzzDatabaseHelper.getReadableDatabase();
            //Create a cursor
            cursor = db.query(
                    "DRINK",                                                                // SELECT ... FROM DRINK
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},   // ...NAME, DESCRIPTION, IMAGE_RESOURCE_ID, FAVORITE...
                    "_id = ?",                                                              // WHERE _id = ...
                    new String[]{Integer.toString(drinkNumber)},                            // ... drinkNumber
                    null,
                    null,
                    null
            );
            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {
                //Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int imageResourceId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) == 1); // 1-true, 0-false

                //Populate the drink name
                TextView drinkNameTextView = findViewById(R.id.name);
                drinkNameTextView.setText(nameText);

                //Populate the drink description
                TextView drinkDescriptionTextView = findViewById(R.id.description);
                drinkDescriptionTextView.setText(descriptionText);

                //Populate the drink image
                ImageView drinkImageView = findViewById(R.id.photo);
                drinkImageView.setImageResource(imageResourceId);
                drinkImageView.setContentDescription(nameText);

                CheckBox favorite = findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);
            }
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        } finally {
            assert cursor != null;
            cursor.close();
            db.close();
        }
    }

    public void onFavoriteClicked(View view) {
        Toast.makeText(this, "drinkNumber = " + drinkNumber, Toast.LENGTH_SHORT).show();

//        CheckBox favoriteCheckBox = findViewById(R.id.favorite);
        CheckBox favoriteCheckBox = (CheckBox) view;        // Try another way of getting favoriteCheckBox

        ContentValues favoriteCheckBoxValue = new ContentValues();
        favoriteCheckBoxValue.put("FAVORITE", favoriteCheckBox.isChecked());

        // Create db again because we always close it after onCreate() finished
        SQLiteDatabase db = null;
        try {
            SQLiteOpenHelper startbuzzDatabaseHelper = new StarbuzzDataBaseHelper(this);
            db = startbuzzDatabaseHelper.getReadableDatabase();

            db.update(
                    "DRINK",
                    favoriteCheckBoxValue,
                    "_id = ?",
                    new String[] {Integer.toString(drinkNumber)}
            );

        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        } finally {
            if (db != null ) db.close();
        }
    }
}