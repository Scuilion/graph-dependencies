package com.netflix.assignment;

import com.netflix.assignment.ex.BadInputException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GraphReaderTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private List<String> graphFile = Arrays.asList("A -> B", "A -> C", "B -> C", "B -> D");

    @Test
    public void createsAdjacencyList() {
        Map<String, List<String>> adjList = GraphReader.parse(graphFile);
        assertThat(adjList.get("A"), is(equalTo(Arrays.asList("B", "C"))));
        assertThat(adjList.get("B"), is(equalTo(Arrays.asList("C", "D"))));
        assertThat(adjList.size(), is(equalTo(2)));
    }

    @Test
    public void createAdjacencyListFromFile() throws IOException {
        final File tempFile = tempFolder.newFile("graph.txt");
        FileUtils.writeStringToFile(tempFile, graphFile.stream().collect(Collectors.joining("\n")), Charset.defaultCharset());

        Map<String, List<String>> adjList = GraphReader.parse(tempFile.getPath());
        assertThat(adjList.get("A"), is(equalTo(Arrays.asList("B", "C"))));
        assertThat(adjList.get("B"), is(equalTo(Arrays.asList("C", "D"))));
        assertThat(adjList.size(), is(equalTo(2)));
    }

    @Test(expected = BadInputException.class)
    public void badFileInput() {
        GraphReader.parse("no-existing-file.txt");
    }

    @Test(expected = BadInputException.class)
    public void badInput() {
        GraphReader.parse(Arrays.asList("bad"));
    }
}
