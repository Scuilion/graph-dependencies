package com.netflix.assignment;

import com.netflix.assignment.ex.BadInputException;
import org.jgrapht.DirectedGraph;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DependencyReport {

    private final Map<String, List<String>> adjList;

    public DependencyReport(final File file) {
        if (!file.exists()) {
            throw new BadInputException("File does not exist");
        }
        adjList = GraphReader.parse(readFileLines(file));
    }

    public DependencyReport(final String filePath) {
        final File f = new File(filePath);
        if (!f.exists()) {
            throw new BadInputException("File does not exist");
        }
        adjList = GraphReader.parse(readFileLines(f));
    }


    private List<String> readFileLines(final File file) {
        try {
            return Files.readAllLines(file.toPath(), Charset.defaultCharset());
        } catch (final IOException e) {
            throw new BadInputException("Failed to parse file.", e);
        }
    }

    public void print() {
        final DependencyGraph dg = new DependencyGraph();
        dg.createGraph(adjList);
        dg.printGraph();
    }
}
