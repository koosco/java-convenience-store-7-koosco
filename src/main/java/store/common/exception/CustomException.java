package store.common.exception;

public class CustomException extends IllegalArgumentException {

    private final ErrorMessage errorMessage;

    public CustomException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage.get();
    }
}
