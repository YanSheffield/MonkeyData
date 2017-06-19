package com.example.geyan.monkeydata;

/**
 * Created by geyan on 15/06/2017.
 */

public class MainMethod {

    public static void main(String[] args){
        //clear history before running new task
        ProcessRunner.runProcess(false,"adb logcat -c");

        //command for running monkey
        String monkeyRunningCmdInit = "adb -e shell monkey --ignore-crashes ";
        //please change this package to run different app
        String monkeyRunningCmdPackage = "-p com.example.geyan.mp3player ";
        String monkeyRunningCmdEventCount = "1000";
        ProcessRunner.runProcess(false,monkeyRunningCmdInit+monkeyRunningCmdPackage+monkeyRunningCmdEventCount);
        System.out.println("Monkey finished");

        //command for generating a text file for storing log information
        String logcatCmdInit = "adb logcat -d ";
        String logcatTagActivityMangerInfoLevel = "ActivityManager:I ";
        String logcatFormat = "-v brief ";
        String logcatOtherTagErrorLevel = "*:E";
        //please replace "with-d2.txt" with any name file you like
        String locationLogFile = ">log/with-d5.txt";
        ProcessRunner.runProcess(false,logcatCmdInit+logcatTagActivityMangerInfoLevel+logcatFormat+logcatOtherTagErrorLevel+locationLogFile);

        //extract the information from log file
        LogFilter textReader = new LogFilter();
        textReader.readLogFile();

    }
}

