package audio.tests;

/**
 * Primary Author: Andrew McManaway
 * Code Review: Liam Byrne
 * External Tester: James Watt
 */

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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Deque;
import common.audio.IAudioHandler;
import common.audio.Track;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AudioTests {

	// Track Interface and Implemented common

	@Test
	public void test01_MusicTrack() {
		Track track = MusicTrack.TEST_MUSIC;
		assertTrue(track.getSoundFile().contains("test_track.wav"));
	}

	@Test
	public void test02_SoundTrack() {
		Track track = SoundTrack.CLICK;
		assertTrue(track.getSoundFile().contains("click.wav"));
	}

	// AudioHandler.class Testing

	@Test
	public void test03_testInvalidSong() throws InterruptedException {
		IAudioHandler audio = new AudioHandler();
		try {
			audio.playSound(() -> "not_valid_track.mp3");
			fail("Should have failed");
		} catch (AudioException ignored) {
		}
	}

	/**
	 * Automated Testing: A valid test is determined by two clicks.
	 */

	@Test
	public void test04_playSound() throws InterruptedException {
		IAudioHandler audio = new AudioHandler();
		audio.playSound(SoundTrack.CLICK);
		Thread.sleep(500);
		audio.playSound(SoundTrack.CLICK);
		Thread.sleep(1000);
	}

	@Test
	public void test05_queueMusic() throws InterruptedException {
		IAudioHandler audio = new AudioHandler();
		audio.queueMusic(SoundTrack.CLICK);
		audio.queueMusic(SoundTrack.CLICK);
		Thread.sleep(1000);
	}

	@Test
	public void test06_next() throws InterruptedException {
		IAudioHandler audio = new AudioHandler();
		audio.queueMusic(SoundTrack.CLICK);
		audio.queueMusic(SoundTrack.CLICK);
		audio.queueMusic(SoundTrack.CLICK);
		audio.next();
		Thread.sleep(1000);
	}

	@Test
	public void test07_forceMusic() throws InterruptedException {
		IAudioHandler audio = new AudioHandler();
		audio.queueMusic(SoundTrack.CLICK);
		audio.queueMusic(SoundTrack.CLICK);
		audio.forceMusic(SoundTrack.CLICK);
		Thread.sleep(1000);
	}

	@Test
	public void test08_volumeControl() throws InterruptedException {
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

	// External Testing
	// External Tester: James.Watt
	// I have gone through the audio tests and re-run them to check the audio is
	// performing as it should. I have discovered that the audio runs well, and that
	// andrew's tests cover all of the public functionality that will be used in the
	// game. I have added two more tests to check that the game music runs well
	// and have decided that with the addition of my two tests that the audio
	// package is sufficiently tested.
	// These tests are used by running them and listening to the output.
	// I have decided to not test the private methods of andrew's well encapsulated
	// audio class
	// as any bugs would have been discovered by testing the public methods which
	// use the private methods.
	@Test
	public void test09_queuePlays() throws InterruptedException {
		IAudioHandler audio = new AudioHandler();
		audio.queueMusic(SoundTrack.CLICK);
		Thread.sleep(2000);
		audio.next();
		audio.queueMusic(MusicTrack.TEST_MUSIC);
		Thread.sleep(2000);

	}

	@Test
	public void test10_msuicPlays() throws InterruptedException {
		IAudioHandler audio = new AudioHandler();
		audio.playSound(MusicTrack.TEST_MUSIC);
		Thread.sleep(2000);
		audio.stop();

	}

	@Test
	public void test11_msuicPlays() throws InterruptedException {
		IAudioHandler audio = new AudioHandler();
		audio.playSound(MusicTrack.GAME_MUSIC);
		Thread.sleep(2000);
		audio.stop();

	}

	@Test
	public void test12_msuicPlays() throws InterruptedException {
		IAudioHandler audio = new AudioHandler();
		audio.playSound(MusicTrack.MENU_MUSIC);
		Thread.sleep(2000);
		audio.stop();

	}

}
