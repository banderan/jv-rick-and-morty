package mate.academy.rickandmorty.exception;

public class AnimationException extends RuntimeException {
    public AnimationException() {
    }

    public AnimationException(String message) {
        super(message);
    }

    public AnimationException(String message, Throwable cause) {
        super(message, cause);
    }
}
