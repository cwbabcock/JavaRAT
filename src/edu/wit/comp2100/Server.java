package edu.wit.comp2100;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Hashtable;

public class Server {

    //using hash table in case we use a constant thread to search for clients
    private Hashtable<String, Client> clients = new Hashtable<String, Client>();

    /*
    Take user input of a full string as user input
    parse as a string[] of arguments to parseCommand()
     */
    public void command(String args){
        //TODO Implement this method
    }

    /*
    perform actions based on each section of the command input

    valid first arguments (args[0]) are included in the switch/case block
     */
    private void parseCommand(String[] args){
        //TODO implement this method
    }

    /*
    start a thread to start searching for network clients/bootstrappers
     */
    private boolean startNetworkScan(){
        //TODO implement this method
        return false;
    }

    /*
    stop thread for searching network clients/bootstrappers
     */
    private boolean stopNetworkScan(){
        //TODO implement this method
        return false;
    }

    private boolean createClient(Inet4Address client_IP) throws UnknownHostException { //TODO handle exception
        Client newClient = new Client(client_IP);
        clients.put(newClient.getClientName(), newClient);
        return true;
    }


}
