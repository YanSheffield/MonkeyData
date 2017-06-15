package com.example.geyan.monkeydata;

/**
 * Created by geyan on 15/06/2017.
 */

public class WriteLogcat implements Runnable{

    String cmd;

    WriteLogcat(String cmd){
        this.cmd = cmd;
    }

    @Override
    public void run() {
        ProcessRunner.runProcess(false,cmd);
    }
}
