package com.sec.resourceparse;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;

public class Manager {

    public static void main(String [] args) {

        logToFile();

        //resources.arsc file path
        String resPath = "resources.arsc";
        FileInputStream ins = null;
        ByteArrayOutputStream ous = null;
        ByteBuffer byteBuffer = null;
        try {
            ins = new FileInputStream(resPath);
            int length = ins.available();
            System.out.println("input file length " + length);
            byteBuffer = ByteBuffer.allocateDirect(length);
            byte [] data = new byte[length];
            ins.read(data);
            byteBuffer.put(data);
            byteBuffer.position(0);
            com.sec.resourceparse.ByteBuffer nByteBuffer = new com.sec.resourceparse.ByteBuffer(byteBuffer);
            ParseUtils.parseRes(nByteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void logToFile() {
        try {
            PrintStream printStream = new PrintStream(new FileOutputStream("res_parse_output.txt", false));
            System.setOut(printStream);
        } catch (Exception e) {
            System.out.println("!!!!logToFile failed");
            e.printStackTrace();
        }
    }
}
