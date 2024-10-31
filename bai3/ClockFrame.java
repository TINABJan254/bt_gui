package bai3;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

public class ClockFrame extends JFrame {
    private JPanel clockPanel;
    private JLabel digitalClockLabel;

    public ClockFrame() {
        setTitle("Đồng Hồ Kim và Đồng Hồ Số");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel hiển thị đồng hồ kim
        clockPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawClockFace(g);
                drawClockHands(g);
            }
        };
        clockPanel.setPreferredSize(new Dimension(400, 400));
        add(clockPanel, BorderLayout.CENTER);

        // Digital clock
        digitalClockLabel = new JLabel();
        digitalClockLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        digitalClockLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(digitalClockLabel, BorderLayout.SOUTH);

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
                updateDigitalClock();
            }
        }, 0, 1000);
    }

    private void drawClockFace(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        
        // viền
        g2d.setStroke(new BasicStroke(5));
        g2d.drawOval(50, 50, 300, 300);

        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("12", 190, 75);
        g.drawString("3", 325, 205);
        g.drawString("6", 195, 345);
        g.drawString("9", 60, 205);
    }

    private void drawClockHands(Graphics g) {
        LocalTime time = LocalTime.now();
        int hours = time.getHour();
        int minutes = time.getMinute();
        int seconds = time.getSecond();

        //vị trí 
        double secondAngle = Math.toRadians((seconds - 15) * 6);
        double minuteAngle = Math.toRadians((minutes - 15) * 6);
        double hourAngle = Math.toRadians((hours % 12 - 3) * 30 + minutes * 0.5);

        // Vẽ kim giờ
        g.setColor(Color.BLACK);
        g.drawLine(200, 200, 200 + (int) (70 * Math.cos(hourAngle)), 200 + (int) (70 * Math.sin(hourAngle)));

        // Vẽ kim phút
        g.setColor(Color.BLUE);
        g.drawLine(200, 200, 200 + (int) (100 * Math.cos(minuteAngle)), 200 + (int) (100 * Math.sin(minuteAngle)));

        // Vẽ kim giây
        g.setColor(Color.RED);
        g.drawLine(200, 200, 200 + (int) (120 * Math.cos(secondAngle)), 200 + (int) (120 * Math.sin(secondAngle)));
    }

    private void updateDigitalClock() {
        LocalTime time = LocalTime.now();
        String timeText = String.format("%02d:%02d:%02d", time.getHour(), time.getMinute(), time.getSecond());
        digitalClockLabel.setText(timeText);
    }
}

