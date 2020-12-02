import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.sql.PreparedStatement;
import java.awt.*;

public class Watchlist extends JPanel{
    private Connection connection;
    private InfoHolder infoHolder;
    private DataEntry dataEntry;
    private JTable WatchlistTable;
    private JTable MovieInfo;
    private JPanel panel1;
    private JLabel Label;
    private JButton button1;

    public Watchlist(InfoHolder infoHolder, Connection connection, DataEntry dataEntry) {
        this.infoHolder = infoHolder;
        this.connection = connection;
        this.dataEntry = dataEntry;
        WatchlistTable = new JTable();
        MovieInfo = new JTable();
        panel1 = new JPanel();
        Label = new JLabel("---------- mediaid | Your rating | Progress (%) | Movie title | Release year | Average rating | Length (minutes) | Country ----------");
        button1 = new JButton("Update Your Rating");
        add(Label);
        add(WatchlistTable);
        add(MovieInfo);
        add(button1);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int pos = Integer.parseInt(WatchlistTable.getValueAt(0, 1).toString());
                    PreparedStatement st = connection.prepareStatement("UPDATE watchlist SET watchlist.rating=? WHERE customerid=? AND userprofile=?");
                    st.setInt(1, pos);
                    st.setInt(2, infoHolder.getId());
                    st.setString(3, "" + infoHolder.getUserProfile());
                    st.executeUpdate();

                }
                catch(SQLException d){

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