package frontend;

import backend.DataBase;
import backend.MyConnection;
import backend.Respondent;
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
        start.addActionListener(e -> {
            new WindowArray();
            setVisible(false);
        });
        add(start, BorderLayout.SOUTH);
        start.requestFocus();
    }

    private void newBittonBase(){
        JPanel panel = new JPanel(new GridLayout(4,1));
        JButton create = new JButton("Создать базу");
        create.addActionListener(e -> dataBase.createNewBase());
        panel.add(create);
        JButton newRow = new JButton("Добавить строку");
        newRow.addActionListener(e -> dataBase.insert(new Respondent("Проверка")));
        panel.add(newRow);
        JButton showAll = new JButton("Показать все");
        showAll.addActionListener(e -> System.out.println(dataBase.getAll().toString()));
        panel.add(showAll);
        JButton delete = new JButton("Удалить базу");
        delete.addActionListener(e -> dataBase.deleteTable());
        panel.add(delete);
        add(panel, BorderLayout.EAST);
    }

    private ImagePanel setBackground(){
        return new ImagePanel(new ImageIcon("./src/main/resources/соц.jpg").getImage());
    }
}
