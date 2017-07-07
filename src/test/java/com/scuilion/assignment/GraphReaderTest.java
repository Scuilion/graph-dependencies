package com.scuilion.assignment;

import com.scuilion.assignment.ex.BadInputException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GraphReaderTest {

    private final List<String> graphFile = Arrays.asList("A -> B", "A -> C", "B -> C", "B -> D");

    @Test
    public void createsAdjacencyList() {
        final Map<String, List<String>> adjList = GraphReader.parse(graphFile);
        assertThat(adjList.get("A"), is(equalTo(Arrays.asList("B", "C"))));
        assertThat(adjList.get("B"), is(equalTo(Arrays.asList("C", "D"))));
        assertThat(adjList.size(), is(equalTo(2)));
    }

    @Test(expected = BadInputException.class)
    public void badInput() {
        GraphReader.parse(Collections.singletonList("bad"));
    }
}
