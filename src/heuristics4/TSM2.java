package heuristics4;
/*
* TRAVEL SALESMAN PROBLEM USING BACKTRACKING ALGORITHM
* */
public class TSM2 {
    public static void main(String[] args) {
        //n is the number of nodes i. e v
        int n = 4;
        int[][] grapth = {{0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}};

        // Boolean array to check if a node
        // has been visited or not
        boolean v[] = new boolean[n];
        //mark the 0th node as visited
        v[0] = true;
        int ans = Integer.MAX_VALUE;
        // Find the minimum weight Hamiltonian Cycle
        ans = TSP(grapth, v, 0, n, 1, 0, ans);

        System.out.println(ans);
    }

    // Function to find the minimum weight
    // Hamiltonian Cycle
    static int TSP(int[][] graph, boolean[] v, int currPos, int n, int count, int cost, int ans) {
        // If last node is reached and it has a link
        // to the starting node i.e the source then
        // keep the minimum value out of the total cost
        // of traversal and "ans"
        // Finally return to check for more possible values
        if (count == n && graph[currPos][0] > 0) {
            ans = Math.min(ans, cost + graph[currPos][0]);
            return ans;
        }
        // BACKTRACKING STEP
        // Loop to traverse the adjacency list
        // of currPos node and increasing the count
        // by 1 and cost by graph[currPos,i] value
        for (int i = 0; i < n; i++) {
            if (v[i] == false && graph[currPos][i] > 0) {
                //mark as visited
                v[i] = true;
                ans = TSP(graph, v, i, n, count + 1, cost + graph[currPos][i], ans);
                //mark the ith node as unvisited
                v[i] = false;
            }
        }
        return ans;
    }
}
