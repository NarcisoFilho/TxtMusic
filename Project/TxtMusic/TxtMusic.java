package TxtMusic;
import TxtEditor.TxtEditor;
import mainWindow.MainWindow;

import java.awt.EventQueue;
import java.io.FileNotFoundException;

import Controller.Controller;
import MusicPlayer.MusicPlayer;

public class TxtMusic {
    public static void main(String args[]) throws FileNotFoundException{      
    	Controller ctrl = new Controller();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(ctrl);
					ctrl.setMainGUI(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});  
    }
}