package com.scuilion.assignment;

import com.scuilion.assignment.ex.BadInputException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class DependencyReport {

    private final GraphReader graphReader = new GraphReader();
    private final TreeNode dependencyNode;

    public DependencyReport(final File file) {
        if (!file.exists()) {
            throw new BadInputException("File does not exist");
        }
        dependencyNode = graphReader.parse(readFileLines(file));
    }

    public DependencyReport(final String filePath) {
        final File f = new File(filePath);
        if (!f.exists()) {
            throw new BadInputException("File does not exist");
        }
        dependencyNode = graphReader.parse(readFileLines(f));
    }


    private List<String> readFileLines(final File file) {
        try {
            return Files.readAllLines(file.toPath(), Charset.defaultCharset());
        } catch (final IOException e) {
            throw new BadInputException("Failed to parse file.", e);
        }
    }

    public void print() {
        ConsoleWriter.write(dependencyNode);
    }
}
