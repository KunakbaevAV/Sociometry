package frontend;

import backend.Respondent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * @autor Kunakbaev Artem
 */
class WindowRespondent extends JFrame {
    private Respondent respondent;
    private String motiv = "Дружеский";
    private int numberOfChoises = 5;

    WindowRespondent(Respondent respondent) {
        this.respondent = respondent;
        setBounds(500, 100, 400, 400);
        setTitle("Ответы респондентов");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        newTitle();

        newTable();

        newButtons();

        setVisible(true);
    }

    private void newTitle() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JLabel respondentLabel = new JLabel("Респондент: ");
        JLabel respondentName = new JLabel(respondent.getName());
        JLabel motivLabel = new JLabel("Мотив: ");
        JLabel motivInput = new JLabel(motiv);
        panel.add(new JSeparator());
        panel.add(new JSeparator());

        panel.add(respondentLabel);
        panel.add(respondentName);
        panel.add(new JSeparator());
        panel.add(new JSeparator());

        panel.add(motivLabel);
        panel.add(motivInput);
        panel.add(new JSeparator());
        panel.add(new JSeparator());
        add(panel, BorderLayout.NORTH);
    }

    private void newTable() {
        try {
            Robot robot = new Robot();

            JPanel panel = new JPanel(new GridLayout(1, 2));
            JPanel likesPanel = new JPanel(new GridLayout(6, 1));
            JPanel dislikesPanel = new JPanel(new GridLayout(6, 1));

            JLabel likeLabel = new JLabel("Положительные выборы");
            likesPanel.add(likeLabel);
            ArrayList<JTextField> likes = new ArrayList<JTextField>();
            for (int i = 0; i < numberOfChoises; i++) {
                JTextField textField = new JTextField();
                setCommand(textField, robot);
                likes.add(textField);
                likesPanel.add(textField);
            }

            JLabel dislikeLabel = new JLabel("Отрицательные выборы");
            dislikesPanel.add(dislikeLabel);
            ArrayList<JTextField> dislikes = new ArrayList<JTextField>();
            for (int i = 0; i < numberOfChoises; i++) {
                JTextField textField = new JTextField();
                setCommand(textField, robot);
                dislikes.add(textField);
                dislikesPanel.add(textField);
            }

            panel.add(likesPanel);
            panel.add(dislikesPanel);
            add(panel, BorderLayout.CENTER);

            likes.get(0).requestFocus();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void setCommand(final JTextField textField, final Robot robot) {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField.setText("enter");
                    robot.keyPress(KeyEvent.VK_TAB);
                }
            }
        });
    }

    private void newButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));

        JButton ok = new JButton("Далее");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                WindowArray.war.setVisible(true);
            }
        });
        ok.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    setVisible(false);
                    WindowArray.war.setVisible(true);
                }
            }
        });

        JButton cancel = new JButton("Отмена");
        cancel.setFocusable(false);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                WindowArray.war.setVisible(true);
            }
        });

        panel.add(cancel);
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
    }
}
