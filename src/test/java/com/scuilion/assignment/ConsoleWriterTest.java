package com.scuilion.assignment;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ConsoleWriterTest {

    @Test
    public void testTextIndicators() {
        final String levelZero = ConsoleWriter.printNode(false, 0, "A");
        final String levelOne = ConsoleWriter.printNode(false, 1, "A");
        final String levelTwo = ConsoleWriter.printNode(false, 2, "A");
        final String levelThree = ConsoleWriter.printNode(false, 3, "A");
        assertThat(levelZero, is(equalTo("A")));
        assertThat(levelOne, is(equalTo("|_ A")));
        assertThat(levelTwo, is(equalTo("|  |_ A")));
        assertThat(levelThree, is(equalTo("|     |_ A")));
    }

    @Test
    public void testTextEndIndicators() {
        final String levelZero = ConsoleWriter.printNode(true, 0, "A");
        final String levelOne = ConsoleWriter.printNode(true, 1, "A");
        final String levelTwo = ConsoleWriter.printNode(true, 2, "A");
        final String levelThree = ConsoleWriter.printNode(true, 3, "A");
        assertThat(levelZero, is(equalTo("A")));
        assertThat(levelOne, is(equalTo("\\_ A")));
        assertThat(levelTwo, is(equalTo("|  \\_ A")));
        assertThat(levelThree, is(equalTo("|     \\_ A")));
    }

}
