package audio;

/*  Andrew McManaway (mcmanaaandr)
 *  ID: 300371233
 *  SWEN 222 Group Project
 */

import audio.tracks.Track;

import javax.sound.sampled.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The AudioHandler, once created by the VIEW, will allow for program to request Background-Music and Sound-Effects
 * to run concurrently as the program runs. This Class creates separate threads for handling the music-queue and different
 * sound effects, without stalling the main game Thread when a Sound-File is Requested.
 */
public class AudioHandler implements IAudioHandler {
    private String assetsFolder;

    private AudioClip currentSong;
    private Deque<AudioClip> musicQueue;

    private int volume = -20;

    public AudioHandler() {
        this.assetsFolder = "../assets/sounds/";
        this.musicQueue = new ArrayDeque<>();
    }

    @Override
    public void playSound(Track track) {
        startClip(createAudioClip(track, false));
    }

    @Override
    public void queueMusic(Track track) {
        musicQueue.offerLast(createAudioClip(track, true));
        if (currentSong == null) next();
    }

    @Override
    public void forceMusic(Track track) {
        musicQueue.offerFirst(createAudioClip(track, true));
        next();
    }

    @Override
    public void next() {
        if (currentSong != null) currentSong.getClip().stop();
        if (musicQueue.isEmpty()) return;

        currentSong = musicQueue.poll();
        startClip(currentSong);
    }

    private void startClip(AudioClip clip) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(clip.getPath()));
            clip.getClip().open(stream);

            FloatControl volumeControl = (FloatControl) clip.getClip().getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(this.volume);

            clip.getClip().start();
        } catch (Exception e) {
            throw new AudioException("Encountered Issue when starting AudioClip");
        }
    }

    /**
     * When called, this method will create a new Clip provided by the AudioSystem Class.
     *
     * @return Generated Clip Object.
     */
    private AudioClip createAudioClip(Track track, boolean wasQueued) {
        try {
            AudioClip audioClip = new AudioClip(AudioSystem.getClip(), track);
            audioClip.getClip().addLineListener(e -> {
                if (e.getType().equals(LineEvent.Type.STOP)) {
                    if (wasQueued) {
                        this.currentSong = null;
                        next();
                    }
                    e.getLine().close();
                }
            });

            return audioClip;
        } catch (LineUnavailableException e) {
            throw new AudioException("Encountered Issue when queuing AudioClip");
        }
    }

    private class AudioClip {
        private String path;
        private Clip clip;
        private Track track;

        private AudioClip(Clip clip, Track track) {
            this.clip = clip;
            this.track = track;
            this.path = String.format("%s%s", assetsFolder, track.getSoundFile());
        }

        private Clip getClip() {
            return this.clip;
        }

        public Track getTrack() {
            return track;
        }

        private String getPath() {
            return path;
        }
    }
}

