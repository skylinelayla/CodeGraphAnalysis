package com.shikun.graphOutput;

import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgrapht.graph.*;
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * Created by shikun on 2017/3/26.
 */
public class GrahpTest {
    public static void main(String[] args) {
        GraphModel model = new DefaultGraphModel();
        GraphLayoutCache view = new GraphLayoutCache(model, new DefaultCellViewFactory());
        JGraph graph = new JGraph(model, view);
        DefaultGraphCell[] cells = new DefaultGraphCell[3];
        cells[0] = new DefaultGraphCell(new String("Hello"));
        GraphConstants.setBounds(cells[0].getAttributes(), new Rectangle2D.Double(20, 20, 40, 20));
        GraphConstants.setGradientColor(cells[0].getAttributes(), Color.orange);
        GraphConstants.setOpaque(cells[0].getAttributes(), true);
        DefaultPort port0 = new DefaultPort();
        cells[0].add(port0);
        cells[1] = new DefaultGraphCell(new String("World"));
        GraphConstants.setBounds(cells[1].getAttributes(), new Rectangle2D.Double(140, 140, 20, 20));
        GraphConstants.setGradientColor(cells[1].getAttributes(), Color.orange);
        GraphConstants.setOpaque(cells[1].getAttributes(), true);
        DefaultPort port1 = new DefaultPort();
        cells[1].add(port1);
//        DefaultEdge edge = new DefaultEdge();
//        edge.setSource(cells[0].getChildAt(0));
//        edge.setTarget(cells[1].getChildAt(0));
//        cells[2]=edge;
        org.jgraph.graph.DefaultEdge edge = new org.jgraph.graph.DefaultEdge();
        edge.setSource(cells[0].getChildAt(0));
        edge.setTarget(cells[1].getChildAt(0));
        cells[2] = edge;

        int arrow = GraphConstants.ARROW_CLASSIC;
        GraphConstants.setLineEnd(edge.getAttributes(), arrow);
        GraphConstants.setEndFill(edge.getAttributes(), true);


        graph.getGraphLayoutCache().insert(cells);
        System.out.println(model.getRootCount());
        System.out.println(model.getRootAt(0));
        System.out.println(model.getRootAt(1));
        System.out.println(model.getIndexOfRoot(edge));
        System.out.println(model.contains(cells[1]));
//        System.out.println(model.getSource(edge) + " " + model.getTarget(edge));
//        DefaultGraphCell cell2 = model.getSource(edge);
        System.out.println(model.getSource(edge) instanceof DefaultGraphCell);
        System.out.println(model.isEdge(edge));
        System.out.println(model.getChildCount(cells[1]));
        System.out.println(model.getChild(cells[0], 0));

//        JFrame frame = new JFrame();
//        frame.getContentPane().add(new JScrollPane(graph));
//        frame.pack();
//        frame.setVisible(true);

        java.util.List listEdges = new ArrayList();
        int numbChildren = model.getChildCount(cells);
        for(int i=0;i<numbChildren;i++) {
            Object port = model.getChild(cells, i);
            if (model.isPort(port)) {
                Iterator iter = model.edges(port);
                while (iter.hasNext()) {
                    listEdges.add(iter.next());

                }
            }
        }

        System.out.print(listEdges);

        Object sourceVertex = model.getParent(model.getSource(edge));
        Object targetVertex = model.getParent(model.getTarget(edge));
        System.out.println(sourceVertex + " " + targetVertex);



    }
}
