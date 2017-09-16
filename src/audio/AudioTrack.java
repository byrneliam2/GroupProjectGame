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
    private boolean repeat;

    public AudioTrack(String name, File soundFile, SoundType type, boolean repeat) {
        this.name = name;
        this.soundFile = soundFile;
        this.type = type;
        this.repeat = repeat;
    }

    /**
     * SoundType enum determines what type of AudioTrack is requested.
     */
    private enum SoundType{
        MUSIC, SOUND_EFFECT
    }
}
