public class LojaException extends RuntimeException {
    public LojaException() {
        super();
    }

    public LojaException(String message) {
        super(message);
    }

    public LojaException(String message, Throwable cause) {
        super(message, cause);
    }
}
