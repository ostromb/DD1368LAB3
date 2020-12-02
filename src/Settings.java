import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
public class Settings extends JPanel{
    private Connection connection;
    private InfoHolder infoHolder;
    private DataEntry dataEntry;
    public Settings(InfoHolder infoHolder, Connection connection, DataEntry dataEntry)  {
        setSize(500,500);
        setVisible(true);
        JPanel subs = new JPanel();

        JButton butupdatesub = new JButton("Update Subscription");
        JLabel lblsubs = new JLabel("Manage Subscription");
        subs.add(lblsubs);


        setLayout(new BorderLayout());
        add(subs,BorderLayout.NORTH);
        JPanel profiles = new JPanel();
        JButton butprofileadd = new JButton("Add Profile");
        JButton butprofileremove = new JButton("Remove Profile");
        JTextField addfield = new JTextField(30);
        JTextField removefield = new JTextField(30);
        add(profiles, BorderLayout.CENTER);
        profiles.add(addfield);
        profiles.add(butprofileadd);
        profiles.add(removefield);
        profiles.add(butprofileremove);





        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM (SELECT * FROM subscription INNER JOIN paymentinfo p on subscription.subscriptionid = p.subscriptionid) as k WHERE customerid = ?");
            st.setInt(1, infoHolder.getId());
            ResultSet rs = st.executeQuery();
            rs.next();
            JTextField cardnr = new JTextField(rs.getString(2));
            JTextField cardissue = new JTextField(rs.getString(3));
            String crd = Integer.toString((rs.getInt(4)));
            JTextField carddate = new JTextField(crd);
            JTextField cardplan = new JTextField(rs.getString(5));
            String cra = Integer.toString(rs.getInt(6));
            JTextField cardamount = new JTextField(cra);



            subs.add(cardnr);
            subs.add(cardissue);
            subs.add(carddate);
            subs.add(cardplan);
            subs.add(cardamount);

            subs.add(butupdatesub);
            subs.revalidate();

            PreparedStatement lt = connection.prepareStatement("SELECT * FROM customerprofiles WHERE customerid=?");
            lt.setInt(1,infoHolder.getId());
            ResultSet rl = lt.executeQuery();

            while (rl.next()) {
                JLabel lblprof = new JLabel(rl.getString("name"));
                profiles.add(lblprof);


            }

            butprofileremove.addActionListener(new ActionListener() {




                @Override
                public void actionPerformed(ActionEvent e) {
                    String remname = removefield.getText();



                    try {
                        PreparedStatement lt1 = connection.prepareStatement("DELETE FROM customerprofiles WHERE customerid = ? AND name = ?");
                        lt1.setInt(1,infoHolder.getId());
                        lt1.setString(2,remname);
                        lt1.executeQuery();
                        repaint();
                        revalidate();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }
            });

            butprofileadd.addActionListener(new ActionListener() {




                @Override
                public void actionPerformed(ActionEvent e) {
                    String addname = addfield.getText();



                    try {


                        PreparedStatement lt1 = connection.prepareStatement("INSERT INTO customerprofiles (customerid, userprofile, name, birthdate) VALUES (?,?,?, NULL)");
                        lt1.setInt(1,infoHolder.getId());
                        lt1.setString(2,"d");
                        lt1.setString(3,addname);
                        lt1.executeQuery();
                        repaint();
                        revalidate();



                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }
            });

            butupdatesub.addActionListener(new ActionListener() {


                @Override
                public void actionPerformed(ActionEvent e) {
                    String c1 = cardnr.getText();
                    String c2 = cardissue.getText();
                    int c3 = Integer.parseInt(carddate.getText());
                    String c4 = cardplan.getText();
                    int c5 = Integer.parseInt(cardamount.getText());
                    try {
                        PreparedStatement st1 = connection.prepareStatement("UPDATE subscription SET card_number = ?, card_issue = ?, card_date = ?, sub_type = ?,payment_amount = ? WHERE subscriptionid = ?" );
                        st1.setString(1,c1);
                        st1.setString(2,c2);
                        st1.setInt(3,c3);
                        st1.setString(4,c4);
                        st1.setInt(5,c5);
                        st1.setInt(6, infoHolder.getId());

                        st1.executeQuery();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }






    }

}
