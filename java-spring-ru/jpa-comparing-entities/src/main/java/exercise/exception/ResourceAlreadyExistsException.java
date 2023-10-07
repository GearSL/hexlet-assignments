package exercise.exception;

// BEGIN
public class ResourceAlreadyExistsException extends RuntimeException {
    public ResourceAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
// END
