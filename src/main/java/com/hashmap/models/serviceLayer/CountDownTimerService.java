package com.hashmap.models.serviceLayer;


import com.hashmap.models.dataAccessLayer.DataAccessLayer;
import com.hashmap.models.dataAccessLayer.InMemoryDAO;

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
                    DataAccessLayer dataAccessLayer = new InMemoryDAO();
                   dataAccessLayer.updateIsAuctionOpen(auctionID, false);
                }
            }
        }, 0, 1000);
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }
}