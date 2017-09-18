package audio;

/**
 * This Class is Thrown when AudioHandler encounters an error attempting to play/queue a sound file.
 */
public class AudioException extends RuntimeException{
    public AudioException(String message) {
        super(message);
    }
}
