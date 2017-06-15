package com.example.geyan.monkeydata;

/**
 * Created by geyan on 15/06/2017.
 */

public class MonkeyRunning implements Runnable{

    String cmd = null;

    MonkeyRunning(String cmd){
        this.cmd = cmd;
    }
    @Override
    public void run() {
        ProcessRunner.runProcess(false,cmd);
    }
}
