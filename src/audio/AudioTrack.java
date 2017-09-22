package audio;

/*  Andrew McManaway (mcmanaaandr)
 *  ID: 300371233
 *  SWEN 222 Group Project
 */

/**
 * An AudioTrack is an Object designed to store information for any track that is requested, this contains it's
 * sound type (Music or SoundEffect) and the resource location for the sound-file.
 */
public class AudioTrack {
    private AudioFile soundFile;
    private AudioType soundType;
    private boolean looped;

    public AudioTrack(AudioFile soundFile, AudioType soundType, boolean loop) {
        this.soundFile = soundFile;
        this.soundType = soundType;
        this.looped = loop;
    }

    public String getSoundFile() {
        return soundFile.getFilepath();
    }

    public AudioType getSoundType() {
        return soundType;
    }

    public boolean isLooped() {
        return looped;
    }

    public enum AudioFile{
        /* Music Files */
        TEST_MUSIC("music/test_track.wav"),
        /* Effect Files */
        CLICK("effects/click.wav");

        private String filepath;

        AudioFile(String filepath) {
            this.filepath = filepath;
        }

        public String getFilepath() {
            return filepath;
        }
    }

    /**
     * SoundType enum determines what type of AudioTrack is requested.
     */
    public enum AudioType{
        MUSIC,
        EFFECT
    }
}
