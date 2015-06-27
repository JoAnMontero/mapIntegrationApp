package uo.sdm.mapintegrationapp.ui;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.List;
import java.util.Properties;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.business.CollectionManager;
import uo.sdm.mapintegrationapp.model.Collectible;

public class CollectionActivity extends ActionBarActivity {

    private CollectionManager collectionManager;

    public void showCollection(List<Collectible> elements){
        String path;

        LinearLayout lL = (LinearLayout)findViewById(R.id.activity_collection);
        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        for(Collectible element : elements){
            path = "drawable/"+element.getType();
            int id = getResources().getIdentifier(path,null,null);
            ImageButton btn = new ImageButton(this);
            btn.setImageDrawable(getResources().getDrawable(id));
            lL.addView(btn,lP);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        collectionManager = new CollectionManager(this);
        showCollection(collectionManager.getOwnCollectibles());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_collection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
