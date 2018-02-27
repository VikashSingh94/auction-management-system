package com.hashmap.service;

import java.util.Timer;
import java.util.TimerTask;

class TimerService {

    final private Timer timer;
    private int secondsLeft;
    private Listener listener; // listener field


    private TimerService(int seconds, Listener listener) {
        this.timer = new Timer();
        this.secondsLeft = seconds;
        this.listener = listener;

        // Decrease seconds left every 1 second.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                secondsLeft--;
                if (secondsLeft == 0) {
                    timer.cancel();
                    listener.onAuctionEnd();
                    listener.paymentProcess();
                }
            }
        }, 0, 1000);
    }


}
