package bai2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private JPanel drawingPanel;
    private JComboBox<String> shapeSelector;
    private JComboBox<String> fillTypeSelector;
    private JButton chooseColorButton, drawButton, clearButton, settingButton;
    private Color selectedColor = Color.BLACK;
    private int shapeWidth = 200, shapeHeight = 150;
    private int circleDiameter = 150;
    private int squareSide = 150;

    public MyFrame() {
        setTitle("Shape Drawer");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tạo bảng điều khiển
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        // Chọn loại hình
        shapeSelector = new JComboBox<>(new String[] {"Hình Vuông", "Hình Chữ Nhật", "Hình Tròn", "Hình Đa Giác"});
        controlPanel.add(new JLabel("Chọn Hình:"));
        controlPanel.add(shapeSelector);

        // Chọn kiểu rỗng hay đặc
        fillTypeSelector = new JComboBox<>(new String[] {"Rỗng", "Đặc"});
        controlPanel.add(new JLabel("Kiểu hình:"));
        controlPanel.add(fillTypeSelector);

        // Chọn màu
        chooseColorButton = new JButton("Chọn Màu");
        chooseColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Chọn màu", selectedColor);
                if (color != null) {
                    selectedColor = color;
                }
            }
        });
        controlPanel.add(chooseColorButton);
        
        // Nút "Vẽ" để vẽ hình khi được nhấn
        drawButton = new JButton("Vẽ");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawShape();
            }
        });
        controlPanel.add(drawButton);
        
        // Nút "Xóa" để xóa tất cả các hình
        clearButton = new JButton("Xóa");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingPanel.repaint();
            }
        });
        controlPanel.add(clearButton);
        
        // Nút thiết lập kích thước
        settingButton = new JButton("Thiết lập kích thước");
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) shapeSelector.getSelectedItem();
                if (selectedShape.equals("Hình Vuông")){
                    showSquareSettingDialog();
                }
                else if (selectedShape.equals("Hình Chữ Nhật")){
                    showRectSettingDialog();
                }
                else if (selectedShape.equals("Hình Tròn")){
                    showCircleSettingDialog();
                }
            }
        });
        controlPanel.add(settingButton);
        
        // Bảng vẽ hình
        drawingPanel = new JPanel();
        drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel, BorderLayout.CENTER);
        
        add(controlPanel, BorderLayout.NORTH);
    }
    
    // Hàm tạo và hiển thị JDialog để nhập kích thước
    private void showRectSettingDialog() {
        JDialog sizeDialog = new JDialog(this, "Thiết lập kích thước", true);
        sizeDialog.setSize(300, 150);
        sizeDialog.setLayout(new GridLayout(3, 2));
        JLabel widthLabel = new JLabel("Chiều rộng:");
        JTextField widthField = new JTextField(String.valueOf(shapeWidth));
        JLabel heightLabel = new JLabel("Chiều cao:");
        JTextField heightField = new JTextField(String.valueOf(shapeHeight));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    shapeWidth = Integer.parseInt(widthField.getText());
                    shapeHeight = Integer.parseInt(heightField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(sizeDialog, "Vui lòng nhập số hợp lệ cho chiều rộng và chiều cao.");
                }
                sizeDialog.dispose();
            }
        });

        sizeDialog.add(widthLabel);
        sizeDialog.add(widthField);
        sizeDialog.add(heightLabel);
        sizeDialog.add(heightField);
        sizeDialog.add(okButton);

        sizeDialog.setLocationRelativeTo(this);
        sizeDialog.setVisible(true);
    }
    
    // Hàm tạo và hiển thị JDialog để nhập đường kính cho hình tròn
    private void showCircleSettingDialog() {
        JDialog circleDialog = new JDialog(this, "Thiết lập đường kính hình tròn", true);
        circleDialog.setSize(300, 100);
        circleDialog.setLayout(new FlowLayout());

        JLabel diameterLabel = new JLabel("Đường kính:");
        JTextField diameterField = new JTextField(String.valueOf(circleDiameter), 10);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            try {
                circleDiameter = Integer.parseInt(diameterField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(circleDialog, "Vui lòng nhập số hợp lệ cho đường kính.");
            }
            circleDialog.dispose();
        });

        circleDialog.add(diameterLabel);
        circleDialog.add(diameterField);
        circleDialog.add(okButton);

        circleDialog.setLocationRelativeTo(this);
        circleDialog.setVisible(true);
    }

    // Hàm tạo và hiển thị JDialog để nhập cạnh cho hình vuông
    private void showSquareSettingDialog() {
        JDialog squareDialog = new JDialog(this, "Thiết lập cạnh hình vuông", true);
        squareDialog.setSize(300, 100);
        squareDialog.setLayout(new FlowLayout());

        JLabel sideLabel = new JLabel("Cạnh:");
        JTextField sideField = new JTextField(String.valueOf(squareSide), 10);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            try {
                squareSide = Integer.parseInt(sideField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(squareDialog, "Vui lòng nhập số hợp lệ cho cạnh.");
            }
            squareDialog.dispose();
        });

        squareDialog.add(sideLabel);
        squareDialog.add(sideField);
        squareDialog.add(okButton);

        squareDialog.setLocationRelativeTo(this);
        squareDialog.setVisible(true);
    }

    private void drawShape() {
        Graphics g = drawingPanel.getGraphics();
        g.setColor(selectedColor);

        String selectedShape = (String) shapeSelector.getSelectedItem();
        String fillType = (String) fillTypeSelector.getSelectedItem();
        
        int w = drawingPanel.getWidth(), h = drawingPanel.getHeight();
        
        int x = (int)(Math.random() * w), y = (int)(Math.random() * h);
        
        if ("Hình Vuông".equals(selectedShape)) {
            if ("Đặc".equals(fillType)) {
                g.fillRect(x, y, squareSide, squareSide);
            } else {
                g.drawRect(x, y, squareSide, squareSide);
            }
        } else if ("Hình Chữ Nhật".equals(selectedShape)) {
            if ("Đặc".equals(fillType)) {
                g.fillRect(x, y, shapeWidth, shapeHeight);
            } else {
                g.drawRect(x, y, shapeWidth, shapeHeight);
            }
        } else if ("Hình Tròn".equals(selectedShape)) {
            if ("Đặc".equals(fillType)) {
                g.fillOval(x, y, circleDiameter, circleDiameter);
            } else {
                g.drawOval(x, y, circleDiameter, circleDiameter);
            }
        } else if ("Hình Đa Giác".equals(selectedShape)) {
            int[] xPoints = {x, x + 40, x + 20, x - 20, x - 40};
            int[] yPoints = {y, y + 50, y + 100, y + 100, y + 50};
    
            if ("Đặc".equals(fillType)) {
                g.fillPolygon(xPoints, yPoints, xPoints.length);
            } else {
                g.drawPolygon(xPoints, yPoints, xPoints.length);
            }
        }
    }
}

