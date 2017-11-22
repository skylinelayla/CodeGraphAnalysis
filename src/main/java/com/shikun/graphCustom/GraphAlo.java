package com.shikun.graphCustom;
/**
 * @author lijiechu
 * @create on 17/4/11
 * @description
 */
import java.lang.reflect.Array;
import java.util.*;

public class GraphAlo {
    private ArrayList vertexList;
    private int[][] edges;
    private int  numbOfEdges;
    private ArrayList baseLine = new ArrayList();
    //Use vector to simulate a traversable stack
    private Vector stackifyVector = new Vector();
    //All the baselines
    private Vector<Vector> baselines = new Vector<Vector>();

    private Set resultSet = new HashSet();
    public Set getResultSet(){
        return this.resultSet;
    }

    //基线路径
    private Vector basePath = new Vector();
    //基线路径组
    private Vector<Vector> basePathCollection = new Vector<Vector>();

    public GraphAlo(int n) {
        this.edges=new int[n][n];
        this.vertexList = new ArrayList(n);
        this.numbOfEdges=0;

    }



    //还需添加去重功能

    //获取节点个数
    public int getVertex() {
        return this.vertexList.size();
    }
    //输入节点顺序数组
    public void getVertexList(){
        for(int i=0;i<this.vertexList.size();i++) {
            System.out.println(this.vertexList.get(i));
        }
    }

    //获取边的个数
    public int getNumbOfEdges(){
        return this.numbOfEdges;
    }

    //返回节点i的数据
    public Object getValueByIndex(int i) {
        return this.vertexList.get(i);
    }

    //返回v1 v2权值（如果有的话）
    public int getWeight(int v1, int v2) {
        return this.edges[v1][v2];
    }

    //插入节点
    public void insertVertex(Object vertex) {
        vertexList.add(this.vertexList.size(), vertex);
    }

    //插入边
    public void insertEdge(int v1, int v2, int weight) {
        this.edges[v1][v2]=weight;
        this.numbOfEdges++;
    }

    //删除边
    public void deleteEdge(int v1, int v2) {
        this.edges[v1][v2]=0;
        numbOfEdges--;
    }
    //显示二维数组
    public void displayArray() {
        for(int i=0;i<this.edges.length;i++) {
            System.out.println(" ");
            for(int j=0;j<this.edges[i].length;j++) {
                System.out.print(this.edges[i][j]);
            }

        }
    }
    //遍历矩阵每列，统计每列矩阵的1的个数,比如取得第n列元素
    public int totalN(int n){
        ArrayList tempColumn = new ArrayList();
        int N=0;
        for(int i=0;i<this.edges.length;i++) {
            tempColumn.add(this.edges[i][n]);
        }
        for(int i=0;i<tempColumn.size();i++) {
            if ((Integer)tempColumn.get(i) == 1) {
                N++;
            }
        }

        return N;

    }
    //遍历矩阵每列，统计为1的索引值。
    public ArrayList totalColumnN(int n){
        ArrayList tempColumn = new ArrayList();
        ArrayList result = new ArrayList();
        int N=0;
        for(int i=0;i<this.edges.length;i++) {
            tempColumn.add(this.edges[i][n]);
        }
        for(int i=0;i<tempColumn.size();i++) {
            if ((Integer) tempColumn.get(i) == 1) {
                result.add(i);
            }
        }
        return result;
    }

    //遍历矩阵每行，统计为1的索引值
    public ArrayList totalRowN(int n) {
        ArrayList tempRow = new ArrayList();
        ArrayList result = new ArrayList();
        for(int i=0;i<this.edges.length;i++) {
            tempRow.add(this.edges[n][i]);
        }
        for(int i=0;i<tempRow.size();i++) {
            if ((Integer) tempRow.get(i) == 1) {
                result.add(i);
            }
        }

        return result;
    }

    //合并两个集合，剔除重复
    public Set join(ArrayList arrayList1, ArrayList arrayList2) {
        Set result = new TreeSet(arrayList1);
        for (Object i : arrayList2) {
            result.add(i);
        }

        return result;
    }

