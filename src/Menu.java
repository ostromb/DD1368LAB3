import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Menu extends JFrame {
    private JButton searchButton;
    private JPanel MenuPanel;
    private JTabbedPane tabbedPane1;
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
        getContentPane().add(MenuPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);


        searchButton.addActionListener(new ActionListener() {
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
                             */

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
    }
    }


