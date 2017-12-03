package com.tkeburia.redplanetrobotics.exception;

public class InputException extends RuntimeException{

    public InputException()
    {
        super();
    }

    public InputException(String message)
    {
        super(message);
    }

    public InputException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public InputException(Throwable cause)
    {
        super(cause);
    }
}
