import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
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


                    PreparedStatement st = connection.prepareStatement(" SELECT COUNT(*) FROM (SELECT DISTINCT name,movieid FROM "+fromstr+"  WHERE name  LIKE '%"+getarrays[0]+"%' AND country LIKE '%"+getarrays[2]+"%' AND CAST(year AS TEXT) LIKE '%"+getarrays[1]+"%' AND genre LIKE '%"+getarrays[4]+"%' AND director LIKE '%"+getarrays[3]+"%' AND actor LIKE '%"+getarrays[5]+"%' AND CAST(length AS TEXT) LIKE '%"+getarrays[7]+"%' AND CAST(rating AS TEXT) LIKE '%"+getarrays[6]+"%') as O");
                    ResultSet rs1 = st.executeQuery();
                    rs1.next();
                    int size = rs1.getInt(1);



                    try {
                        PreparedStatement st1 = connection.prepareStatement("SELECT DISTINCT name,movieid FROM "+fromstr+"  WHERE name  LIKE '%"+getarrays[0]+"%' AND country LIKE '%"+getarrays[2]+"%' AND CAST(year AS TEXT) LIKE '%"+getarrays[1]+"%' AND genre LIKE '%"+getarrays[4]+"%' AND director LIKE '%"+getarrays[3]+"%' AND actor LIKE '%"+getarrays[5]+"%' AND CAST(length AS TEXT) LIKE '%"+getarrays[7]+"%' AND CAST(rating AS TEXT) LIKE '%"+getarrays[6]+"%'");
                        ResultSet rs = st1.executeQuery();
                        JButton[] moviebuttons = new JButton[size];

                        int ticker = 0;

                        while (rs.next()) {

                            moviebuttons[ticker] = new JButton(rs.getString(arrays[0]));
                            int mid = rs.getInt(2);
                            sr.add(moviebuttons[ticker]);
                            sr.revalidate();
                            found = true;
                            int finalTicker = ticker;
                            moviebuttons[ticker].addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {


                                    try {

                                        PreparedStatement genp = connection.prepareStatement("SELECT genre FROM genres WHERE mediaid=?");
                                        genp.setInt(1,mid);
                                        ResultSet genrs = genp.executeQuery();
                                        List<String> genlist = new ArrayList<>();
                                        while (genrs.next()) {
                                             genlist.add(genrs.getString(1));
                                        }

                                        PreparedStatement dirp = connection.prepareStatement("SELECT director FROM directors WHERE mediaid=?");
                                        dirp.setInt(1,mid);
                                        ResultSet dirrs = dirp.executeQuery();
                                        List<String> dirlist = new ArrayList<>();
                                        while (dirrs.next()) {
                                            dirlist.add(dirrs.getString(1));
                                        }

                                        PreparedStatement actp = connection.prepareStatement("SELECT actor FROM actors WHERE mediaid=?");
                                        actp.setInt(1,mid);
                                        ResultSet actrs = actp.executeQuery();
                                        List<String> actlist = new ArrayList<>();
                                        while (actrs.next()) {
                                            actlist.add(actrs.getString(1));
                                        }

                                        PreparedStatement lengp = connection.prepareStatement("SELECT length,country,year FROM movies WHERE movieid=?");
                                        lengp.setInt(1,mid);
                                        ResultSet lengrs = lengp.executeQuery();
                                        lengrs.next();
                                        String lengthv = lengrs.getString("length");
                                        String countryv = lengrs.getString("country");
                                        String yearv = lengrs.getString("year");




                                        JOptionPane.showMessageDialog(null, genlist+"\n"+dirlist+"\n"+actlist+"\n"+lengthv+"\n"+countryv+"\n"+yearv);
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                }
                            });
                            ticker +=1;



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
