package frontend;

import backend.Sociometry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @autor Kunakbaev Artem
 */
public class WindowMain extends JFrame {


    public WindowMain() throws HeadlessException {


        add(setBackground(), BorderLayout.CENTER);

        setBounds(500,200,400,300);
        setTitle("Социометрия");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        newBittonBase();

        newButtonStart();

        setVisible(true);
    }

    private void newButtonStart() {
        JButton start = new JButton("начать");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new WindowArray();
                setVisible(false);
            }
        });
        add(start, BorderLayout.SOUTH);
        start.requestFocus();
    }

    private void newBittonBase(){
        JPanel panel = new JPanel(new GridLayout(4,1));
        JButton create = new JButton("Создать базу");
        panel.add(create);
        JButton look = new JButton("Посмотреть базу");
        panel.add(look);
        JButton change = new JButton("Изменить базу");
        panel.add(change);
        JButton delete = new JButton("Удалить базу");
        panel.add(delete);
        add(panel, BorderLayout.EAST);
    }

    private ImagePanel setBackground(){
        return new ImagePanel(new ImageIcon("./src/main/resources/соц.jpg").getImage());
    }
}
