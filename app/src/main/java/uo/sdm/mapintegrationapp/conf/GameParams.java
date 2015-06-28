package uo.sdm.mapintegrationapp.conf;


import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Hans on 27/06/2015.
 */
public final class GameParams {
    public static final String LOG_TAG = "GameParams";

    private static GameParams instance;

    private static Properties properties;

    private GameParams(Context context) {
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open("game.conf");
            properties = new Properties();
            properties.load(inputStream);
            Log.w(LOG_TAG, "Conf property file loaded.");
        } catch (IOException e) {
            Log.w(LOG_TAG, "Failed to open conf property file.");
        }
    }

    public static GameParams getInstance(Context context) {
        if (instance == null)
            instance = new GameParams(context);
        return instance;
    }

    public int getProperty(String name) {
        return Integer.parseInt(properties.getProperty(name));
    }
}
