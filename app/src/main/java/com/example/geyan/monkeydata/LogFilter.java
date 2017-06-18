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

    private String regexForActivity = "^I.+Displayed";
    private String regexForFatalError = ":\\s*\\bat\\b";

    private Matcher matcherForActivity;
    private Matcher matcherForFatalError;

    private Pattern patternForActivity;
    private Pattern patternForFatalError;

    private boolean isEnterFatalInfo = false;

    public void readLogFile() {
        patternForActivity = Pattern.compile(regexForActivity);
        patternForFatalError = Pattern.compile(regexForFatalError);

        try {
            String filePath = "/Users/geyan/AndroidStudioProjects/MonkeyData/log/with-d2.txt";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = bufferedReader.readLine();
            while (line != null) {
                line = bufferedReader.readLine();
                if (line!=null){
                    filterUniqueActivityName(line);
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
        matcherForActivity = patternForActivity.matcher(logLine);
        if (matcherForActivity.find()){
            coveredActivity = logLine.substring(logLine.lastIndexOf("/")+1,logLine.lastIndexOf(":"));
            coveredActivities.add(coveredActivity);
        }
    }

    public void filterUniqueFatalException(String fatalInfo){
        if (fatalInfo.contains("FATAL EXCEPTION") || isEnterFatalInfo){
            isEnterFatalInfo = true;
            matcherForFatalError = patternForFatalError.matcher(fatalInfo);
            if(matcherForFatalError.find()){
                foundFatalErrors.add(fatalInfo.substring(fatalInfo.indexOf("at ")+3,fatalInfo.length()));
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
