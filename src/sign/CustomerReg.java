package sign;

// import files
import database.DatabaseClass;
import swing.Theme;

// import java swing and awt packages
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import java sql package
import java.sql.PreparedStatement;

public class CustomerReg extends JFrame implements ActionListener {

    // variables
    private static final long serialVersionUID = 1L;
    private final JButton buttonExit, buttonSubmit, buttonBack;
    private final JTextField customerUserTF, customerFirstNameTF, customerLastNameTF, customerPhoneTF, passwordF,
            customerAddressTF;
    private final JComboBox customerPhoneCB;
    private final JRadioButton male;

    String[] str1 = {"93-AFG", "374-ARM", "994-AZE", "1-CAN", "86-CHN", "20-EGY", "33-FRA", "49-DEU", "91-IND",
            "98-IR", "39-ITA", "81-JPN", "7-RUS", "34-ESP", "90-TUR", "44-GBR", "1-USA"};

    public CustomerReg() {
        super("Customer Registration");

        this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Theme.BACKGROUND_PANEL);

        JLabel title = new JLabel("Registration");
        title.setBounds(30, 40, 270, 75);
        title.setOpaque(true);
        title.setBorder(new EmptyBorder(0, 20, 0, 0));
        title.setFont(Theme.FONT_TITLE);
        title.setForeground(Theme.COLOR_TITLE);
        title.setBackground(Theme.BACKGROUND_TITLE);
        panel.add(title);

        JLabel customerUserLabel = new JLabel("Username: ");
        customerUserLabel.setBounds(60, 140, 140, 30);
        customerUserLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerUserLabel);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(60, 190, 140, 30);
        passwordLabel.setFont(Theme.FONT_REGULAR);
        panel.add(passwordLabel);

        JLabel customerFirstNameLabel = new JLabel("First Name: ");
        customerFirstNameLabel.setBounds(60, 240, 140, 30);
        customerFirstNameLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerFirstNameLabel);

        JLabel customerLastNameLabel = new JLabel("Last Name: ");
        customerLastNameLabel.setBounds(60, 290, 140, 30);
        customerLastNameLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerLastNameLabel);

        JLabel customerSexLabel = new JLabel("Sex: ");
        customerSexLabel.setBounds(60, 340, 140, 30);
        customerSexLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerSexLabel);

        JLabel customerPhoneLabel = new JLabel("Phone No: ");
        customerPhoneLabel.setBounds(60, 390, 140, 30);
        customerPhoneLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerPhoneLabel);

        JLabel customerAddressLabel = new JLabel("Address: ");
        customerAddressLabel.setBounds(60, 440, 140, 30);
        customerAddressLabel.setFont(Theme.FONT_REGULAR);
        panel.add(customerAddressLabel);

        customerUserTF = new JTextField();
        customerUserTF.setBounds(200, 140, 220, 30);
        customerUserTF.setFont(Theme.FONT_INPUT);
        panel.add(customerUserTF);

        passwordF = new JPasswordField();
        passwordF.setBounds(200, 190, 220, 30);
        passwordF.setFont(Theme.FONT_INPUT);
        panel.add(passwordF);

        customerFirstNameTF = new JTextField();
        customerFirstNameTF.setBounds(200, 240, 220, 30);
        customerFirstNameTF.setFont(Theme.FONT_INPUT);
        panel.add(customerFirstNameTF);

        customerLastNameTF = new JTextField();
        customerLastNameTF.setBounds(200, 290, 220, 30);
        customerLastNameTF.setFont(Theme.FONT_INPUT);
        panel.add(customerLastNameTF);

        male = new JRadioButton("Male");
        male.setBounds(200, 348, 80, 20);
        male.setSelected(false);
        male.setFont(Theme.FONT_INPUT);
        male.setBackground(Theme.BACKGROUND_PANEL);
        panel.add(male);

        JRadioButton female = new JRadioButton("Female");
        female.setBounds(280, 348, 80, 20);
        female.setSelected(false);
        female.setFont(Theme.FONT_INPUT);
        female.setBackground(Theme.BACKGROUND_PANEL);
        panel.add(female);

        ButtonGroup btng = new ButtonGroup();
        btng.add(male);
        btng.add(female);

        customerPhoneCB = new JComboBox(str1);
        customerPhoneCB.setBounds(200, 390, 95, 30);
        customerPhoneCB.setFont(Theme.FONT_INPUT);
        panel.add(customerPhoneCB);

        customerPhoneTF = new JTextField();
        customerPhoneTF.setBounds(296, 390, 125, 30);
        customerPhoneTF.setFont(Theme.FONT_INPUT);
        panel.add(customerPhoneTF);

        customerAddressTF = new JTextField();
        customerAddressTF.setBounds(200, 440, 220, 30);
        customerAddressTF.setFont(Theme.FONT_INPUT);
        panel.add(customerAddressTF);

        buttonSubmit = new JButton("Submit");
        buttonSubmit.setBounds(480, 560, 200, 35);
        buttonSubmit.setFont(Theme.FONT_BUTTON);
        buttonSubmit.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
        buttonSubmit.setForeground(Theme.COLOR_BUTTON_PRIMARY);
        buttonSubmit.addActionListener(this);
        panel.add(buttonSubmit);

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

        JLabel header = new JLabel();
        header.setBackground(Theme.BACKGROUND_HEADER);
        header.setOpaque(true);
        header.setBounds(0, 0, Theme.GUI_WIDTH, 75);
        panel.add(header);

        this.add(panel);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(buttonBack)) {
            this.setVisible(false);
            new CustomerLogin().setVisible(true);
        } else if (ae.getSource().equals(buttonExit)) {
            System.exit(0);
        } else if (ae.getSource().equals(buttonSubmit)) {
            try {
                String sql = "INSERT INTO customer (username, password, first_name, last_name, sex, phone_no, address ) values (?,?,?,?,?,?,?)";
                PreparedStatement statement = DatabaseClass.getInstance().getConnection().prepareStatement(sql);
                statement.setString(1, customerUserTF.getText());
                statement.setString(2, passwordF.getText());
                statement.setString(3, customerFirstNameTF.getText());
                statement.setString(4, customerLastNameTF.getText());
                statement.setString(5, male.isSelected() ? "Male" : "Female");
                String selectedItem = (String) customerPhoneCB.getSelectedItem();
                String[] selectedItemArr = selectedItem.split("-");
                statement.setLong(6, Long.parseLong(selectedItemArr[0] + customerPhoneTF.getText()));
                statement.setString(7, customerAddressTF.getText());
                int row = statement.executeUpdate();
                if (row > 0) {
                    JOptionPane.showMessageDialog(null, "Submit Customer");
                    this.setVisible(false);
                    new CustomerLogin().setVisible(true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
