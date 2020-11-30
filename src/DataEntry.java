import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
public class DataEntry {
    public static Connection getConnection() {
        Connection connection = null;
        try {


            String host="psql-dd1368-ht20.sys.kth.se";
            String port="5432";
            String db_name="mssostromb";
            String username="ostromb";
            String password="DRlr3u2CBjgj";
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
    }

}
