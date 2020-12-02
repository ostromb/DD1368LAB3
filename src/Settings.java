import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import java.util.ArrayList;

public class Settings extends JPanel{
    private Connection connection;
    private InfoHolder infoHolder;
    private DataEntry dataEntry;
    private JLabel[] lblprof = new JLabel[3];
    public Settings(InfoHolder infoHolder, Connection connection, DataEntry dataEntry)  {
        setSize(500,500);
        setVisible(true);
        JPanel subs = new JPanel();
        JPanel movies = new JPanel();

        JButton butupdatesub = new JButton("Update Subscription");
        JLabel lblsubs = new JLabel("Manage Subscription");
        subs.add(lblsubs);


        setLayout(new BorderLayout());
        add(subs,BorderLayout.NORTH);
        JPanel profiles = new JPanel();
        JPanel profileshown = new JPanel();
        JButton butprofileadd = new JButton("Add Profile");
        JButton butprofileremove = new JButton("Remove Profile");
        JButton butprofileupdate = new JButton("Update");
        JTextField addfieldlet = new JTextField(1);
        JTextField addfieldname = new JTextField(15);
        JTextField addfieldbirth = new JTextField(10);
        JTextField removefield = new JTextField(1);
        add(profiles, BorderLayout.CENTER);
        add(profileshown, BorderLayout.SOUTH);
        profiles.add(addfieldlet);
        profiles.add(addfieldname);
        profiles.add(addfieldbirth);
        profiles.add(butprofileadd);
        profiles.add(removefield);
        profiles.add(butprofileremove);
        profiles.add(butprofileupdate);


        for (int i= 0;i<infoHolder.getUserProfiles().size();i++) {

            lblprof[i] = new JLabel(String.valueOf(infoHolder.getUserProfiles().get(i))+"."+infoHolder.getProfile_names().get(i));
            profileshown.add(lblprof[i]);
            profileshown.revalidate();
            profileshown.repaint();

        }



        JButton butmovieadd = new JButton("Add movie");
        JLabel lbladdmovie = new JLabel("Add information about new movie");
        JTextField addmovieid = new JTextField(30);
        JTextField addmoviename = new JTextField(30);
        JTextField addmovieyear = new JTextField(30);
        JTextField addmovierating = new JTextField(30);
        JTextField addmovielength = new JTextField(30);
        JTextField addmoviecountry = new JTextField(30);
        JTextField addmovieagerestrict = new JTextField(30);
        JTextField addmoviedateadded = new JTextField(30);
        movies.add(butmovieadd);
        movies.add(addmovieid);
        movies.add(addmoviename);
        movies.add(addmovieyear);
        movies.add(addmovierating);
        movies.add(addmovielength);
        movies.add(addmoviecountry);
        movies.add(addmovieagerestrict);
        movies.add(addmoviedateadded);

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





            butprofileremove.addActionListener(new ActionListener() {




                @Override
                public void actionPerformed(ActionEvent e) {



                    if (infoHolder.getUserProfiles().size() != 0) {
                        String remname = removefield.getText();
                        try {
                            PreparedStatement lt1 = connection.prepareStatement("DELETE FROM customerprofiles WHERE customerid = ? AND userprofile = ?");
                            lt1.setInt(1, infoHolder.getId());
                            lt1.setString(2, remname);
                            lt1.executeQuery();
                            repaint();
                            revalidate();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }


                    }
                }
            });

            butprofileadd.addActionListener(new ActionListener() {




                @Override
                public void actionPerformed(ActionEvent e) {


                    if (infoHolder.getUserProfiles().size()<3) {
                    String addlet = addfieldlet.getText();
                    String addname = addfieldname.getText();
                    Date adddate = Date.valueOf(addfieldbirth.getText());


                    try {


                        PreparedStatement lt1 = connection.prepareStatement("INSERT INTO customerprofiles (customerid, userprofile, name, birthdate) VALUES (?,?,?,?)");
                        lt1.setInt(1,infoHolder.getId());
                        lt1.setString(2,addlet);
                        lt1.setString(3,addname);
                        lt1.setDate(4,adddate);
                        lt1.executeQuery();




                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }
                }
            });
            butprofileupdate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    profileshown.removeAll();

                    PreparedStatement pst = null;
                    try {
                        pst = connection.prepareStatement("SELECT * FROM customerprofiles");

                    ResultSet ra = pst.executeQuery();
                    ArrayList profile_names = new ArrayList<String>();
                    ArrayList userProfiles = new ArrayList<Character>();

                    while (ra.next()) {
                        if(ra.getInt(1) == infoHolder.getId()) {

                            profile_names.add(ra.getString(3));
                            userProfiles.add(ra.getString(2).charAt(0));
                        }
                    }

                    infoHolder.setProfile_names(profile_names);
                    infoHolder.setUserProfiles(userProfiles);
                        for (int i= 0;i<infoHolder.getUserProfiles().size();i++) {

                            lblprof[i] =  new JLabel(String.valueOf(infoHolder.getUserProfiles().get(i))+"."+infoHolder.getProfile_names().get(i));
                            profileshown.add(lblprof[i]);
                            profileshown.revalidate();
                            profileshown.repaint();



                        }





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

            /* butmovieadd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Integer movieid = Integer.parseInt(addmovieid.getText().toString());
                    String name = addmoviename.getText();
                    Integer year = Integer.parseInt(addmovieyear.getText().toString());
                    Integer rating = Integer.parseInt(addmovierating.getText().toString());
                    Integer length = Integer.parseInt(addmovielength.getText().toString());
                    String country = addmoviecountry.getText();
                    String agerestrict = addmovieagerestrict.getText();
                    String date_added = addmoviedateadded.getText();

                    try {
                        PreparedStatement st1 = connection.prepareStatement("INSERT INTO movies (movieid, name, year, rating, length, country, agerestrict, date_added) VALUES (?,?,?,?,?,?,?)");
                        st1.setInt(1, movieid);
                        st1.setString(2, name);
                        st1.setInt(3, year);
                        st1.setInt(4, rating);
                        st1.setInt(5, length);
                        st1.setString(6, country);
                        st1.setString(7, agerestrict);
                        st1.setString(8, date_added);

                    } catch (SQLException m){

                    }
                }
            });
            */

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }









    }

}
