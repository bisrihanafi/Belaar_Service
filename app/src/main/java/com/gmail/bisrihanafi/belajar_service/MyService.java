package com.gmail.bisrihanafi.belajar_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service {
    MediaPlayer mediaPlayar;
    public MyService() {
    }
    //TODO 1 : bagian konstruktor yang mendeklarasikan media player dari sumberdaya dan mengaturnya sehingga dia looping
    @Override
    public void onCreate() {
        mediaPlayar=MediaPlayer.create(this, R.raw.music);
        mediaPlayar.setLooping(true);
    }
    //TODO 2 : menghetikan music dengan stop
    @Override
    public void onDestroy() {
        mediaPlayar.stop();
    }
    //TODO 3 : memulai pemutaran music dengan start
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayar.start();
        return  START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}
