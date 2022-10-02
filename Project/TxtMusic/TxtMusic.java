package TxtMusic;
import TxtEditor.TxtEditor;
import java.io.FileNotFoundException;
import MusicPlayer.MusicPlayer;

public class TxtMusic {
    public static void main(String args[]) throws FileNotFoundException{      
        TxtEditor text = new TxtEditor();
        MusicPlayer music = new MusicPlayer();        

        String path = "..\\Project\\teste.txt";
        text.loadTxtFile(path);
        String content = text.getContent();
        music.play(content);        
    }
}