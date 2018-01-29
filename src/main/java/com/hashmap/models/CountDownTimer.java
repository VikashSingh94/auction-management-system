package com.hashmap.models;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class CountDownTimer{

    final private Timer timer;
    private int secondsLeft;

    public CountDownTimer(int seconds, final UUID auctionID) {
        timer = new Timer();
        secondsLeft =  seconds;
        // Decrease seconds left every 1 second.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                secondsLeft--;
                if (secondsLeft == 0) {
                    timer.cancel();
                    DataBase.updateIsAuctionOpen(auctionID,false);
                }
            }
        }, 0, 1000);
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}