    //度量元1计算方法
    public ArrayList metrics1(){
        ArrayList metrics = new ArrayList();

        for(int i=0;i<this.getVertex();i++) {
            metrics.add(this.totalN(i));
        }
        //找到最大元素索引值，还要考虑多个元素重复值。
        ArrayList result = new ArrayList();
        int max = this.findMax(metrics);

        for(int i=0;i<metrics.size();i++) {
            if ((Integer)metrics.get(i) == max) {
                result.add(i);
            }
        }
        ArrayList classResult = new ArrayList();
        //根据索引值找到元素
        for(int i=0;i<result.size();i++) {
            classResult.add(this.vertexList.get((Integer) result.get(i)));

        }

        return classResult;
    }

    public int findMax(ArrayList arrayList) {
        //找到最大元素
        int max=0;
        for(int i=0;i<arrayList.size();i++) {
            if((Integer)arrayList.get(i)>max){
                max=(Integer) arrayList.get(i);
            }
        }
        System.out.println(max);
        return max;
    }
    //度量元3计算方法
    public ArrayList metrics3(int n) {
        ArrayList result = new ArrayList();
        //已经知道索引为n的方法具有缺陷，需要找到该方法所在行和所在列的元素
        ArrayList tempColumn = totalColumnN(n);
        ArrayList tempRow = totalRowN(n);
        Set tempResult = new TreeSet();
        tempResult = join(tempColumn, tempRow);

        Iterator it = tempResult.iterator();
        while (it.hasNext()) {
            result.add(this.vertexList.get((Integer) it.next()));

        }
        return result;
    }

    //找到每行的出度数量，即节点的出度数量，还需要记录出度节点
    public ArrayList findEntry(){
        ArrayList result = new ArrayList();
        ArrayList tempRow = new ArrayList();
        //遍历矩阵每行
        for (int i = 0; i < this.edges.length; i++) {
            //格式【N,NODE1，NODE2，NODE3】
            TempRowInfo tempRowInfo = new TempRowInfo();
            int N=0;
            for(int j=0;j<this.edges.length;j++){
                if (this.edges[i][j] == 1) {
                    N++;
                    tempRowInfo.setNodelist(j);
                }
                tempRowInfo.setNumberofEntry(N);
            }
            result.add(tempRowInfo.constructMap());
        }
        return result;
    }
    //度量元2计算方法
    public void metric2(){
        //首先计算出独立路径总数
        int pathNumer = this.getNumbOfEdges() - this.vertexList.size() + 2;
        System.out.println(pathNumer);



    }
    public void findBaseLine(){
        ArrayList pathBaseStart = new ArrayList();
        int startIndex=0;
        ArrayList tempNodeList = new ArrayList();
        //得到基线路径，首先需要确定起点，基线路径不唯一
        ArrayList pathTotal = findEntry(); //遍历的结果都在pathTotal中
        for(int i=0;i<pathTotal.size();i++) {
            tempNodeList=(ArrayList) ((Map) pathTotal.get(i)).get("nodeList");
            //判断条件出度为1，

            if ((Integer) ((Map) pathTotal.get(i)).get("NumberofEntry") >= 1 && (Integer) ((Map) pathTotal.get(i)).get("NumberofEntry") != 0 ) {

                pathBaseStart.add(i);//起点数组.

            }
        }
        //所有起点的数组,找到最长的作为基线路径
        for(int i=0;i<pathBaseStart.size();i++) {
            ArrayList tempPathTotal=pathTotal;
            int tempStart=(Integer) pathBaseStart.get(i);
            this.baseLine = new ArrayList();

            this.stackifyVector = new Vector();

            findNext(tempStart, pathTotal, 1);


        }

        System.out.println(this.baselines);
        //找到长度最长的元素

        int max=0;
        Vector resultBaseline = new Vector();

        for(int i=0;i<this.baselines.size();i++) {

            if (this.baselines.get(i).size() > max) {
                max = this.baselines.get(i).size();

            }

        }
        for(int i=0;i<this.baselines.size();i++) {
            if (this.baselines.get(i).size() == max) {
                resultBaseline = this.baselines.get(i);
            }
        }

//        System.out.println(resultBaseline);
//        findPaths(resultBaseline, pathTotal);
//        System.out.println(findPaths(resultBaseline, pathTotal));

    }

    /*
    startIndex是起点的索引值
    arrayList是pathTotal，即需要遍历的所有信息.递归方法
     */

