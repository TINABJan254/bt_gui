package bai1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooserFrame extends JFrame {
    private JPanel panel;

    public ColorChooserFrame() {
        setTitle("Color Chooser Example");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        add(panel, BorderLayout.CENTER);

        JButton colorButton = new JButton("Chọn Màu");
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mở hộp thoại chọn màu
                Color selectedColor = JColorChooser.showDialog(null, "Chọn màu", panel.getBackground());

                if (selectedColor != null) {
                    // Đổi màu nền thành màu được chọn
                    panel.setBackground(selectedColor);
                }
            }
        });

        add(colorButton, BorderLayout.SOUTH);
    }
}

