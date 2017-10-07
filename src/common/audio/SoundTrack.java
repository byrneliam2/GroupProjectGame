package common.audio;/*
 * andre: 300338518
 * 23/09/2017
 * Group Project
 */

public enum SoundTrack implements Track{
    /* Sound Files */
    CLICK("click.wav"),
    SHOOT("click.wav"), //TODO: Get another file for this
    HURT("click.wav"); //TODO: Get another file for this

    private String filename;

    SoundTrack(String musicTrack) {
        this.filename = musicTrack;
    }

    public String getSoundFile() {
        return ("effects/" + filename);
    }
}
