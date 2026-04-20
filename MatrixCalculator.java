import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
 
public class MatrixCalculator extends JFrame {
 
    // =========================================================
    //  Fields
    // =========================================================
    private JComboBox<Integer> rowsA, colsA, rowsB, colsB;
 
    private JPanel panelA, panelB, panelResult;
    private JTextField[][] fieldsA, fieldsB, fieldsResult;
 
    private JButton btnAdd, btnSub, btnMul, btnTransA, btnTransB;
    private JButton btnDetA, btnDetB, btnInvA, btnInvB, btnClear;
 
    private JLabel statusLabel;
 
    // =========================================================
    //  setStatus and showError declared FIRST
    //  (fixes "method undefined" errors in VS Code / Eclipse)
    // =========================================================
    private void setStatus(String msg) {
        if (statusLabel != null) {
            statusLabel.setText(msg);
        }
    }
 
    private void showError(String msg) {
        setStatus("Error: " + msg);
        JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
 
    // =========================================================
    //  Constructor
    // =========================================================
    public MatrixCalculator() {
        setTitle("Matrix Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(245, 245, 250));
 
        // Build status bar first so statusLabel is ready
        add(buildStatusBar(),  BorderLayout.SOUTH);
        add(buildTopPanel(),   BorderLayout.NORTH);
        add(buildMatrixArea(), BorderLayout.CENTER);
 
        pack();
        setMinimumSize(new Dimension(960, 620));
        setLocationRelativeTo(null);
        setVisible(true);
 
        buildMatrixGrids();
    }
 
    // =========================================================
    //  Styled button helper
    // =========================================================
    private JButton makeButton(String text, Color bg) {
        JButton b = new JButton(text);
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setMargin(new Insets(5, 10, 5, 10));
        return b;
    }
 
    // =========================================================
    //  TOP PANEL: size selectors + operation buttons
    // =========================================================
    private JPanel buildTopPanel() {
        JPanel top = new JPanel(new BorderLayout(8, 8));
        top.setBorder(new EmptyBorder(12, 12, 4, 12));
        top.setOpaque(false);
 
        // --- size row ---
        JPanel sizeRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        sizeRow.setOpaque(false);
 
        Integer[] sizes = { 1, 2, 3, 4, 5 };
        rowsA = new JComboBox<>(sizes); rowsA.setSelectedIndex(1);
        colsA = new JComboBox<>(sizes); colsA.setSelectedIndex(1);
        rowsB = new JComboBox<>(sizes); rowsB.setSelectedIndex(1);
        colsB = new JComboBox<>(sizes); colsB.setSelectedIndex(1);
 
        sizeRow.add(new JLabel("Matrix A:"));
        sizeRow.add(rowsA);
        sizeRow.add(new JLabel("x"));
        sizeRow.add(colsA);
        sizeRow.add(Box.createHorizontalStrut(20));
        sizeRow.add(new JLabel("Matrix B:"));
        sizeRow.add(rowsB);
        sizeRow.add(new JLabel("x"));
        sizeRow.add(colsB);
 
        JButton btnResize = makeButton("Resize Matrices", new Color(70, 130, 180));
        btnResize.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buildMatrixGrids();
            }
        });
        sizeRow.add(Box.createHorizontalStrut(16));
        sizeRow.add(btnResize);
 
        // --- operations row ---
        JPanel opRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        opRow.setOpaque(false);
 
        btnAdd    = makeButton("A + B",       new Color(60, 150, 90));
        btnSub    = makeButton("A - B",       new Color(200, 100, 50));
        btnMul    = makeButton("A x B",       new Color(130, 60, 180));
        btnTransA = makeButton("Transpose A", new Color(50, 140, 160));
        btnTransB = makeButton("Transpose B", new Color(50, 140, 160));
        btnDetA   = makeButton("det(A)",      new Color(180, 140, 30));
        btnDetB   = makeButton("det(B)",      new Color(180, 140, 30));
        btnInvA   = makeButton("Inverse A",   new Color(160, 50, 100));
        btnInvB   = makeButton("Inverse B",   new Color(160, 50, 100));
        btnClear  = makeButton("Clear All",   new Color(120, 120, 130));
 
        JButton[] allBtns = { btnAdd, btnSub, btnMul, btnTransA, btnTransB,
                               btnDetA, btnDetB, btnInvA, btnInvB, btnClear };
        for (JButton b : allBtns) {
            opRow.add(b);
        }
 
