package TxtMusic;

import java.io.FileNotFoundException;

import TxtEditor.TxtEditor;

public class TxtMusic {
    public static void main(String args[]) throws FileNotFoundException{
        String path = "txt path file here.";
        TxtEditor text = new TxtEditor();
        text.loadTxtFile(path);
    }
}