import java.util.Scanner;

public class MatrixOperations {

    // ─────────────────────────────────────────────
    //  1. Display a matrix
    // ─────────────────────────────────────────────
    public static void displayMatrix(int[][] matrix, String label) {
        System.out.println("\n" + label + ":");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%6d", val);
            }
            System.out.println();
        }
    }

    // ─────────────────────────────────────────────
    //  2. Transpose
    // ─────────────────────────────────────────────
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[j][i] = matrix[i][j];

        return result;
    }

    // ─────────────────────────────────────────────
    //  3. Addition of two matrices
    // ─────────────────────────────────────────────
    public static int[][] addition(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;

        if (rows != b.length || cols != b[0].length) {
            System.out.println("Error: Matrices must have the same dimensions for addition.");
            return null;
        }

        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = a[i][j] + b[i][j];

        return result;
    }

    // ─────────────────────────────────────────────
    //  4. Sum of each row
    // ─────────────────────────────────────────────
    public static int[] sumOfRows(int[][] matrix) {
        int rows = matrix.length;
        int[] rowSums = new int[rows];

        for (int i = 0; i < rows; i++) {
            int sum = 0;
            for (int val : matrix[i])
                sum += val;
            rowSums[i] = sum;
        }
        return rowSums;
    }

    // ─────────────────────────────────────────────
    //  5. Sum of each column
    // ─────────────────────────────────────────────
    public static int[] sumOfColumns(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] colSums = new int[cols];

        for (int j = 0; j < cols; j++) {
            int sum = 0;
            for (int i = 0; i < rows; i++)
                sum += matrix[i][j];
            colSums[j] = sum;
        }
        return colSums;
    }

    // ─────────────────────────────────────────────
    //  6. Sum of main diagonal (requires square matrix)
    // ─────────────────────────────────────────────
    public static int sumOfMainDiagonal(int[][] matrix) {
        if (matrix.length != matrix[0].length) {
            System.out.println("Error: Main diagonal sum requires a square matrix.");
            return Integer.MIN_VALUE;
        }
        int sum = 0;
        for (int i = 0; i < matrix.length; i++)
            sum += matrix[i][i];
        return sum;
    }

    // ─────────────────────────────────────────────
    //  7. Sum of anti-diagonal (requires square matrix)
    // ─────────────────────────────────────────────
    public static int sumOfAntiDiagonal(int[][] matrix) {
        int n = matrix.length;
        if (n != matrix[0].length) {
            System.out.println("Error: Anti-diagonal sum requires a square matrix.");
            return Integer.MIN_VALUE;
        }
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += matrix[i][n - 1 - i];
        return sum;
    }

    // ─────────────────────────────────────────────
    //  Helper – read a matrix from keyboard
    // ─────────────────────────────────────────────
    public static int[][] readMatrix(Scanner sc, String name, int rows, int cols) {
        System.out.println("\nEnter elements for " + name
                + " (" + rows + "x" + cols + ") row by row:");
        int[][] m = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                System.out.print("  [" + i + "][" + j + "]: ");
                m[i][j] = sc.nextInt();
            }
        return m;
    }

    // ─────────────────────────────────────────────
    //  MAIN – interactive demo
    // ─────────────────────────────────────────────
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════════╗");
        System.out.println("║   Matrix Operations Demo     ║");
        System.out.println("╚══════════════════════════════╝");

        // ── Read square matrix A ──
        System.out.print("\nEnter size n for square matrix A (n x n): ");
        int n = sc.nextInt();
        int[][] A = readMatrix(sc, "Matrix A", n, n);
        displayMatrix(A, "Matrix A");

        // ── Transpose ──
        int[][] T = transpose(A);
        displayMatrix(T, "Transpose of A");

        // ── Row sums ──
        int[] rowSums = sumOfRows(A);
        System.out.println("\nSum of each row in A:");
        for (int i = 0; i < rowSums.length; i++)
            System.out.println("  Row " + i + " → " + rowSums[i]);

        // ── Column sums ──
        int[] colSums = sumOfColumns(A);
        System.out.println("\nSum of each column in A:");
        for (int j = 0; j < colSums.length; j++)
            System.out.println("  Col " + j + " → " + colSums[j]);

        // ── Diagonal sums ──
        System.out.println("\nSum of main diagonal    : " + sumOfMainDiagonal(A));
        System.out.println("Sum of anti-diagonal    : " + sumOfAntiDiagonal(A));

        // ── Addition with a second matrix of the same size ──
        System.out.println("\n─── Matrix Addition ───");
        int[][] B = readMatrix(sc, "Matrix B", n, n);
        displayMatrix(B, "Matrix B");

        int[][] C = addition(A, B);
        if (C != null)
            displayMatrix(C, "A + B");

        sc.close();
        System.out.println("\nDone!");
    }
}