package bai4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame {
    private JTextField inputField;
    private JButton addButton, subtractButton, multiplyButton, divideButton, powerButton, sqrtButton;
    
    public CalculatorFrame() {
        setTitle("Máy Tính Bỏ Túi");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setEditable(false);
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        add(inputField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 2));

        // Nút Cộng
        addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation('+');
            }
        });
        buttonPanel.add(addButton);

        // Nút Trừ
        subtractButton = new JButton("-");
        subtractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation('-');
            }
        });
        buttonPanel.add(subtractButton);

        // Nút Nhân
        multiplyButton = new JButton("*");
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation('*');
            }
        });
        buttonPanel.add(multiplyButton);

        // Nút Chia
        divideButton = new JButton("/");
        divideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation('/');
            }
        });
        buttonPanel.add(divideButton);

        // Nút Lũy Thừa
        powerButton = new JButton("^");
        powerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation('^');
            }
        });
        buttonPanel.add(powerButton);

        // Nút Căn Bậc Hai
        sqrtButton = new JButton("√");
        sqrtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSqrt();
            }
        });
        buttonPanel.add(sqrtButton);

        add(buttonPanel, BorderLayout.CENTER);
    }

    private void performOperation(char operator) {
        String[] numbers = inputField.getText().split("\\s+");
        if (numbers.length < 2) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập 2 số cách nhau bằng dấu cách.");
            return;
        }

        try {
            double num1 = Double.parseDouble(numbers[0]);
            double num2 = Double.parseDouble(numbers[1]);
            double result = 0;

            switch (operator) {
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
                    if (num2 == 0) {
                        JOptionPane.showMessageDialog(this, "Không thể chia cho 0.");
                        return;
                    }
                    result = num1 / num2;
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    break;
            }

            inputField.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ.");
        }
    }

    private void performSqrt() {
        String input = inputField.getText();
        try {
            double num = Double.parseDouble(input);
            double result = Math.sqrt(num);
            inputField.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ.");
        }
    }
}

