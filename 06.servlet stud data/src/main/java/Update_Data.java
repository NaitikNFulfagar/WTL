import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Update_Data extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<form action='Update_Data' method='post'>");
        out.println("<strong>Enter Roll Number for Updation</strong><br>");
        out.println("<input type='text' name='sroll'>");
        out.println("<br>");
        out.println("<strong>Enter New phone</strong><br>");
        out.println("<input type='text' name='newphone'>");
        out.println("<br><br>");
        out.println("<input type='submit' value='submit'>");
        out.println("</form></body></html>");

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String studRoll = req.getParameter("sroll");
            String newPhone = req.getParameter("newphone");

            if (studRoll != null && newPhone != null && !studRoll.isEmpty() && !newPhone.isEmpty()) {
                int rollNumber = Integer.parseInt(studRoll);

                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/students";
                String username = "root";
                String password = "";

                con = DriverManager.getConnection(url, username, password);

                // Use prepared statement to avoid SQL injection
                String query = "UPDATE student_tab SET sphone=? WHERE sroll=?";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, newPhone);
                pstmt.setInt(2, rollNumber);
                int rowsUpdated = pstmt.executeUpdate();

                if (rowsUpdated > 0) {
                    out.println("<b>Record updated successfully</b>");
                } else {
                    out.println("<b>No records found for the provided roll number</b>");
                }
            } else {
                out.println("<b>Roll number and new phone are required fields</b>");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error in database operation", e);
        } catch (NumberFormatException e) {
            out.println("<b>Invalid roll number format</b>");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                throw new ServletException("Error closing resources", e);
            }
            out.close();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
