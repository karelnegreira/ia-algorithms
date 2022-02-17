package heuristics3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
BRANCH AND BOUND ALGORITHM ***
 *The ideal Cost function for an 8-puzzle Algorithm :
We assume that moving one tile in any direction will have a 1 unit cost. Keeping that in mind,
* we define a cost function for the 8-puzzle algorithm as below:

   c(x) = f(x) + h(x) where
   f(x) is the length of the path from root to x
        (the number of moves so far) and
   h(x) is the number of non-blank tiles not in
        their goal position (the number of mis-
        -placed tiles). There are at least h(x)
        moves to transform state x to a goal state
 *
 */

public class Puzzle {
    public int dimension = 3;
    //botton left top right
    int row[] = {1, 0, -1, 0};
    int col[] = {0, -1, 0, 1};

    public static void main(String[] args) {
        int[][] initial = { {1, 8, 2}, {0, 4, 3}, {7, 6, 5} };
        int[][] goal    = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };
        //white tiles coordinate
        int x = 1, y = 0;
        Puzzle puzzle = new Puzzle();
        if (puzzle.isSolvable(initial)) {
            puzzle.solve(initial, goal, x, y);
        } else {
            System.out.println("THE PUZZLE CANNOT BE SOLVED!");
        }
    }

    public int calculateCost(int[][] initial, int[][] goal) {
        int count = 0;
        int n = initial.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (initial[i][j] != 0 && initial[i][j] != goal[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isSafe(int x, int y) {
        return (x >= 0 && x < dimension && y >= 0 && y < dimension);
    }

    public void printPath(Node root) {
        if (root == null) {
            return;
        }
        printPath(root.parent);
        printMatrix(root.matrix);
        System.out.println();
    }

    public boolean isSolvable(int[][] matrix) {
        int count = 0;
        List<Integer> array = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                array.add(matrix[i][j]);
            }
        }
        Integer[] anotherArray = new Integer[array.size()];
        array.toArray(anotherArray);

        for (int i = 0; i < anotherArray.length; i++) {
            for (int j = i + 1; j < anotherArray.length; j++) {
                if (anotherArray[i] != 0 && anotherArray[j] != 0 && anotherArray[i] > anotherArray[j]) {
                    count++;
                }
            }
        }

        return count % 2 == 0;
    }

    public void solve(int[][] initial, int[][] goal, int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>(1000, (a, b) -> (a.cost + a.level) - (b.cost + b.level) );
        Node root = new Node(initial, x, y, x, y, 0, null);
        root.cost = calculateCost(initial, goal);
        pq.add(root);

        while (!pq.isEmpty()) {
            Node min = pq.poll();
            if (min.cost == 0) {
                printPath(min);
                return;
            }
            //--> there are four directions the piece can actually go...
            for (int i = 0; i < 4; i++) {
                if (isSafe(min.x + row[i], min.y + col[i])) {
                    Node child = new Node(min.matrix, min.x, min.y, min.x + row[i], min.y + col[i], min.level + 1, min);
                    child.cost = calculateCost(child.matrix, goal);
                    pq.add(child);
                }
            }
        }
    }

}
