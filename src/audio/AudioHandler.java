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
    private Queue<AudioTrack> musicQueue;

    public AudioHandler(){
        this.musicQueue = new ArrayDeque<>();
    }


}

