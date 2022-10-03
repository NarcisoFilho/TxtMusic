package Controller;

import java.awt.EventQueue;
import java.io.File;

import MusicPlayer.MusicPlayer;
import mainWindow.MainWindow;

public class Controller 
{
	private MainWindow mainGUI;
	private MusicPlayer player;
	
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
		player = new MusicPlayer();
		player = new MusicPlayer();
	}
	
	public void setMainGUI(MainWindow mainGUI)
	{
		this.mainGUI = mainGUI;
	}
	
	public void play()
	{
		if (player == null)
		{
			player = new MusicPlayer();
			player.play(mainGUI.getText());
		}
		else if (player.getIsPlaying())
		{
			if (player.getIsPaused())
			{
				System.out.println("Sent resume");
				player.play("");
			}
		}
		else
		{
			player = new MusicPlayer();
			player.play(mainGUI.getText());
		}
	}
	
	public void pause()
	{
		System.out.println("pause");
		player.pause();
	}
	
	public void stop()
	{
		System.out.println("stop");
		player.stopPlayback();
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
	
	public void save(File file)
	{
		player.save(file, mainGUI.getText());
		System.out.println("Save: " + file.getName());
	}
}
