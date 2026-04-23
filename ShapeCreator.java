import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeCreator extends JFrame implements ActionListener {
    private JButton circleBtn, ovalBtn, rectangleBtn, squareBtn, triangleBtn;
    private JButton roundedRectBtn, lineBtn, arcBtn, pentagonBtn, hexagonBtn;
    private JPanel buttonPanel, drawingPanel;
    private String selectedShape = "";

    public ShapeCreator() {
        setTitle("Shape Creator - 10 Different Shapes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 5, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create buttons
        circleBtn = new JButton("Circle");
        ovalBtn = new JButton("Oval");
        rectangleBtn = new JButton("Rectangle");
        squareBtn = new JButton("Square");
        triangleBtn = new JButton("Triangle");
        roundedRectBtn = new JButton("Rounded Rect");
        lineBtn = new JButton("Line");
        arcBtn = new JButton("Arc");
        pentagonBtn = new JButton("Pentagon");
        hexagonBtn = new JButton("Hexagon");

        // Add action listeners
        circleBtn.addActionListener(this);
        ovalBtn.addActionListener(this);
        rectangleBtn.addActionListener(this);
        squareBtn.addActionListener(this);
        triangleBtn.addActionListener(this);
        roundedRectBtn.addActionListener(this);
        lineBtn.addActionListener(this);
        arcBtn.addActionListener(this);
        pentagonBtn.addActionListener(this);
        hexagonBtn.addActionListener(this);

        // Add buttons to panel
        buttonPanel.add(circleBtn);
        buttonPanel.add(ovalBtn);
        buttonPanel.add(rectangleBtn);
        buttonPanel.add(squareBtn);
        buttonPanel.add(triangleBtn);
        buttonPanel.add(roundedRectBtn);
        buttonPanel.add(lineBtn);
        buttonPanel.add(arcBtn);
        buttonPanel.add(pentagonBtn);
        buttonPanel.add(hexagonBtn);

        // Drawing Panel
        drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(3));

                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;

                switch (selectedShape) {
                    case "Circle":
                        g2d.drawOval(centerX - 100, centerY - 100, 200, 200);
                        break;
                    case "Oval":
                        g2d.drawOval(centerX - 120, centerY - 80, 240, 160);
                        break;
                    case "Rectangle":
                        g2d.drawRect(centerX - 120, centerY - 80, 240, 160);
                        break;
                    case "Square":
                        g2d.drawRect(centerX - 100, centerY - 100, 200, 200);
                        break;
                    case "Triangle":
                        int[] xPoints = {centerX, centerX - 100, centerX + 100};
                        int[] yPoints = {centerY - 100, centerY + 80, centerY + 80};
                        g2d.drawPolygon(xPoints, yPoints, 3);
                        break;
                    case "Rounded Rect":
                        g2d.drawRoundRect(centerX - 120, centerY - 80, 240, 160, 30, 30);
                        break;
                    case "Line":
                        g2d.drawLine(centerX - 150, centerY, centerX + 150, centerY);
                        break;
                    case "Arc":
                        g2d.drawArc(centerX - 100, centerY - 100, 200, 200, 0, 180);
                        break;
                    case "Pentagon":
                        int[] xPent = new int[5];
                        int[] yPent = new int[5];
                        for (int i = 0; i < 5; i++) {
                            double angle = i * 2 * Math.PI / 5 - Math.PI / 2;
                            xPent[i] = centerX + (int)(100 * Math.cos(angle));
                            yPent[i] = centerY + (int)(100 * Math.sin(angle));
                        }
                        g2d.drawPolygon(xPent, yPent, 5);
                        break;
                    case "Hexagon":
                        int[] xHex = new int[6];
                        int[] yHex = new int[6];
                        for (int i = 0; i < 6; i++) {
                            double angle = i * 2 * Math.PI / 6;
                            xHex[i] = centerX + (int)(100 * Math.cos(angle));
                            yHex[i] = centerY + (int)(100 * Math.sin(angle));
                        }
                        g2d.drawPolygon(xHex, yHex, 6);
                        break;
                }
            }
        };
        drawingPanel.setBackground(Color.WHITE);

        add(buttonPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        selectedShape = e.getActionCommand();
        drawingPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ShapeCreator());
    }
}