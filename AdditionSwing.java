// ═══════════════════════════════════════════════════════════════════
//  AdditionSwing.java
//  A clean Swing GUI to add two numbers
//  Run: javac AdditionSwing.java  →  java AdditionSwing
// ═══════════════════════════════════════════════════════════════════

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class AdditionSwing extends JFrame {

    // ── Components ──
    private JTextField num1Field;
    private JTextField num2Field;
    private JTextField resultField;
    private JButton    addButton;
    private JButton    clearButton;
    private JLabel     statusLabel;

    // ── Constructor – build the GUI ──
    public AdditionSwing() {
        setTitle("Addition Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 320);
        setLocationRelativeTo(null);          // centre on screen
        setResizable(false);

        buildUI();
    }

    private void buildUI() {

        // ── Main panel with padding ──
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 35, 20, 35));
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(8, 8, 8, 8);
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // ── Title label ──
        JLabel titleLabel = new JLabel("Addition of Two Numbers", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(33, 97, 140));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // ── Separator ──
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(189, 195, 199));
        gbc.gridy = 1; gbc.insets = new Insets(2, 0, 12, 0);
        mainPanel.add(sep, gbc);
        gbc.insets = new Insets(8, 8, 8, 8);

        // ── Number 1 ──
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(makeLabel("First Number  :"), gbc);

        num1Field = makeTextField();
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(num1Field, gbc);

        // ── Number 2 ──
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(makeLabel("Second Number :"), gbc);

        num2Field = makeTextField();
        gbc.gridx = 1; gbc.gridy = 3;
        mainPanel.add(num2Field, gbc);

        // ── Result (read-only) ──
        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(makeLabel("Result        :"), gbc);

        resultField = makeTextField();
        resultField.setEditable(false);
        resultField.setBackground(new Color(212, 230, 241));
        resultField.setFont(new Font("Segoe UI", Font.BOLD, 15));
        resultField.setForeground(new Color(23, 32, 42));
        gbc.gridx = 1; gbc.gridy = 4;
        mainPanel.add(resultField, gbc);

        // ── Buttons ──
        addButton   = makeButton("  Add  ",   new Color(41, 128, 185), Color.WHITE);
        clearButton = makeButton("  Clear  ", new Color(189, 195, 199), new Color(44, 62, 80));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnPanel.setBackground(new Color(245, 247, 250));
        btnPanel.add(addButton);
        btnPanel.add(clearButton);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        gbc.insets = new Insets(14, 8, 6, 8);
        mainPanel.add(btnPanel, gbc);

        // ── Status label ──
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusLabel.setForeground(new Color(192, 57, 43));
        gbc.gridy = 6; gbc.insets = new Insets(0, 8, 0, 8);
        mainPanel.add(statusLabel, gbc);

        add(mainPanel);

        // ── Action Listeners ──
        addButton.addActionListener(e -> performAddition());
        clearButton.addActionListener(e -> clearFields());

        // Allow Enter key to trigger addition
        KeyAdapter enterKey = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) performAddition();
            }
        };
        num1Field.addKeyListener(enterKey);
        num2Field.addKeyListener(enterKey);
    }

    // ── Core logic ──
    private void performAddition() {
        String s1 = num1Field.getText().trim();
        String s2 = num2Field.getText().trim();

        if (s1.isEmpty() || s2.isEmpty()) {
            statusLabel.setText("⚠  Please enter both numbers.");
            resultField.setText("");
            return;
        }

        try {
            double n1     = Double.parseDouble(s1);
            double n2     = Double.parseDouble(s2);
            double result = n1 + n2;

            // Show as integer if no decimal part
            if (result == Math.floor(result) && !Double.isInfinite(result))
                resultField.setText(String.valueOf((long) result));
            else
                resultField.setText(String.valueOf(result));

            statusLabel.setForeground(new Color(39, 174, 96));
            statusLabel.setText("✔  Addition successful!");

        } catch (NumberFormatException ex) {
            resultField.setText("");
            statusLabel.setForeground(new Color(192, 57, 43));
            statusLabel.setText("✘  Invalid input! Enter numbers only.");
        }
    }

    private void clearFields() {
        num1Field.setText("");
        num2Field.setText("");
        resultField.setText("");
        statusLabel.setText(" ");
        num1Field.requestFocus();
    }

    // ── UI helpers ──
    private JLabel makeLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(new Color(44, 62, 80));
        return lbl;
    }

    private JTextField makeTextField() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setPreferredSize(new Dimension(160, 32));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(174, 182, 191), 1, true),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
        return tf;
    }

    private JButton makeButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(110, 36));

        // Hover effect
        Color hoverBg = bg.darker();
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hoverBg); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(bg);      }
        });
        return btn;
    }

    // ── Entry point ──
    public static void main(String[] args) {
        // Run on Event Dispatch Thread (Swing best practice)
        SwingUtilities.invokeLater(() -> {
            new AdditionSwing().setVisible(true);
        });
    }
}
