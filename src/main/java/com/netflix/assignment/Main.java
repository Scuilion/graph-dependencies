package com.netflix.assignment;

import com.netflix.assignment.ex.BadInputException;

import java.io.File;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new BadInputException("Requires a file path.");
        }
        final File f = getFile(args[0]);

        System.out.println("herre");
        final DependencyReport d = new DependencyReport(f);
        d.print();;
    }

    private static File getFile(final String fileName) {
        File f = new File(fileName);
        if (!f.exists()) {
            String s = Paths.get("").toAbsolutePath().toString();
            System.out.println(s);
        }
        return f;
    }
}
