package common.audio;

/**
 * Primary Author: Andrew McManaway
 * Code Review: Liam Byrne
 * External Tester: James Watt
 */

public enum SoundTrack implements Track{
    /* Sound Files */
    CLICK("click.wav"),
    SHOOT("shoot.wav"),
    DEATH("click.wav"), //TODO: Get another file for this
    HURT("click.wav"); //TODO: Get another file for this

    private String filename;

    SoundTrack(String musicTrack) {
        this.filename = musicTrack;
    }

    public String getSoundFile() {
        return ("effects/" + filename);
    }
}
