import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class DataEntry {
    private LoginForm LF;
    private ChooseProfile CP;
    private Menu MF;
    private InfoHolder infoHolder;
    private Connection connection;

    public void LFtoCP(){
        CP = new ChooseProfile(infoHolder, connection, this);
        LF.setVisible(false);
        CP.setVisible(true);
        LF.dispose();
    }

    public void CPtoMF(){
        MF = new Menu(infoHolder, connection, this);
        CP.setVisible(false);
        MF.setVisible(true);
        MF.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        CP.dispose();
    }


    DataEntry() {
        connection = DataEntry.getConnection();
        infoHolder = new InfoHolder();
        LF = new LoginForm(infoHolder, connection, this);
    }

    public static void main (String[] args) {
        new DataEntry();
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String host="psql-dd1368-ht20.sys.kth.se";
            String port="5432";
            String db_name="mssostromb";
            String username="axu";
            String password="TTahULyD87Za";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"", ""+username+"", ""+password+"");

            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Failed");
            }

            return connection;
        }catch(Exception e) {
            e.printStackTrace();
            return connection;
        }
    }}