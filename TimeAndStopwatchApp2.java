package FOLDER_LOCKER;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeAndStopwatchApp2 {
    private JFrame frame;
    private JLabel currentTimeLabel;
    private JLabel stopwatchLabel;
    private JButton startButton;
    private JButton pauseButton;
    private JButton resetButton;
    private Timer stopwatchTimer;
    private Timer currentTimeTimer;
    private int elapsedTime;
    private boolean isRunning;

    public TimeAndStopwatchApp2() {
        frame = new JFrame("Time and Stopwatch App");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        currentTimeLabel = new JLabel();
        currentTimeLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
        frame.add(currentTimeLabel);

        stopwatchLabel = new JLabel("00:00:00");
        stopwatchLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
        frame.add(stopwatchLabel);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startStopwatch();
            }
        });
        frame.add(startButton);

        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseStopwatch();
            }
        });
        frame.add(pauseButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetStopwatch();
            }
        });
        frame.add(resetButton);

        stopwatchTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeLabel();
            }
        });

        currentTimeTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCurrentTimeLabel();
            }
        });

        isRunning = false;
        elapsedTime = 0;

        frame.setVisible(true);
    }

    private void updateCurrentTimeLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(new Date());
        currentTimeLabel.setText("Current Time: " + currentTime);
    }

    private void updateTimeLabel() {
        if (isRunning) {
            elapsedTime++;
            int hours = elapsedTime / 3600;
            int minutes = (elapsedTime % 3600) / 60;
            int seconds = elapsedTime % 60;
            String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            stopwatchLabel.setText(time);
        }
    }

    private void startStopwatch() {
        isRunning = true;
        stopwatchTimer.start();
        currentTimeTimer.start();
    }

    private void pauseStopwatch() {
        isRunning = false;
        stopwatchTimer.stop();
        currentTimeTimer.stop();
    }

    private void resetStopwatch() {
        isRunning = false;
        elapsedTime = 0;
        stopwatchLabel.setText("00:00:00");
        updateCurrentTimeLabel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimeAndStopwatchApp2();
            }
        });
    }
}
