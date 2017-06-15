package com.example.geyan.monkeydata;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by geyan on 15/06/2017.
 */

public class TextReader {
    private String filePath = "/Users/geyan/Desktop/log/log5.txt";
    private List<String> textData = new ArrayList<>();
    private Set<String> coveredActivities = new HashSet<>();

    public void read() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line = bufferedReader.readLine();
            while (line != null) {
                line = bufferedReader.readLine();
                textData.add(line);
                System.out.println(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        filterStrings();
        for (String coveredActivity:coveredActivities){
            System.out.println("covered Activity: "+coveredActivity);
        }
    }

    public void filterStrings() {
        String singleLine;
        for (int i = 0; i < textData.size(); i++) {
            singleLine = textData.get(i);
            if (singleLine != null) {
                if (singleLine.substring(0, 1).equals("I")) {
                    String verifyDisplay[] = textData.get(i).split(":");
                    if (verifyDisplay[1].startsWith(" Displayed")) {
                        String splitBackslash[] = verifyDisplay[1].split("/");
                        coveredActivities.add(splitBackslash[1]);
                    }
                }
            }
        }
    }
}
