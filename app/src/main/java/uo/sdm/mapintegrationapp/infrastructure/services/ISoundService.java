package uo.sdm.mapintegrationapp.infrastructure.services;

import android.content.Context;

/**
 * Created by Adrian on 28/06/2015.
 */
public interface ISoundService {

    public void start(Context context, Integer uri,Boolean loop,String key);
    public void stop(String key);
    public void remove(String key);
    public boolean isInterrupted(String key);
    public void restart(String key);
    public boolean isExists(String key);
}
