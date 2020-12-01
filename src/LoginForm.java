import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

public class LoginForm extends JFrame {
    private Connection connection;
    private JLabel mailLabel, passLabel;
    private JTextField email_field;
    private JPasswordField password_field;
    private JButton button1;
    private InfoHolder infoHolder;
    private DataEntry dataEntry;

    public LoginForm(InfoHolder infoHolder, Connection connection, DataEntry dataEntry) {
        this.infoHolder = infoHolder;
        this.connection = connection;
        this.dataEntry = dataEntry;
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        mailLabel = new JLabel("email");
        this.add(mailLabel);
        email_field = new JTextField(30);
        this.add(email_field);
        passLabel = new JLabel("password");
        this.add(passLabel);
        password_field = new JPasswordField(30);
        this.add(password_field);
        button1 = new JButton("Login");
        this.add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String mail = email_field.getText();
                    String pass = password_field.getText();
                    PreparedStatement st = connection.prepareStatement("SELECT email, password  FROM customer WHERE  email=? AND password=?");
                    st.setString(1, mail);
                    st.setString(2, pass);
                    ResultSet rs = st.executeQuery();
                    if(rs.next()) {
                        JOptionPane.showMessageDialog(null,"Login Successful");
                        PreparedStatement st1 = connection.prepareStatement("SELECT customerid FROM customer WHERE email=? AND password=?");
                        st1.setString(1, mail);
                        st1.setString(2, pass);
                        ResultSet rs1 = st1.executeQuery();
                        rs1.next();
                        infoHolder.setId(rs1.getInt(1));
                        dataEntry.LFtoCP();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Login failed");
                    }
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}