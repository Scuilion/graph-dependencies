package com.scuilion.assignment;

import java.util.Collections;
import java.util.List;

public class ConsoleWriter {

    public static void write(final TreeNode tree) {
        final List<TreeItem> treeItems = tree.traverse();
        for(TreeItem ti : treeItems){
            System.out.println(printNode(ti.isLast(), ti.getDepth(), ti.getName()));
        }
    }

    static String printNode(final Boolean end, final int level, final String node) {
        final String delimiter = end ? "\\_ " : "|_ ";
        final String depLine;
        if (level == 0) {
            depLine = node;
        } else if (level == 1) {
            depLine = delimiter + node;
        } else {
            depLine = "|" + spaces(level) + delimiter + node;
        }
        return depLine;
    }

    private static String spaces(final int level) {
        final int spaces = level * 3 - 4;
        return String.join("", Collections.nCopies(spaces, " "));
    }
}
