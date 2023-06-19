package local.hfad.hfad06listviewsandadapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class TopLevelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);

        //Create an OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener =
                (AdapterView<?> parent,
                 View view,
                 int position,
                 long id) -> {
                if (position == 0) {
                    Intent intent = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);

                    Log.i(this.getClass().getName(), "item id position " + position);
                    Toast.makeText(TopLevelActivity.this, "item id position " + position, Toast.LENGTH_SHORT).show();

                    TopLevelActivity.this.startActivity(intent);
                }
        };

        //Add the listener to the list view
        ListView listView = findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);
    }
}