    public Vector findNext(int startIndex, ArrayList arrayList, int depth) {

        if(this.stackifyVector.contains(startIndex)){

            this.baselines.add((Vector) stackifyVector.clone());
            this.baseLine.add(stackifyVector.clone());

            return this.stackifyVector;
        }else {
            this.stackifyVector.add(startIndex);//添加起点元素
            Map startElement = (Map) arrayList.get(startIndex);
            ArrayList nodeInfoList = (ArrayList) startElement.get("nodeList");

            if (nodeInfoList.size() == 0) {

                return this.stackifyVector;

            } else {
                for (int i = 0; i < nodeInfoList.size(); i++) {
                    //popup simulation
                    this.stackifyVector.setSize(depth);
                    findNext((Integer) nodeInfoList.get(i), arrayList,depth+1);

                }
            }
        }
        return this.stackifyVector;
    }

    //参数基线路径，和pathTotal
//    private Vector findPaths(Vector vector, ArrayList arrayList) {
//        //根据基线路径寻找基路径，每个节点判断是否有出度大于等于2的节点
//
//        this.basePath = vector;
//
//        for(int i=0;i<vector.size();i++) {
//            Map tempNodeInfo = (Map) arrayList.get((Integer) vector.get(i));
//            ArrayList nodelistInfo=(ArrayList) tempNodeInfo.get("nodeList");
//
//            if ((Integer)tempNodeInfo.get("NumberofEntry") >= 1) {
//                //将nodelist第二个点或者第三个点加入到basePath中
//                nodelistInfo.remove(vector.get(i+1));
//
//
//                    for (int j = 0; j < nodelistInfo.size(); j++) {
//                        this.basePath.set(i + 1, nodelistInfo.get(j));
//                        System.out.println(this.basePath);
//                        return findPaths(this.basePath, arrayList);
//                    }
//
//
//
//            }else{
//
//
//
//                return this.basePath;
//
//            }
//        }
//
//        return this.basePath;
//    }








    public static void main(String[] args) {

        int n=7;
        String[] labels = {"A", "B", "C", "D", "E", "F", "G"};
        GraphAlo graphAlo = new GraphAlo(n);
        for (String label : labels) {
            graphAlo.insertVertex(label);
        }
        //插入边

        graphAlo.insertEdge(0, 1, 1);
        graphAlo.insertEdge(0, 3, 1);
        graphAlo.insertEdge(1, 4, 1);
        graphAlo.insertEdge(3, 4, 1);
        graphAlo.insertEdge(3, 5, 1);
        graphAlo.insertEdge(4, 5, 1);
        graphAlo.insertEdge(1, 2, 1);
        graphAlo.insertEdge(2, 1, 1);
        graphAlo.insertEdge(2, 6, 1);
        graphAlo.insertEdge(5, 6, 1);


        graphAlo.displayArray();

        System.out.println(graphAlo.findEntry());
        ArrayList result = new ArrayList();
        graphAlo.findBaseLine();



    }

    public ArrayList tempResult = new ArrayList();
    public int timesCount=0;

    public void findFirstBase(ArrayList arrayList,int startIndex) {
        timesCount++;

        //得到起点元素

//        ArrayList tempResult = new ArrayList();
        Map startElement = (Map) arrayList.get(startIndex);
        ArrayList startElementNodeInfo=(ArrayList)startElement.get("nodeList");


            this.tempResult.add(startIndex);
            for (int i = 0; i < startElementNodeInfo.size(); i++) {
                if (!this.tempResult.contains(startElementNodeInfo.get(i))) {
                    this.tempResult.add(startElementNodeInfo.get(i));
                    findFirstBase(arrayList, (Integer) startElementNodeInfo.get(i));
                } else {
                    continue;
                }
            }
            this.resultSet.add(tempResult);
        }


}

class TempRowInfo{
    private int NumberofEntry;
    private ArrayList nodelist = new ArrayList();

    public int getNumberofEntry() {
        return NumberofEntry;
    }

    public void setNumberofEntry(int numberofEntry) {
        NumberofEntry = numberofEntry;
    }

    public ArrayList getNodelist() {
        return nodelist;
    }

    public void setNodelist(int nodeIndex) {
        this.nodelist.add(nodeIndex);

    }

    @Override
    public String toString() {
        return "TempRowInfo{" +
                "NumberofEntry=" + NumberofEntry +
                ", nodelist=" + nodelist +
                '}';
    }

    public Map constructMap() {
        Map map = new HashMap();
        map.put("NumberofEntry", this.NumberofEntry);
        map.put("nodeList", this.nodelist);
        return map;
    }
}


