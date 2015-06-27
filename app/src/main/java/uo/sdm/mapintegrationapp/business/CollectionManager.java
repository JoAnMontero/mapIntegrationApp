package uo.sdm.mapintegrationapp.business;

import android.app.Activity;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import uo.sdm.mapintegrationapp.model.Collectible;
import uo.sdm.mapintegrationapp.persistence.CollectibleDataSource;

/**
 * Created by Adrian on 26/06/2015.
 */
public class CollectionManager {

    private Activity activity;
    private CollectibleDataSource ds;

    public CollectionManager(Activity activity) {
        this.activity = activity;
        ds = new CollectibleDataSource(activity);
    }

    public List<Collectible> getOwnCollectibles(){
        ds.open();
        List<Collectible> list = ds.listCollectibles();
        ds.close();
        return list;
    }
    public void generateCollection(){
        try {
            Properties props = new Properties();
            AssetManager asset = activity.getAssets();
            InputStream inputStream = asset.open("cardsinfo.properties");
            props.load(inputStream);
            Integer type = new Random().nextInt(100)+1;
            String[] str = props.getProperty(type.toString()).split("_");
            String name = str[0];
            String category = str[1];
            Integer factor = Integer.parseInt(str[2]);
            if(new Random().nextInt(100)+1 >= factor){
                ds.open();
                ds.addCollectible(new Collectible(type,name,category,1));
                ds.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
