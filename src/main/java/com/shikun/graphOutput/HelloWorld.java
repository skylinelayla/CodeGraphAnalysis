package com.shikun.graphOutput;

import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by shikun on 2017/3/12.
 */
public class HelloWorld {
    private HelloWorld() {

    }

    public static void main(String[] args) {


        DirectedGraph<URL, DefaultEdge> hrefGraph = createHrefGraph();
        System.out.println(hrefGraph.toString());

        UndirectedGraph<String, DefaultEdge> stringGraph = createStringGraph();
        System.out.println(stringGraph);
    }

    private static DirectedGraph<URL,DefaultEdge> createHrefGraph() {
        DirectedGraph<URL, DefaultEdge> g = new DefaultDirectedGraph<URL, DefaultEdge>(DefaultEdge.class);
        try {
            URL amazon = new URL("[http://www.amazon.com](http://www.amazon.com)");
            URL yahoo = new URL("[http://www.yahoo.com](http://www.yahoo.com)");
            URL ebay = new URL("[http://www.ebay.com](http://www.ebay.com)");

            // add the vertices
            g.addVertex(amazon);
            g.addVertex(yahoo);
            g.addVertex(ebay);

            // add edges to create linking structure
            g.addEdge(yahoo, amazon);
            g.addEdge(yahoo, ebay);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return g;
    }

    private static UndirectedGraph<String,DefaultEdge> createStringGraph(){
        UndirectedGraph<String, DefaultEdge> g = new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);
        return g;
    }

}
