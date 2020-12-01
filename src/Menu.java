import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

public class Menu extends JFrame {
    private Connection connection;
    private InfoHolder infoHolder;
    //private JButton[] profile_button;
    //private ArrayList<String> profile_names;
    private DataEntry dataEntry;

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

    public Menu(InfoHolder infoHolder, Connection connection, DataEntry dataEntry) {
        this.infoHolder = infoHolder;
        this.connection = connection;
        this.dataEntry = dataEntry;
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
        });
         }*/

    }
}