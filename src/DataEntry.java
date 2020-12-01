import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class DataEntry extends JFrame {
    private Connection connection;
    private LoginForm LF;
    private ChooseProfile CP;
    private int chosencustomer;
    public void setCurrentComp(JComponent e) {
        add(e, BorderLayout.CENTER);
        invalidate();
        validate();
        repaint();
    }

    DataEntry() {
        connection = DataEntry.getConnection();
        LF = new LoginForm();
        CP = new ChooseProfile();
        setTitle("Netflix :)");
        setSize(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        this.add(LF);
    }

    public static void main (String[] args) {
        new DataEntry();

    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String host="psql-dd1368-ht20.sys.kth.se";
            String port="5432";
            String db_name="mssostromb";
            String username="axu";
            String password="TTahULyD87Za";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"", ""+username+"", ""+password+"");

            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Failed");
            }

            return connection;
        }catch(Exception e) {
            e.printStackTrace();
            return connection;
        }
    }
    class LoginForm extends JPanel {
        private JLabel mailLabel, passLabel;
        private JTextField email_field;
        private JPasswordField password_field;
        private JButton button1;


        public LoginForm() {
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
                            chosencustomer = rs1.getInt(1);
                            System.out.println("CustomerID: " + chosencustomer);
                            CP.profile_choice();
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

    class ChooseProfile extends JPanel {
        private JButton[] profile_button;
        private ArrayList<String> profile_names;
        public void profile_choice() {
            try {
                PreparedStatement pst = connection.prepareStatement("SELECT * FROM customerprofiles");
                ResultSet rs = pst.executeQuery();
                profile_names = new ArrayList<>();
                while (rs.next()) {
                    if(rs.getInt(1) == chosencustomer) {
                        profile_names.add(rs.getString(3));
                    }
                }
                for (int i=0; i<profile_names.size(); i++) {
                    profile_button[i].setText(profile_names.get(i));
                    add(profile_button[i]);
                }
                LF.removeAll();
                LF.validate();
                LF.repaint();
                setCurrentComp(this);

            } catch (SQLException ex) {
            }
        }

        public ChooseProfile() {
            profile_button = new JButton[3];
            profile_button[0] = new JButton();
            profile_button[1] = new JButton();
            profile_button[2] = new JButton();
        }
    }
}