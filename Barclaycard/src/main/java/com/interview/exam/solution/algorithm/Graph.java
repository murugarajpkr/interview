package com.interview.exam.solution.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by murugaraj on 4/9/2017.
 */
public class  Graph {

    private Set<Node> nodes = new HashSet<Node>();

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

}
