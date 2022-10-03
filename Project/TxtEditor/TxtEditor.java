package TxtEditor;

import TxtEditor.TxtCursor.TxtCursor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TxtEditor {
    private String content;
    private TxtCursor cursor;
    private boolean enable;

    // Constructor
    public TxtEditor(){
        this.content = "";
        this.enable = false;
    }

    // Getters and setters
    public String getContent(){
        return this.content;
    }

    public boolean getEnable(){
        return this.enable;
    }

    public boolean setEnable(boolean enable){
        this.enable = enable;
        return this.enable;
    }

    // Load the content of the .txt file
    public boolean loadTxtFile(String path) throws FileNotFoundException{
        // Try to open the .txt file.
        Scanner in = new Scanner(new FileReader(path));
        // Read all file.
        while (in.hasNextLine()) {
            this.content += in.nextLine() + '\n';            
        }        
        return true;
    }

    // Save content file
    public boolean saveInTxtFile(String path) throws IOException{
        Path filePath = Paths.get(path);
        // Write in the file.
        //Files.writeString(filePath, this.content);
        return true;
    }

    // Add a char in the content
    public boolean addChar(char letter){
        this.content += letter;
        return true;
    }

    // Remove the last char of the content
    public boolean removeChar(){
        if (this.content == null){
            return false;
        }
        else{
            // Remove the last char of the string
            this.content = this.content.substring(0, this.content.length() - 1);
            return true;
        }
    }

}
