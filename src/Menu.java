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
        JPanel pn1 = new Search(infoHolder, connection,dataEntry);
        tabbedPane1.add("Search",pn1);
        tabbedPane1.add("Watchlist",pn2);
        tabbedPane1.add("Settings",pn3);
        add(tabbedPane1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(false);



    }
}