package Controller;

import java.awt.EventQueue;
import java.io.File;

import MusicPlayer.MusicPlayer;
import mainWindow.MainWindow;

public class Controller 
{
	private MusicPlayer player;
	private MainWindow mainGUI;
	
	/*public static void main(String[] args) 
	{
		Controller self = new Controller();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(self);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	public Controller()
	{
		this.player = new MusicPlayer();
	}
	
	public void setMainGUI(MainWindow mainGUI)
	{
		this.mainGUI = mainGUI;
	}
	
	public void play()
	{
		player.play(mainGUI.getText());
	}
	
	public void pause()
	{
		System.out.println("pause");
		player.pause();
	}
	
	public void stop()
	{
		System.out.println("stop");
		player.stop();
	}
	
	public void setVolume(int volume)
	{
		System.out.println("volume: " + Integer.toString(volume));
		player.setVolumeMultiplier((float)volume/100.0f);
	}
	
	public void mute()
	{
		System.out.println("mute");
	}
	
	public void load(File file)
	{
		System.out.println("Load: " + file.getName());
	}
	
	public void save(File file)
	{
		System.out.println("Save: " + file.getName());
	}
}
