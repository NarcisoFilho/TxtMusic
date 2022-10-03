package MusicPlayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class MusicPlayer extends Thread{
	private final int MAX_VOLUME = 4;
	private final int DEFAULT_VOLUME = 32;
	private final int DEFAULT_VOLUME_LEVEL = 1;
	private final int MIN_OCTAVE = 3;
	private final int MAX_OCTAVE = 8;
	private final int DEFAULT_OCTAVE = 5;
	private enum e_operation {play, pause, stop};
	
	private e_operation operation;
	private String text;
	private char lastNote;
	private int volumeLevel;
    private int volume;
    private float volumeMultiplier;
    private int octave; 
    private ArrayList<String> instruments;
    private int actualInstrument;
    private int bmp;
    private ArrayList<String> song;
    private boolean shouldStop;
    private boolean isPlaying;
    private boolean isPaused;

    // Constructor 
    public MusicPlayer(float volMult){
    	isPlaying = false;
        isPaused = false;
    	this.song = new ArrayList<String>();
    	this.shouldStop = false;
    	this.volumeLevel = DEFAULT_VOLUME_LEVEL;
        this.volume = DEFAULT_VOLUME;
        this.volumeMultiplier = volMult;
        this.octave = DEFAULT_OCTAVE;        
        this.actualInstrument = 0;
        this.bmp = 0;
        this.instruments = new ArrayList<>();

        this.instruments.add("V0 I[Agogo] ");
        this.instruments.add("V0 I[Pan_Flute] ");
        this.instruments.add("V0 I[Church_Organ] ");
        this.instruments.add("V0 I[Harpsichord] ");
        this.instruments.add("V0 I[Tubular_Bells] ");
    }

    // Getters and setters
    protected int getVolume(){
        return this.volume;
    }
    
    public void setVolumeMultiplier(float volumeMul) {
    	this.volumeMultiplier = volumeMul;
	}
    
    protected boolean increaseVolume(){
    	switch (volumeLevel)
    	{
    	case 0:
    		volumeLevel = 1;
    		volume = 16;
    		break;
    	case 1:
    		volumeLevel = 2;
    		volume = 32;
    		break;
    	case 2:
    		volumeLevel = 3;
    		volume = 64;
    		break;
    	case 3:
    		volumeLevel = 4;
    		volume = 127;
    		break;
    	case 4:
    		volumeLevel = 0;
    		volume = 0;
    		break;
    	default:
    		volumeLevel = 0;
    		volume = 0;
    		break;
    	}
        return true;
    }
   
    protected int getOctave(){
        return this.octave;
    }

    protected boolean increaseOctave(){
        if (octave < MAX_OCTAVE)
        {
        	octave++;
        }
        else
        {
        	octave = MIN_OCTAVE;
        }
        return true;
    }

    protected int getActualInstrument(){
        return this.actualInstrument;
    }

    protected boolean setActualInstrument(int actualInstrument){
        this.actualInstrument = actualInstrument;
        return true;
    }

    protected int getBpm(){
        return this.bmp;
    }

    protected boolean setBpm(int bpm){
        this.bmp = bpm;
        return true;
    }
    
    public boolean getIsPlaying()
    {
    	return isPlaying;
    }
    
    public boolean getIsPaused()
    {
    	return isPaused;
    }
    
    // Play the music
    public boolean play(String text){
    	if (isPlaying)
    	{
    		System.out.println("Playing");
    		if (isPaused)
    		{
    			isPaused = false;
    			System.out.println("Resume");
    		}
    	}
    	else
    	{
    			isPlaying = true;
        		this.text = text;
        		this.start();
    	}
        return true;
    }
    
    @Override
    public void run()
    {
    		startPlayback();
    		isPlaying = false;
    		return;
    }
    
    private void startPlayback()
    {
    	setActualInstrument(0);
    	octave = DEFAULT_OCTAVE;
    	volume = DEFAULT_VOLUME;
    	volumeLevel = DEFAULT_VOLUME_LEVEL;
    	int counter = 0;
    	
        Player player = new Player();
    	for (int i = 0; i < text.length(); i++)
    	{
    		playNote(player, parseNote(text.charAt(i)));
    		while (isPaused && !shouldStop)
    		{
    			if (!isPaused || shouldStop) //WTF? Don't know why this works
    				break;
    		}
    		System.out.println("playing " + Integer.toString(counter));
    		if (shouldStop)
    			break;
    	}
    	return;
    }
    
    public void save(File file, String text)
    {
    	this.text = text;
    	buildSong();
    	
    	File saveFile;
    	int index = file.getName().lastIndexOf(".");
    	if (!(index == -1))
    	{
    		String extension = file.getName().substring(index);
        	if (!(extension.compareTo(".mid") == 0))
        		saveFile = new File(file.getAbsolutePath() + ".mid");
        	else
        		saveFile = file;
    	}
    	else
    	{
    		saveFile = new File(file.getAbsolutePath() + ".mid");
    	}
    	String fullSong = new String();
    	
    	
    	for (int i = 0; i < song.size();i++)
    	{
    		fullSong += song.get(i);
    	}
    	Pattern pattern = new Pattern(fullSong);
    	try {
			org.jfugue.midi.MidiFileManager.savePatternToMidi(pattern, saveFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    // Pause the music
    public boolean pause(){
    	if (isPlaying)
    	{
    		isPaused = true;
        	return true;
    	}
        return false;
    }

    //Stop the music
    public boolean stopPlayback(){
    	shouldStop = true;
        return true;
    }
    
    private String getVolumeString()
    {
    	int vol = (int)((float)volume * volumeMultiplier);
    	return ":CON(7, "+ Integer.toString(vol) + ") ";
    }

    private void buildSong()
    {
    	song.clear();
    	for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            appendNoteToSong(parseNote(c));        
        }
    }
    
    // Play only one note.
    protected String parseNote(char note){
        
        switch (note)
        {
        case '!':// Change instrument to Agogo
        	setActualInstrument(0);
        	lastNote = 'a';
        	return " ";
        case ';':// Change instrument to Pan Flute
        	setActualInstrument(1);
        	lastNote = 'a';
        	return " ";
        case ',':// Change instrument to Church_Organ
        	setActualInstrument(2);     
        	lastNote = 'a';
        	return " ";
        case 'O':// Change instrument to Harpsichord
        case 'o':
        case 'I':
        case 'i':
        case 'U':
        case 'u':
        	setActualInstrument(3);
        	lastNote = 'a';
        	return " ";
        case '\r':
        case '\n':// Change instrument to Tubular_Bells
        	setActualInstrument(4); 
        	lastNote = 'a';
        	return " ";
        case '0':// Change instrument. Number + Note
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
        	 int newInstrument = getActualInstrument() + Character.getNumericValue(note);
             if (newInstrument < 5)
                 setActualInstrument(newInstrument);                        
             else
             {
                 newInstrument -= 5;  
                 if(newInstrument < 5)
                     setActualInstrument(newInstrument);
                 else
                 {
                     newInstrument -= 5; 
                     setActualInstrument(newInstrument);
                 }
             }
             lastNote = 'a';
             return " ";
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        	lastNote = note;
        	return formNoteString(note);
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'f':
        case 'g':
        	if (lastNote >= 'A' && lastNote <= 'G')
        	{
        		note = lastNote;
        		lastNote = note;
        		return formNoteString(note);
        	}
        	break;
        case '?':// Increase octave
        	lastNote = 'a';
        	increaseOctave();
        	return " ";
        case ' ':// Double volume
        	lastNote = 'a';
        	increaseVolume();
        	return " ";
        default:
        	if (lastNote >= 'A' && lastNote <= 'G')
        	{
        		note = lastNote;
        		lastNote = note;
        		return formNoteString(note);
        	}
        	lastNote = 'a';
        	return " ";
        }
        return " ";
    }
    
    private String formNoteString(int note)
    {
    	return " " + (getVolumeString() + instruments.get(getActualInstrument()) + Character.toString(note) + Integer.toString(octave));
    }
    
    private void appendNoteToSong(String note)
    {
    	song.add(note);
    }
    
    private void playNote(Player player, String note)
    {
    	player.play(note);
    }
}