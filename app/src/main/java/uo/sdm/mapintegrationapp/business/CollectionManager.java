package uo.sdm.mapintegrationapp.business;

import android.app.Activity;

import java.util.List;
import java.util.Random;

import uo.sdm.mapintegrationapp.conf.CardParams;
import uo.sdm.mapintegrationapp.model.Collectible;
import uo.sdm.mapintegrationapp.persistence.CollectibleDataSource;

/**
 * Created by Adrian on 26/06/2015.
 */
public class CollectionManager {

    private Activity activity;
    private CollectibleDataSource ds;
    private CardParams cardParams;

    public CollectionManager(Activity activity) {
        this.activity = activity;
        ds = new CollectibleDataSource(activity);
        cardParams = CardParams.getInstance(activity);
    }
    public Collectible getCollectibleByType(Integer type){
        ds.open();
        Collectible c = ds.getCollectibleByType(type);
        ds.close();
        return c;
    }
    public List<Collectible> getOwnCollectibles(){
        ds.open();
        List<Collectible> list = ds.listCollectibles();
        ds.close();
        return list;
    }
    public Collectible generateCollection(){
        Collectible c;
        Integer type=null;
        Integer factor;
        String[] str;
        String name="";
        String category="";
        Boolean generated = false;
        while (!generated){
            type = new Random().nextInt(100)+1;
            str = cardParams.getProperty(type.toString());
            name = str[0];
            category = str[1];
            factor = Integer.parseInt(str[2]);
            if(new Random().nextInt(100)+1 >= factor)
                generated = true;
        }
        ds.open();
        c = ds.getCollectibleByType(type);
        if(c == null)
            c = ds.addCollectible(new Collectible(type, name, category, 1));
        else {
            c.incrementAmount(1);
            ds.updateCollectible(c);
        }
        ds.close();
        return c;
    }
}
