package controller;

import java.awt.EventQueue;
import java.io.File;

import mainWindow.MainWindow;

public class Controller 
{
	MainWindow frame;
	public static void main(String[] args) 
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
	}
	
	public Controller()
	{
		
	}
	
	public void play()
	{
		System.out.println("play");
	}
	
	public void pause()
	{
		System.out.println("pause");
	}
	
	public void stop()
	{
		System.out.println("stop");
	}
	
	public void setVolume(int volume)
	{
		System.out.println("volume: " + Integer.toString(volume));
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
