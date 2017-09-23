package audio;

/*  Andrew McManaway (mcmanaaandr)
 *  ID: 300371233
 *  SWEN 222 Group Project
 */

import audio.tracks.Track;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * The AudioHandler, once created by the VIEW, will allow for program to request Background-Music and Sound-Effects
 * to run concurrently as the program runs. This Class creates separate threads for handling the music-queue and different
 * sound effects, without stalling the main game Thread when a Sound-File is Requested.
 */
public class AudioHandler {
    private String assetsFolder;

    public AudioHandler(){
        this.assetsFolder = "../assets/sounds/";
        //this.musicQueue = new ArrayDeque<>();
    }


    /**
     * When called, this method will create a new Clip provided by the AudioSystem Class.
     * @return Generated Clip Object.
     * @throws LineUnavailableException Thrown if Clip cannot be generated.
     */
    private Clip generateClip() throws LineUnavailableException{
        Clip clip = AudioSystem.getClip();
        clip.addLineListener(e -> {
            if(e.getType().equals(LineEvent.Type.STOP)) e.getLine().close();
        });
        return clip;
    }

    public void playSound(Track track){
        try {
            String path = String.format("%s%s", assetsFolder, track.getSoundFile());
            Clip clip = generateClip();

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(path));
            clip.open(inputStream);

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-20.0f);   // Requires fine tuning*/

            clip.start();
        } catch (LineUnavailableException | AudioException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}

