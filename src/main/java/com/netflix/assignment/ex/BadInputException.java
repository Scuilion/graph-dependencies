package com.netflix.assignment.ex;

import java.io.IOException;

public class BadInputException extends RuntimeException {

    public BadInputException(final String s) {
        super(s);
    }

    public BadInputException(final String s, final IOException e) {
        super(s, e);
    }
}
