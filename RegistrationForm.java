// ═══════════════════════════════════════════════════════════════════
//  RegistrationForm.java
//  Swing Registration Form with 10 elements + MySQL JDBC connectivity
//
//  SETUP INSTRUCTIONS:
//  1. Install MySQL and create database:
//       CREATE DATABASE registration_db;
//       USE registration_db;
//       CREATE TABLE users (
//           id           INT AUTO_INCREMENT PRIMARY KEY,
//           full_name    VARCHAR(100)  NOT NULL,
//           username     VARCHAR(50)   NOT NULL UNIQUE,
//           password     VARCHAR(100)  NOT NULL,
//           email        VARCHAR(100)  NOT NULL UNIQUE,
//           phone        VARCHAR(15)   NOT NULL,
//           gender       VARCHAR(10)   NOT NULL,
//           dob          DATE          NOT NULL,
//           city         VARCHAR(50)   NOT NULL,
//           country      VARCHAR(50)   NOT NULL,
//           agree_terms  TINYINT(1)    NOT NULL DEFAULT 0,
//           created_at   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
//       );
//
//  2. Download MySQL JDBC driver:
//       https://dev.mysql.com/downloads/connector/j/
//       (or from Maven: mysql-connector-j-8.x.x.jar)
//
//  3. Compile & Run:
//       javac -cp ".;mysql-connector-j-8.3.0.jar" RegistrationForm.java
//       java  -cp ".;mysql-connector-j-8.3.0.jar" RegistrationForm
//       (On Linux/Mac use : instead of ;)
//       javac -cp ".:mysql-connector-j-8.3.0.jar" RegistrationForm.java
//       java  -cp ".:mysql-connector-j-8.3.0.jar" RegistrationForm
// ═══════════════════════════════════════════════════════════════════

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegistrationForm extends JFrame {

    // ── DB Config — change these to match your MySQL setup ──
    private static final String DB_URL  = "jdbc:mysql://localhost:3306/registration_db";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "your_password";  // ← change this

    // ── Colors ──
    private static final Color BG         = new Color(236, 240, 245);
    private static final Color HEADER_BG  = new Color(30,  87, 153);
    private static final Color BTN_GREEN  = new Color(39, 174,  96);
    private static final Color BTN_RED    = new Color(192, 57,  43);
    private static final Color FIELD_BG   = Color.WHITE;
    private static final Color LABEL_CLR  = new Color(44,  62,  80);

    // ── 10 Form Elements ──
    // 1. Full Name
    private JTextField  fullNameField;
    // 2. Username
    private JTextField  usernameField;
    // 3. Password
    private JPasswordField passwordField;
    // 4. Email
    private JTextField  emailField;
    // 5. Phone
    private JTextField  phoneField;
    // 6. Gender (Radio buttons)
    private JRadioButton maleBtn, femaleBtn, otherBtn;
    private ButtonGroup  genderGroup;
    // 7. Date of Birth
    private JTextField  dobField;
    // 8. City
    private JTextField  cityField;
    // 9. Country (ComboBox)
    private JComboBox<String> countryBox;
    // 10. Agree to Terms (CheckBox)
    private JCheckBox   agreeCheck;

    // ── Status label ──
    private JLabel statusLabel;

    // ─────────────────────────────────────────────────────────────
    public RegistrationForm() {
        setTitle("User Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        buildUI();
    }

    // ─────────────────────────────────────────────────────────────
    private void buildUI() {

        // ── Header panel ──
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(HEADER_BG);
        header.setPreferredSize(new Dimension(550, 70));
        JLabel title = new JLabel("User Registration Form", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.CENTER);

        // ── Form panel ──
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(BG);
        form.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints g = new GridBagConstraints();
        g.insets  = new Insets(7, 5, 7, 5);
        g.fill    = GridBagConstraints.HORIZONTAL;
        g.weightx = 1.0;

        int row = 0;

        // ── 1. Full Name ──
        addLabel(form, g, "1.  Full Name *", row, 0);
        fullNameField = makeField("Enter full name");
        addField(form, g, fullNameField, row, 1);
        row++;

        // ── 2. Username ──
        addLabel(form, g, "2.  Username *", row, 0);
        usernameField = makeField("Choose a username");
        addField(form, g, usernameField, row, 1);
        row++;

        // ── 3. Password ──
        addLabel(form, g, "3.  Password *", row, 0);
        passwordField = new JPasswordField();
        styleField(passwordField);
        addField(form, g, passwordField, row, 1);
        row++;

        // ── 4. Email ──
        addLabel(form, g, "4.  Email *", row, 0);
        emailField = makeField("example@email.com");
        addField(form, g, emailField, row, 1);
        row++;

        // ── 5. Phone ──
        addLabel(form, g, "5.  Phone *", row, 0);
        phoneField = makeField("+91-XXXXXXXXXX");
        addField(form, g, phoneField, row, 1);
        row++;

        // ── 6. Gender (Radio Buttons) ──
        addLabel(form, g, "6.  Gender *", row, 0);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        genderPanel.setBackground(BG);
        maleBtn   = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");
        otherBtn  = new JRadioButton("Other");
        styleRadio(maleBtn); styleRadio(femaleBtn); styleRadio(otherBtn);
        maleBtn.setSelected(true);
        genderGroup = new ButtonGroup();
        genderGroup.add(maleBtn); genderGroup.add(femaleBtn); genderGroup.add(otherBtn);
        genderPanel.add(maleBtn); genderPanel.add(femaleBtn); genderPanel.add(otherBtn);
        g.gridx = 1; g.gridy = row;
        form.add(genderPanel, g);
        row++;

        // ── 7. Date of Birth ──
        addLabel(form, g, "7.  Date of Birth *", row, 0);
        dobField = makeField("YYYY-MM-DD");
        addField(form, g, dobField, row, 1);
        row++;

        // ── 8. City ──
        addLabel(form, g, "8.  City *", row, 0);
        cityField = makeField("Enter your city");
        addField(form, g, cityField, row, 1);
        row++;

        // ── 9. Country (ComboBox) ──
        addLabel(form, g, "9.  Country *", row, 0);
        String[] countries = {
            "-- Select Country --", "India", "USA", "UK", "Canada",
            "Australia", "Germany", "France", "Japan", "Brazil", "Other"
        };
        countryBox = new JComboBox<>(countries);
        countryBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        countryBox.setBackground(FIELD_BG);
        countryBox.setPreferredSize(new Dimension(260, 32));
        g.gridx = 1; g.gridy = row;
        form.add(countryBox, g);
        row++;

        // ── 10. Agree to Terms (CheckBox) ──
        addLabel(form, g, "10. Terms *", row, 0);
        agreeCheck = new JCheckBox("I agree to the Terms & Conditions");
        agreeCheck.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        agreeCheck.setBackground(BG);
        agreeCheck.setForeground(LABEL_CLR);
        g.gridx = 1; g.gridy = row;
        form.add(agreeCheck, g);
        row++;

        // ── Separator ──
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(174, 182, 191));
        g.gridx = 0; g.gridy = row; g.gridwidth = 2;
        g.insets = new Insets(10, 0, 10, 0);
        form.add(sep, g);
        g.gridwidth = 1;
        g.insets = new Insets(7, 5, 7, 5);
        row++;

        // ── Buttons ──
        JButton registerBtn = makeButton("  Register  ", BTN_GREEN, Color.WHITE);
        JButton clearBtn    = makeButton("  Clear  ",    new Color(149, 165, 166), Color.WHITE);
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnPanel.setBackground(BG);
        btnPanel.add(registerBtn);
        btnPanel.add(clearBtn);
        g.gridx = 0; g.gridy = row; g.gridwidth = 2;
        g.insets = new Insets(5, 5, 5, 5);
        form.add(btnPanel, g);
        row++;

        // ── Status label ──
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        g.gridy = row;
        form.add(statusLabel, g);

        // ── Scroll pane ──
        JScrollPane scroll = new JScrollPane(form);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(12);

        // ── Assemble frame ──
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(scroll,  BorderLayout.CENTER);

        // ── Listeners ──
        registerBtn.addActionListener(e -> registerUser());
        clearBtn.addActionListener(e -> clearForm());
    }

    // ─────────────────────────────────────────────────────────────
    //  REGISTER – validate then send to DB
    // ─────────────────────────────────────────────────────────────
    private void registerUser() {

        // ── Collect values ──
        String fullName = fullNameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String email    = emailField.getText().trim();
        String phone    = phoneField.getText().trim();
        String gender   = maleBtn.isSelected() ? "Male"
                        : femaleBtn.isSelected() ? "Female" : "Other";
        String dob      = dobField.getText().trim();
        String city     = cityField.getText().trim();
        String country  = (String) countryBox.getSelectedItem();
        boolean agreed  = agreeCheck.isSelected();

        // ── Validate ──
        if (fullName.isEmpty() || username.isEmpty() || password.isEmpty()
                || email.isEmpty() || phone.isEmpty() || dob.isEmpty() || city.isEmpty()) {
            setStatus("⚠  All fields are required!", BTN_RED);
            return;
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            setStatus("⚠  Invalid email format!", BTN_RED); return;
        }
        if (!phone.matches("^[+]?[0-9\\-]{7,15}$")) {
            setStatus("⚠  Invalid phone number!", BTN_RED); return;
        }
        if (!dob.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            setStatus("⚠  Date format must be YYYY-MM-DD!", BTN_RED); return;
        }
        if (password.length() < 6) {
            setStatus("⚠  Password must be at least 6 characters!", BTN_RED); return;
        }
        if (country.startsWith("--")) {
            setStatus("⚠  Please select a country!", BTN_RED); return;
        }
        if (!agreed) {
            setStatus("⚠  You must agree to Terms & Conditions!", BTN_RED); return;
        }

        // ── Insert into MySQL via JDBC ──
        String sql = "INSERT INTO users "
                   + "(full_name, username, password, email, phone, gender, dob, city, country, agree_terms) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection con  = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString (1, fullName);
                ps.setString (2, username);
                ps.setString (3, password);   // In production: hash with BCrypt!
                ps.setString (4, email);
                ps.setString (5, phone);
                ps.setString (6, gender);
                ps.setDate   (7, Date.valueOf(dob));
                ps.setString (8, city);
                ps.setString (9, country);
                ps.setInt    (10, agreed ? 1 : 0);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    setStatus("✔  Registration successful! Welcome, " + username + "!", BTN_GREEN);
                    clearForm();
                }
            }

        } catch (ClassNotFoundException ex) {
            setStatus("✘  JDBC Driver not found! Add mysql-connector.jar to classpath.", BTN_RED);
            ex.printStackTrace();
        } catch (SQLIntegrityConstraintViolationException ex) {
            setStatus("✘  Username or Email already exists!", BTN_RED);
        } catch (SQLException ex) {
            setStatus("✘  DB Error: " + ex.getMessage(), BTN_RED);
            ex.printStackTrace();
        }
    }

    // ─────────────────────────────────────────────────────────────
    private void clearForm() {
        fullNameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        emailField.setText("");
        phoneField.setText("");
        maleBtn.setSelected(true);
        dobField.setText("");
        cityField.setText("");
        countryBox.setSelectedIndex(0);
        agreeCheck.setSelected(false);
        statusLabel.setText(" ");
        fullNameField.requestFocus();
    }

    private void setStatus(String msg, Color color) {
        statusLabel.setForeground(color);
        statusLabel.setText(msg);
    }

    // ── UI Helpers ──
    private void addLabel(JPanel p, GridBagConstraints g, String text, int row, int col) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(LABEL_CLR);
        g.gridx = col; g.gridy = row;
        p.add(lbl, g);
    }

    private void addField(JPanel p, GridBagConstraints g, JComponent c, int row, int col) {
        g.gridx = col; g.gridy = row;
        p.add(c, g);
    }

    private JTextField makeField(String placeholder) {
        JTextField tf = new JTextField();
        tf.setToolTipText(placeholder);
        styleField(tf);
        return tf;
    }

    private void styleField(JTextField tf) {
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tf.setBackground(FIELD_BG);
        tf.setPreferredSize(new Dimension(260, 32));
        tf.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(174, 182, 191), 1, true),
            BorderFactory.createEmptyBorder(4, 8, 4, 8)
        ));
    }

    private void styleRadio(JRadioButton rb) {
        rb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rb.setBackground(BG);
        rb.setForeground(LABEL_CLR);
    }

    private JButton makeButton(String text, Color bg, Color fg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(130, 38));
        Color hover = bg.darker();
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(MouseEvent e)  { btn.setBackground(bg);    }
        });
        return btn;
    }

    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }
}