import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.Calendar;

import net.proteanit.sql.DbUtils;
import java.sql.PreparedStatement;
import java.awt.*;

public class Watchlist extends JPanel{
    private Connection connection;
    private InfoHolder infoHolder;
    private DataEntry dataEntry;
    public JTable MovieInfo;
    //public JTable WatchlistTable;
    //public JPanel panel1;
    //public JPanel panel2;
    //public JLabel Label;
    //public JButton button1;
    //public JButton button2;

    public Watchlist(InfoHolder infoHolder, Connection connection, DataEntry dataEntry) {
        this.infoHolder = infoHolder;
        this.connection = connection;
        this.dataEntry = dataEntry;
        JTable WatchlistTable = new JTable();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel Label = new JLabel();
        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JTextField movieid = new JTextField(4);
        JTextField newrating = new JTextField(2);
        JTextField addwatchlist = new JTextField(7);
        JTextField addcustomer = new JTextField(4);
        JTextField adduser = new JTextField(1);
        JTextField addmedia = new JTextField(4);
        JTextField addmediatype = new JTextField(1);
        JTextField addrating = new JTextField(1);
        JTextField addprogress = new JTextField(1);
        JTextField removewatchlist = new JTextField(4);
        WatchlistTable = new JTable();
        MovieInfo = new JTable();
        panel1 = new JPanel();
        panel2 = new JPanel();
        Label = new JLabel("---------- mediaid | Your rating | Progress (%) | Movie title | Release year | Average rating | Length (minutes) | Country ----------");
        button1 = new JButton("Update Your Rating");
        button2 = new JButton("Add to Watchlist");
        button3 = new JButton("Remove from Watchlist");
        add(Label);
        add(WatchlistTable);
        add(MovieInfo);
        add(movieid);
        add(newrating);
        add(button1);
        add(panel1, BorderLayout.NORTH);
        panel1.add(removewatchlist);
        panel1.add(button3);
        add(panel2, BorderLayout.SOUTH);
        panel2.add(addwatchlist);
        panel2.add(addcustomer);
        panel2.add(adduser);
        panel2.add(addmedia);
        panel2.add(addmediatype);
        panel2.add(addrating);
        panel2.add(addprogress);
        panel2.add(button2);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String rating = newrating.getText();
                Integer customer = infoHolder.getId();
                String profile = infoHolder.getProfile();
                String watchid = movieid.getText();

                try {
                    PreparedStatement st = connection.prepareStatement("UPDATE watchlist SET rating=? WHERE customerid=? AND userprofile=? AND mediaid=?");
                    st.setInt(1, Integer.parseInt(rating));
                    st.setInt(2, customer);
                    st.setString(3, profile);
                    st.setInt(4, Integer.parseInt(watchid));
                    st.executeUpdate();
                }
                catch(SQLException d){
                    d.printStackTrace();
                }
            }
        }); 

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String watchlistid = addwatchlist.getText();
                String customerid = addcustomer.getText();
                String user = adduser.getText();
                String mediaid = addmedia.getText();
                String mediatype = addmediatype.getText();
                String rating = addrating.getText();
                String progress = addprogress.getText();

                try {
                    PreparedStatement st2 = connection.prepareStatement("INSERT INTO Watchlist (watchlistid, customerid, userprofile, mediaid, media_type, rating, progress) VALUES(?,?,?,?,?,?,?)");
                    st2.setInt(1, Integer.parseInt(watchlistid));
                    st2.setInt(2, Integer.parseInt(customerid));
                    st2.setString(3, user);
                    st2.setInt(4, Integer.parseInt(mediaid));
                    st2.setString(5, mediatype);
                    st2.setInt(6, Integer.parseInt(rating));
                    st2.setInt(7, Integer.parseInt(progress));
                }
                catch(SQLException w){
                    w.printStackTrace();
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String media = removewatchlist.getText();
                Integer customer = infoHolder.getId();
                String profile = infoHolder.getProfile();

                try {
                    PreparedStatement st3 = connection.prepareStatement("DELETE FROM Watchlist WHERE mediaid=? AND customerid=? AND userprofile=?");
                    st3.setInt(1, Integer.parseInt(media));
                    st3.setInt(2, customer);
                    st3.setString(3, profile);
                    st3.executeQuery();
                } catch (SQLException rw){
                    rw.printStackTrace();
                }
            }
        });

        try {
            PreparedStatement st1 = connection.prepareStatement("SELECT mediaid, watchlist.rating, watchlist.progress, movies.name, movies.year, movies.rating, movies.length, movies.country FROM watchlist INNER JOIN movies ON mediaid=movieid WHERE customerid=? AND userprofile=?");
            st1.setInt(1, infoHolder.getId());
            st1.setString(2, "" + infoHolder.getUserProfile());
            ResultSet rs1 = st1.executeQuery();

            WatchlistTable.setModel(DbUtils.resultSetToTableModel(rs1));
        }
        catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
        }
    }
}


/*
import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;

public class Watchlist extends JPanel{
    private Connection connection;
    private InfoHolder infoHolder;
    private DataEntry dataEntry;
    private ArrayList<Integer> allmedia;
    private GridLayout layout;
    public Watchlist (InfoHolder infoHolder, Connection connection, DataEntry dataEntry) {
        System.out.println(infoHolder.getId() + " " + infoHolder.getUserProfile());
        this.infoHolder = infoHolder;
        this.connection = connection;
        this.dataEntry = dataEntry;
        layout = new GridLayout(1, 3);
        setLayout(layout);

        try {
            allmedia = new ArrayList<>();
            PreparedStatement st1 = connection.prepareStatement("SELECT mediaid, rating, progress FROM watchlist WHERE customerid=? AND userprofile=?");
            st1.setInt(1, infoHolder.getId());
            st1.setString(2, "" + infoHolder.getUserProfile());
            ResultSet rs1 = st1.executeQuery();
            PreparedStatement st2 = connection.prepareStatement("SELECT * FROM movies WHERE movieid=?");
            st2.setInt(1, rs1.getInt(1));
            ResultSet rs2 = st2.executeQuery();
                while (rs1.next() && rs2.next()) {
                    allmedia.add(rs2.getInt(1));
                    add(new JLabel(rs2.getString(2) + rs2.getInt(3) + rs2.getInt(4) + rs2.getInt(5) + rs2.getString(6) + rs2.getString(7) + rs2.getString(8)));
                    add(new JLabel(rs1.getInt(7) + "%"));
                    add(new JTextField(rs1.getInt(6)));
                    System.out.println(rs1.getInt(6));
                    layout.setRows(layout.getRows() + 1);
            }
        }
        catch(SQLException e) {

        }

    }
}
*/