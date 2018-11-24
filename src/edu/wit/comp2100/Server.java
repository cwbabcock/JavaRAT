package edu.wit.comp2100;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;

public class Server {

    //using hash table in case we use a constant thread to search for clients
    private static Hashtable<String, Client> clients = new Hashtable<String, Client>();

    //queues commands made by a user so the server doesn't just hang while actions are done.
    private ArrayBlockingQueue<String[]> commandQueue = new ArrayBlockingQueue<String[]>(10);

    public static void main(String[] args){
        start();
    }

    /*
    - run network scanner
    -
    - start taking user input
     */
    private static void start(){
        System.out.println("Scanning network.... \n\n");
        if (!startNetworkScan()){
            System.out.println("FATAL ERR: Network Scan Failed");
            System.exit(0);
        }
        System.out.println("Network scan finished!");

        //at this point, all clients should be listed included in the hash table.

        //list clients for user initially
        System.out.println("\n\n Available Clients: ");
        for (String s : clients.keySet()){
            System.out.println(s + ", " + clients.get(s).getRAT_IP().getHostAddress());
        }


    }

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

    private void parseServerCommand(String[] args){

    }

    /*
    Scan's network once for now looking for clients with port 1337 opened.
    if found, adds them to
     */
    private static boolean startNetworkScan(){
        //TODO fix hardcoded IP address (make it find "192.168.0." on its own

        try {
            ArrayList<String> IPs = portScanner.getBootstrapperIPs();

           //traverse list of valid IP's and add to clients
           for (String ip : IPs){

               InetAddress a = Inet4Address.getByName(ip);
               Client n = new Client(a);

               clients.put(n.getClientName(), n);
               System.out.println(n.getClientName() + " available.");

           }
        } catch (IOException e){
            return false;
        }
        return true;
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