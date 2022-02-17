package heuristics2;

/*EL ALGORITMO DE LAS 8 REINAS
1) Start in the leftmost column
2) If all queens are placed
    return true
3) Try all rows in the current column.
   Do following for every tried row.
    a) If the queen can be placed safely in this row
       then mark this [row, column] as part of the
       solution and recursively check if placing
       queen here leads to a solution.
    b) If placing the queen in [row, column] leads to
       a solution then return true.
    c) If placing queen doesn't lead to a solution then
       unmark this [row, column] (Backtrack) and go to
       step (a) to try other rows.
3) If all rows have been tried and nothing worked,
   return false to trigger backtracking.
*
*/

public class EightQueensProblem {
    int queen = 8;
    int[] array = new int[queen];
    static int count = 0;

    public static void main(String[] args) {
        EightQueensProblem eightQueensProblem = new EightQueensProblem();
        eightQueensProblem.check(0);

        System.out.printf("Hay% d soluciones en total ", count);
    }
    /*
    * It places the queens
    * in the checkboard
    * */
    private void check(int n) {
        if (n == queen) {
            print();
            return;
        }
        //se colocan las reinas por turno y se verifica si hay conflicto
        for (int i = 0; i < queen; i++) {
            //coloca la reina actual n en la columna i de la fila
            array[n] = i;
            //determinar si la posicion actual entra en conflicto
            if (judge(n)) {
                //luego se ponen n + 1 reinas
                check(n + 1); //--> recursively
            }
        }
    }
    /*
    * checks if there is
    * a conflict...
    * */
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) { //if in same column, row or diagonal...
                return false;
            }
        }
        return true; // it accomplishes the conditions.

    }
    public void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
