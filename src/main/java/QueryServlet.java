import com.mysql.jdbc.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by caoyuncong on
 * 2017/6/10 9:03
 * ip.
 */
@WebServlet(urlPatterns = "/index")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ip = req.getParameter("ip");

        try {
            new Driver();
            Connection connection = DriverManager.getConnection("jdbc:mysql:///?user=root&password=system");
            String sql = "SELECT * FROM db_ip.ip WHERE inet_aton(?) BETWEEN inet_aton(min) AND inet_aton(max)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, ip);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                req.setAttribute("message", resultSet.getString("geo"));
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
