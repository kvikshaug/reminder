package no.kvikshaug.reminder.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reminder {

    private JPanel mainPanel;
    private JLabel headerLabel;
    private JButton closeButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextField hostTextField;
    private JTable table;

    public Reminder() {
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            System.err.println("Could not find/use system theme, rolling back to default theme.");
        }

        JFrame frame = new JFrame("Reminder");
        frame.setContentPane(new Reminder().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation((int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth() - frame.getWidth()) / 2),
                (int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight() - frame.getHeight()) / 2));
        frame.setVisible(true);
    }
}
