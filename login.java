import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class login extends HttpServlet {

static final String DB_URL = "jdbc:mysql://localhost:3306/login";
static final String USER = "root";
static final String PASS = "";
static final String QUERY = "SELECT Username,Password FROM login";
public static String username;
public static String password;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ClassNotFoundException {

    response.setContentType("text/html;charset=UTF-8");
    Class.forName("com.mysql.cj.jdbc.Driver");

    try (PrintWriter out = response.getWriter();
         Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(QUERY)) {

        username = request.getParameter("username");
        password = request.getParameter("password");
        boolean loginSuccessful = false;

        while (rs.next()) {
            String dbUsername = rs.getString("Username");
            String dbPassword = rs.getString("Password");


            if (username.equals(dbUsername) && password.equals(dbPassword)) {
//                out.println("<p>Login Successful</p>");
//                out.println("" + rs.getString("username"));
//                out.println("" + rs.getString("password"));
                loginSuccessful = true;
                break;
            }
        }

        if (loginSuccessful) {
            out.println("<script>window.location = 'index.html';</script>");
        } else {
            out.println("<p>Invalid credentials. Please try again.</p>");
        }
        
        

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Login Servlet";
    }
}