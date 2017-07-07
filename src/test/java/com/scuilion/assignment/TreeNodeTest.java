package com.scuilion.assignment;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TreeNodeTest {

    @Test
    public void testTraverse() {

        final TreeNode a = new TreeNode("A");
        a.add("A", "B");
        a.add("B", "C");
        a.add("B", "D");
        a.add("B", "Z");
        a.add("A", "E");

        final List<TreeItem> listNode = a.traverse();
        assertThat(listNode, is(not(nullValue())));
    }


}
