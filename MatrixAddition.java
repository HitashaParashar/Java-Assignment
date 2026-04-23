import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class MatrixAddition extends JFrame {

    private JTextField[][] matrix1Fields;
    private JTextField[][] matrix2Fields;
    private JTextField[][] resultFields;
    private JSpinner rowSpinner;
    private JSpinner colSpinner;
    private JPanel matrix1Panel;
    private JPanel matrix2Panel;
    private JPanel resultPanel;
    private JButton addButton;
    private JButton clearButton;
    private JButton resizeButton;
    private int rows = 3;
    private int cols = 3;

    // Color scheme
    private static final Color BG_COLOR       = new Color(15, 15, 30);
    private static final Color PANEL_COLOR     = new Color(25, 25, 50);
    private static final Color HEADER_COLOR    = new Color(35, 35, 70);
    private static final Color ACCENT_BLUE     = new Color(64, 156, 255);
    private static final Color ACCENT_PURPLE   = new Color(150, 80, 255);
    private static final Color ACCENT_GREEN    = new Color(50, 220, 130);
    private static final Color FIELD_BG        = new Color(40, 40, 75);
    private static final Color FIELD_FG        = Color.WHITE;
    private static final Color RESULT_BG       = new Color(20, 60, 40);
    private static final Color TEXT_MUTED      = new Color(160, 160, 200);

    public MatrixAddition() {
        setTitle("Matrix Addition — Java Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(BG_COLOR);

        add(buildHeader(),      BorderLayout.NORTH);
        add(buildMainArea(),    BorderLayout.CENTER);
        add(buildControlBar(),  BorderLayout.SOUTH);

        pack();
        setMinimumSize(new Dimension(820, 600));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // ── Header ──────────────────────────────────────────────────────────────
    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(HEADER_COLOR);
        header.setBorder(new EmptyBorder(18, 24, 18, 24));

        JLabel title = new JLabel("Matrix Addition Calculator");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(ACCENT_BLUE);

        JLabel subtitle = new JLabel("Enter values and click ADD to compute the result");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(TEXT_MUTED);

        JPanel left = new JPanel(new GridLayout(2, 1, 2, 2));
        left.setOpaque(false);
        left.add(title);
        left.add(subtitle);

        // Size controls
        JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        sizePanel.setOpaque(false);

        sizePanel.add(makeLabel("Rows:"));
        rowSpinner = makeSpinner(rows);
        sizePanel.add(rowSpinner);

        sizePanel.add(makeLabel("Cols:"));
        colSpinner = makeSpinner(cols);
        sizePanel.add(colSpinner);

        resizeButton = makeButton("Resize", ACCENT_PURPLE);
        resizeButton.setPreferredSize(new Dimension(80, 32));
        resizeButton.addActionListener(e -> resizeMatrices());
        sizePanel.add(resizeButton);

        header.add(left,      BorderLayout.WEST);
        header.add(sizePanel, BorderLayout.EAST);
        return header;
    }

    // ── Main matrix area ────────────────────────────────────────────────────
    private JPanel buildMainArea() {
        JPanel area = new JPanel(new GridBagLayout());
        area.setBackground(BG_COLOR);
        area.setBorder(new EmptyBorder(12, 16, 4, 16));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 6, 4, 6);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill   = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        // Matrix A
        gbc.gridx = 0; gbc.weightx = 1.0;
        matrix1Panel = new JPanel();
        matrix1Panel.setBackground(PANEL_COLOR);
        matrix1Fields = new JTextField[rows][cols];
        area.add(wrapInCard(matrix1Panel, "Matrix A", ACCENT_BLUE), gbc);

        // Plus sign
        gbc.gridx = 1; gbc.weightx = 0;
        JLabel plus = new JLabel("+");
        plus.setFont(new Font("Segoe UI", Font.BOLD, 32));
        plus.setForeground(ACCENT_BLUE);
        plus.setHorizontalAlignment(SwingConstants.CENTER);
        area.add(plus, gbc);

        // Matrix B
        gbc.gridx = 2; gbc.weightx = 1.0;
        matrix2Panel = new JPanel();
        matrix2Panel.setBackground(PANEL_COLOR);
        matrix2Fields = new JTextField[rows][cols];
        area.add(wrapInCard(matrix2Panel, "Matrix B", ACCENT_PURPLE), gbc);

        // Equals sign
        gbc.gridx = 3; gbc.weightx = 0;
        JLabel eq = new JLabel("=");
        eq.setFont(new Font("Segoe UI", Font.BOLD, 32));
        eq.setForeground(ACCENT_GREEN);
        eq.setHorizontalAlignment(SwingConstants.CENTER);
        area.add(eq, gbc);

        // Result Matrix
        gbc.gridx = 4; gbc.weightx = 1.0;
        resultPanel = new JPanel();
        resultPanel.setBackground(new Color(20, 45, 35));
        resultFields = new JTextField[rows][cols];
        area.add(wrapInCard(resultPanel, "Result (A + B)", ACCENT_GREEN), gbc);

        buildMatrixGrid(matrix1Panel, matrix1Fields, FIELD_BG, FIELD_FG, false);
        buildMatrixGrid(matrix2Panel, matrix2Fields, FIELD_BG, FIELD_FG, false);
        buildMatrixGrid(resultPanel,  resultFields,  RESULT_BG, ACCENT_GREEN, true);

        return area;
    }

    // ── Control bar ─────────────────────────────────────────────────────────
    private JPanel buildControlBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 12));
        bar.setBackground(HEADER_COLOR);

        addButton   = makeButton("  ➕  ADD Matrices",   ACCENT_BLUE);
        clearButton = makeButton("  🗑  Clear All",       new Color(180, 60, 60));

        addButton.setPreferredSize(new Dimension(200, 42));
        clearButton.setPreferredSize(new Dimension(160, 42));

        addButton.addActionListener(e   -> addMatrices());
        clearButton.addActionListener(e -> clearAll());

        bar.add(addButton);
        bar.add(clearButton);
        return bar;
    }

    // ── Grid builder ────────────────────────────────────────────────────────
    private void buildMatrixGrid(JPanel panel, JTextField[][] fields,
                                  Color bg, Color fg, boolean readOnly) {
        panel.removeAll();
        panel.setLayout(new GridLayout(rows, cols, 5, 5));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JTextField tf = new JTextField("0", 4);
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setBackground(bg);
                tf.setForeground(fg);
                tf.setCaretColor(fg);
                tf.setFont(new Font("Consolas", Font.BOLD, 15));
                tf.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(80, 80, 120), 1, true),
                    new EmptyBorder(4, 4, 4, 4)
                ));
                tf.setEditable(!readOnly);
                if (readOnly) tf.setFocusable(false);
                fields[r][c] = tf;
                panel.add(tf);
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    // ── Card wrapper ────────────────────────────────────────────────────────
    private JPanel wrapInCard(JPanel inner, String label, Color accent) {
        JPanel card = new JPanel(new BorderLayout(0, 6));
        card.setBackground(PANEL_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(accent, 2, true),
            new EmptyBorder(6, 6, 6, 6)
        ));

        JLabel lbl = new JLabel(label, SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(accent);
        lbl.setBorder(new EmptyBorder(2, 0, 4, 0));

        card.add(lbl,   BorderLayout.NORTH);
        card.add(inner, BorderLayout.CENTER);
        return card;
    }

    // ── Logic ────────────────────────────────────────────────────────────────
    private void addMatrices() {
        try {
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    double a = Double.parseDouble(matrix1Fields[r][c].getText().trim());
                    double b = Double.parseDouble(matrix2Fields[r][c].getText().trim());
                    double sum = a + b;
                    // Show as int if whole number
                    if (sum == Math.floor(sum) && !Double.isInfinite(sum))
                        resultFields[r][c].setText(String.valueOf((int) sum));
                    else
                        resultFields[r][c].setText(String.format("%.2f", sum));
                }
            }
            highlightResult(true);
        } catch (NumberFormatException ex) {
            highlightResult(false);
            JOptionPane.showMessageDialog(this,
                "⚠  Please enter valid numbers in all cells.",
                "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearAll() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                matrix1Fields[r][c].setText("0");
                matrix2Fields[r][c].setText("0");
                resultFields[r][c].setText("0");
            }
        }
        highlightResult(false);
    }

    private void resizeMatrices() {
        rows = (int) rowSpinner.getValue();
        cols = (int) colSpinner.getValue();
        matrix1Fields = new JTextField[rows][cols];
        matrix2Fields = new JTextField[rows][cols];
        resultFields  = new JTextField[rows][cols];
        buildMatrixGrid(matrix1Panel, matrix1Fields, FIELD_BG,  FIELD_FG,    false);
        buildMatrixGrid(matrix2Panel, matrix2Fields, FIELD_BG,  FIELD_FG,    false);
        buildMatrixGrid(resultPanel,  resultFields,  RESULT_BG, ACCENT_GREEN, true);
        pack();
    }

    private void highlightResult(boolean success) {
        Color border = success ? ACCENT_GREEN : new Color(180, 60, 60);
        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                resultFields[r][c].setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(border, success ? 2 : 1, true),
                    new EmptyBorder(4, 4, 4, 4)
                ));
    }

    // ── Helpers ──────────────────────────────────────────────────────────────
    private JLabel makeLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(TEXT_MUTED);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return l;
    }

    private JSpinner makeSpinner(int value) {
        JSpinner s = new JSpinner(new SpinnerNumberModel(value, 1, 8, 1));
        s.setPreferredSize(new Dimension(55, 30));
        s.getEditor().getComponent(0).setBackground(FIELD_BG);
        ((JSpinner.DefaultEditor) s.getEditor()).getTextField().setForeground(Color.WHITE);
        ((JSpinner.DefaultEditor) s.getEditor()).getTextField().setFont(
            new Font("Consolas", Font.BOLD, 13));
        return s;
    }

    private JButton makeButton(String text, Color accent) {
        JButton btn = new JButton(text) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed())
                    g2.setColor(accent.darker());
                else if (getModel().isRollover())
                    g2.setColor(accent.brighter());
                else
                    g2.setColor(accent);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // ── Main ─────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(MatrixAddition::new);
    }
}
