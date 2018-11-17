package algo;

import java.util.*;

class KruskalGraph
{
    int V =0, E=0;
    Edge[] edge;
    class Edge{
        int src, dest ,weight;
        Edge(){
            src = dest = weight = 0;
        }
    }
    KruskalGraph(int v, int e){
        this.V = v;
        this.E = e;
        this.edge = new Edge[e];
        for(int i=0; i<e; i++){
            edge[i] = new Edge();
        }
    }
    void addEdge(int i, int s, int d, int w){
        edge[i].src=s;
        edge[i].dest=d;
        edge[i].weight=w;
    }
}

public class Kruskal {
    public boolean isCycle(KruskalGraph graph, boolean[] mst){
        
        
////        int[] mstVertex = new int[graph.V];
//        Set<Integer> mstVertex = new HashSet<>();
////        KruskalGraph.Edge[] mstEdge = new KruskalGraph.Edge[mst];
//        for(int i=0; i< mst.length ;i++){
//            if(mst[i] == true){
//                if(mstVertex.contains(graph.edge[i].src) && mstVertex.contains(graph.edge[i].dest)){
//                    return true;
//                }
//                mstVertex.add(graph.edge[i].dest);
//                mstVertex.add(graph.edge[i].src);
//                
////                for(int j=0; j < mstVertex.length ; j++){
////                    mstVertex[j]=graph.edge[i].src;
////                    mstVertex[j]=graph.edge[i].dest;
////                }
//            }
////            mstEdge[i] = new KruskalGraph.Edge();
//        }
        return false;
    }
    class subset{
        int parent, rank;
    }
    public int FindSet(subset[] subsets, int i){
        if(subsets[i].parent!=i)
            subsets[i].parent = FindSet(subsets, subsets[i].parent);
        return subsets[i].parent;
    }
    public void UnionSet(subset[] subsets, int x, int y){
        int xroot  = FindSet(subsets, x);
        int yroot  = FindSet(subsets, y);
        
        if(subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if(subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else{
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
    public void KruskalAlgo(KruskalGraph graph){
        KruskalSortEdge(graph);
        boolean[] mst = new boolean[graph.edge.length];
        subset[] subsets = new subset[graph.V];
        
        for(int j=0; j < graph.V; j++){
            subsets[j] = new subset();
            subsets[j].parent = j;
            subsets[j].rank = 0;
        }
        
        for(int i=0; i < graph.edge.length; i++)
        {
            int x = FindSet(subsets, graph.edge[i].src);
            int y = FindSet(subsets, graph.edge[i].dest);
            if(x!=y){
                mst[i] = true;
                UnionSet(subsets, x, y);
            }
        }
//        for(int i = 0; i<graph.edge.length; i++ ){
//            mst[i]=true;
//            System.out.println(isCycle(graph, mst));
//            if(isCycle(graph, mst)){  //true if forms cycle, false if not forms cycle
//                mst[i] = false;
//            }
//        }
        
        for (int i=0; i<graph.edge.length; i++) {
            System.out.println(mst[i]+",w="+graph.edge[i].weight+",s="+graph.edge[i].src + ",d=" + graph.edge[i].dest  + " ");
        }
        
        
        
    }
    public void KruskalSortEdge(KruskalGraph graph){
        //Using Insertion Sorting
        int key,i;
        for(int j=1; j<graph.edge.length; j++){
            key = graph.edge[j].weight;
            KruskalGraph.Edge[] e = graph.edge.clone();
            i=j-1;
            while(i>=0 && graph.edge[i].weight > key){
                    graph.edge[i+1] = graph.edge[i];
                    i--;
            }
            i++;
            graph.edge[i]=e[j];
        }
    }
    public static void main(String[] args) {
        KruskalGraph graph = new KruskalGraph(9,14);
        graph.addEdge(0,0,1,4);//i,s,d,w
        graph.addEdge(1,0,7,8);
        graph.addEdge(2,1,7,11);
        graph.addEdge(3,1,2,8);
        graph.addEdge(4,2,3,7);
        graph.addEdge(5,2,8,2);
        graph.addEdge(6,2,5,4);
        graph.addEdge(7,3,4,9);
        graph.addEdge(8,3,5,14);
        graph.addEdge(9,4,5,10);
        graph.addEdge(10,5,6,2);
        graph.addEdge(11,6,7,1);
        graph.addEdge(12,6,8,6);
        graph.addEdge(13,7,8,7);
        
        Kruskal krus = new Kruskal();
        krus.KruskalAlgo(graph);
        

    }
}
