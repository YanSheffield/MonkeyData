package com.example.geyan.monkeydata;

/**
 * Created by geyan on 15/06/2017.
 */

public class MainMethod {

    public static void main(String[] args){
//        String monkeyRunningCmdInit = "adb -e shell monkey --ignore-crashes ";
//        String monkeyRunningCmdPackage = "-p com.example.geyan.mp3player ";
//        String monkeyRunningCmdEventCount = "5000";
//        ProcessRunner.runProcess(false,"adb logcat -c");
//        new Thread(new MonkeyRunner(monkeyRunningCmdInit+monkeyRunningCmdPackage+monkeyRunningCmdEventCount)).start();
//
//        String logcatCmdInit = "adb logcat ";
//        String logcatTagActivityMangerInfoLevel = "ActivityManager:I ";
//        String logcatFormat = "-b default ";
//        String logcatOtherTagErrorLevel = "*:E";
//        String locationLogFile = ">log/log6.txt";
//        new Thread(new WriteLogcat(logcatCmdInit+logcatTagActivityMangerInfoLevel+logcatFormat+logcatOtherTagErrorLevel+locationLogFile)).start();

        TextReader textReader = new TextReader();
        textReader.readLogFile();
    }


}

