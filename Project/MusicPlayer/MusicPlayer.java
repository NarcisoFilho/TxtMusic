package MusicPlayer;

import java.util.ArrayList;
import MusicPlayer.MusicalInstrument.MusicalInstrument;

public class MusicPlayer {
    private int volume;
    private int octave; 
    private ArrayList<MusicalInstrument> instruments;
    private int actualInstrument;
    private int bmp;

    // Constructor 
    public MusicPlayer(){
        this.volume = 0;
        this.octave = 0;        
        this.actualInstrument = 0;
        this.bmp = 0;
    }

    // Getters and setters
    protected int getVolume(){
        return this.volume;
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
    public boolean play(){
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
        return true;
    }
}