package frontend;

import backend.Group;
import backend.Respondent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @autor Kunakbaev Artem
 */
class WindowAllRespondents extends JFrame {
    private Group group;
    private int summResp;

    WindowAllRespondents(Group group) {
        this.group = group;
        summResp = group.getGroupSize();

        setBounds(500,100,400,400);
        setTitle("Ответы респондентов");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        newPannelRespondents();

        newButtons();

        setVisible(true);
    }

    private void newPannelRespondents() {
        JPanel panel = new JPanel(new GridLayout(summResp,1));
        for (int i = 0; i < summResp; i++) {
            JButton btn = new JButton(group.getRespondent(i).getName());
            newBtnCommand(btn, i);
            panel.add(btn);
        }
        JScrollPane scroll = new JScrollPane(panel);
        add(scroll, BorderLayout.CENTER);
    }

    private void newBtnCommand(JButton btn, final int i) {//команда для каждой кнопки
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WindowRespondent(group.getRespondent(i));
                setVisible(false);
            }
        });
    }

    private void newButtons() {
        JPanel panel = new JPanel(new GridLayout(1,2));

        JButton ok = new JButton("Далее");
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton cancel = new JButton("Отмена");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        panel.add(cancel);
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
    }
}
