package com.shikun.graphOutput;

import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.jgraph.graph.EdgeView;
import org.jgraph.graph.GraphCell;
import org.jgraph.graph.GraphLayoutCache;
import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.ext.JGraphModelAdapter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

/**
 * Created by shikun on 2017/3/12.
 */
public class ReadFile {

    public  JGraph jGraph;

    public  void parseText() throws Exception {

        BufferedReader br = new BufferedReader(new FileReader("temp4.txt"));
        DirectedGraph<String, DefaultEdge> graphInfo = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                //遍历所有行数
                String[] tempLine = line.split(" ");
                graphInfo.addVertex(tempLine[0]);
                graphInfo.addVertex(tempLine[2]);

                graphInfo.addEdge(tempLine[0], tempLine[2]);
                line = br.readLine();
            }
            System.out.println(graphInfo);
            JFrame frame = new JFrame();
            frame.setSize(400, 500);
             jGraph = new JGraph(new JGraphModelAdapter(graphInfo));
            JScrollPane scroller = new JScrollPane(jGraph);

            GraphLayoutCache cache = jGraph.getGraphLayoutCache();
//            Random r = new Random();
//            for (Object item : jGraph.getRoots()) {
//                GraphCell cell = (GraphCell) item;
//                CellView view = cache.getMapping(cell, true);
//                Rectangle2D bounds = view.getBounds();
//                bounds.setRect(r.nextDouble() * 1800, r.nextDouble() * 900, bounds.getWidth(), bounds.getHeight());
//            }


            frame.setVisible(true);
            frame.setExtendedState(Frame.MAXIMIZED_BOTH);
            frame.getContentPane().add(jGraph);
            frame.getContentPane().setBackground(Color.BLACK);


            frame.repaint();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();
        try {
            readFile.parseText();
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
    /*
    random vertex location
     */
    public void randomizeLocations() {

    }
    public void removeEdgeLabels() {
        GraphLayoutCache cache = jGraph.getGraphLayoutCache();
        CellView[] cells = cache.getCellViews();
        for (CellView cell : cells) {
            if (cell instanceof EdgeView) {
                EdgeView ev = (EdgeView) cell;
                DefaultEdge eval = (DefaultEdge) ev.getCell();
            }
        }
        cache.reload();
        jGraph.repaint();
    }


}
