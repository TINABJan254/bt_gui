/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testjavaswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai1 extends JFrame {
    private JPanel panel;

    public Bai1() {
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

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            Bai1 example = new Bai1();
//            example.setVisible(true);
//        });
//    }
}

