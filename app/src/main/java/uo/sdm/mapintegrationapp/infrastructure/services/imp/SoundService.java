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

    private Map<String,PlayerThread> soundMap = new HashMap<>();

    @Override
    public void start(Context context, Integer uri,Boolean loop, String key){
        if(loop == false) {
            createNewThread(context, uri, loop).run();
        }else {
            if(soundMap.containsKey(key) && soundMap.get(key) != null){
                if(!soundMap.get(key).getMp().isPlaying())
                    soundMap.get(key).getMp().start();
            }else{
                soundMap.put(key,createNewThread(context, uri, loop));
                soundMap.get(key).run();
            }
            /*if(soundMap.containsKey(key) && soundMap.get(key) != null){
                if(soundMap.get(key).getState() == Thread.State.WAITING) {
                    soundMap.get(key).notify();
                }
                if(soundMap.get(key).getState() == Thread.State.TERMINATED){
                    soundMap.put(key,createNewThread(context, uri, loop));
                    soundMap.get(key).run();
                }
            }else{
                soundMap.put(key,createNewThread(context, uri, loop));
                soundMap.get(key).run();
            }*/
        }
    }
    @Override
    public void restart(String key){
        soundMap.get(key).getMp().seekTo(0);
    }

    @Override
    public void stop(String key){
        if(soundMap.get(key)!= null){
            soundMap.get(key).getMp().pause();
        }

    }
    @Override
    public void remove(String key){
        soundMap.get(key).interrupt();
        soundMap.remove(key);
    }
    @Override
    public boolean isWait(String key){
        return soundMap.get(key).getState() == Thread.State.WAITING;
    }
    @Override
    public boolean isExists(String key){
        return soundMap.containsKey(key);
    }

    private PlayerThread createNewThread(final Context context, final Integer uri,final boolean loop){
        return new PlayerThread(context, uri, loop);
    }

    public class PlayerThread extends Thread{
        MediaPlayer mp;
        Integer pos = 0;
        Context context;
        Integer uri;
        boolean loop;
        public PlayerThread(final Context context, final Integer uri,final boolean loop){
            this.context = context;
            this.uri = uri;
            this.loop = loop;
            mp = MediaPlayer.create(context,uri);
        }
        @Override
        public void run() {
            super.run();
            mp.start();
        }
        public MediaPlayer getMp(){
            return  mp;
        }


    }



}
