import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class PaintBrush extends JFrame {
    private DrawingCanvas canvas;
    private JComboBox<String> colorBox;
    private JSlider widthSlider;
    private JButton clearBtn;
    private JRadioButton pencilBtn, eraserBtn;

    public PaintBrush() {
        setTitle("Paint Brush - Color & Width Selection");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create canvas
        canvas = new DrawingCanvas();
        add(canvas, BorderLayout.CENTER);

        // Create control panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(5, 1, 10, 10));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Color selection
        JPanel colorPanel = new JPanel();
        colorPanel.add(new JLabel("Color: "));
        String[] colors = {"Black", "Red", "Blue", "Green", "Yellow", "Orange", "Pink", "Cyan"};
        colorBox = new JComboBox<>(colors);
        colorBox.addActionListener(e -> {
            String color = (String) colorBox.getSelectedItem();
            canvas.setDrawingColor(color);
        });
        colorPanel.add(colorBox);
        controlPanel.add(colorPanel);

        // Width selection
        JPanel widthPanel = new JPanel();
        widthPanel.add(new JLabel("Width: "));
        widthSlider = new JSlider(1, 20, 5);
        widthSlider.setMajorTickSpacing(5);
        widthSlider.setPaintTicks(true);
        widthSlider.setPaintLabels(true);
        widthSlider.addChangeListener(e -> canvas.setBrushWidth(widthSlider.getValue()));
        widthPanel.add(widthSlider);
        controlPanel.add(widthPanel);

        // Tool selection
        JPanel toolPanel = new JPanel();
        pencilBtn = new JRadioButton("Pencil", true);
        eraserBtn = new JRadioButton("Eraser");
        ButtonGroup toolGroup = new ButtonGroup();
        toolGroup.add(pencilBtn);
        toolGroup.add(eraserBtn);
        pencilBtn.addActionListener(e -> canvas.setTool("pencil"));
        eraserBtn.addActionListener(e -> canvas.setTool("eraser"));
        toolPanel.add(pencilBtn);
        toolPanel.add(eraserBtn);
        controlPanel.add(toolPanel);

        // Clear button
        clearBtn = new JButton("Clear Canvas");
        clearBtn.addActionListener(e -> canvas.clearCanvas());
        controlPanel.add(clearBtn);

        // Instructions
        JLabel instruction = new JLabel("Drag mouse to draw", SwingConstants.CENTER);
        controlPanel.add(instruction);

        add(controlPanel, BorderLayout.EAST);
        setVisible(true);
    }

    class DrawingCanvas extends JPanel {
        private java.util.ArrayList<Point> points = new java.util.ArrayList<>();
        private java.util.ArrayList<Color> colors = new java.util.ArrayList<>();
        private java.util.ArrayList<Integer> widths = new java.util.ArrayList<>();
        private Color currentColor = Color.BLACK;
        private int currentWidth = 5;
        private String currentTool = "pencil";
        private Point lastPoint;

        public DrawingCanvas() {
            setBackground(Color.WHITE);
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    lastPoint = e.getPoint();
                    if (currentTool.equals("eraser")) {
                        currentColor = Color.WHITE;
                    }
                    points.add(lastPoint);
                    colors.add(currentColor);
                    widths.add(currentWidth);
                    repaint();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    Point newPoint = e.getPoint();
                    points.add(newPoint);
                    colors.add(currentColor);
                    widths.add(currentWidth);
                    
                    if (currentTool.equals("eraser")) {
                        currentColor = Color.WHITE;
                    }
                    repaint();
                    lastPoint = newPoint;
                }
            });
        }

        public void setDrawingColor(String colorName) {
            switch (colorName) {
                case "Black": currentColor = Color.BLACK; break;
                case "Red": currentColor = Color.RED; break;
                case "Blue": currentColor = Color.BLUE; break;
                case "Green": currentColor = Color.GREEN; break;
                case "Yellow": currentColor = Color.YELLOW; break;
                case "Orange": currentColor = Color.ORANGE; break;
                case "Pink": currentColor = Color.PINK; break;
                case "Cyan": currentColor = Color.CYAN; break;
            }
            if (currentTool.equals("eraser")) {
                currentColor = Color.WHITE;
            }
        }

        public void setBrushWidth(int width) {
            this.currentWidth = width;
        }

        public void setTool(String tool) {
            this.currentTool = tool;
            if (tool.equals("eraser")) {
                currentColor = Color.WHITE;
            } else {
                String selected = (String) colorBox.getSelectedItem();
                setDrawingColor(selected);
            }
        }

        public void clearCanvas() {
            points.clear();
            colors.clear();
            widths.clear();
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (int i = 0; i < points.size() - 1; i++) {
                Point p1 = points.get(i);
                Point p2 = points.get(i + 1);
                g2d.setColor(colors.get(i));
                g2d.setStroke(new BasicStroke(widths.get(i), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaintBrush());
    }
}
