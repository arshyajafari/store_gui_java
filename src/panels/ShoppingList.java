package panels;

// import files
import database.DatabaseClass;
import swing.Theme;

// import java awt and swing packages
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// import java sql packages
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingList extends JFrame implements ActionListener {

    // variables
    private static final long serialVersionUID = 1L;
    private final JComboBox byWhatCB;
    private final JTable table;
    private final JButton buttonExit, buttonBack, buttonCheck, buttonDelete;
    public static String selectedShopingList = null;
    private DefaultTableModel model;
    JTextField keywordTF;
    public ShoppingList() {
        super("Shopping List");

        this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Theme.BACKGROUND_PANEL);

        JLabel title = new JLabel("Shoping List");
        title.setBounds(30, 40, 280, 75);
        title.setOpaque(true);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        title.setFont(Theme.FONT_TITLE);
        title.setForeground(Theme.COLOR_TITLE);
        title.setBackground(Theme.BACKGROUND_TITLE);
        panel.add(title);

        buttonExit = new JButton("Exit");
        buttonExit.setBounds(Theme.GUI_WIDTH - 140, 40, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonExit.setFont(Theme.FONT_BUTTON);
        buttonExit.setBackground(Color.WHITE);
        buttonExit.setForeground(Theme.COLOR_TITLE);
        buttonExit.addActionListener(this);
        panel.add(buttonExit);

        buttonBack = new JButton("Back");
        buttonBack.setBounds(Theme.GUI_WIDTH - 140, 80, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonBack.setFont(Theme.FONT_BUTTON);
        buttonBack.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonBack.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonBack.addActionListener(this);
        panel.add(buttonBack);

        buttonDelete = new JButton("Delete");
        buttonDelete.setBounds(Theme.GUI_WIDTH - 140, 120, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonDelete.setFont(Theme.FONT_BUTTON);
        buttonDelete.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonDelete.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonDelete.addActionListener(this);
        panel.add(buttonDelete);

        JLabel keywordLabel = new JLabel("Keyword: ");
        keywordLabel.setBounds(60, 140, 140, 30);
        keywordLabel.setFont(Theme.FONT_REGULAR);
        panel.add(keywordLabel);

        keywordTF = new JTextField();
        keywordTF.setBounds(160, 140, 240, 30);
        keywordTF.setFont(Theme.FONT_INPUT);
        panel.add(keywordTF);

        byWhatCB = new JComboBox(new Object[]{"By ID", "By Name"});
        byWhatCB.setBounds(400, 140, 100, 29);
        byWhatCB.setFont(Theme.FONT_INPUT);
        panel.add(byWhatCB);

        buttonCheck = new JButton("Search");
        buttonCheck.setBounds(500, 140, Theme.BUTTON_PRIMARY_WIDTH, 30);
        buttonCheck.setFont(Theme.FONT_BUTTON);
        buttonCheck.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonCheck.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonCheck.addActionListener(this);
        panel.add(buttonCheck);

        table = new JTable();
        table.setDefaultEditor(Object.class, null);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(40, 185, 600, 300);
        panel.add(scroll);

        this.setTableData(0, null);

        JLabel header = new JLabel();
        header.setBackground(Theme.BACKGROUND_HEADER);
        header.setOpaque(true);
        header.setBounds(0, 0, Theme.GUI_WIDTH, 75);
        panel.add(header);

        this.add(panel);
    }

    private void setTableData(int id, String name) {
        model = new DefaultTableModel(new String[]{"ID", "Customer ID", "Product Code", "Name", "Price"}, 0);
        String sql = "SELECT * FROM shoping_list ";
        boolean nameWhere = false;
        if (id != 0 || name != null) {
            sql += "WHERE ";
            if (id != 0) {
                sql += "id = " + id + " ";
            }
            if (name != null) {
                nameWhere = true;
                sql += "name = ? ";
            }
        }
        PreparedStatement statement;
        try {
            statement = DatabaseClass.getInstance().getConnection().prepareStatement(sql);
            if (nameWhere)
                statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("id"), rs.getString("customer_id"),
                        rs.getString("product_code"), rs.getString("name"), rs.getString("price")});
            }
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(buttonExit)) {
            System.exit(0);
        } else if (ae.getSource().equals(buttonBack)) {
            this.setVisible(false);
            new Customer().setVisible(true);
        } else if (ae.getSource().equals(buttonDelete)) {
            this.selectedShopingList = null;
            try {
                String sql = "Delete FROM shoping_list where id="
                        + model.getValueAt(table.getSelectedRow(), 0).toString();
                PreparedStatement state;
                try {
                    state = DatabaseClass.getInstance().getConnection().prepareStatement(sql);
                    state.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                this.model.removeRow(table.getSelectedRow());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please select an option");
            }
        } else if (ae.getSource().equals(buttonCheck)) {
            if (keywordTF.getText().length() > 0) {
                if (byWhatCB.getSelectedIndex() == 0) {
                    try {
                        int i = Integer.parseInt(keywordTF.getText());
                        this.setTableData(i, null);
                    } catch (Exception ignored) {
                    }
                } else {
                    this.setTableData(0, keywordTF.getText());
                }
            } else {
                this.setTableData(0, null);
            }
        }
    }
}