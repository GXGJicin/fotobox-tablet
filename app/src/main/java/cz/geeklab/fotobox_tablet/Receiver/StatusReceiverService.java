package cz.geeklab.fotobox_tablet.Receiver;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;

import cz.geeklab.fotobox_tablet.apps.StatusButton;
import cz.geeklab.fotobox_tablet.socket.SocketClientTool;

/**
 * Created by Jaroslav on 20. 6. 2015.
 */
public class StatusReceiverService extends Service{

        Timer timer = new Timer();
        MyTimerTask timerTask;
        ResultReceiver resultReceiver;

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {

            resultReceiver = intent.getParcelableExtra("receiver");

            timerTask = new MyTimerTask();
            timer.scheduleAtFixedRate(timerTask, 250, 250);
            return START_STICKY;
        }
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }
        @Override
        public void onDestroy() {
            super.onDestroy();
            timer.cancel();
            Bundle bundle = new Bundle();
            bundle.putString("end", "Timer Stopped....");
            resultReceiver.send(200, bundle);
        }

        class MyTimerTask extends TimerTask
        {
            public MyTimerTask() {
                Bundle bundle = new Bundle();
                bundle.putString("start", "Timer Started....");
                resultReceiver.send(100, bundle);
            }
            @Override
            public void run() {
                StatusButton result = SocketClientTool.getStatusButton("192.168.15.108", 4322);
                Bundle bundle = new Bundle();
                bundle.putString("status", result.getState().toString());
                resultReceiver.send(1050, bundle);
            }
        }
    }
