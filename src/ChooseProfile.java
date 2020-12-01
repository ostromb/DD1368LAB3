import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.ResultSet;

public class ChooseProfile extends JFrame{

    private JPanel panel1;
    private JButton profile_a;
    private JButton profile_b;
    private JButton profile_c;

    public ChooseProfile(String name, String pass) {
        getContentPane().add(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        profile_a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                Connection connection = DataEntry.getConnection();

            }
        });

    }
}
