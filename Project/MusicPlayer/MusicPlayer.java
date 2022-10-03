package MusicPlayer;

import java.util.ArrayList;
import org.jfugue.player.Player;

public class MusicPlayer {
	private char lastNote;
    private int volume;
    private int totalVolume;
    private int octave; 
    private ArrayList<String> instruments;
    private int actualInstrument;
    private int bmp;

    // Constructor 
    public MusicPlayer(){
        this.volume = 1;
        this.totalVolume = 100;
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
    
    public void setVolumeMultiplier(float volume) {
		// TODO Auto-generated method stub
		
	}
    
    protected boolean setVolume(int volume){
        this.volume = volume;
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

    // Play only one note.
    protected boolean playNote(char note){
        Player player = new Player();
        
        switch (note)
        {
        case '!':
        	setActualInstrument(0);
        	break;
        case ';':
        	setActualInstrument(1);
        	break;
        case ',':
        	setActualInstrument(2);     
        	break;
        case 'O':
        case 'o':
        case 'I':
        case 'i':
        case 'U':
        case 'u':
        	setActualInstrument(3);
        	break;
        case '\r':
        case '\n':
        	setActualInstrument(4); 
        	break;
        case '0':
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
        	player.play(instruments.get(getActualInstrument()) + String.valueOf(note));
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
        		player.play(instruments.get(getActualInstrument()) + String.valueOf(lastNote));
        	}
        	
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
}