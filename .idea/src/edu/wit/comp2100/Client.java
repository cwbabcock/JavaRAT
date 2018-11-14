package edu.wit.comp2100;

import java.io.File;
import java.net.Inet4Address;

public class Client {

    //class variables
    private String machineName;
    private Inet4Address Server_IP;
    private Inet4Address RAT_IP;
    private boolean isActive;
    private File lastRecievedFile;

    Client(){
        this.Server_IP = Inet4Address.getLocalHost(); //TODO handle exception
    }

    Client(Inet4Address client_IP){
        this();
        this.RAT_IP = client_IP;
    }


}
