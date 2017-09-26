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
    private Track currentSong;
    private Queue<Track> musicQueue;

    public AudioHandler(){
        this.assetsFolder = "../assets/sounds/";
        this.musicQueue = new ArrayDeque<>();
    }

    /**
     * When called, this method will create a new Clip provided by the AudioSystem Class.
     * @return Generated Clip Object.
     * @throws LineUnavailableException Thrown if Clip cannot be generated.
     */
    private Clip generateClip(boolean queued) throws LineUnavailableException{
        Clip clip = AudioSystem.getClip();
        clip.addLineListener(e -> {
            if(e.getType().equals(LineEvent.Type.STOP)) {
                e.getLine().close();
                if(queued) {
                    this.currentSong = null;
                    playNext();
                }
            }
        });
        return clip;
    }

    private void playNext(){
        if(musicQueue.isEmpty()) return;
        this.currentSong = musicQueue.poll();

        try {
            String path = String.format("%s%s", assetsFolder, this.currentSong.getSoundFile());
            Clip clip = generateClip(true);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(path));
            clip.open(inputStream);

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-20.0f);   // Requires fine tuning

            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void queueBGM(Track track){
        musicQueue.offer(track);
        if(currentSong == null) playNext();
    }

    public void playSound(Track track){
        try {
            String path = String.format("%s%s", assetsFolder, track.getSoundFile());
            Clip clip = generateClip(false);

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.getClass().getResourceAsStream(path));
            clip.open(inputStream);

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-20.0f);   // Requires fine tuning

            clip.start();
        } catch (LineUnavailableException | AudioException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}

