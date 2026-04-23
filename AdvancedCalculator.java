import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedCalculator extends JFrame implements ActionListener {
    
    private JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private char operation;
    private boolean startNewNumber = true;
    private String currentOperator = "";
    
    // Buttons
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[9];
    private JButton addButton, subButton, mulButton, divButton;
    private JButton eqButton, clrButton, delButton, dotButton, sqrtButton;
    
    public AdvancedCalculator() {
        // Frame settings
        setTitle("Advanced Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 550);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(50, 50, 50));
        setLayout(new BorderLayout(10, 10));
        
        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setForeground(Color.BLACK);
        display.setText("0");
        display.setPreferredSize(new Dimension(380, 70));
        add(display, BorderLayout.NORTH);
        
        // Initialize buttons
        for(int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(new Font("Arial", Font.BOLD, 24));
            numberButtons[i].setFocusPainted(false);
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(new Color(70, 70, 70));
            numberButtons[i].setForeground(Color.WHITE);
        }
        
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("×");
        divButton = new JButton("÷");
        eqButton = new JButton("=");
        clrButton = new JButton("C");
        delButton = new JButton("⌫");
        dotButton = new JButton(".");
        sqrtButton = new JButton("√");
        
        JButton[] btns = {addButton, subButton, mulButton, divButton, eqButton, clrButton, delButton, dotButton, sqrtButton};
        functionButtons = btns;
        
        for(JButton btn : functionButtons) {
            btn.setFont(new Font("Arial", Font.BOLD, 24));
            btn.setFocusPainted(false);
            btn.addActionListener(this);
            btn.setBackground(new Color(255, 140, 0));
            btn.setForeground(Color.WHITE);
        }
        
        // Special styling for different buttons
        clrButton.setBackground(new Color(220, 53, 69));
        delButton.setBackground(new Color(220, 53, 69));
        eqButton.setBackground(new Color(40, 167, 69));
        sqrtButton.setBackground(new Color(23, 162, 184));
        
        // Create panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(50, 50, 50));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add buttons to panel
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(divButton);
        
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(mulButton);
        
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(subButton);
        
        panel.add(numberButtons[0]);
        panel.add(dotButton);
        panel.add(sqrtButton);
        panel.add(addButton);
        
        panel.add(clrButton);
        panel.add(delButton);
        panel.add(new JLabel());
        panel.add(eqButton);
        
        add(panel, BorderLayout.CENTER);
        
        // Make frame visible
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Number buttons
        for(int i = 0; i < 10; i++) {
            if(e.getSource() == numberButtons[i]) {
                if(startNewNumber) {
                    display.setText(String.valueOf(i));
                    startNewNumber = false;
                } else {
                    String currentText = display.getText();
                    if(!currentText.equals("0")) {
                        display.setText(currentText + i);
                    } else {
                        display.setText(String.valueOf(i));
                    }
                }
            }
        }
        
        // Decimal point
        if(e.getSource() == dotButton) {
            if(startNewNumber) {
                display.setText("0.");
                startNewNumber = false;
            } else if(!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        }
        
        // Addition
        if(e.getSource() == addButton) {
            num1 = Double.parseDouble(display.getText());
            operation = '+';
            startNewNumber = true;
            currentOperator = "+";
        }
        
        // Subtraction
        if(e.getSource() == subButton) {
            num1 = Double.parseDouble(display.getText());
            operation = '-';
            startNewNumber = true;
            currentOperator = "-";
        }
        
        // Multiplication
        if(e.getSource() == mulButton) {
            num1 = Double.parseDouble(display.getText());
            operation = '*';
            startNewNumber = true;
            currentOperator = "×";
        }
        
        // Division
        if(e.getSource() == divButton) {
            num1 = Double.parseDouble(display.getText());
            operation = '/';
            startNewNumber = true;
            currentOperator = "÷";
        }
        
        // Square root
        if(e.getSource() == sqrtButton) {
            num1 = Double.parseDouble(display.getText());
            if(num1 >= 0) {
                result = Math.sqrt(num1);
                display.setText(formatResult(result));
                startNewNumber = true;
            } else {
                display.setText("Error");
                startNewNumber = true;
            }
        }
        
        // Equals
        if(e.getSource() == eqButton) {
            num2 = Double.parseDouble(display.getText());
            
            switch(operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if(num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error: Division by zero");
                        startNewNumber = true;
                        return;
                    }
                    break;
                default:
                    return;
            }
            
            display.setText(formatResult(result));
            startNewNumber = true;
        }
        
        // Clear
        if(e.getSource() == clrButton) {
            display.setText("0");
            num1 = 0;
            num2 = 0;
            result = 0;
            startNewNumber = true;
            currentOperator = "";
        }
        
        // Delete (backspace)
        if(e.getSource() == delButton) {
            String currentText = display.getText();
            if(currentText.length() > 1) {
                display.setText(currentText.substring(0, currentText.length() - 1));
            } else {
                display.setText("0");
                startNewNumber = true;
            }
        }
    }
    
    private String formatResult(double value) {
        // Check if it's a whole number
        if(value == (long) value) {
            return String.valueOf((long) value);
        }
        // Limit decimal places to 10
        String formatted = String.format("%.10f", value);
        // Remove trailing zeros
        formatted = formatted.replaceAll("0*$", "");
        // Remove decimal point if no decimals left
        formatted = formatted.replaceAll("\\.$", "");
        return formatted;
    }
    
    public static void main(String[] args) {
        // Run GUI in Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch(Exception e) {
                    e.printStackTrace();
                }
                new AdvancedCalculator();
            }
        });
    }
}
