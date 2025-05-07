import java.util.Scanner;

public class NQueensBacktrack {

    static boolean isSafe(int[][] board, int row, int col, int n) {
        // Check left row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1)
                return false;
        }

        // Check upper diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check lower diagonal
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    static boolean solveNQueens(int[][] board, int col, int n) {
        if (col >= n)
            return true;

        for (int row = 0; row < n; row++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 1;

                if (solveNQueens(board, col + 1, n))
                    return true;

                board[row][col] = 0; // backtrack
            }
        }

        return false;
    }

    static void printBoard(int[][] board, int n) {
        for (int[] row : board) {
            for (int col : row) {
                System.out.print((col == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter N (number of queens): ");
        int n = sc.nextInt();

        int[][] board = new int[n][n];

        if (solveNQueens(board, 0, n)) {
            System.out.println("One of the possible solutions:");
            printBoard(board, n);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
