package common.audio;/*
 * andre: 300338518
 * 23/09/2017
 * Group Project
 */

public enum MusicTrack implements Track{
    /* Music Files */
    MENU_MUSIC("main_menu.wav"),
    GAME_MUSIC("main_menu.wav"),  //TODO: CHANGE ME
    TEST_MUSIC("test_track.wav");

    private String filename;

    MusicTrack(String musicTrack) {
        this.filename = musicTrack;
    }

    public String getSoundFile() {
        return ("music/" + filename);
    }
}
