package audio;

/*  Andrew McManaway (mcmanaaandr)
 *  ID: 300371233
 *  SWEN 222 Group Project
 */

import java.io.File;

/**
 * An AudioTrack is an Object designed to store information for any track that is requested, this contains it's
 * sound type (Music or SoundEffect) and the resource location for the sound-file.
 */
public class AudioTrack {
    private String name;
    private File soundFile;
    private SoundType type;

    public AudioTrack(String name, File soundFile, SoundType type) {
        this.name = name;
        this.soundFile = soundFile;
        this.type = type;
    }

    String getName() {
        return name;
    }

    SoundType getType() {
        return type;
    }

    /**
     * SoundType enum determines what type of AudioTrack is requested.
     */
    public enum SoundType{
        MUSIC, EFFECT
    }
}
