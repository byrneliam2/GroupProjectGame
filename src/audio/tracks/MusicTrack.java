package audio.tracks;/*
 * andre: 300338518
 * 23/09/2017
 * Group Project
 */

public enum MusicTrack implements Track{
    /* Music Files */
    TEST_MUSIC("test_track.wav");

    private String filepath;

    MusicTrack(String musicTrack) {
        this.filepath = musicTrack;
    }

    public String getSoundFile() {
        return ("music/" +  filepath);
    }
}
