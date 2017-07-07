package com.netflix.assignment;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graphs;
import org.jgrapht.experimental.dag.DirectedAcyclicGraph;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DependencyReport {

    DirectedGraph<String, String> g;

    DirectedGraph<String, String> createGraph(Map<String, List<String>> adjList) {
        g = new DirectedAcyclicGraph<String, String>(String.class);

        for(String entry: adjList.keySet()) {
            g.addVertex(entry);
        }
        for(Map.Entry<String, List<String>> entry: adjList.entrySet()) {
            if(null != entry.getValue()) {
                for (final String sink : entry.getValue()) {
                    final String edgeName = entry.getKey() + sink;
                    g.addEdge(entry.getKey(), sink, edgeName);
                }
            }
        }
        return g;
    }
    //outDegreeOf
    int level = 0;

    void getLevels(List<String> nodes) {
        if (nodes.size() == 1) {
//            System.out.print(String.join("", Collections.nCopies(level, " ")));
//            System.out.println(nodes.get(0));
            printNode(false, level, nodes.get(0));
            ++level;
            getLevels(Graphs.successorListOf(g, nodes.get(0)));
        } else if (nodes.size() > 1) {
            for (String node : nodes) {
                List<String> eh = Graphs.successorListOf(g, node);
                if (eh.isEmpty()) {
//                    atLevel(node);
                    printNode(true, level, node);
                } else {
//                    System.out.print(String.join("", Collections.nCopies(level, " ")));
//                    System.out.println(node);
                    printNode(false, level, node);
                    ++level;
                    getLevels(eh);
                }
            }
            --level;
        }
    }

    private void atLevel(String node) {
        System.out.print(String.join("", Collections.nCopies(level, " ")));
        System.out.println(node);
    }

    public static String printNode(final Boolean end, final int level, final String node) {
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
}
