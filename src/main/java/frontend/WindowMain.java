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

        setBounds(500,200,250,300);
        setTitle("Социометрия");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
    }

    private ImagePanel setBackground(){
        return new ImagePanel(new ImageIcon("./src/main/resources/соц.jpg").getImage());
    }
}
