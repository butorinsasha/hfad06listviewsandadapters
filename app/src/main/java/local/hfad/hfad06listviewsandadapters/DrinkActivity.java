package local.hfad.hfad06listviewsandadapters;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends Activity {

    public static final String EXTRA_DRINK_NUMBER = "drinkNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the intent
        int drinkNumber = (int) getIntent().getExtras().get(EXTRA_DRINK_NUMBER);
        Drink drink = Drink.drinksArray[drinkNumber];

        //Populate the drink image
        ImageView drinkImageView = findViewById(R.id.photo);
        drinkImageView.setImageResource(drink.getImageResourceId());
        drinkImageView.setContentDescription(drink.getName());

        //Populate the drink name
        TextView drinkNameTextView = findViewById(R.id.name);
        drinkNameTextView.setText(drink.getName());

        //Populate the drink description
        TextView drinkDescriptionTextView = findViewById(R.id.description);
        drinkDescriptionTextView.setText(drink.getDescription());
    }
}