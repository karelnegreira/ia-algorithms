package uninformedsearch;

import java.util.Iterator;
import java.util.LinkedList;

public class DFSGrapth {
    private int V;
    //array that lists
    //all adjacency nodes to a given one
    private LinkedList<Integer> adj[];

    public static void main(String[] args) {
        DFSGrapth g = new DFSGrapth(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println(
                "Following is Depth First Traversal "
                        + "(starting from vertex 2)");

        g.DFS(2);
    }

    public DFSGrapth(int v) {
        this.V = v;
        adj = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    //function to add edge into the grapth
    private void addEdge(int v, int w) {
        adj[v].add(w);
    }
    //a function used by DFS
    void DFSUtil(int v, boolean[] visited) {
        //it marks the current node as visited and prints it...
        visited[v] = true;
        System.out.println(v + " ");
        //recur for all vertexes adjacent to this vertex.
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }
    // The function to do DFS traversal.
    // It uses recursive
    // DFSUtil()
    void DFS(int v) {
        boolean visited[] = new boolean[V];
        // Mark all the vertices as
        // not visited(set as
        // false by default in java)
        DFSUtil(v, visited);
    }

}
