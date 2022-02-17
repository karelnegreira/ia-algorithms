package heuristics3;

public class Node {
    public Node parent;
    public int[][] matrix;
    //blank tile coordinate
    public int x, y;
    //number of misplaced tiles
    public int cost;
    //number of moves
    public int level;

    public Node(int[][] matrix, int x, int y, int newX, int newY, int level, Node parent) {
        this.parent = parent;
        this.matrix = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            this.matrix[i] = matrix[i].clone();
        }
        //swap values
        this.matrix[x][y] = this.matrix[x][y] + this.matrix[newX][newY];
        this.matrix[newX][newY] = this.matrix[x][y] - this.matrix[newX][newY];
        this.matrix[x][y] = this.matrix[x][y] - this.matrix[newX][newY];

        this.cost = Integer.MAX_VALUE;
        this.level = level;
        this.x = newX;
        this.y = newY;
    }

}
