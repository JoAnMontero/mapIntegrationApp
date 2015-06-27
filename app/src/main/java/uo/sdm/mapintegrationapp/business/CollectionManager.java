package uo.sdm.mapintegrationapp.business;

import android.app.Activity;

import java.util.List;

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
}
