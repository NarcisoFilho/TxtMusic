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
    public MusicPlayer(){
    	isPlaying = false;
        isPaused = false;
    	this.song = new ArrayList<String>();
    	this.shouldStop = false;
    	this.volumeLevel = DEFAULT_VOLUME_LEVEL;
        this.volume = DEFAULT_VOLUME;
        this.volumeMultiplier = 0.5f;
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
    	
    	buildSong();
        Player player = new Player();
    	for (int i = 0; i < song.size(); i++)
    	{
    		playSong(player, song.get(i));
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
    	File saveFile = new File(file.getAbsolutePath() + ".mid");
    	String fullSong = new String();
    	for (int i = 0; i < song.size();i++)
    	{
    		fullSong += song.get(i);
    	}
    	Pattern pattern = new Pattern(fullSong);
    	buildSong();
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
            addNote(c);        
        }
    }
    
    // Play only one note.
    protected boolean addNote(char note){
        
        switch (note)
        {
        case '!':// Change instrument to Agogo
        	setActualInstrument(0);
        	break;
        case ';':// Change instrument to Pan Flute
        	setActualInstrument(1);
        	break;
        case ',':// Change instrument to Church_Organ
        	setActualInstrument(2);     
        	break;
        case 'O':// Change instrument to Harpsichord
        case 'o':
        case 'I':
        case 'i':
        case 'U':
        case 'u':
        	setActualInstrument(3);
        	break;
        case '\r':
        case '\n':// Change instrument to Tubular_Bells
        	setActualInstrument(4); 
        	break;
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
        	break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        	appendNoteToSong(note);
        	break;
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
        		appendNoteToSong(note);
        	}
        	break;
        case '?':// Increase octave
        	increaseOctave();
        	break;
        case ' ':// Double volume
        	increaseVolume();
        	break;
        default:
        	if (lastNote >= 'A' && lastNote <= 'G')
        	{
        		note = lastNote;
        		appendNoteToSong(note);
        	}
        	break;
        }
        // Change instrument to Agogo
        /*if (note == '!')
            setActualInstrument(0);
        // Change instrument to Pan Flute                              
        else if (note == ';')
            setActualInstrument(1);
        // Change instrument to Church_Organ
        else if (note == ',')
            setActualInstrument(2);     
        // Change instrument to Harpsichord
        else if (note == 'O' || note == 'o' || note == 'I' || note == 'i' || note == 'U' || note == 'u')
            setActualInstrument(3);
        // Change instrument to Tubular_Bells
        // Not working.
        else if (note == '\r' || note == '\n')
        setActualInstrument(4); 
        // Change instrument. Number + Note
        else if (note == '0' || note == '1' || note == '2' || note == '3' || note == '4' || note == '5'
                || note == '6' || note == '7' || note == '8' || note == '9'){
                    int newInstrument = getActualInstrument() + Character.getNumericValue(note);
                    if (newInstrument < 5)
                        setActualInstrument(newInstrument);                        
                    else{
                        newInstrument -= 5;  
                        if(newInstrument < 5)
                            setActualInstrument(newInstrument);
                        else{
                            newInstrument -= 5; 
                            setActualInstrument(newInstrument);
                        }
                    }                     
                }
        // Increase octave
        else if (note == '?')
            // todo!
            this.octave += 1;   
        // Double volume
        else if (note == ' ')
            // todo!
            this.volume *= 2;
        // Repeat last note or pause music.        
        else
            // Repeat last note.
            if (lastNote == 'A' || lastNote == 'B' || lastNote == 'C' || lastNote == 'D' 
                || lastNote == 'E' || lastNote == 'F' || lastNote == 'G')
                player.play(instruments.get(getActualInstrument()) + String.valueOf(lastNote));
            // Pause music.
            else
                pause();   
        // Play the note.   
        player.play(instruments.get(getActualInstrument()) + String.valueOf(note));
        // Store last note.
        */
        lastNote = note;
        return true;
    }
    
    private void appendNoteToSong(int note)
    {
    	System.out.println("Vol: " + Integer.toString(volume));
    	song.add(" " + (getVolumeString() + instruments.get(getActualInstrument()) + Character.toString(note) + Integer.toString(octave)));
    }
    
    private void playSong(Player player, String note)
    {
    	player.play(note);
    }
}