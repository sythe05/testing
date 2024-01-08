import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class signup extends HttpServlet {

    static final String DB_URL = "jdbc:mysql://localhost:3306/login";
    static final String USER = "root";
    static final String PASS = "";
    static final String QUERY = "SELECT * FROM login";
    static final String INSERT_QUERY = "insert into login(Username,Email,Password)values(?,?,?)";
    public static String username, email, password;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        response.setContentType("text/html;charset=UTF-8");
        Class.forName("com.mysql.cj.jdbc.Driver");

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PrintWriter out = response.getWriter();
            username = request.getParameter("username");
            email = request.getParameter("email");
            password = request.getParameter("password");
            try (PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, password);
                out.println("<script>window.location = 'login.html';</script>");
                int i = stmt.executeUpdate();
                if (i >= 0) {
                    out.println("<script>window.location = 'login.html';</script>");
                } else {
                    out.println("<script>window.location = 'login.html';</script>");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                processRequest(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet";
    }
}