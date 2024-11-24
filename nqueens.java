import java.util.Arrays;

public class nqueens {

    // Function to print the chessboard
    private static void printSolution(int[] board) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i] == j) {
                    System.out.print("Q "); // Queen placed
                } else {
                    System.out.print(". "); // Empty space
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    // Function to check if a queen can be placed at position (row, col)
    private static boolean isSafe(int row, int col, int[] board) {
        // Check previous rows for conflicts
        for (int i = 0; i < row; i++) {
            // Check if the column or diagonals are under attack
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    // Backtracking function to solve the N-Queens problem
    private static boolean solveNQueens(int row, int[] board) {
        int n = board.length;

        // If all queens are placed, return true
        if (row == n) {
            printSolution(board);
            return true;
        }

        // Try placing a queen in each column of the current row
        boolean foundSolution = false;
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, board)) {
                board[row] = col; // Place the queen
                // Recursively place queens in the next rows
                foundSolution = solveNQueens(row + 1, board) || foundSolution;
                // If placing queen doesn't lead to a solution, backtrack
                board[row] = -1; // Remove the queen (backtrack)
            }
        }
        return foundSolution;
    }

    public static void main(String[] args) {
        int n = 8; // Size of the chessboard (8x8 for the classic problem)
        int[] board = new int[n];
        Arrays.fill(board, -1); // Initialize the board with -1 (no queens placed)

        System.out.println("Solution to the " + n + "-Queens problem:");
        if (!solveNQueens(0, board)) {
            System.out.println("No solution exists");
        }
    }
}
