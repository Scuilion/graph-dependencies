package com.scuilion.assignment;

import com.scuilion.assignment.ex.BadInputException;

import java.util.ArrayList;
import java.util.List;

class GraphReader {

    TreeNode parse(final List<String> graphList) {
        List<String> rest = new ArrayList<>();
        if (graphList.isEmpty()) {
            throw new BadInputException("Must provide at least one value.");
        }
        final String start = graphList.get(0);
        if (graphList.size() > 1) {
            rest = graphList.subList(1, graphList.size());
        }

        final Edge parentDep = getDep(start);
        final TreeNode parent = new TreeNode(parentDep.source);
        parent.add(parentDep.source, parentDep.dest);

        for (final String line : rest) {
            final Edge d = getDep(line);
            parent.add(d.source, d.dest);
        }
        return parent;
    }

    private Edge getDep(final String line) {
        final String[] edge = line.split("->");
        if (edge.length == 2) {
            return new Edge(edge[0].trim(), edge[1].trim());
        } else {
            throw new BadInputException("Bad input: " + line);
        }
    }

    private class Edge{
        String source;
        String dest;

        Edge(final String source, final String dest) {
            this.source = source;
            this.dest = dest;
        }
    }
}
