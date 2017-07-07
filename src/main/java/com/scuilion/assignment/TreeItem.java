package com.scuilion.assignment;

public class TreeItem {
    private int depth;
    private String name;
    private boolean last;

    TreeItem(int depth, String name, boolean last){
        this.depth = depth;
        this.name = name;
        this.last = last;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(final int depth) {
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(final boolean last) {
        this.last = last;
    }
}
