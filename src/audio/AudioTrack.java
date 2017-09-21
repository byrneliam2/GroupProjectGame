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
    private String path;
    private SoundType type;

    public AudioTrack(String path, SoundType type) {
        this.path = path;
        this.type = type;
    }

    /**
     * SoundType enum determines what type of AudioTrack is requested.
     */
    public enum SoundType{
        MUSIC, EFFECT
    }
}
