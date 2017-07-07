package com.netflix.assignment;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GraphReaderTest {

    private List<String> graphFile = Arrays.asList("A -> B", "A -> C", "B -> C", "B -> D");

    @Test
    public void createsAdjacencyList() {
        Map<String, List<String>> adjList = GraphReader.parse(graphFile);
        assertThat(adjList.get("A"), is(equalTo(Arrays.asList("B", "C"))));
        assertThat(adjList.get("B"), is(equalTo(Arrays.asList("C", "D"))));
        assertThat(adjList.size(), is(equalTo(2)));
    }

    @Test(expected = RuntimeException.class)
    public void badInput() {
        GraphReader.parse(Arrays.asList("bad"));
    }
}
