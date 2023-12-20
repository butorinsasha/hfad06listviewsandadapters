package local.hfad.hfad06listviewsandadapters;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {

    public static final String EXTRA_DRINK_NUMBER = "drinkNumber";
    public int drinkNumber = 0;

    private class UpdateDrinkTask extends AsyncTask<Integer, Void, Boolean> {

        ContentValues favoriteDrinkContentValues = new ContentValues();

        @Override
        protected void onPreExecute(){
            CheckBox favoriteCheckBox = findViewById(R.id.favorite);
            favoriteDrinkContentValues.put("FAVORITE", favoriteCheckBox.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... integers) {
            SQLiteOpenHelper startbuzzDatabaseHelper = new StarbuzzDataBaseHelper(DrinkActivity.this);
            try (SQLiteDatabase db = startbuzzDatabaseHelper.getWritableDatabase()) {

                db.update(
                        "DRINK",
                        favoriteDrinkContentValues,
                        "_id = ?",
//                        new String[]{Integer.toString(drinkNumber)}
                        new String[]{Integer.toString(integers[0])}
                );
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }

//        @Override
//        protected void onProgressUpdate() {
//
//        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast.makeText(DrinkActivity.this, "Database unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

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
        new UpdateDrinkTask().execute(drinkNumber);
    }
}