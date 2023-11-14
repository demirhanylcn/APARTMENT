public class TooManyThingsException extends Exception {
    public TooManyThingsException() {
        super("Too many things in the room. Remove some old items to insert a new object.");
    }

    public TooManyThingsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Error: " + super.getMessage();
    }
}
