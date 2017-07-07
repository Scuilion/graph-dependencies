package com.netflix.assignment;

import com.netflix.assignment.ex.BadInputException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GraphReader {

    static Map<String, List<String>> parse(final String path) {
        Map<String, List<String>> adjList;
        try {
            final List<String> f = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
            adjList = parse(f);
        } catch (IOException e) {
            throw new BadInputException("Failed to parse file.", e);
        }
        return adjList;
    }

    static Map<String, List<String>> parse(final List<String> graphList) {
        final Map<String, List<String>> adjList = new HashMap<>();

        for (final String s : graphList) {
            final String[] vert = s.split("->");
            if (vert.length == 2) {
                adjList.computeIfAbsent(vert[0].trim(), k -> new ArrayList<>()).add(vert[1].trim());
            } else {
                throw new BadInputException("Bad input: " + s);
            }
        }
        return adjList;

    }
}
