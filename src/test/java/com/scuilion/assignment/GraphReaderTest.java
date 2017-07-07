package com.scuilion.assignment;

import com.scuilion.assignment.ex.BadInputException;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GraphReaderTest {

    final GraphReader graphReader = new GraphReader();

    @Test
    public void createSimpleTree() {
        final List<String> graphFile = Arrays.asList("A -> B", "A -> C", "B -> C", "B -> D");
        final TreeNode tree = graphReader.parse(graphFile);

        List<String> aChildren = tree.getChildAt("A").children().stream().map(TreeNode::getName).collect(Collectors.toList());
        assertThat(aChildren, is(equalTo(Arrays.asList("B", "C"))));

        List<String> bChildren = tree.getChildAt("B").children().stream().map(TreeNode::getName).collect(Collectors.toList());
        assertThat(bChildren, is(equalTo(Arrays.asList("C", "D"))));

        tree.toString();
    }

    @Test(expected = BadInputException.class)
    public void badInput() {
        graphReader.parse(Collections.singletonList("bad"));
    }
}
