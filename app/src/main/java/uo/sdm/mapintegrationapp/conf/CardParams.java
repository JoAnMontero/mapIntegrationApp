package uo.sdm.mapintegrationapp.conf;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Adrian on 28/06/2015.
 */
public class CardParams {
    public static final String LOG_TAG = "CardParams";

    private static CardParams instance;

    private static Properties properties;

    private CardParams(Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open("cardsinfo.properties");
            properties = new Properties();
            properties.load(inputStream);
            Log.w(LOG_TAG, "Conf property file loaded.");
        } catch (IOException e) {
            Log.w(LOG_TAG, "Failed to open conf property file.");
        }
    }

    public static CardParams getInstance(Context context) {
        if (instance == null)
            instance = new CardParams(context);
        return instance;
    }

    public String[] getProperty(String type) {
        return properties.getProperty(type).split("_");
    }
}
