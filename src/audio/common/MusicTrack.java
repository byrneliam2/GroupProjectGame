package audio.common;/*
 * andre: 300338518
 * 23/09/2017
 * Group Project
 */

public enum MusicTrack implements Track{
    /* Music Files */
    MAIN_MENU("main_menu.wav"),
    TEST_MUSIC("test_track.wav");

    private String filename;

    MusicTrack(String musicTrack) {
        this.filename = musicTrack;
    }

    public String getSoundFile() {
        return ("music/" + filename);
    }
}
