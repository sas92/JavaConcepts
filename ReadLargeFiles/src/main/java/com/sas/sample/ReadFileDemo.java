package com.sas.sample;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * Reference: https://www.baeldung.com/java-read-lines-large-file
 * https://howtodoinjava.com/java7/nio/3-ways-to-read-files-using-java-nio/
 */

public class ReadFileDemo {
    private final static String FILE_NAME = "D:/Softwares/node-v10.16.3-x64.msi";       // Large file
    //private final static String FILE_NAME = "C:/Users/Saswata_Adhya/Desktop/Interview questions/Broadridge/FAQs.TXT";          //Small file
    private static long initialUsage, endUsage, initialTime, endTime;

    // Slower
    private static void readFileUsingScanner() {
        initialCapture();
        try (
                Scanner scanner = new Scanner(new FileInputStream(FILE_NAME));
        ) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            endCapture();
        }
    }

    // Faster
    private static void readFileUsingLineIterator() {
        initialCapture();
        File file = new File(FILE_NAME);
        try (
                LineIterator lineIterator = FileUtils.lineIterator(file);
        ) {
            while (lineIterator.hasNext()) {
                String line = lineIterator.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            endCapture();
        }
    }

    // Fastest
    private static void readFileUsingNio() {
        initialCapture();
        try (
                RandomAccessFile accessFile = new RandomAccessFile(FILE_NAME, "r");
                FileChannel fileChannel = accessFile.getChannel();
        ) {
            long fileSize = fileChannel.size();     // long fileSize = 1024;
            ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
            fileChannel.read(buffer);
            buffer.flip();
            for (int i = 0; i < fileSize; i++) {
                char character = (char) buffer.get();
            }
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            endCapture();
        }
    }

    private static long checkMemoryUsage() {
        long memoryUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
        System.out.println("Memory used: " + memoryUsed + " MB");
        return memoryUsed;
    }

    private static void initialCapture() {
        initialUsage = checkMemoryUsage();
        initialTime = System.currentTimeMillis();
    }

    private static void endCapture() {
        endTime = System.currentTimeMillis();
        endUsage = checkMemoryUsage();
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1 readFileUsingScanner\n" +
                "2 readFileUsingLineIterator\n" +
                "3 readFileUsingNio\n" +
                "Enter your choice: ");
        int response = scanner.nextInt();
        System.out.println();

        switch (response) {
            case 1:
                readFileUsingScanner();
                System.out.println("* readFileUsingScanner() used: " + (endUsage - initialUsage) + " MB\n" +
                        "Time taken: " + (endTime - initialTime) + "\n");
                break;

            case 2:
                readFileUsingLineIterator();
                System.out.println("* readFileUsingLineIterator() used: " + (endUsage - initialUsage) + " MB\n" +
                        "Time taken: " + (endTime - initialTime));
                break;

            case 3:
                readFileUsingNio();
                System.out.println("* readFileUsingNio() used: " + (endUsage - initialUsage) + " MB\n" +
                        "Time taken: " + (endTime - initialTime));
                break;
        }
    }
}

