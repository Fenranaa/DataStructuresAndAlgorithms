package cn.fenrana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    private List<String> vertextList; //储存顶点集合
    private int[][] edges; // 储蓄图对应的邻接矩阵
    private int numOfEdges; // 边的数目
    //定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;

    public Graph(int n) {
        vertextList = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     * 获得节点的第一个邻接点
     *
     * @return 节点的第一个邻接点的小标
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertextList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接点的下标来获取下一个邻接点
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertextList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历
     */
    public void dfs(boolean[] isVisited, int i) {
        //首先输出我们访问的节点
        System.out.print(getValueByIndex(i) + "->");
        //将节点设置成已访问
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }

            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 深度优先遍历
     */
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 广度优先遍历
     */
    public void bfs(boolean[] isVisited, int i) {
        int u; //表示队列的头结点对应下标
        int w; //邻接结点w
        //队列，记录结点访问的顺序
        LinkedList<Integer> queue = new LinkedList<>();
        //访问节点，输出节点访问顺序
        System.out.print(getValueByIndex(i) + "->");
        //标记节点已访问
        isVisited[i] = true;

        while (!queue.isEmpty()) {
            //取到队列的头节点下标
            u = (Integer) queue.removeFirst();
            // 得到第一个邻接点的
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    isVisited[i] = true;
                    //加入队列
                    queue.add(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }
    /**
     *遍历所有的节点广度优先遍历
     * */
    public void bfs() {
        isVisited = new boolean[vertextList.size()];
        for (int i = 0; i < getNumOfVertex(); i ++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //插入节点
    public void insertVertxt(String vertext) {
        vertextList.add(vertext);
    }
    //添加边

    /**
     * @param v1     表示点的下标即使第几个顶点  "A"-"B" "A"->0 "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回v1和v2的值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //返回节点i为下标的对应的数据
    public String getValueByIndex(int i) {
        return vertextList.get(i);
    }

    //图中常用的方法
    //返回结点的个数
    public int getNumOfVertex() {
        return vertextList.size();
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    public static void main(String[] args) {
        String Vertexs[] = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(5);
        //循环的添加顶点
        for (String vertex : Vertexs) {
            graph.insertVertxt(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1); // A-B
        graph.insertEdge(0, 2, 1); //
        graph.insertEdge(1, 2, 1); //
        graph.insertEdge(1, 3, 1); //
        graph.insertEdge(1, 4, 1); //

//        graph.showGraph();
        graph.dfs();
    }

}
