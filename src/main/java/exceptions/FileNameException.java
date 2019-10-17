package exceptions;

public class FileNameException extends Throwable {

    FileNameException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
