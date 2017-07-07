package com.scuilion.assignment;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DependencyReportTest {

    private final List<String> graphFile = Arrays.asList("A -> B", "A -> C", "B -> C", "B -> D");

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void crateReportWithFile() throws IOException {
        final File tempFile = tempFolder.newFile("graph.txt");
        FileUtils.writeStringToFile(tempFile, graphFile.stream().collect(Collectors.joining("\n")), Charset.defaultCharset());
        final DependencyReport dr = new DependencyReport(tempFile);
        assertThat(dr, is(not(nullValue())));
    }
}
