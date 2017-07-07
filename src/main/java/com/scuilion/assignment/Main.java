package com.scuilion.assignment;

import com.scuilion.assignment.ex.BadInputException;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new BadInputException("Requires a file path.");
        }
        final File f = new File(args[0]);

        (new DependencyReport(f)).print();
    }

}
