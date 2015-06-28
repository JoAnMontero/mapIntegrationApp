package uo.sdm.mapintegrationapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.infrastructure.factories.ServiceFactory;
import uo.sdm.mapintegrationapp.infrastructure.services.ISoundService;
import uo.sdm.mapintegrationapp.persistence.PlacesDataSource;


public class MainMenu extends ActionBarActivity {
    private static final String LOG_TAG = "MainActivity";

    private ISoundService soundService = ServiceFactory.soundService;
    private static String key_main_theme = "KEY_MAIN_THEM";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initSound();
        // TODO move to a debug layout
        PlacesDataSource dataSource = new PlacesDataSource(this);
        textView = (TextView) findViewById(R.id.main_dbcount_label);
        dataSource.open();
        textView.setText(String.valueOf(dataSource.getAllPlaces().size()));
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
        soundService.start(getApplicationContext(),R.raw.press_button,false,"");
        final Intent mIntent = new Intent(MainMenu.this, MapActivity.class);
        soundService.stop(key_main_theme);
        startActivity(mIntent);
    }

    public void launchCollectionActivity(View view){
        soundService.start(getApplicationContext(),R.raw.press_button,false,"");
        final Intent mIntent = new Intent(MainMenu.this,CollectionActivity.class);
        soundService.stop(key_main_theme);
        startActivity(mIntent);
    }

    // TODO move to a debug layout
    public void clearDB(View view) {
        PlacesDataSource dataSource = new PlacesDataSource(this);
        dataSource.open();
        dataSource.deleteAllPlaces();
        textView.setText(String.valueOf(dataSource.getAllPlaces().size()));
        dataSource.close();
    }
    private boolean music = false;
    private void initSound(){
        soundService.start(getApplicationContext(), R.raw.maintheme, true, key_main_theme);
    }

    @Override
    protected void onPause() {
        soundService.stop(key_main_theme);
        super.onPause();
    }
}
