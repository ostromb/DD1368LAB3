import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Settings extends JPanel{
    private Connection connection;
    private InfoHolder infoHolder;
    private DataEntry dataEntry;
    private JLabel[] lblprof = new JLabel[3];
    private JLabel lblexp = new JLabel("Hello");
    private JLabel lblstart = new JLabel("Hello");
    private JLabel lblcustomerid = new JLabel("");

    private int months = 0;
    private JTextField cardnr = new JTextField("");
    private JTextField cardissue = new JTextField("");
    private JTextField carddate = new JTextField("");
    private JTextField cardplan = new JTextField("");
    private JTextField cardamount = new JTextField("");
    private JTextField carddisc = new JTextField("");
    public Settings(InfoHolder infoHolder, Connection connection, DataEntry dataEntry)  {
        setSize(500,500);
        setVisible(true);
        JPanel subpanel = new JPanel();
        JPanel subs = new JPanel();
        JPanel subsf = new JPanel();
        JPanel subsdur = new JPanel();
        JPanel customerpanel = new JPanel();


        JPanel centerscreen = new JPanel();

        JButton butupdatesub = new JButton("Update Subscription");
        JLabel lblsubs = new JLabel("Manage Subscription");
        subsf.add(lblsubs);

        setLayout(new BorderLayout());
        add(subpanel,BorderLayout.NORTH);
        subpanel.add(subsdur,BorderLayout.SOUTH);
        subpanel.add(subsf,BorderLayout.WEST);
        subpanel.add(subs,BorderLayout.EAST);

        JPanel profiles = new JPanel();
        JPanel profileshown = new JPanel();
        JButton butprofileadd = new JButton("Add Profile");
        JButton butprofileremove = new JButton("Remove Profile");
        JButton butprofileupdate = new JButton("Update");
        JTextField addfieldlet = new JTextField(1);
        JTextField addfieldname = new JTextField(15);
        JTextField addfieldbirth = new JTextField(10);
        JTextField removefield = new JTextField(1);
        add(centerscreen, BorderLayout.CENTER);
        centerscreen.add(profiles, BorderLayout.WEST);
        centerscreen.add(profileshown, BorderLayout.EAST);
        centerscreen.add(customerpanel, BorderLayout.NORTH);
        JButton butchangeid = new JButton("Change Customer ID");
        JTextField changefield  = new JTextField(4);
        lblcustomerid  = new JLabel(String.valueOf(infoHolder.getId()));

        customerpanel.add(butchangeid);
        customerpanel.add(changefield);
        customerpanel.revalidate();
        customerpanel.repaint();


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

        JPanel bottomscreen = new JPanel();
        JPanel buttonpanel = new JPanel();
        JPanel fieldpanel1 = new JPanel();
        JPanel fieldpanel2 = new JPanel();
        JPanel fieldpanel3 = new JPanel();

        JButton butmovieadd = new JButton("Add movie");
        JButton butmovieremove = new JButton("Remove movie");
        JButton butactoradd = new JButton("Add actor");
        JButton butdirectoradd = new JButton("Add director");
        JButton butgenreadd = new JButton("Add genre");
        JButton butremakeadd = new JButton("Add remake");
        JButton butcustomeradd = new JButton("Add customer");
        JButton butcustomerremove = new JButton("Remove customer");
        JButton butupdaterating = new JButton("Update rate");

        JTextField addmovieid = new JTextField(15);
        JTextField removemovie = new JTextField(15);
        JTextField addmoviename = new JTextField(15);
        JTextField addmovieyear = new JTextField(4);
        JTextField addmovierating = new JTextField(2);
        JTextField addmovielength = new JTextField(4);
        JTextField addmoviecountry = new JTextField(20);
        JTextField addmovieagrestrict = new JTextField(1);
        JTextField addmoviedateadded = new JTextField(10);
        addmoviedateadded.setText("");
        JTextField addactormovie = new JTextField(4);
        JTextField addactors = new JTextField(20);
        JTextField adddirectormovie = new JTextField(4);
        JTextField adddirectors = new JTextField(20);
        JTextField addgenremovie = new JTextField(4);
        JTextField addgenres = new JTextField(20);
        JTextField addremakemovie = new JTextField(4);
        JTextField addremakes = new JTextField(4);
        JTextField addcustomerid = new JTextField(3);
        JTextField removecustomerid = new JTextField(3);
        JTextField addcustomeremail = new JTextField(25);
        JTextField addcustomerpw = new JTextField(15);
        JTextField addcustomerphone = new JTextField(12);
        JTextField addcustomeraddress = new JTextField(25);
        JTextField addcustomerdiscount = new JTextField(3);
        JTextField addratemovie = new JTextField(4);
        JTextField addnewrating = new JTextField(2);

        bottomscreen.setPreferredSize(new Dimension((int)getToolkit().getScreenSize().getWidth(), 500));
        add(bottomscreen, BorderLayout.SOUTH);
        bottomscreen.add(buttonpanel, BorderLayout.EAST);
        bottomscreen.add(fieldpanel1, BorderLayout.SOUTH);
        bottomscreen.add(fieldpanel2, BorderLayout.NORTH);
        bottomscreen.add(fieldpanel3, BorderLayout.CENTER);
        buttonpanel.add(butmovieadd);
        fieldpanel1.add(addmovieid);
        fieldpanel1.add(addmoviename);
        fieldpanel1.add(addmovieyear);
        fieldpanel1.add(addmovierating);
        fieldpanel1.add(addmovielength);
        fieldpanel1.add(addmoviecountry);
        fieldpanel1.add(addmovieagrestrict);
        fieldpanel1.add(addmoviedateadded);
        fieldpanel1.add(butmovieremove);
        fieldpanel1.add(removemovie);
        fieldpanel2.add(butactoradd);
        fieldpanel2.add(addactormovie);
        fieldpanel2.add(addactors);
        fieldpanel2.add(butdirectoradd);
        fieldpanel2.add(adddirectormovie);
        fieldpanel2.add(adddirectors);
        fieldpanel2.add(butgenreadd);
        fieldpanel2.add(addgenremovie);
        fieldpanel2.add(addgenres);
        fieldpanel2.add(butremakeadd);
        fieldpanel2.add(addremakemovie);
        fieldpanel2.add(addremakes);
        fieldpanel3.add(butcustomeradd);
        fieldpanel3.add(addcustomerid);
        fieldpanel3.add(addcustomeremail);
        fieldpanel3.add(addcustomerpw);
        fieldpanel3.add(addcustomerphone);
        fieldpanel3.add(addcustomeraddress);
        fieldpanel3.add(addcustomerdiscount);
        fieldpanel3.add(butcustomerremove);
        fieldpanel3.add(removecustomerid);
        fieldpanel3.add(butupdaterating);
        fieldpanel3.add(addratemovie);
        fieldpanel3.add(addnewrating);


        try {
            PreparedStatement st = connection.prepareStatement("SELECT * FROM (SELECT * FROM (SELECT subscription.*, customerid FROM subscription INNER JOIN paymentinfo p on subscription.subscriptionid = p.subscriptionid) as k WHERE customerid = ?) as l INNER JOIN subscriptionduration ON l.subscriptionid = subscriptionduration.subscriptinid");
            st.setInt(1, infoHolder.getId());
            ResultSet rs = st.executeQuery();
            rs.next();
            final JTextField[] cardnr = {new JTextField(rs.getString(2))};
            final JTextField[] cardissue = {new JTextField(rs.getString(3))};
            String crd = Integer.toString((rs.getInt(4)));
            final JTextField[] carddate = {new JTextField(crd)};
            final JTextField[] cardplan = {new JTextField(rs.getString(5))};
            String cra = Integer.toString(rs.getInt(6));
            final JTextField[] cardamount = {new JTextField(cra)};
            lblstart = new JLabel(rs.getString(7));
            lblexp = new JLabel(rs.getString(10));
            PreparedStatement ts = connection.prepareStatement("SELECT disount FROM customer WHERE customerid =?");
            ts.setInt(1,infoHolder.getId());
            ResultSet rss = ts.executeQuery();
            rss.next();
            String disstr = Integer.toString(rss.getInt(1));
            final JTextField[] carddisc = {new JTextField(disstr)};

            subs.add(cardnr[0]);
            subs.add(cardissue[0]);
            subs.add(carddate[0]);
            subs.add(cardplan[0]);
            subs.add(cardamount[0]);
            subs.add(carddisc[0]);

            subsdur.add(lblstart);
            subsdur.add(lblexp);

            subsdur.revalidate();
            subsdur.repaint();


            subs.add(butupdatesub);

            subsf.add(butupdatesub);

            revalidate();

            /** SUB UPDATE */
            butupdatesub.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String c1 = cardnr[0].getText();
                    String c2 = cardissue[0].getText();
                    int c3 = Integer.parseInt(carddate[0].getText());
                    String c4 = cardplan[0].getText();
                    int c5 = Integer.parseInt(cardamount[0].getText());
                    int c6 = Integer.parseInt(carddisc[0].getText());
                    try {
                        Calendar startydate = Calendar.getInstance(); //This to obtain today's date in our Calendar var.

                        java.sql.Date srtsqldate = new Date (startydate.getTimeInMillis());
                        // Date srtsqldate = (Date) startydate.getTime();
                        String srtstrdate = String.valueOf(srtsqldate);
                        Calendar expydate = Calendar.getInstance();

                        PreparedStatement st1 = connection.prepareStatement("UPDATE subscription SET card_number = ?, card_issue = ?, card_date = ?, sub_type = ?,payment_amount = ?, sub_start = ? WHERE subscriptionid = ?" );
                        st1.setString(1,c1);
                        st1.setString(2,c2);
                        st1.setInt(3,c3);
                        st1.setString(4,c4);
                        st1.setInt(5,c5);
                        st1.setDate(6, srtsqldate);
                        st1.setInt(7, infoHolder.getId());
                        st1.executeUpdate();

                        PreparedStatement trs = connection.prepareStatement("UPDATE customer SET disount = ? WHERE customerid = ?");
                        trs.setInt(1,c6);
                        trs.setInt(2,infoHolder.getId());
                        trs.executeUpdate();



                        infoHolder.setMonthplancost(c4);
                        int months = c5/infoHolder.getMonthplancost();
                        expydate.add(Calendar.MONTH, months);
                        java.sql.Date expsqldate = new Date (expydate.getTimeInMillis());
                        String expstrdate = String.valueOf(expsqldate);
                        System.out.println(expstrdate);
                        PreparedStatement durp = connection.prepareStatement("UPDATE subscriptionduration SET sub_exp=? WHERE subscriptinid=?");
                        durp.setDate(1,expsqldate);
                        durp.setInt(2,infoHolder.getId());
                        durp.executeUpdate();
                        lblstart = new JLabel(srtstrdate);
                        lblexp = new JLabel(expstrdate);

                        subs.removeAll();
                        subs.add(cardnr[0]);
                        subs.add(cardissue[0]);
                        subs.add(carddate[0]);
                        subs.add(cardplan[0]);
                        subs.add(cardamount[0]);
                        subs.add(carddisc[0]);
                        subs.add(lblcustomerid);
                        subs.revalidate();
                        subs.repaint();





                        subsdur.removeAll();
                        subsdur.add(lblstart);
                        subsdur.add(lblexp);
                        subsdur.revalidate();
                        subsdur.repaint();

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            });
            subs.add(lblcustomerid);

            butchangeid.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int cidnew = Integer.parseInt(changefield.getText());


                        infoHolder.setId(cidnew);
                        JButton butchangeid = new JButton("Change Customer ID");
                        JTextField changefield = new JTextField(4);
                        lblcustomerid = new JLabel(String.valueOf(infoHolder.getId()));
                        PreparedStatement st = connection.prepareStatement("SELECT * FROM (SELECT * FROM (SELECT subscription.*, customerid FROM subscription INNER JOIN paymentinfo p on subscription.subscriptionid = p.subscriptionid) as k WHERE customerid = ?) as l INNER JOIN subscriptionduration ON l.subscriptionid = subscriptionduration.subscriptinid");
                        st.setInt(1, infoHolder.getId());
                        ResultSet rs = st.executeQuery();
                        rs.next();

                        final JTextField[] cardnr = {new JTextField(rs.getString(2))};
                        final JTextField[] cardissue = {new JTextField(rs.getString(3))};
                        String crd = Integer.toString((rs.getInt(4)));
                        final JTextField[] carddate = {new JTextField(crd)};
                        final JTextField[] cardplan = {new JTextField(rs.getString(5))};
                        String cra = Integer.toString(rs.getInt(6));
                        final JTextField[] cardamount = {new JTextField(cra)};
                        PreparedStatement ts = connection.prepareStatement("SELECT disount FROM customer WHERE customerid =?");
                        ts.setInt(1,infoHolder.getId());
                        ResultSet rss = ts.executeQuery();
                        rss.next();
                        String disstr = Integer.toString(rss.getInt(1));
                        final JTextField[] carddisc = {new JTextField(disstr)};


                        subs.removeAll();
                        subs.add(cardnr[0]);
                        subs.add(cardissue[0]);
                        subs.add(carddate[0]);
                        subs.add(cardplan[0]);
                        subs.add(cardamount[0]);
                        subs.add(carddisc[0]);
                        subs.add(lblcustomerid);
                        subs.revalidate();
                        subs.repaint();



                        customerpanel.revalidate();
                        customerpanel.repaint();

                    } catch (Exception a) {

                    }
                }
            });

            butprofileremove.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {


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
                        lt1.executeUpdate();

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



            butmovieadd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String movieid = addmovieid.getText();
                    String name = addmoviename.getText();
                    String year = addmovieyear.getText();
                    String rating = addmovierating.getText();
                    String length = addmovielength.getText();
                    String country = addmoviecountry.getText();
                    String agrestrict = addmovieagrestrict.getText();
                    Date date_added = Date.valueOf(addmoviedateadded.getText());

                    try {
                        PreparedStatement st1 = connection.prepareStatement("INSERT INTO movies (movieid, name, year, rating, length, country, agrestrict, date_added) VALUES (?,?,?,?,?,?,?,?)");
                        st1.setInt(1, Integer.parseInt(movieid));
                        st1.setString(2, name);
                        st1.setInt(3, Integer.parseInt(year));
                        st1.setInt(4, Integer.parseInt(rating));
                        st1.setInt(5, Integer.parseInt(length));
                        st1.setString(6, country);
                        st1.setString(7, agrestrict);
                        st1.setDate(8, date_added);
                        st1.executeQuery();

                    } catch (SQLException m){
                    }
                }
            });

            butactoradd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String actormovie = addactormovie.getText();
                    String type = "M";
                    String actor = addactors.getText();

                    try {
                        PreparedStatement st2 = connection.prepareStatement("INSERT INTO actors (mediaid, type, actor) VALUES (?,?,?)");
                        st2.setInt(1, Integer.parseInt(actormovie));
                        st2.setString(2, type);
                        st2.setString(3, actor);
                        st2.executeQuery();
                    } catch (SQLException a){
                    }

                }
            });

            butdirectoradd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String directormovie = adddirectormovie.getText();
                    String type = "M";
                    String director = adddirectors.getText();

                    try {
                        PreparedStatement st3 = connection.prepareStatement("INSERT INTO directors (mediaid, type, director) VALUES (?,?,?)");
                        st3.setInt(1, Integer.parseInt(directormovie));
                        st3.setString(2, type);
                        st3.setString(3, director);
                        st3.executeQuery();
                    } catch (SQLException d){
                    }
                }
            });

            butgenreadd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String genremovie = addgenremovie.getText();
                    String type = "M";
                    String genre = addgenres.getText();

                    try {
                        PreparedStatement st4 = connection.prepareStatement("INSERT INTO genres (mediaid, type, genre) VALUES (?,?,?)");
                        st4.setInt(1, Integer.parseInt(genremovie));
                        st4.setString(2, type);
                        st4.setString(3, genre);
                        st4.executeQuery();
                    } catch (SQLException g){
                    }
                }
            });

            butremakeadd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String remakemovie = addremakemovie.getText();
                    String type = "M";
                    String remake = addremakes.getText();

                    try {
                        PreparedStatement st5 = connection.prepareStatement("INSERT INTO remakes (mediaid, media_type, original_year) VALUES (?,?,?)");
                        st5.setInt(1, Integer.parseInt(remakemovie));
                        st5.setString(2, type);
                        st5.setInt(3, Integer.parseInt(remake));
                        st5.executeQuery();
                    } catch (SQLException r){
                    }
                }
            });

            butmovieremove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String movieid = removemovie.getText();

                    try {
                        PreparedStatement st6 = connection.prepareStatement("DELETE FROM movies WHERE movieid=?");
                        st6.setInt(1, Integer.parseInt(movieid));
                        st6.executeQuery();
                    } catch (SQLException rm){

                    }
                }
            });

            butcustomeradd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String customerid = addcustomerid.getText();
                    String email = addcustomeremail.getText();
                    String pw = addcustomerpw.getText();
                    String phone = addcustomerphone.getText();
                    String address = addcustomeraddress.getText();
                    String discount = addcustomerdiscount.getText();

                    try {
                        PreparedStatement st7 = connection.prepareStatement("INSERT INTO customer (customerid, email, password, phonenumber, address, disount) VALUES (?,?,?,?,?,?)");
                        st7.setInt(1, Integer.parseInt(customerid));
                        st7.setString(2, email);
                        st7.setString(3, pw);
                        st7.setString(4, phone);
                        st7.setString(5, address);
                        st7.setInt(6, Integer.parseInt(discount));
                        st7.executeQuery();
                    } catch (SQLException ac){
                        ac.printStackTrace();
                    }
                }
            });

            butcustomerremove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String customerid = removecustomerid.getText();

                    try {
                        PreparedStatement st8 = connection.prepareStatement("DELETE FROM customer WHERE customerid=?");
                        st8.setInt(1, Integer.parseInt(customerid));
                        st8.executeQuery();
                    } catch (SQLException rc){
                    }
                }
            });

            butupdaterating.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    String rating = addnewrating.getText();
                    Integer customer = infoHolder.getId();
                    String profile = "" + infoHolder.getUserProfile();
                    String watchid = addratemovie.getText();

                    try {
                        PreparedStatement st9 = connection.prepareStatement("UPDATE watchlist SET rating=? WHERE customerid=? AND userprofile=? AND mediaid=?");
                        st9.setInt(1, Integer.parseInt(rating));
                        st9.setInt(2, customer);
                        st9.setString(3, profile);
                        st9.setInt(4, Integer.parseInt(watchid));
                        System.out.println(rating + customer + profile + watchid);
                        st9.executeUpdate();
                    }
                    catch(SQLException r){
                        r.printStackTrace();
                    }
                }
            });

        } catch (SQLException throwables) {
        }
    }
}