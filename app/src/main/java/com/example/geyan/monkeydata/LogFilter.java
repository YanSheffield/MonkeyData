package com.example.geyan.monkeydata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by geyan on 15/06/2017.
 */

public class LogFilter{

    private Set<String> coveredActivities = new HashSet<>();
    private Set<String> foundFatalErrors = new HashSet<>();

    private String coveredActivity;

    private String regexForActivity = "^I.+\\bDisplayed\\b";
    private String regexForFatalError = ":\\s*\\bat\\b";

    private Matcher matcherForActivity;
    private Matcher matcherForFatalError;

    private Pattern patternForActivity;
    private Pattern patternForFatalError;

    private boolean isEnterFatalInfo = false;

    public void readLogFile() {

        try {
            //this text file is used to store log info.Please change this path based on your machine
            //but this path must be same with variable "locationLogFile" in MainMethod.java
            String filePath = "/Users/geyan/AndroidStudioProjects/MonkeyData/log/with-d5.txt";
            //read this file line by line
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = bufferedReader.readLine();
            while (line != null) {
                line = bufferedReader.readLine();
                if (line!=null){
                    //obtain covered activities
                    filterUniqueActivityName(line);
                    //obtain unique fatal error
                    filterUniqueFatalException(line);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reportResult();
    }

    public void filterUniqueActivityName(String logLine) {
        //regex aims to filter the statement that starts with I and contain "Display"
        patternForActivity = Pattern.compile(regexForActivity);
        matcherForActivity = patternForActivity.matcher(logLine);
        if (matcherForActivity.find()){
            //obtain activity name
            coveredActivity = logLine.substring(logLine.lastIndexOf("/")+1,logLine.lastIndexOf(":"));
            coveredActivities.add(coveredActivity);
        }
    }

    public void filterUniqueFatalException(String fatalInfo){
        // "FATAL EXCEPTION need to be contained in this statement"
        if (fatalInfo.contains("FATAL EXCEPTION") || isEnterFatalInfo){
            isEnterFatalInfo = true;
            //"at" must follow by a colon
            patternForFatalError = Pattern.compile(regexForFatalError);
            matcherForFatalError = patternForFatalError.matcher(fatalInfo);
            if(matcherForFatalError.find()){
                foundFatalErrors.add(fatalInfo.substring(fatalInfo.indexOf("at ")+3,fatalInfo.length()));
                //Only the first statement containing "at" is present
                isEnterFatalInfo = false;
            }
        }
    }

    public void reportResult(){
        System.out.println("Monkey covers "+coveredActivities.size()+" activities:");
        for (String coveredActivity:coveredActivities){
            System.out.println("covered Activity: "+coveredActivity);
        }
        if (foundFatalErrors.size()==0){
            System.out.println("No Fatal Error");
        }else {
            for (String foundErrors:foundFatalErrors){
                System.out.println("fatal exception emerges: "+foundErrors);
            }
        }
    }
}
