package audio.tests;

import audio.tracks.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

/**
 * Relies on Automated Testing
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AudioTests {

    //Track.class (and children)

    @Test
    public void test01_MusicTrack(){
        Track track = MusicTrack.TEST_MUSIC;
        assertTrue(track.getSoundFile().contains("test_track.wav"));
    }

    @Test
    public void test02_SoundTrack(){
        Track track = SoundTrack.CLICK;
        assertTrue(track.getSoundFile().contains("click.wav"));
    }

    //AudioHandler.class




}
