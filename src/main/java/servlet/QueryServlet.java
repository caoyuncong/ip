package servlet;

import com.mysql.jdbc.Driver;
import util.Db;

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
@WebServlet(urlPatterns = "/ip")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ip = req.getParameter("ip").trim();
        if (ip.length() == 0) {
            ip = req.getRemoteAddr();
        }
        req.getSession().setAttribute("geo", getGeo(ip));
        req.getRequestDispatcher("index.jsp").forward(req, resp);

    }


    public static String getGeo(String ip) {
        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT geo FROM db_ip.ip WHERE inet_aton(?) BETWEEN inet_aton(min) AND  inet_aton(max)";
        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            } else {
                return null;
            }
            preparedStatement.setString(1, ip);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getString("geo");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, preparedStatement, connection);
        }
        return null;
    }
}
