package uo.sdm.mapintegrationapp.ui;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.infrastructure.factories.ServiceFactory;
import uo.sdm.mapintegrationapp.infrastructure.services.ISoundService;


public class MainMenu extends Activity {
    private static final String LOG_TAG = "MainActivity";

    private ISoundService soundService = ServiceFactory.soundService;
    private static String key_main_theme = "KEY_MAIN_THEME";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        initSound();
    }

    public void launchMapActivity(View view) {
        soundService.start(getApplicationContext(), R.raw.press_button, false, "");
        final Intent mIntent = new Intent(MainMenu.this, MapActivity.class);
        soundService.stop(key_main_theme);
        startActivity(mIntent);
    }

    public void launchCollectionActivity(View view) {
        soundService.start(getApplicationContext(), R.raw.press_button, false, "");
        final Intent mIntent = new Intent(MainMenu.this, CollectionActivity.class);
        soundService.stop(key_main_theme);
        startActivity(mIntent);
    }

    private boolean music = false;

    private void initSound() {
        soundService.start(getApplicationContext(), R.raw.maintheme, true, key_main_theme);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        soundService.start(getApplicationContext(), R.raw.maintheme, true, key_main_theme);
    }

    @Override
    protected void onPause() {
        soundService.stop(key_main_theme);
        super.onPause();
    }

    @Override
    protected void onResume() {
        soundService.start(getApplicationContext(), R.raw.maintheme, true, key_main_theme);
        super.onResume();
    }
}