        // Anonymous inner classes instead of lambdas - works on all Java versions
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { addMatrices(); }
        });
        btnSub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { subtractMatrices(); }
        });
        btnMul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { multiplyMatrices(); }
        });
        btnTransA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { transposeMatrix('A'); }
        });
        btnTransB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { transposeMatrix('B'); }
        });
        btnDetA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { determinant('A'); }
        });
        btnDetB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { determinant('B'); }
        });
        btnInvA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { inverse('A'); }
        });
        btnInvB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { inverse('B'); }
        });
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { clearAll(); }
        });
 
        top.add(sizeRow, BorderLayout.NORTH);
        top.add(opRow,   BorderLayout.CENTER);
        return top;
    }
 
    // =========================================================
    //  MATRIX AREA: A | B | Result
    // =========================================================
    private JScrollPane buildMatrixArea() {
        JPanel wrapper = new JPanel(new GridLayout(1, 3, 16, 0));
        wrapper.setBorder(new EmptyBorder(8, 12, 8, 12));
        wrapper.setOpaque(false);
 
        panelA      = makeMatrixPanel("Matrix A");
        panelB      = makeMatrixPanel("Matrix B");
        panelResult = makeMatrixPanel("Result");
 
        wrapper.add(panelA);
        wrapper.add(panelB);
        wrapper.add(panelResult);
 
        JScrollPane scroll = new JScrollPane(wrapper);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        return scroll;
    }
 
    private JPanel makeMatrixPanel(String title) {
        JPanel p = new JPanel(new BorderLayout(4, 4));
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(180, 180, 200), 1),
                title,
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("SansSerif", Font.BOLD, 13),
                new Color(70, 70, 120)));
        p.setBackground(new Color(252, 252, 255));
        return p;
    }
 
    // =========================================================
    //  Build / rebuild grid of JTextFields
    // =========================================================
    private void buildMatrixGrids() {
        int rA = (Integer) rowsA.getSelectedItem();
        int cA = (Integer) colsA.getSelectedItem();
        int rB = (Integer) rowsB.getSelectedItem();
        int cB = (Integer) colsB.getSelectedItem();
 
        fieldsA      = buildGrid(panelA,      rA, cA, false);
        fieldsB      = buildGrid(panelB,      rB, cB, false);
        fieldsResult = buildGrid(panelResult, rA, cA, true);
 
        pack();
        revalidate();
        repaint();
        setStatus("Matrices resized.  A: " + rA + "x" + cA + "   B: " + rB + "x" + cB);
    }
 
    private JTextField[][] buildGrid(JPanel panel, int rows, int cols, boolean readOnly) {
        panel.removeAll();
        JPanel grid = new JPanel(new GridLayout(rows, cols, 4, 4));
        grid.setOpaque(false);
        grid.setBorder(new EmptyBorder(8, 8, 8, 8));
 
        JTextField[][] fields = new JTextField[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JTextField tf = new JTextField("0", 4);
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setFont(new Font("Monospaced", Font.PLAIN, 14));
                tf.setBackground(readOnly ? new Color(235, 240, 255) : Color.WHITE);
                tf.setEditable(!readOnly);
                tf.setBorder(BorderFactory.createLineBorder(new Color(190, 190, 220)));
                fields[r][c] = tf;
                grid.add(tf);
            }
        }
        panel.add(grid, BorderLayout.CENTER);
        return fields;
    }
 
    // =========================================================
    //  Status bar
    // =========================================================
    private JPanel buildStatusBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 4));
        bar.setBackground(new Color(230, 230, 240));
        bar.setBorder(new MatteBorder(1, 0, 0, 0, new Color(200, 200, 215)));
        statusLabel = new JLabel("Ready.");
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(60, 60, 90));
        bar.add(statusLabel);
        return bar;
    }
 
    // =========================================================
    //  Read matrix from text fields
    // =========================================================
    private double[][] readMatrix(JTextField[][] fields) throws NumberFormatException {
        int rows = fields.length;
        int cols = fields[0].length;
        double[][] m = new double[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                m[r][c] = Double.parseDouble(fields[r][c].getText().trim());
            }
        }
        return m;
    }
 
    // =========================================================
    //  Write result to result panel
    // =========================================================
    private void writeResult(double[][] result) {
        fieldsResult = buildGrid(panelResult, result.length, result[0].length, true);
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                fieldsResult[r][c].setText(fmt(result[r][c]));
            }
        }
        panelResult.revalidate();
        panelResult.repaint();
        pack();
    }
 
    private String fmt(double v) {
        if (v == Math.floor(v) && !Double.isInfinite(v)) {
            return String.valueOf((long) v);
        }
        return String.format("%.4f", v);
    }
 
    // =========================================================
    //  Matrix operations
    // =========================================================
    private void addMatrices() {
        try {
            double[][] A = readMatrix(fieldsA);
            double[][] B = readMatrix(fieldsB);
            if (A.length != B.length || A[0].length != B[0].length) {
                showError("Addition requires identical dimensions.");
                return;
            }
            double[][] R = new double[A.length][A[0].length];
            for (int r = 0; r < A.length; r++) {
                for (int c = 0; c < A[0].length; c++) {
                    R[r][c] = A[r][c] + B[r][c];
                }
            }
            writeResult(R);
            setStatus("A + B computed (" + R.length + "x" + R[0].length + ")");
        } catch (NumberFormatException ex) {
            showError("Invalid number in matrix.");
        }
    }
 
    private void subtractMatrices() {
        try {
            double[][] A = readMatrix(fieldsA);
            double[][] B = readMatrix(fieldsB);
            if (A.length != B.length || A[0].length != B[0].length) {
                showError("Subtraction requires identical dimensions.");
                return;
            }
            double[][] R = new double[A.length][A[0].length];
            for (int r = 0; r < A.length; r++) {
                for (int c = 0; c < A[0].length; c++) {
                    R[r][c] = A[r][c] - B[r][c];
                }
            }
            writeResult(R);
            setStatus("A - B computed (" + R.length + "x" + R[0].length + ")");
        } catch (NumberFormatException ex) {
            showError("Invalid number in matrix.");
        }
    }
 
    private void multiplyMatrices() {
        try {
            double[][] A = readMatrix(fieldsA);
            double[][] B = readMatrix(fieldsB);
            if (A[0].length != B.length) {
                showError("Multiplication requires cols(A) = rows(B).");
                return;
            }
            int rR    = A.length;
            int cR    = B[0].length;
            int inner = A[0].length;
            double[][] R = new double[rR][cR];
            for (int r = 0; r < rR; r++) {
                for (int c = 0; c < cR; c++) {
                    for (int k = 0; k < inner; k++) {
                        R[r][c] += A[r][k] * B[k][c];
                    }
                }
            }
            writeResult(R);
            setStatus("A x B computed (" + rR + "x" + cR + ")");
        } catch (NumberFormatException ex) {
            showError("Invalid number in matrix.");
        }
    }
 
    private void transposeMatrix(char which) {
        try {
            double[][] M = readMatrix(which == 'A' ? fieldsA : fieldsB);
            double[][] T = new double[M[0].length][M.length];
            for (int r = 0; r < M.length; r++) {
                for (int c = 0; c < M[0].length; c++) {
                    T[c][r] = M[r][c];
                }
            }
            writeResult(T);
            setStatus("Transpose of " + which + " (" + T.length + "x" + T[0].length + ")");
        } catch (NumberFormatException ex) {
            showError("Invalid number in matrix.");
        }
    }
 
    private void determinant(char which) {
        try {
            double[][] M = readMatrix(which == 'A' ? fieldsA : fieldsB);
            if (M.length != M[0].length) {
                showError("Determinant requires a square matrix.");
                return;
            }
            double d = det(M);
            writeResult(new double[][] {{ d }});
            setStatus("det(" + which + ") = " + fmt(d));
        } catch (NumberFormatException ex) {
            showError("Invalid number in matrix.");
        }
    }
 
    private void inverse(char which) {
        try {
            double[][] M = readMatrix(which == 'A' ? fieldsA : fieldsB);
            if (M.length != M[0].length) {
                showError("Inverse requires a square matrix.");
                return;
            }
            double d = det(M);
            if (Math.abs(d) < 1e-10) {
                showError("Matrix is singular (det = 0). No inverse exists.");
                return;
            }
            writeResult(inv(M));
            setStatus("Inverse of " + which + " computed.");
        } catch (NumberFormatException ex) {
            showError("Invalid number in matrix.");
        }
    }
 
    private void clearAll() {
        for (JTextField[] row : fieldsA) {
            for (JTextField tf : row) { tf.setText("0"); }
        }
        for (JTextField[] row : fieldsB) {
            for (JTextField tf : row) { tf.setText("0"); }
        }
        buildGrid(panelResult, 2, 2, true);
        panelResult.revalidate();
        panelResult.repaint();
        setStatus("Cleared.");
    }
 
    // =========================================================
    //  Math helpers
    // =========================================================
    private double det(double[][] m) {
        int n = m.length;
        if (n == 1) return m[0][0];
        if (n == 2) return m[0][0] * m[1][1] - m[0][1] * m[1][0];
        double d = 0;
        for (int c = 0; c < n; c++) {
            d += (c % 2 == 0 ? 1 : -1) * m[0][c] * det(minor(m, 0, c));
        }
        return d;
    }
 
    private double[][] minor(double[][] m, int skipR, int skipC) {
        int n = m.length;
        double[][] result = new double[n - 1][n - 1];
        int ri = 0;
        for (int row = 0; row < n; row++) {
            if (row == skipR) continue;
            int ci = 0;
            for (int col = 0; col < n; col++) {
                if (col == skipC) continue;
                result[ri][ci++] = m[row][col];
            }
            ri++;
        }
        return result;
    }
 
    private double[][] inv(double[][] m) {
        int n = m.length;
        double d = det(m);
        double[][] adj = new double[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                adj[c][r] = ((r + c) % 2 == 0 ? 1 : -1) * det(minor(m, r, c));
            }
        }
        double[][] inverse = new double[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                inverse[r][c] = adj[r][c] / d;
            }
        }
        return inverse;
    }
 
    // =========================================================
    //  Entry point
    // =========================================================
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        // Anonymous Runnable instead of lambda - works on all Java versions
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MatrixCalculator();
            }
        });
    }
}
    