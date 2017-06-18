package com.example.geyan.monkeydata;

/**
 * Created by geyan on 15/06/2017.
 */

public class MainMethod {

    public static void main(String[] args){

        ProcessRunner.runProcess(false,"adb logcat -c");

        String monkeyRunningCmdInit = "adb -e shell monkey --ignore-crashes ";
        String monkeyRunningCmdPackage = "-p com.example.geyan.mp3player ";
        String monkeyRunningCmdEventCount = "1000";
        ProcessRunner.runProcess(false,monkeyRunningCmdInit+monkeyRunningCmdPackage+monkeyRunningCmdEventCount);
        System.out.println("Monkey finished");

        String logcatCmdInit = "adb logcat -d ";
        String logcatTagActivityMangerInfoLevel = "ActivityManager:I ";
        String logcatFormat = "-v brief ";
        String logcatOtherTagErrorLevel = "*:E";
        String locationLogFile = ">log/with-d2.txt";
        ProcessRunner.runProcess(false,logcatCmdInit+logcatTagActivityMangerInfoLevel+logcatFormat+logcatOtherTagErrorLevel+locationLogFile);

        LogFilter textReader = new LogFilter();
        textReader.readLogFile();

    }
}

