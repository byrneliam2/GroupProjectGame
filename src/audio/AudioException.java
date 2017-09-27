package audio;

/**
 * This Class is Thrown when classes implementing IAudioHandler encounter an error attempting to play/queue a sound file.
 */
public class AudioException extends RuntimeException{
    AudioException(String message) {
        super(message);
    }
}
