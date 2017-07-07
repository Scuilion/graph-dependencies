package com.netflix.assignment;

import org.jgrapht.DirectedGraph;
import org.jgrapht.VertexFactory;
import org.jgrapht.generate.CompleteGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.DepthFirstIterator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class DependencyReportTest {

    @Test
    public void createsAdjacencyList() {
        final Map<String, List<String>> adjList = new HashMap<>();
        adjList.put("A", Arrays.asList("B", "C"));
        adjList.put("B", Arrays.asList("C", "D"));
        adjList.put("C", null);
        adjList.put("D", null);

        final DependencyReport dependencyReport = new DependencyReport();
        DirectedGraph<String, String> g = dependencyReport.createGraph(adjList);

        assertThat(g.vertexSet(), containsInAnyOrder("A", "B", "C", "D"));
        assertThat(g.edgesOf("A"), containsInAnyOrder("AB", "AC"));
    }

    @Test
    public void testLevelCreation() {
        final Map<String, List<String>> adjList = new HashMap<>();
        adjList.put("A", Arrays.asList("B", "C"));
        adjList.put("B", Arrays.asList("C", "D"));
        adjList.put("C", null);
        adjList.put("D", null);

        final DependencyReport dependencyReport = new DependencyReport();
        DirectedGraph<String, String> g = dependencyReport.createGraph(adjList);

        dependencyReport.getLevels(Arrays.asList("A"));

        assertTrue(true);
    }

    @Test
    public void testTextIndicators() {
        final String levelZero = DependencyReport.printNode(false, 0, "A");
        final String levelOne = DependencyReport.printNode(false, 1, "A");
        final String levelTwo = DependencyReport.printNode(false, 2, "A");
        final String levelThree = DependencyReport.printNode(false, 3, "A");
        assertThat(levelZero, is(equalTo("A")));
        assertThat(levelOne, is(equalTo("|_ A")));
        assertThat(levelTwo, is(equalTo("|  |_ A")));
        assertThat(levelThree, is(equalTo("|     |_ A")));
    }

    @Test
    public void testTextEndIndicators() {
        final String levelZero = DependencyReport.printNode(true, 0, "A");
        final String levelOne = DependencyReport.printNode(true, 1, "A");
        final String levelTwo = DependencyReport.printNode(true, 2, "A");
        final String levelThree = DependencyReport.printNode(true, 3, "A");
        assertThat(levelZero, is(equalTo("A")));
        assertThat(levelOne, is(equalTo("\\_ A")));
        assertThat(levelTwo, is(equalTo("|  \\_ A")));
        assertThat(levelThree, is(equalTo("|     \\_ A")));
    }


}
