package com.shikun.graphCustom;

import java.util.*;

public class Graph {

    private int[][] G;//邻接矩阵
    private int k;//顶点数目
    private boolean[] visited;//判断顶点是否被访问过

    public Graph(int k,int[][] G){
        this.k=k;
        this.G=G;
        visited = new boolean[k];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k;
        k = sc.nextInt(); //顶点数(顶点为0,1,2,...k-1)
        int m=sc.nextInt();//边数

        //构建邻接矩阵
        int[][] G = new int[k][k];
        for (int i = 0; i <m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            G[u][v] = 1;
            G[v][u] = 1;//无向图的对称
        }
        Graph g=new Graph(k,G);
        g.bfs(0);
//         g.dfs(0);
        System.out.println();
    }

    //广搜
    private void bfs(int v) {
        //队列用来保存被访问节点的分支节点
        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(v);
        while (!que.isEmpty()) {
            v = que.poll();
            System.out.print(v+" ");
            visited[v] = true;
            //将被访问节点的分支节点加入到队列中
            for (int i = 0; i < k; i++) {
                if (G[v][i] == 1 && !visited[i]) {
                    que.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    //深搜
    private  void dfs(int v) {
        visited[v] = true;
        System.out.print(v+" ");
        for (int i = 0; i <k; i++) {
            //递归调用搜索没有被访问过的当前节点的下一个节点(邻接点)
            if (G[v][i] == 1 && !visited[i])
                dfs(i);
        }
    }

}
