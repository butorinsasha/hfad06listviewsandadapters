package local.hfad.hfad06listviewsandadapters;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {

    public static final String EXTRA_DRINK_NUMBER = "drinkNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the intent
        int drinkNumber = (int) getIntent().getExtras().get(EXTRA_DRINK_NUMBER);
        Drink drink = Drink.drinksArray[drinkNumber];

        //Create a cursor
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            SQLiteOpenHelper startbuzzDatabaseHelper = new StarbuzzDataBaseHelper(this);
            db = startbuzzDatabaseHelper.getReadableDatabase();
            //Create a cursor
            cursor = db.query(
                    "DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkNumber)},
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

                Toast.makeText(this, "Database in action", Toast.LENGTH_SHORT).show();
            }
        } catch (SQLiteException e) {
            Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT).show();
        } finally {
            cursor.close();
            db.close();
        }

        //Populate the drink name
//        TextView drinkNameTextView = findViewById(R.id.name);
//        drinkNameTextView.setText(drink.getName());

        //Populate the drink description
//        TextView drinkDescriptionTextView = findViewById(R.id.description);
//        drinkDescriptionTextView.setText(drink.getDescription());

        //Populate the drink image
//        ImageView drinkImageView = findViewById(R.id.photo);
//        drinkImageView.setImageResource(drink.getImageResourceId());
//        drinkImageView.setContentDescription(drink.getName());

    }
}