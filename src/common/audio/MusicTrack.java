package common.audio;

/**
 * Primary Author: Andrew McManaway
 * Code Review: Liam Byrne
 * External Tester: James Watt
 */

public enum MusicTrack implements Track{
    /* Music Files */
    MENU_MUSIC("main_menu.wav"),
    GAME_MUSIC("game_music.wav"),  //TODO: CHANGE ME
    TEST_MUSIC("test_track.wav");

    private String filename;

    MusicTrack(String musicTrack) {
        this.filename = musicTrack;
    }

    public String getSoundFile() {
        return ("music/" + filename);
    }
}
