public class CannotReadFileContentException extends RuntimeException {
    public CannotReadFileContentException() {
    }

    public CannotReadFileContentException(final String message) {
        super(message);
    }

    public CannotReadFileContentException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
