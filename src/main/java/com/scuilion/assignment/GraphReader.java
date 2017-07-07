package com.scuilion.assignment;

import com.scuilion.assignment.ex.BadInputException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class GraphReader {

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
