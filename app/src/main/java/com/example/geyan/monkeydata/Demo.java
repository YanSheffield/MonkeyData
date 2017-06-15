package com.example.geyan.monkeydata;

import java.io.InputStream;
import java.util.function.Consumer;

/**
 * Created by geyan on 15/06/2017.
 */

public class Demo implements Runnable{
    boolean isWIndows = System.getProperty("os.name").toLowerCase().startsWith("windows");

    private InputStream inputStream;
    @SuppressWarnings("Since15")
    private Consumer<String> consumer;
    @Override
    public void run() {

    }
}
