package Controller;
import TxtEditor.TxtEditor;
import java.io.FileNotFoundException;
import org.jfugue.player.Player;

public class Controller {
    public static void main(String[] args) throws FileNotFoundException {

        TxtEditor text = new TxtEditor();

        String path = "..Project\\teste.txt";
        text.loadTxtFile(path);

        Player player = new Player();
        player.play(text.getContent());
      }

}
