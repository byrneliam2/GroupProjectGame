package audio.tests;

import audio.AudioException;
import audio.AudioHandler;
import common.audio.IAudioHandler;
import common.audio.MusicTrack;
import common.audio.SoundTrack;
import common.audio.Track;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Deque;
import common.audio.IAudioHandler;
import common.audio.Track;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AudioTests {

    //Track Interface and Implemented common

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

    //AudioHandler.class Testing

    @Test
    public void test03_testInvalidSong() throws InterruptedException{
        IAudioHandler audio = new AudioHandler();
        try {
            audio.playSound(() -> "not_valid_track.mp3");
            fail("Should have failed");
        } catch (AudioException ignored) {}
    }

    /**
     * Automated Testing: A valid test is determined by two clicks.
     */

    @Test
    public void test04_playSound() throws InterruptedException{
        IAudioHandler audio = new AudioHandler();
        audio.playSound(SoundTrack.CLICK);
        Thread.sleep(500);
        audio.playSound(SoundTrack.CLICK);
        Thread.sleep(1000);
    }

    @Test
    public void test05_queueMusic() throws InterruptedException{
        IAudioHandler audio = new AudioHandler();
        audio.queueMusic(SoundTrack.CLICK);
        audio.queueMusic(SoundTrack.CLICK);
        Thread.sleep(1000);
    }

    @Test
    public void test06_next() throws InterruptedException{
        IAudioHandler audio = new AudioHandler();
        audio.queueMusic(SoundTrack.CLICK);
        audio.queueMusic(SoundTrack.CLICK);
        audio.queueMusic(SoundTrack.CLICK);
        audio.next();
        Thread.sleep(1000);
    }

    @Test
    public void test07_forceMusic() throws InterruptedException{
        IAudioHandler audio = new AudioHandler();
        audio.queueMusic(SoundTrack.CLICK);
        audio.queueMusic(SoundTrack.CLICK);
        audio.forceMusic(SoundTrack.CLICK);
        Thread.sleep(1000);
    }

    @Test
    public void test08_volumeControl() throws InterruptedException{
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
//External Testing
 //Test audio handler not iaudiohandler
    @Test
    public void test09_audio() throws InterruptedException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
        AudioHandler audio = new AudioHandler();
        Field audioQ = audio.getClass().getDeclaredField("musicQueue");
        audioQ.setAccessible(true);
        Class s = audio.getClass();
        //s.getDeclaredConstructor(null);

       // s.get
        //Deque d = (Deque)audioQ.get(audioQ);
        Track track = MusicTrack.TEST_MUSIC;

        //audio.playSound(track);
        //assert(0==d.size());
        //audio.queueMusic(SoundTrack.CLICK);
        //assert(1==d.size());
        //audio.queueMusic(SoundTrack.CLICK);
        //assert(2==d.size());
        //audio.queueMusic(SoundTrack.CLICK);
        //assert(3==d.size());
        //audio.stop();

        Track click = SoundTrack.CLICK;






        Thread.sleep(500);

    }



}
