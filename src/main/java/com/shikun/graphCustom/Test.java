package com.shikun.graphCustom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by shikun on 2017/3/28.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("temp2.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            ArrayList<String> result=new ArrayList<String>();
            ArrayList<String> caller = new ArrayList<String>();
            ArrayList<String> callee = new ArrayList<String>();
            ArrayList<String> vertex=new ArrayList<String>();


            while(line != null){
                //遍历所有行数
                result.add(line);
                line = br.readLine();
            }

//            System.out.println(result);
            for(String temp:result){
                String[] tempList=temp.split(" ");

                vertex.add(tempList[0]);
                vertex.add(tempList[2]);

            }

            ArrayList<String> vertexResult = new ArrayList<String>(new HashSet<String>(vertex));

            int vertexNum=vertexResult.size();
            GraphAlo graph = new GraphAlo(vertexNum);
            for(int i=0;i<vertexResult.size();i++) {
                graph.insertVertex(vertexResult.get(i));
            }
            for (String temp : result) {
                String[] tempList = temp.split(" ");
                int startVertex = vertexResult.indexOf(tempList[0]);
                int endVertex = vertexResult.indexOf(tempList[2]);
                graph.insertEdge(startVertex, endVertex, 1);
            }

            System.out.println(graph.getNumbOfEdges());
            System.out.println(graph.getVertex());
            graph.getVertexList();
            System.out.println(graph.metrics1());
            System.out.println(graph.metrics3(6));
            graph.findBaseLine();
            graph.metric2();
            System.out.println(graph.findEntry());
            graph.findBaseLine();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }



}
