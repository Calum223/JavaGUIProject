

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class realGUI extends JFrame implements ActionListener {

    private JFrame frame;
    private JPanel panel;
    private JButton lvl1;
    private JButton lvl2;
    private JButton lvl3;


    public realGUI() {

        frame = new JFrame("Whack-a-mole");
        // setting up all the buttons
        panel = new JPanel(new GridLayout(4, 0, 50, 50));
        lvl1 = new JButton();
        lvl2 = new JButton();
        lvl3 = new JButton();

        lvl1.addActionListener(this);
        lvl2.addActionListener(this);
        lvl3.addActionListener(this);



        lookOfButton(lvl1, "Whack-a-mole Level 1");
        lookOfButton(lvl2, "Whack-a-mole Level 2");
        lookOfButton(lvl3, "Whack-a-mole Level 3");


        panel.add(lvl1);
        panel.add(lvl2);
        panel.add(lvl3);

        // basic frame setup
        frame.add(panel);
        frame.setSize(1280, 1024);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }

    public static void main(String[] args) {

        new realGUI();
    }

    // if more difficulties are wanted, easy to edit look of buttons
    private void lookOfButton(JButton button, String text) {
        button.setText(text);
        button.setFont(new Font("Arial", Font.PLAIN, 100));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // testing which button is clicked, opening new frame based on that
        if (e.getSource() == lvl1) {

            frame.dispose();
            new Level1();

        } else if (e.getSource() == lvl2) {
            frame.dispose();
            new Level2();

        } else {
            frame.dispose();
            new Level3();


    }
}}
