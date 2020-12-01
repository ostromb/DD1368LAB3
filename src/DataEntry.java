import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class DataEntry {
    private Connection connection;
    private LoginForm LF;
    private ChooseProfile CP;
    private Menu MF;
    private int chosencustomer;
    private String chosenprofile;

    DataEntry() {
        connection = DataEntry.getConnection();
        LF = new LoginForm();
        CP = new ChooseProfile();
        MF = new Menu();
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
    class LoginForm extends JFrame {
        private JLabel mailLabel, passLabel;
        private JTextField email_field;
        private JPasswordField password_field;
        private JButton button1;

        public LoginForm() {
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

    class ChooseProfile extends JFrame {
        private JButton[] profile_button;
        private ArrayList<String> profile_names;

        public void profile_choice() {
            LF.setVisible(false);
            setVisible(true);
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
            } catch (SQLException ex) {
            }
        }

        public ChooseProfile() {
            profile_button = new JButton[3];
            profile_button[0] = new JButton();
            profile_button[1] = new JButton();
            profile_button[2] = new JButton();
            setSize(Toolkit.getDefaultToolkit().getScreenSize());
            setLayout(new FlowLayout());
            profile_button[0].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MF.setVisible(true);
                    MF.setSize(Toolkit.getDefaultToolkit().getScreenSize());
                    MF.revalidate();
                    dispose();
                    chosenprofile = profile_names.get(0);
                }
            });
            profile_button[1].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MF.setVisible(true);
                    MF.setSize(Toolkit.getDefaultToolkit().getScreenSize());
                    MF.revalidate();
                    dispose();
                    chosenprofile = profile_names.get(1);
                }
            });
            profile_button[2].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MF.setVisible(true);
                    MF.setSize(Toolkit.getDefaultToolkit().getScreenSize());
                    MF.revalidate();
                    dispose();
                    chosenprofile = profile_names.get(2);
                }
            });
        }
    }

    class Menu extends JFrame {
        private JButton searchButton;
        private JPanel MenuPanel = new JPanel();
        private JPanel pn1 = new JPanel();
        private JPanel pn2 = new JPanel();
        private JPanel pn3 = new JPanel();
        private JTabbedPane tabbedPane1 = new JTabbedPane();
        private JTextField nameField;
        private JTextField yearField;
        private JTextField countryField;
        private JTextField directorField;
        private JTextField genreField;
        private JTextField actorField;
        private JTextField ratingField;
        private JTextField lengthField;
        private JLabel lblLength;
        private JLabel lblName;
        private JLabel lblActor;
        private JLabel lblYear;
        private JLabel lblCountry;
        private JLabel lblDirector;
        private JLabel lblGenre;
        private JLabel lblRating;

        public Menu() {
            tabbedPane1.add("Search",pn1);
            tabbedPane1.add("Watchlist",pn2);
            tabbedPane1.add("Settings",pn3);
            add(tabbedPane1);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setVisible(false);

            /**searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Connection connection = DataEntry.getConnection();
            try {
            String name = nameField.getText();
            String year = yearField.getText();
            String country = countryField.getText();
            String director = directorField.getText();
            String genre = genreField.getText();
            String actor = actorField.getText();
            String rating = ratingField.getText();
            String length = lengthField.getText();
            PreparedStatement st = connection.prepareStatement("SELECT *  FROM movies  WHERE name LIKE '%"+name+"%'");

            ResultSet rs = st.executeQuery();
            JFrame frame = new JFrame();
            frame.setSize(500, 300);
            JPanel sr = new JPanel();
            JScrollPane srPane = new JScrollPane(sr);

            frame.add(srPane);
            frame.setVisible(true);
            if(rs.next()) {
            while(rs.next()) {
            /**
             * loggs user in and opens menu panel
            JLabel movielabel = new JLabel(rs.getString("name"));
            sr.add(movielabel);
            sr.revalidate();
            }
            }
            else {
            JOptionPane.showMessageDialog(null,"No search Result");
            }
            }catch(Exception ex) {
            ex.printStackTrace();
            }
            }
            });*/
        }
    }
}