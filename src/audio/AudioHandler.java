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

    private float currentVolume;

    public AudioHandler() {
        this.assetsFolder = "../assets/sounds/";
        this.musicQueue = new ArrayDeque<>();
        this.currentVolume = 0.6f;
    }

    @Override
    public void playSound(Track track) {
        AudioClip clip = createAudioClip(track, false);
        startClip(clip);
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
        if (currentSong != null) {
            currentSong.getClip().stop();
            return;
        }
        if (musicQueue.isEmpty()) return;

        currentSong = musicQueue.poll();
        startClip(currentSong);
    }

    @Override
    public void setAudioVolume(float percentage) {
        if(percentage < 0.0f) percentage = 0.0f;
        else if(percentage > 1.0f) percentage = 1.0f;
        this.currentVolume = percentage;

        if(currentSong != null) setClipVolume(currentSong);
    }

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

    private void startClip(AudioClip clip) {
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(clip.getPath()));

            clip.getClip().open(stream);
            setClipVolume(clip);
            clip.getClip().start();
        } catch (Exception e) {
            throw new AudioException("Encountered Issue when starting AudioClip");
        }
    }

    private void setClipVolume(AudioClip clip){
        FloatControl volume = (FloatControl) clip.getClip().getControl(FloatControl.Type.MASTER_GAIN);
        BooleanControl mute = (BooleanControl) clip.getClip().getControl(BooleanControl.Type.MUTE);

        if(currentVolume == 0.0f) mute.setValue(true);
        else {
            volume.setValue((int) (-50 + (50 * currentVolume)));
            mute.setValue(false);
        }
    }


    private class AudioClip {
        private String path;
        private Clip clip;

        private AudioClip(Clip clip, Track track) {
            this.clip = clip;
            this.path = String.format("%s%s", assetsFolder, track.getSoundFile());
        }

        private Clip getClip() {
            return this.clip;
        }

        private String getPath() {
            return path;
        }
    }
}

