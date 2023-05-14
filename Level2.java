
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Level2 extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel panel;

    private int startingTime = 25;
    private JButton mole;
    private int count;
    private Timer timer;
    private JLabel counterToPanel;
    private Timer countdownTimer;
    private JFrame timerFrame;

    public Level2() {
        // locating and assigning variable to the image of the mole
        ImageIcon moleImage = new ImageIcon("mainmole.png");

        frame = new JFrame("Whack-a-mole");
        frame.setResizable(false);
        frame.setSize(900, 900);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });

        panel = new JPanel();
        frame.add(panel);

        mole = new JButton(moleImage);
        mole.setPreferredSize(new Dimension(100, 100));
        panel.add(mole);
        // timer for how often the mole shows up and disappears, lower number to make
        // enemies spawn and go away faster
        mole.addActionListener(this);

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random random = new Random();
                int x = random.nextInt(frame.getWidth() - mole.getWidth());
                int y = random.nextInt(frame.getHeight() - mole.getHeight());
                mole.setLocation(x, y);
                panel.add(mole);
                if (count == 10) {
                    timer.stop();
                }
            }
        });

        timer.start();

        timerFrame = new JFrame("Timer");
        timerFrame.setResizable(false);
        timerFrame.setSize(200, 100);
        timerFrame.setLocation(frame.getX() + frame.getWidth() + 50, frame.getY());
        timerFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        timerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmExit();
            }
        });
        // bunch of code to create new frame for the timer and score counter
        JPanel timerPanel = new JPanel();
        timerFrame.add(timerPanel);

        JLabel time = new JLabel();
        timerPanel.add(time);
        counterToPanel = new JLabel();
        counterToPanel.setText(String.valueOf(count));
        timerPanel.add(counterToPanel);
        // countdown timer, delay is 1000 (acts as one second)
        countdownTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (startingTime == 0) {
                    JOptionPane.showMessageDialog(frame, "Ran out of time.");
                    cleanup();
                } else {
                    startingTime--;
                    time.setText(String.valueOf(startingTime));
                }
            }
        });

        countdownTimer.start();

        frame.setVisible(true);
        timerFrame.setVisible(true);
    }

    private void confirmExit() {
        int option = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Confirm Exit",
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            cleanup();
        }
    }

    private void cleanup() {
        countdownTimer.stop();
        timerFrame.dispose();
        frame.dispose();
        new realGUI();
    }

    public static void main(String[] args) {
        new Level2();
    }

    // works with counts and such, for some reason this HAS to exist for
    // code to function
    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        panel.remove(mole);
        panel.repaint();
        counterToPanel.setText(String.valueOf(count));
        if (count == 10) {
            countdownTimer.stop();
            cleanup();
        }
    }
}