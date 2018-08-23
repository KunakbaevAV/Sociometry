package frontend;

import backend.Group;
import backend.Respondent;
import backend.Sociometry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @autor Kunakbaev Artem
 */
public class WindowArray extends JFrame {
    private Sociometry sociometry;
    private Group group;
    private JTextArea textArea;

    public static void main(String[] args) {
        new WindowArray();
    }

    public WindowArray() throws HeadlessException {

        setBounds(500,100,400,600);
        setTitle("Введите список группы");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        newPanel();

        newButtons();

        setVisible(true);
    }



    private void newPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(textArea);
        panel.add(scroll);
        add(panel, BorderLayout.CENTER);
    }

    private void newButtons() {
        JPanel panel = new JPanel(new GridLayout(1,2));

        JButton ok = new JButton("Далее");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String[] names = textArea.getText().split("\n");
                group = new Group(names[0]);
                for (int i = 1; i < names.length; i++) {
                    if(!names[i].equals("")) group.addRespondent(new Respondent(names[i]));
                }
            }
        });

        JButton cancel = new JButton("Отмена");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println(group.getNameGroup());
                System.out.println("");
                for (int i = 0; i < group.getGroupSize(); i++) {
                    System.out.println(group.getRespondent(i).getName());
                }
            }
        });

        panel.add(cancel);
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
    }

}
