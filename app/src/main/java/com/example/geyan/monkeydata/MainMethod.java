package com.example.geyan.monkeydata;

/**
 * Created by geyan on 15/06/2017.
 */

public class MainMethod {

    public static void main(String[] args){
//        TextReader textReader = new TextReader();
//        textReader.read();
//        MonkeyRunning monkeyRunning = new MonkeyRunning();
//        monkeyRunning.exeCommandLogcat();
        ProcessRunner.runProcess(false,"adb logcat -c");
        new Thread(new MonkeyRunning("adb -e shell monkey --ignore-crashes -p com.example.geyan.mp3player 1000")).start();

        new Thread(new WriteLogcat("adb logcat ActivityManager:I -b default -v brief *:E>log/log5.txt")).start();
//        List<String> result = ProcessRunner.runProcess(false,"adb logcat>log/log6.txt");

    }


}

