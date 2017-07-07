package com.netflix.assignment;

import org.jgrapht.DirectedGraph;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class DependencyGraphTest {

    @Test
    public void createsGraph() throws IOException {
        final Map<String, List<String>> adjList = new HashMap<>();
        adjList.put("A", Arrays.asList("B", "C"));
        adjList.put("B", Arrays.asList("C", "D"));
        adjList.put("C", null);
        adjList.put("D", null);

        final DependencyGraph dependencyGraph = new DependencyGraph();

        DirectedGraph<String, String> g = dependencyGraph.createGraph(adjList);

        assertThat(g.vertexSet(), containsInAnyOrder("A", "B", "C", "D"));
        assertThat(g.edgesOf("A"), containsInAnyOrder("AB", "AC"));
    }

    @Test
    public void whenNotAllSinksAreSources() {
        final Map<String, List<String>> adjList = GraphReader.parse(Collections.singletonList("A->B"));

        final DependencyGraph dependencyGraph = new DependencyGraph();
        DirectedGraph<String, String> g = dependencyGraph.createGraph(adjList);
    }

    @Test
    public void testLevelCreation() {
        final Map<String, List<String>> adjList = new HashMap<>();
        adjList.put("A", Arrays.asList("B", "C"));
        adjList.put("B", Arrays.asList("C", "D"));
        adjList.put("C", null);
        adjList.put("D", null);

        final DependencyGraph dependencyGraph = new DependencyGraph();
        dependencyGraph.createGraph(adjList);

        dependencyGraph.getLevels(Arrays.asList("A"));

        assertTrue(true);
    }

    @Test
    public void testTextIndicators() {
        final String levelZero = DependencyGraph.printNode(false, 0, "A");
        final String levelOne = DependencyGraph.printNode(false, 1, "A");
        final String levelTwo = DependencyGraph.printNode(false, 2, "A");
        final String levelThree = DependencyGraph.printNode(false, 3, "A");
        assertThat(levelZero, is(equalTo("A")));
        assertThat(levelOne, is(equalTo("|_ A")));
        assertThat(levelTwo, is(equalTo("|  |_ A")));
        assertThat(levelThree, is(equalTo("|     |_ A")));
    }

    @Test
    public void testTextEndIndicators() {
        final String levelZero = DependencyGraph.printNode(true, 0, "A");
        final String levelOne = DependencyGraph.printNode(true, 1, "A");
        final String levelTwo = DependencyGraph.printNode(true, 2, "A");
        final String levelThree = DependencyGraph.printNode(true, 3, "A");
        assertThat(levelZero, is(equalTo("A")));
        assertThat(levelOne, is(equalTo("\\_ A")));
        assertThat(levelTwo, is(equalTo("|  \\_ A")));
        assertThat(levelThree, is(equalTo("|     \\_ A")));
    }

}
