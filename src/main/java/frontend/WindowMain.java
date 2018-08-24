package frontend;

import backend.DataBase;
import backend.MyConnection;
import backend.Sociometry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @autor Kunakbaev Artem
 */
public class WindowMain extends JFrame {
    private static DataBase dataBase;

    public WindowMain() throws HeadlessException {

        dataBase = new DataBase();

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
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataBase.createTable();
            }
        });
        panel.add(create);
        JButton newRow = new JButton("Добавить строку");
        newRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataBase.addRow("Проверка");
            }
        });
        panel.add(newRow);
        JButton showAll = new JButton("Показать все");
        showAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataBase.readAll();
            }
        });
        panel.add(showAll);
        JButton delete = new JButton("Удалить базу");
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataBase.deleteTable();
            }
        });
        panel.add(delete);
        add(panel, BorderLayout.EAST);
    }

    private ImagePanel setBackground(){
        return new ImagePanel(new ImageIcon("./src/main/resources/соц.jpg").getImage());
    }
}
