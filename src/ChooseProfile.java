import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

public class ChooseProfile extends JFrame {
    private Connection connection;
    private InfoHolder infoHolder;
    private JButton[] profile_button;
    private ArrayList<String> profile_names;
    private DataEntry dataEntry;

    public ChooseProfile(InfoHolder infoHolder, Connection connection, DataEntry dataEntry) {
        this.infoHolder = infoHolder;
        this.connection = connection;
        this.dataEntry = dataEntry;
        profile_button = new JButton[3];
        profile_button[0] = new JButton();
        profile_button[1] = new JButton();
        profile_button[2] = new JButton();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLayout(new FlowLayout());
        profile_button[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoHolder.setProfile(profile_names.get(0));
                dataEntry.CPtoMF();
            }
        });
        profile_button[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoHolder.setProfile(profile_names.get(1));
                dataEntry.CPtoMF();
            }
        });
        profile_button[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                infoHolder.setProfile(profile_names.get(2));
                dataEntry.CPtoMF();
            }
        });

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM customerprofiles");
            ResultSet rs = pst.executeQuery();
            profile_names = new ArrayList<>();

            while (rs.next()) {
                if(rs.getInt(1) == infoHolder.getId()) {
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
}