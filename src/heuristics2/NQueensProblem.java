package heuristics2;

public class NQueensProblem {
    final int N = 8;

    public static void main(String[] args) {
        NQueensProblem nQueensProblem = new NQueensProblem();
        nQueensProblem.solveNQ();
    }

     /* This function solves the N Queen problem using
       Backtracking.  It mainly uses solveNQUtil () to
       solve the problem. It returns false if queens
       cannot be placed, otherwise, return true and
       prints placement of queens in the form of 1s.
       Please note that there may be more than one
       solutions, this function prints one of the
       feasible solutions.*/
    public boolean solveNQ() {
        int board[][] = {
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0 }
        };
        if (solveNUtil(board, 0) == false) {
            System.out.println("THE SYSTEM CANNOT FIND A SOLUTION");
            return false;
        }
        printSolution(board);
        return true;
    }

    /**/
    public boolean solveNUtil(int board[][], int col) {
        /*Base case: if all queens are placed then return true*/
        if (col >= N) {
            return true;
        }
        /*
        * Considere this column and try placing
        * this queen in all rows one by one
        * */
        for (int i = 0; i < N; i++) {
            /*Check if the queen can be placed
            * on board[i][col]*/
            if (isSafe(board, i , col)) {
                /*Place this queen in board[i][col]*/
                board[i][col] = 1;
                /*recur to place the rest of the queens*/
                if (solveNUtil(board, col + 1) == true) {
                    return true;
                }
                /* If placing queen in board[i][col]
                   doesn't lead to a solution then
                   remove queen from board[i][col] */
                board[i][col] = 0; //>> BACKTRACK!
            }
        }

        return false;
    }

    /* A utility function to check if a queen can
       be placed on board[row][col]. Note that this
       function is called when "col" queens are already
       placed in columns from 0 to col -1. So we need
       to check only left side for attacking queens */
    public boolean isSafe(int board[][], int row, int column) {
        int i, j;
        /* check this row on left side*/
        for (i = 0; i < column; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        /*Check upper diagonal on left side*/
        for (i = row, j = column; i >= 0 && j >= 0; i--, j-- ) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        /*Checks lower diagonal on left side*/
        for (i = row, j = column; j >= 0 && i < N; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;   //there are no queens in either row left, right and diagonal. Ok to place there...
    }

    public void printSolution(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
