package audio.tracks;/*
 * andre: 300338518
 * 23/09/2017
 * Group Project
 */

public enum SoundTrack implements Track{
    /* Sound Files */
    CLICK("click.wav");

    private String filepath;

    SoundTrack(String musicTrack) {
        this.filepath = musicTrack;
    }

    public String getSoundFile() {
        return ("effects/" +  filepath);
    }
}
