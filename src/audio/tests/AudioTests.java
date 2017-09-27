package audio.tests;

import audio.AudioException;
import audio.AudioHandler;
import audio.IAudioHandler;
import audio.tracks.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.swing.*;

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

    //AudioHandler.class (Automated)

    @Test
    public void test03_playSound() throws InterruptedException{
        IAudioHandler audio = new AudioHandler();
        audio.playSound(SoundTrack.CLICK);
        Thread.sleep(1000);
    }

    @Test
    public void test04_queueMusic() throws InterruptedException{
        IAudioHandler audio = new AudioHandler();
        audio.queueMusic(SoundTrack.CLICK);
        audio.queueMusic(SoundTrack.CLICK);
        Thread.sleep(1000);
    }

    @Test
    public void test05_next() throws InterruptedException{
        IAudioHandler audio = new AudioHandler();
        audio.queueMusic(SoundTrack.CLICK);
        audio.queueMusic(SoundTrack.CLICK);
        audio.next();
        Thread.sleep(1000);
    }

    @Test
    public void test06_forceMusic() throws InterruptedException{
        IAudioHandler audio = new AudioHandler();
        audio.queueMusic(SoundTrack.CLICK);
        audio.queueMusic(SoundTrack.CLICK);
        audio.forceMusic(SoundTrack.CLICK);
        Thread.sleep(1000);
    }

    @Test
    public void test07_volumeControl() throws InterruptedException{
        IAudioHandler audio = new AudioHandler();
        audio.setAudioVolume(1.0f);
        audio.playSound(SoundTrack.CLICK);
        Thread.sleep(500);
        audio.setAudioVolume(0.5f);
        audio.playSound(SoundTrack.CLICK);
        Thread.sleep(500);
        audio.setAudioVolume(0.0f);
        audio.playSound(SoundTrack.CLICK);
        Thread.sleep(1000);
    }

    @Test
    public void test08_testInvalidSong() throws InterruptedException{
        IAudioHandler audio = new AudioHandler();
        try {
            audio.playSound(() -> "not_valid_track.mp3");
            fail("Should have failed");
        } catch (AudioException ignored) {}
    }

}
