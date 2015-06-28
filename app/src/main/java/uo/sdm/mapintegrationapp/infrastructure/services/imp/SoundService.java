package uo.sdm.mapintegrationapp.infrastructure.services.imp;

import android.content.Context;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uo.sdm.mapintegrationapp.R;
import uo.sdm.mapintegrationapp.infrastructure.services.ISoundService;

/**
 * Created by Adrian on 28/06/2015.
 */
public class SoundService implements ISoundService {

    private Map<String,Thread> soundMap = new HashMap<>();

    @Override
    public void start(Context context, Integer uri,Boolean loop, String key){
        if(loop == false)
            createNewThread(context,uri,loop);
        else {
            if(soundMap.containsKey(key) && soundMap.get(key) != null){
                soundMap.get(key).interrupt();
            }
            soundMap.put(key,createNewThread(context, uri, loop));
            soundMap.get(key).run();
        }
    }
    @Override
    public void restart(String key){
        soundMap.get(key).run();
    }

    @Override
    public void stop(String key){
        soundMap.get(key).interrupt();
    }
    @Override
    public void remove(String key){
        soundMap.get(key).interrupt();
        soundMap.remove(key);
    }
    @Override
    public boolean isInterrupted(String key){
        return soundMap.get(key).isInterrupted();
    }
    @Override
    public boolean isExists(String key){
        return soundMap.containsKey(key);
    }

    private Thread createNewThread(final Context context, final Integer uri,final boolean loop){
        return new Thread() {
            private MediaPlayer mp = MediaPlayer.create(context,uri);

            @Override
            public synchronized void start() {
                if(!mp.isPlaying())
                    mp.start();
                super.start();
            }

            @Override
            public void run() {
                if (mp != null) {
                    mp.setLooping(loop);
                    mp.start();
                }
            }
            @Override
            public void interrupt() {
                if(mp != null)
                    mp.stop();
                mp = null;
                super.interrupt();
            }
        };
    }


}
