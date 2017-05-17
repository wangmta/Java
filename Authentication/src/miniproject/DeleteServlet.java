package miniproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet(description = "delete data selected", urlPatterns = { "/DeleteServlet" })
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	Connection con;
	PreparedStatement ps;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	 
	public boolean validation(String id) {
		boolean status = false;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "admin");

			ps = con.prepareStatement("select * from registerData where empID=?");
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();
			status = rs.next();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		out = response.getWriter();
		String id = request.getParameter("selectID");

		if (validation(id)) {
			try {
				ps = con.prepareStatement("delete from registerData where empID=?");
				ps.setString(1, id);
				int i = ps.executeUpdate();
				if (i != 0) {
				out.println("<h3>" + id + " had been deleted</h3>");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			out.println("Sorry, no record asociates with this id");
			RequestDispatcher rd = request.getRequestDispatcher("delete.jsp");
			rd.include(request, response);
		}

		out.close();
	}

}
