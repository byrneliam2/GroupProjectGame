package audio;

import audio.tracks.Track;

public interface IAudioHandler {

    /**
     * This method can be called whenever the implementer wishes to play a new track without waiting for the Queue
     * @param track The SoundTrack to be played.
     */
    public void playSound(Track track);

    /**
     * This method will queue a new song to be played. This could either include a check to play the song instantly,
     * or allow the implementor to call the next() method.
     * @param track The SoundTrack to be queued.
     */
    public void queueMusic(Track track);

    /**
     * This method will completely skip the current song playing (if one exists), the provided song will then play,
     * forcing itself in front of the rest of the queue. This is to be used in the scenario of a song needing to be
     * played instantly in the background.
     * @param track The SoundTrack to be forcefully played.
     */
    public void forceMusic(Track track);

    /**
     * This method will allow for the current background music-file to be skipped, and for the next song to be played.
     * If another song doesn't exist, the next() method will just return.
     */
    public void next();

    /**
     * Allow for the implementer to change the volume for all audio-tracks being played.
     * @param percentage A value between 0.0f and 1.0f
     */
    public void setAudioVolume(float percentage);
}
