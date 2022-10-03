package Controller;

import java.awt.EventQueue;
import java.io.File;

import MusicPlayer.MusicPlayer;
import mainWindow.MainWindow;

public class Controller 
{
	private MainWindow mainGUI;
	private MusicPlayer player;
	private float volumeMultiplier;
	private float previousVolume;
	
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
		volumeMultiplier = 0.5f;
		previousVolume = 0.5f;
	}
	
	public void setMainGUI(MainWindow mainGUI)
	{
		this.mainGUI = mainGUI;
	}
	
	public void play()
	{
		if (player == null)
		{
			player = new MusicPlayer(volumeMultiplier);
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
			player = new MusicPlayer(volumeMultiplier);
			player.play(mainGUI.getText());
		}
	}
	
	public void pause()
	{
		System.out.println("pause");
		if (player != null)
			player.pause();
	}
	
	public void stop()
	{
		System.out.println("stop");
		if (player != null)
			player.stopPlayback();
	}
	
	public void setVolume(int volume)
	{
		volumeMultiplier = (float)volume/100.0f;
		System.out.println("volume: " + Integer.toString(volume));
		if (player != null)
			player.setVolumeMultiplier(volumeMultiplier);
	}
	
	public void mute(boolean state)
	{
		if (state)
		{
			previousVolume = volumeMultiplier;
			volumeMultiplier = 0.0f;
		}
		else
		{
			volumeMultiplier = previousVolume;
		}
		if (player != null)
			player.setVolumeMultiplier(volumeMultiplier);
	}
	
	public void save(File file)
	{
		player = new MusicPlayer(volumeMultiplier);
		player.save(file, mainGUI.getText());
		System.out.println("Save: " + file.getName());
	}
	
	public float getVolumeMultiplier()
	{
		return volumeMultiplier;
	}
	
	public float getPreviousVolume()
	{
		return previousVolume;
	}
}
