package audio;

/**
 * Primary Author: Andrew McManaway
 * Code Review: Liam Byrne
 * External Tester: James Watt
 */

import common.audio.IAudioHandler;
import common.audio.Track;

import javax.sound.sampled.*;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The AudioHandler, once created by the VIEW, will allow for program to queue Background-Music, and play Sound Effects
 * on the fly. The AudioLevels of the sound-files can be changed during runtime to allow the user to set their preferred
 * audio levels if the current setup is too loud, or too quiet.
 */
public class AudioHandler implements IAudioHandler {
    private String assetsFolder;

    private AudioClip currentSong;
    private AudioClip loopingSong;
    private Deque<AudioClip> musicQueue;

    private float currentVolume;

    public AudioHandler() {
        this.assetsFolder = "../assets/sounds/";
        this.musicQueue = new ArrayDeque<>();
        this.currentVolume = 0.6f;
    }

    @Override
    public void playSound(Track track) {
        AudioClip clip = createAudioClip(track, false, false);
        startClip(clip);
    }

    @Override
    public void playLoop(Track track) {
        this.stop();
        loopingSong = createAudioClip(track, false, true);
        startClip(loopingSong);

    }

    @Override
    public void queueMusic(Track track) {
        if(loopingSong != null) this.stop();
        musicQueue.offerLast(createAudioClip(track, true, false));
        if (currentSong == null) next();
    }

    @Override
    public void forceMusic(Track track) {
        if(loopingSong != null) this.stop();
        musicQueue.offerFirst(createAudioClip(track, true, false));
        next();
    }

    @Override
    public void next() {
        //Stop the current song if it is currently playing
        if (currentSong != null) {
            currentSong.getClip().stop();
            return;
        }
        if (musicQueue.isEmpty()) return;
        //Play the next song in queue
        currentSong = musicQueue.poll();
        startClip(currentSong);
    }

    @Override
    public void setAudioVolume(float percentage) {
        //Check that the audio is in bounds
        if(percentage < 0.0f) percentage = 0.0f;
        else if(percentage > 1.0f) percentage = 1.0f;
        this.currentVolume = percentage;
        //Change the volume setting, and change song that is currently being played
        if(currentSong != null) setClipVolume(currentSong);
        if(loopingSong != null) setClipVolume(loopingSong);
    }

    @Override
    public void stop() {
        if(loopingSong != null) {
            loopingSong.disableLoop();
            loopingSong.getClip().stop();
            loopingSong = null;
        }
        musicQueue.clear();
        next();
    }

    /**
     * This method is used to create an AudioClip, AudioClips are used by the program to store all data (Clips/FilePaths)
     * which are required for music to be played. This simplifies setup and allows for each Clip to automatically
     * close the AudioStream to prevent Memory leaks.
     * @param track Used to store the filename in the AudioClip object.
     * @param wasQueued Tells the AudioHandler to automatically skip to the next song.
     * @return The Created AudioClip Object.
     */
    private AudioClip createAudioClip(Track track, boolean wasQueued, boolean loop) {
        try {
            //Create the AudioClip
            AudioClip audioClip = new AudioClip(AudioSystem.getClip(), track, loop);
            audioClip.getClip().addLineListener(e -> {
                if (e.getType().equals(LineEvent.Type.STOP)) {
                    //Enqueue next song on end
                    if (wasQueued) {
                        this.currentSong = null;
                        next();
                    } else if (loop) {
                        this.loopingSong = createAudioClip(track, false, true);
                        startClip(loopingSong);
                    }
                    //Close the Stream to save memory
                    e.getLine().close();
                }
            });

            return audioClip;
        } catch (LineUnavailableException e) {
            throw new AudioException("Encountered Issue when queuing AudioClip");
        }
    }

    /**
     * This method is called to start a new clip (when the next song is enqueued, or when a sound-effect is required).
     * This allows for an AudioInputStream to be opened, and for the volume to be changed to the preferred settings.
     * @param clip The AudioClip that needs to be played.
     */
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

    /**
     * This method will change the volume of the clip to the current settings, if the current settings are at 0%,
     * the sound-file will be muted.
     * @param clip The AudioClip that needs to have it's sound set / Muted
     */
    private void setClipVolume(AudioClip clip){
        FloatControl volume = (FloatControl) clip.getClip().getControl(FloatControl.Type.MASTER_GAIN);
        BooleanControl mute = (BooleanControl) clip.getClip().getControl(BooleanControl.Type.MUTE);

        if(currentVolume == 0.0f) mute.setValue(true);
        else {
            volume.setValue((int) (-50 + (50 * currentVolume)));
            mute.setValue(false);
        }
    }


    /**
     * The AudioClip class stores all data required by the AudioHandler to play a Sound-Effect or Play Music.
     * This class can only be created by the AudioHandler, to be used by the AudioHandler. If a user wishes to play
     * a song, they will need to select an enum which extends the 'Track' interface.
     */
    private class AudioClip {
        private Clip clip;
        private String path;
        private boolean loop;

        private AudioClip(Clip clip, Track track, boolean loop) {
            this.clip = clip;
            this.path = String.format("%s%s", assetsFolder, track.getSoundFile());
            this.loop = loop;
        }

        private Clip getClip() {
            return this.clip;
        }

        private String getPath() {
            return path;
        }

        private void disableLoop() {
            this.loop = false;
        }

        private boolean shouldLoop() {
            return loop;
        }
    }
}

