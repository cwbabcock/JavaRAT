package edu.wit.comp2100;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Server {

    //using hash table in case we use a constant thread to search for clients
    private static Hashtable<String, Client> clients = new Hashtable<String, Client>();

    //queues commands made by a user so the server doesn't just hang while actions are done.
    private ArrayBlockingQueue<String[]> commandQueue = new ArrayBlockingQueue<String[]>(10);
    //we are going to use a string[] and boolean for now;
    private static String[] currentCommand;
    private static boolean commandFinished;

    public static void main(String[] args) throws IOException{
        commandFinished = true;
        start();
    }

    /*
    - run network scanner
    -
    - start taking user input
     */
    private static void start() throws IOException{
        System.out.println("Scanning network.... \n\n");
        if (!startNetworkScan()){ //TODO should this be negated here?
            System.out.println("FATAL ERR: Network Scan Failed");
            System.exit(0);
        }
        System.out.println("Network scan finished!");

        //at this point, all clients should be listed included in the hash table.

        listClients();

        while (true) {
            Scanner input = new Scanner(System.in);
            String s;
            //take input and add to input queue
            if (commandFinished) {
                //set next command to an array split by spaces, input parser will handle errors
                System.out.println("Please enter a command or type \"stop\" to exit: ");
                currentCommand = input.nextLine().split(" ");
                commandFinished = false; //set trigger for command to be run

            }
            if (!commandFinished) {
                parseCommand(currentCommand);
                commandFinished = true;
            }
        }
    }

    //list available clients to console
    private static void listClients(){
        System.out.println("\n\n Available Clients: ");
        for (String s : clients.keySet()){
            System.out.println(s + ", " + clients.get(s).getRAT_IP().getHostAddress());
        }
    }

    /*
    breaks first index off an array, returns array with index 1 onward
     */
    private static String[] breakOffFirstPart(String[] args){
        //create new array from everything but the first word in the command
        String[] nextPart = new String[args.length -1];
        System.arraycopy(args, 1, nextPart, 0, args.length -1);
        return nextPart;
    }

    /*
    perform actions based on each section of the command input

    valid first arguments (args[0]) are included in the switch/case block
     */
    private static void parseCommand(String[] command) throws IOException{

        //create new array from everything but the first word in the command
        String[] nextPart = breakOffFirstPart(command);

        switch (command[0]){
            case "server":
                parseServerCommand(nextPart);
                break;
            case "stop":
                System.exit(0);
            default :
                Client n = clients.get(command[0]);
                if (n != null){
                    n.parseCommand(nextPart);
                    break;
                } else {
                    System.out.println("ERR: \"" + command[0] + "\" not recognized as a valid maching name.");
                    break;
                }
        }

    }

    private static void parseServerCommand(String[] command){

        //make sure arguments provided for "server" command
        try{
            String[] nextPart = breakOffFirstPart(command);
            switch (command[0]){
                case "list" :
                    parseList(nextPart);
                    break;
                default :
                    System.out.println("ERR: \"" + command[0] + "\" not recognized as a valid server command");
                    break;
            }
        } catch (NegativeArraySizeException n){
            System.out.println("ERR: no arguments provided for command: server");
            return;
        }

    }

    private static void parseList(String[] command){
        //make sure arguments provided for "server" command
        try{
            String[] nextPart = breakOffFirstPart(command);
            switch (command[0]){
                case "clients" :
                    listClients();
                    break;
                default :
                    System.out.println("ERR: \"" + command[0] + "\" not recognized as a valid list argument");
                    break;
            }
        } catch (NegativeArraySizeException n){
            System.out.println("ERR: no arguments provided for command: list");
            return;
        }
    }

    /*
    Scan's network once for now looking for clients with port 1337 opened.
    if found, adds them to
     */
    private static boolean startNetworkScan(){

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