package local.hfad.hfad06listviewsandadapters;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DrinkCategoryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView drinksList = getListView();

        Log.i(this.getClass().getName(), "drinksList is " + drinksList);

        ArrayAdapter<Drink> drinkListAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Drink.drinks
        );

        drinksList.setAdapter(drinkListAdapter);
    }

    @Override
    protected void onListItemClick(ListView listView,
                                   View itemview,
                                   int position,
                                   long id) {
//        super.onListItemClick(listView, itemview, position, id);
        Intent intent = new Intent(this, DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINK_NUMBER, (int) id);
        startActivity(intent);
    }
}