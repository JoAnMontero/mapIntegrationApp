package uo.sdm.mapintegrationapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.model.types.RuinType;
import uo.sdm.mapintegrationapp.persistence.RuinsDataSource;


public class MainMenu extends ActionBarActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // TODO move to a debug layout
        RuinsDataSource dataSource = new RuinsDataSource(this);
        textView = (TextView) findViewById(R.id.main_dbcount_label);
        dataSource.open();
        textView.setText(String.valueOf(dataSource.getAllRuins().size()));
        dataSource.close();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    public void launchMapActivity(View view) {
        final Intent mIntent = new Intent(MainMenu.this, MapActivity.class);
        startActivity(mIntent);
    }

    // TODO remove once the db initial data is implemented
    public void fillDB(View view) {
        RuinsDataSource dataSource = new RuinsDataSource(this);
        dataSource.open();
        dataSource.createRuin(43.537686, -5.687766, RuinType.Pyramid, 0);
        dataSource.createRuin(43.537786, -5.687766, RuinType.Pyramid, 0);
        dataSource.createRuin(43.537886, -5.687766, RuinType.Temple, 0);
        dataSource.createRuin(43.537986, -5.687766, RuinType.Temple, 0);
        dataSource.createRuin(43.538086, -5.687766, RuinType.Fortress, 0);
        dataSource.createRuin(43.538186, -5.687766, RuinType.Fortress, 0);
        dataSource.close();

        dataSource.open();
        textView.setText(String.valueOf(dataSource.getAllRuins().size()));
        dataSource.close();
    }

    // TODO move to a debug layout
    public void clearDB(View view) {
        RuinsDataSource dataSource = new RuinsDataSource(this);
        dataSource.open();
        dataSource.deleteAllRuins();
        dataSource.close();


        dataSource.open();
        textView.setText(String.valueOf(dataSource.getAllRuins().size()));
        dataSource.close();
    }
}
