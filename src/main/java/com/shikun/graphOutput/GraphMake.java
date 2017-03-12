package com.shikun.graphOutput;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

/** construct graph data structure from method call information
 * Created by shikun on 2017/3/11.
 */
public class GraphMake {



    public void constructDirectGraph(){
        DirectedGraph<String, DefaultEdge> methodGraph = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        methodGraph.addVertex("a");
        methodGraph.addVertex("b");
        methodGraph.addEdge("a", "b");
    }


}
