package audio;

/*
 * Liam: 300338518
 * 4/09/2017
 * SWEN222PROJECT
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Simple audio player that utilises the Java Sound API.
 */
public class AudioLoader {

    /**
     * Load a particular audio stream based on the type parameter.
     * @return AudioInputStream of the specified file, or null if exceptions thrown
     */
    public static AudioInputStream loadStream(String type) {
        String path = "";
        switch(type) {
            //
        }

        try {
            return AudioSystem.getAudioInputStream(AudioLoader.class.getResource(path + ".wav"));
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

