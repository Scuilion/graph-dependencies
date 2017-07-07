package com.netflix.assignment;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.experimental.dag.DirectedAcyclicGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DependencyGraph {

    DirectedGraph<String, String> g;

    int level = 0;

    protected static String printNode(final Boolean end, final int level, final String node) {
        final String delimiter = end ? "\\_ " : "|_ ";
        final String depLine;
        if (level == 0) {
            depLine = node;
        } else if (level == 1) {
            depLine = delimiter + node;
        } else {
            depLine = "|" + spaces(level) + delimiter + node;
        }
        System.out.println(depLine);
        return depLine;
    }

    private static String spaces(final int level) {
        final int spaces = level * 3 - 4;
        return String.join("", Collections.nCopies(spaces, " "));
    }

    DirectedGraph<String, String> createGraph(Map<String, List<String>> adjList) {
        g = new DirectedAcyclicGraph<>(String.class);

        for (final String entry : getVertices(adjList)) {
            g.addVertex(entry);
        }
        for (final Map.Entry<String, List<String>> entry : adjList.entrySet()) {
            if (null != entry.getValue()) {
                for (final String sink : entry.getValue()) {
                    final String edgeName = entry.getKey() + sink;
                    g.addEdge(entry.getKey(), sink, edgeName);
                }
            }
        }
        return g;
    }

    private Set<String> getVertices(final Map<String, List<String>> adjList) {

        final List<String> dupVertices = new ArrayList<>(adjList.keySet());
        for (final Map.Entry<String, List<String>> v : adjList.entrySet()) {
            final List<String> s = v.getValue();
            if (s != null) {
                dupVertices.addAll(s);
            }
        }
        return new HashSet<>(dupVertices);
    }

    void getLevels(final List<String> nodes) {
        if (nodes.size() == 1) {
            printNode(false, level, nodes.get(0));
            ++level;
            getLevels(Graphs.successorListOf(g, nodes.get(0)));
        } else if (nodes.size() > 1) {
            for (final String node : nodes) {
                final List<String> eh = Graphs.successorListOf(g, node);
                if (eh.isEmpty()) {
                    printNode(true, level, node);
                } else {
                    printNode(false, level, node);
                    ++level;
                    getLevels(eh);
                }
            }
            --level;
        }
    }


}
