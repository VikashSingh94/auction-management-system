package com.hashmap.service;

import java.util.Timer;
import java.util.TimerTask;
//
//public class TimerService {
//
//    final private Timer timer;
//    private int secondsLeft;
//    Listener listener;
//
//    public TimerService(int seconds, final UUID auctionID) {
//        timer = new Timer();
//
//        secondsLeft =  seconds;
//        // Decrease seconds left every 1 second.
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                secondsLeft--;
//                if (secondsLeft == 0) {
//                    timer.cancel();
//
//                }
//            }
//        }, 0, 1000);
//    }
//
//    public int getSecondsLeft() {
//        return secondsLeft;
//    }
//}


public class TimerService {

    final private Timer timer;
    private int secondsLeft;
    private Listener listener; // listener field


    public TimerService(int seconds, Listener listener)
    {
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
                    listener.onEvent();
                }
            }
        }, 0, 1000);
    }



    public int getSecondsLeft() {
        return secondsLeft;
    }

}
