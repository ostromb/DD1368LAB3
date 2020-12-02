import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static java.sql.Types.NULL;

public class Search extends JPanel {
    private Connection connection;
    private InfoHolder infoHolder;
    private DataEntry dataEntry;
    private String[] arrays = {"name","year","country","director","genre","actor","rating","length"};
    private JLabel[] labelarrays = new JLabel[8];
    private JTextField[] fieldarrays = new JTextField[8];


    public Search(InfoHolder infoHolder, Connection connection, DataEntry dataEntry) {
        JPanel filterbarp = new JPanel();



        for(int i = 0;i< arrays.length;i++) {
            labelarrays[i] = new JLabel(arrays[i]);
            filterbarp.add(labelarrays[i]);
            fieldarrays[i] = new JTextField(10);
            filterbarp.add(fieldarrays[i]);
        }
        setLayout(new BorderLayout());
        add(filterbarp,BorderLayout.NORTH);

        setSize(500,500);
        //JScrollPane scrolPane = new JScrollPane();
        JButton srbut = new JButton("Search");
        //add(scrolPane);
        filterbarp.add(srbut);
        setVisible(true);
        revalidate();

        JPanel sr = new JPanel();
        add(sr, BorderLayout.CENTER);
        sr.setVisible(true);

        srbut.addActionListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent e) {
                sr.removeAll();
                sr.repaint();
                boolean found = false;

                try {
                    String[] getarrays = new String[8];
                    for (int i = 0; i < getarrays.length; i++) {
                        getarrays[i] = fieldarrays[i].getText();
                    }
                    String fromstr = "(SELECT b.*, actor FROM (SELECT k.*, director FROM (SELECT movies.*, genre FROM genres INNER JOIN movies ON movieid = mediaid ) as k INNER JOIN directors ON k.movieid = directors.mediaid) as b INNER JOIN actors ON b.movieid = actors.mediaid) as c";


                    PreparedStatement st = connection.prepareStatement("SELECT DISTINCT movieid, name FROM "+fromstr+"  WHERE name  LIKE '%"+getarrays[0]+"%' AND country LIKE '%"+getarrays[2]+"%' AND CAST(year AS TEXT) LIKE '%"+getarrays[1]+"%' AND genre LIKE '%"+getarrays[4]+"%' AND director LIKE '%"+getarrays[3]+"%' AND actor LIKE '%"+getarrays[5]+"%' AND CAST(length AS TEXT) LIKE '%"+getarrays[7]+"%' AND CAST(rating AS TEXT) LIKE '%"+getarrays[6]+"%'");
                    ResultSet rs = st.executeQuery();


                    try {
                        /**rs.last();
                        int size = rs.getRow();
                        rs.first();
                        JButton[] moviebuttons = new JButton[size];*/

                        while (rs.next()) {

                            JButton moviebutton = new JButton(rs.getString(arrays[0]));
                            sr.add(moviebutton);
                            sr.revalidate();
                            found = true;


                        }
                        if (!found) {
                            JOptionPane.showMessageDialog(null, "No search Result");
                        }
                    } catch(Exception f) {
                        JOptionPane.showMessageDialog(null, "No search Result");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });







    }
}
