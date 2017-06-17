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

public class TextReader {
    private String filePath = "/Users/geyan/Desktop/log/log4.txt";
    private Set<String> coveredActivities = new HashSet<>();
    private String coveredActivity;
    private String regexPattern = "^I.+Displayed";
    private Matcher matcher;
    private Pattern pattern;

    public void readLogFile() {
        pattern = Pattern.compile(regexPattern);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = bufferedReader.readLine();
            while (line != null) {
                line = bufferedReader.readLine();
                if (line!=null){
                    filterUniqueActivityName(line);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String coveredActivity:coveredActivities){
            System.out.println("covered Activity: "+coveredActivity);
        }
    }

    public void filterUniqueActivityName(String logLine) {
        matcher = pattern.matcher(logLine);
        if (matcher.find()){
            coveredActivity = logLine.substring(logLine.lastIndexOf("/")+1,logLine.lastIndexOf(":"));
            coveredActivities.add(coveredActivity);
        }
    }
}
