package com.scuilion.assignment;

import java.util.ArrayList;
import java.util.List;

class TreeNode {

    private final String name;
    private List<TreeNode> children = new ArrayList<>();
    private Boolean last = false;

    TreeNode(final String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName(){
       return name;
    }

    public void add(final String source, final String dest) {
        if (this.name.equals(source)) {
            this.children.add(new TreeNode(dest));
            setLast();
        } else {
            for (final TreeNode c : this.children) {
                c.add(source, dest);
            }
        }
    }

    private void setLast() {
        this.children.forEach(c -> c.last = false);
        this.children.get(this.children.size() -1 ).last = true;
    }

    public TreeNode getChildAt(final String source) {
        TreeNode child = null;
        if (this.name.equals(source)) {
            child = this;
        } else {
            for (final TreeNode children : this.children) {
                TreeNode temp = children.getChildAt(source);
                child = temp != null ? temp : child;
            }
        }
        return child;
    }

    public List<TreeNode> children(){
        return children;
    }

    public List<TreeItem> traverse() {
        return traverse(new ArrayList<TreeItem>(), 0, this);

    }

    private List<TreeItem> traverse(List<TreeItem> items, int depth, TreeNode node) {
        items.add(new TreeItem(depth, node.name, node.last));
        ++depth;
        for (int i = 0; i < node.children.size(); i++) {
            traverse(items, depth, node.children.get(i));
        }
        return items;
    }

}
