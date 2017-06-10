import com.mysql.jdbc.Driver;

import javax.validation.constraints.Min;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by caoyuncong on
 * 2017/6/10 9:35
 * ip.
 */
public class DumpIPData {
    private static PreparedStatement preparedStatement;
    private static int counter;
    public static void main(String[] args) {
        try {
            new Driver();
            Connection connection = DriverManager.getConnection("jdbc:mysql:///?user=root&password=system");
            connection.setAutoCommit(false);
            String sql = "INSERT INTO db_ip.ip VALUE (NULL ,?,?,?)";
             preparedStatement = connection.prepareStatement(sql);
            try (
                    BufferedReader reader = new BufferedReader(new FileReader("e:/ip.txt"))
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String min=line.split("\\s+")[0];
                    String max = line.split("\\s+")[1];
                    String geo = line.replace(min, "").replace(max, "").trim();
                    dump(min, max, geo);
                    if (++counter % 10000 == 0) {
                        System.out.println(counter);
                        preparedStatement.executeBatch();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            preparedStatement.executeBatch();
            connection.commit();
            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dump(String min, String max, String geo) throws SQLException {
        preparedStatement.setString(1,min);
        preparedStatement.setString(2,max);
        preparedStatement.setString(3,geo);

        preparedStatement.addBatch();
    }
}
