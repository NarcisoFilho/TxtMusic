package mainWindow;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Controller;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JFileChooser fileChooser;
	private JTextArea textArea;
	private JSlider sliderVolume;
	private Controller controller;
	
	private WindowEventListener playEvListener;
	private WindowEventListener pauseEvListener;
	private WindowEventListener stopEvListener;
	private WindowChangeListener volEvListener;
	private WindowEventListener muteEvListener;
	private WindowEventListener openLoadEvListener;
	private WindowEventListener openSaveEvListener;
	private WindowEventListener loadEvListener;
	private WindowEventListener saveEvListener;

	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	public MainWindow(Controller controller) {
		this.controller = controller;
		setResizable(false);
		setTitle("TXTMusic");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(104, 107, 117));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		Box horizontalBox = Box.createHorizontalBox();
		contentPane.add(horizontalBox);
		
		Box verticalBox = Box.createVerticalBox();
		horizontalBox.add(verticalBox);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		JLabel lblText = new JLabel("");
		lblText.setIcon(new ImageIcon(MainWindow.class.getResource("/rsrc/txtMusicText.png")));
		horizontalBox_1.add(lblText);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		horizontalBox_1.add(rigidArea);
		
		JButton btnLoad = new JButton("");
		btnLoad.setIcon(new ImageIcon(MainWindow.class.getResource("/rsrc/loadButton.png")));
		horizontalBox_1.add(btnLoad);
		openLoadEvListener = new WindowEventListener("openFileChooser", this);
		btnLoad.addActionListener(openLoadEvListener);
		
		JButton btnSave = new JButton("");
		btnSave.setIcon(new ImageIcon(MainWindow.class.getResource("/rsrc/saveButton.png")));
		horizontalBox_1.add(btnSave);
		openSaveEvListener = new WindowEventListener("saveFileChooser", this);
		btnSave.addActionListener(openSaveEvListener);
		
		JScrollPane scrollPane = new JScrollPane();
		verticalBox.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);
		
		JButton btnPlay = new JButton("");
		btnPlay.setIcon(new ImageIcon(MainWindow.class.getResource("/rsrc/playButton.png")));
		horizontalBox_2.add(btnPlay);
		playEvListener = new WindowEventListener("play", this);
		btnPlay.addActionListener(playEvListener);
		
		JButton btnPause = new JButton("");
		btnPause.setIcon(new ImageIcon(MainWindow.class.getResource("/rsrc/pauseButton.png")));
		horizontalBox_2.add(btnPause);
		pauseEvListener = new WindowEventListener("pause", this);
		btnPause.addActionListener(pauseEvListener);
		
		JButton btnStop = new JButton("");
		btnStop.setIcon(new ImageIcon(MainWindow.class.getResource("/rsrc/stopButton.png")));
		horizontalBox_2.add(btnStop);
		stopEvListener = new WindowEventListener("stop", this);
		btnStop.addActionListener(stopEvListener);
		
		Box verticalBox_1 = Box.createVerticalBox();
		horizontalBox.add(verticalBox_1);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		verticalBox_1.add(rigidArea_1);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox_1.add(horizontalBox_3);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(50);
		horizontalBox_3.add(horizontalStrut_1);
		
		sliderVolume = new JSlider();
		sliderVolume.setOrientation(SwingConstants.VERTICAL);
		horizontalBox_3.add(sliderVolume);
		volEvListener = new WindowChangeListener("setVolume", this);
		sliderVolume.addChangeListener(volEvListener);
		
		Component horizontalStrut = Box.createHorizontalStrut(50);
		horizontalBox_3.add(horizontalStrut);
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		horizontalBox_4.setAlignmentX(Component.LEFT_ALIGNMENT);
		verticalBox_1.add(horizontalBox_4);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(15);
		horizontalBox_4.add(horizontalStrut_2);
		
		JButton btnMute = new JButton("");
		btnMute.setIcon(new ImageIcon(MainWindow.class.getResource("/rsrc/muteButton.png")));
		horizontalBox_4.add(btnMute);
		muteEvListener = new WindowEventListener("mute", this);
		btnMute.addActionListener(muteEvListener);
		
		fileChooser = new JFileChooser();
		FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("TXT File","txt");
		fileChooser.setFileFilter(extensionFilter);
		loadEvListener = new WindowEventListener("load", this);
		saveEvListener = new WindowEventListener("save", this);
	}
	
	public String getText()
	{
		return textArea.getText();
	}
	
	protected void play()
	{
		controller.play();
	}
	
	protected void pause()
	{
		controller.pause();
	}
	
	protected void stop()
	{
		controller.stop();
	}
	
	protected void setVolume()
	{
		int volume = sliderVolume.getValue();
		controller.setVolume(volume);
	}
	
	protected void mute()
	{
		controller.mute();
	}
	
	protected void load()
	{
		File file = fileChooser.getSelectedFile();
		if (file != null)
		{
			controller.load(file);
			fileChooser.setSelectedFile(null);
		}
	}
	
	protected void save()
	{
		File file = fileChooser.getSelectedFile();
		if (file != null)
		{
			controller.save(file);
			fileChooser.setSelectedFile(null);
		}
	}
	
	protected void openFileChooser()
	{
		fileChooser.removeActionListener(saveEvListener);
		fileChooser.addActionListener(loadEvListener);
		fileChooser.showOpenDialog(this);
	}
	
	protected void saveFileChooser()
	{
		fileChooser.removeActionListener(loadEvListener);
		fileChooser.addActionListener(saveEvListener);
		fileChooser.showOpenDialog(this);
	}
	
}
