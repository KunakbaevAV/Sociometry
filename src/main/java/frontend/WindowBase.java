package frontend;

import backend.DataBase;
import backend.Respondent;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @autor Kunakbaev Artem
 */
public class WindowBase extends JFrame {
    private DataBase dataBase;
    static JTextArea logWindow;

    public WindowBase(DataBase dataBase) throws HeadlessException {
        this.dataBase = dataBase;

        setTitle("База студентов");
        setBounds(500, 200, 600, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        addPanelCommands();

        addLogWindow();

        setVisible(true);
    }

    private void addLogWindow() {
        logWindow = new JTextArea();
        logWindow.setLineWrap(true);
        logWindow.setWrapStyleWord(true);
//        logWindow.setEnabled(false);
        logWindow.setText(dataBase.getLog());
        JScrollPane scroll = new JScrollPane(logWindow);
        add(scroll, BorderLayout.CENTER);
    }

    private void addPanelCommands() {
        JPanel panel = new JPanel(new GridLayout(8, 1));
        JButton btnCreateBD = new JButton("Создать новую базу данных");
        JButton btnInsertStud = new JButton("Добавить нового респондента");
        JButton btnUpdateStid = new JButton("Редактировать респондента (по id)");
        JButton btnSelectStud = new JButton("Показать респондента (по id)");
        JButton btnDeleteStud = new JButton("Удалить респондента (по id)");
        JButton btnSelectAll = new JButton("Показать всех респондентов");
        JButton btnAddBatch = new JButton("Добавить несколько респондентов");
        JButton btnDeleteAll = new JButton("Удалить базу данных");
        btnCreateBD.addActionListener(e -> {
            dataBase.createNewBase();
            updateLog();
        });
        btnInsertStud.addActionListener(e -> new WindowAddResp(dataBase, WindowAddResp.INSERT));
        btnUpdateStid.addActionListener(e -> new WindowAddResp(dataBase, WindowAddResp.UPDATE));
        btnSelectStud.addActionListener(e -> new WindowAddResp(dataBase, WindowAddResp.SELECT));
        btnDeleteStud.addActionListener(e -> new WindowAddResp(dataBase, WindowAddResp.DELETE));
        btnSelectAll.addActionListener(e -> {
            dataBase.getAll();
            updateLog();
        });
        btnAddBatch.addActionListener(e -> {
            dataBase.setLog("Еще не работает");
            updateLog();
        });
        btnDeleteAll.addActionListener(e -> {
            dataBase.deleteTable();
            updateLog();
        });
        panel.add(btnCreateBD);
        panel.add(btnInsertStud);
        panel.add(btnUpdateStid);
        panel.add(btnSelectStud);
        panel.add(btnDeleteStud);
        panel.add(btnSelectAll);
        panel.add(btnAddBatch);
        panel.add(btnDeleteAll);
        add(panel, BorderLayout.EAST);
    }

    private void updateLog() {
        logWindow.setText(dataBase.getLog());
    }
}

class WindowAddResp extends JFrame {
    static final int INSERT = 1;
    static final int UPDATE = 2;
    static final int SELECT = 3;
    static final int DELETE = 4;

    private JTextField idText;
    private JTextField dateText;
    private JTextField groupText;
    private JTextField motivText;
    private JTextField nameText;
    private JTextField likeText;
    private JTextField dislikeText;
    private DataBase base;
    private Respondent currentRespondent;
    private int mod;

    WindowAddResp(DataBase base, int mod) throws HeadlessException {
        this.base = base;
        this.mod = mod;
        currentRespondent = null;
        setTitle("Добавить нового респондента");
        setBounds(550, 250, 200, 250);

        addPannel();

        addButtons();

        setVisible(true);
    }

    private void addButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton ok = new JButton("Ок");
        ok.addActionListener(e -> {
            defaultValues();
            int id = Integer.parseInt(idText.getText());
            String date = dateText.getText();
            String group = groupText.getText();
            String motiv = motivText.getText();
            String name = nameText.getText();
            String likes = likeText.getText();
            String dislikes = dislikeText.getText();

            currentRespondent = new Respondent(id, date, group, motiv, name, likes, dislikes);

            doCommand(currentRespondent);
            WindowBase.logWindow.setText(base.getLog());

            setVisible(false);
        });
        JButton cancel = new JButton("Отмена");
        cancel.addActionListener(e -> setVisible(false));
        panel.add(cancel);
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
    }

    private void defaultValues() {
        if (idText.getText().equals("")) idText.setText("0");
        if (dateText.getText().equals("")){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy, HH:mm");
            dateText.setText(dateFormat.format(new Date()));
        }
        if (motivText.getText().equals("")) motivText.setText("Не указан");
        if (groupText.getText().equals("")) groupText.setText("Не указана");
        if (nameText.getText().equals("")) nameText.setText("Не указано");
        if (likeText.getText().equals("")) likeText.setText("Положительные выборы отсутствуют");
        if (dislikeText.getText().equals("")) dislikeText.setText("Отрицательные выборы отсутствуют");
    }

    private void addPannel() {
        JPanel panel = new JPanel(new GridLayout(7, 2));
        JLabel idLabel = new JLabel("id: ");
        JLabel dateLabel = new JLabel("Дата: ");
        JLabel groupLabel = new JLabel("Группа: ");
        JLabel motivLabel = new JLabel("Мотив: ");
        JLabel nameLabel = new JLabel("Имя: ");
        JLabel likeLabel = new JLabel("Положительные выборы: ");
        JLabel dislikeLabel = new JLabel("Отрицательные выборы: ");
        idText = new JTextField();
        dateText = new JTextField();
        groupText = new JTextField();
        motivText = new JTextField();
        nameText = new JTextField();
        likeText = new JTextField();
        dislikeText = new JTextField();
        panel.add(idLabel);
        panel.add(idText);
        panel.add(dateLabel);
        panel.add(dateText);
        panel.add(groupLabel);
        panel.add(groupText);
        panel.add(motivLabel);
        panel.add(motivText);
        panel.add(nameLabel);
        panel.add(nameText);
        panel.add(likeLabel);
        panel.add(likeText);
        panel.add(dislikeLabel);
        panel.add(dislikeText);
        add(panel, BorderLayout.CENTER);
    }

    private void doCommand(Respondent respondent) {
        switch (mod) {
            case 1:
                base.insert(respondent);
                break;
            case 2:
                base.update(respondent);
                break;
            case 3:
                int id = Integer.parseInt(idText.getText());
                currentRespondent = base.get(id);
                break;
            case 4:
                base.delete(respondent.getId());
                break;
        }
    }

}
