package audio;

/*  Andrew McManaway (mcmanaaandr)
 *  ID: 300371233
 *  SWEN 222 Group Project
 */

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * The AudioHandler, once created by the VIEW, will allow for program to request Background-Music and Sound-Effects
 * to run concurrently as the program runs. This Class creates separate threads for handling the music-queue and different
 * sound effects, without stalling the main game Thread when a Sound-File is Requested.
 */
public class AudioHandler {
    private AudioTrack currentSong; //REPLACE WITH THREAD LINK WHEN READY
    private Queue<AudioTrack> musicQueue;

    public AudioHandler(){
        this.musicQueue = new ArrayDeque<>();
    }

    /**
     * Adds an AudioTrack to be played, forces all previous song to stop playing
     * @param music background music to forcefully play
     */
    public void forceMusic(AudioTrack music){
        //TODO
        checkTrack(music);
        this.currentSong = music;
    }

    /**
     * Adds an AudioTrack to the Queue, if the Queue was empty, begin playing music
     * @param music background music to queue
     */
    public void queueMusic(AudioTrack music){
        //TODO EMPTY CHECKS
        checkTrack(music);
        this.musicQueue.offer(music);
    }

    /**
     * Check if a SoundFile is suitable to be played through the AudioHandler.
     * @param track AudioTrack file to be checked before running
     * @throws AudioException When a AudioTrack is unsuitable to play
     */
    private void checkTrack(AudioTrack track) throws AudioException{
        //ON BAD CASE throw new AudioException("SoundFile failed Initial Loading Checks");
    }

    /**
     * Initiates a new Concurrent Process, this process will close after playing.
     * @param effect sound-effect to play
     */
    public void playEffect(AudioTrack effect){
        checkTrack(effect);
        //TODO
    }


}

