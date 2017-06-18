package com.example.geyan.monkeydata;

import java.util.Arrays;

/**
 * Created by marceloe on 04/01/17.
 */
public class ProcessRunner {
    private static int i = 0;;
    private static final String[] WIN_RUNTIME = { "cmd.exe", "/C" };
    private static final String[] OS_LINUX_RUNTIME = { "/bin/bash", "-l", "-c" };

    private ProcessRunner() {
    }

    @SuppressWarnings("Since15")
    private static <T> T[] concat(T[] first, T[] second) {
        T[] result;
        result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static void runProcess(boolean isWin, String... command) {
        String[] allCommand = null;
        try {
            if (isWin) {
                allCommand = concat(WIN_RUNTIME, command);
            } else {
                allCommand = concat(OS_LINUX_RUNTIME, command);
            }
            Process process = Runtime.getRuntime().exec(allCommand);
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
