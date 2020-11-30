import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.ResultSet;

public class LoginForm {
    private JPanel panel1;
    private JTextField email_field;
    private JPasswordField password_field;
    private JButton button1;

    public LoginForm() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = DataEntry.getConnection();
                try {
                    String name = email_field.getText();
                    String pass = password_field.getText();
                    PreparedStatement st = connection.prepareStatement("SELECT email, password  FROM customer WHERE email=? AND password=?");
                    st.setString(1, name);
                    st.setString(2,pass);

                    ResultSet rs = st.executeQuery();
                    if(rs.next()) {
                        JOptionPane.showMessageDialog(null,"Login Successful");
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
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new LoginForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }
}
