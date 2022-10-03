package MusicPlayer;

import java.util.ArrayList;
import org.jfugue.player.Player;

public class MusicPlayer {
	final int MAX_VOLUME = 4;
	final int DEFAULT_VOLUME = 1;
	
	private char lastNote;
	private int volumeLevel;
    private int volume;
    private float volumeMultiplier;
    private int octave; 
    private ArrayList<String> instruments;
    private int actualInstrument;
    private int bmp;

    // Constructor 
    public MusicPlayer(){
    	this.volumeLevel = 1;
        this.volume = 16;
        this.volumeMultiplier = 1.0f;
        this.octave = 1;        
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

    protected boolean setOctave(int octave){
        this.octave = octave;
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

    // Play the music
    public boolean play(String text){        
    	setActualInstrument(0);
    	volume = 10;
        for (int i = 0; i < text.length(); i++){
            char c = text.charAt(i);
            playNote(c);            
        }       
        return true;
    }

    // Pause the music
    public boolean pause(){
        return true;
    }

    // Stop the music
    public boolean stop(){
        return true;
    }
    
    private String getVolumeString()
    {
    	int vol = (int)((float)volume * volumeMultiplier);
    	return ":CON(7, "+ Integer.toString(volume) + ") ";
    }

    // Play only one note.
    protected boolean playNote(char note){
        Player player = new Player();
        
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
        	playNoteFinal(note, player);
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
        		playNoteFinal(note, player);
        	}
        	break;
        case '?':// Increase octave
        	this.octave += 1;
        	break;
        case ' ':// Double volume
        	increaseVolume();
        	System.out.println("Vol: " + Integer.toString(volume));
        	break;
        default:
        	if (lastNote >= 'A' && lastNote <= 'G')
        	{
        		note = lastNote;
        		playNoteFinal(note, player);
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
    
    private void playNoteFinal(int note, Player player)
    {
    	player.play(getVolumeString() + instruments.get(getActualInstrument()) + String.valueOf(note));
    }
}