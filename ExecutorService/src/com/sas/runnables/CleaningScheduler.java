package com.sas.runnables;

import java.io.File;

/**
 * Cleans old files in a folder
 */
public class CleaningScheduler implements Runnable {
    @Override
    public void run() {
        File folder = new File("C:\\Users\\Saswata_Adhya\\Desktop\\temp");
        File[] files = folder.listFiles();
        System.out.println(files.length + " files found!");
        for (File file : files) {
            System.out.println("File created " + (System.currentTimeMillis() - file.lastModified()) / 1000 / 60 + " minutes ago");
            if (System.currentTimeMillis() - file.lastModified() > 1 * 60 * 1000) {     // 1 minute
                System.out.println("File getting deleted: " + file.getName());
                //file.delete();
            }
        }
    }
}
