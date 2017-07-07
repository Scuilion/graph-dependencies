package com.netflix.assignment;

import com.netflix.assignment.ex.BadInputException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;

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
