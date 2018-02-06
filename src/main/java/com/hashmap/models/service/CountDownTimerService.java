package com.hashmap.models.service;


import com.hashmap.models.dao.InMemoryDoa;
import com.hashmap.models.dao.InMemoryDAOImpl;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class CountDownTimerService {

    final private Timer timer;
    private int secondsLeft;

    public CountDownTimerService(int seconds, final UUID auctionID) {
        timer = new Timer();
        secondsLeft =  seconds;
        // Decrease seconds left every 1 second.
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                secondsLeft--;
                if (secondsLeft == 0) {
                    timer.cancel();
                    InMemoryDoa dataAccessLayer = new InMemoryDAOImpl();
                   dataAccessLayer.updateIsAuctionOpen(auctionID, false);
                }
            }
        }, 0, 1000);
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}