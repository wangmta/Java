package miniproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(description = "register user account", urlPatterns = { "/RegisterServlet" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con = null;
    PreparedStatement ps = null;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
	
		String id = request.getParameter("empID");
		String name = request.getParameter("empName");
		String sa = request.getParameter("salary");
		int salary = Integer.parseInt(sa);
		String cno = request.getParameter("CNO");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "admin");
			ps = con.prepareStatement("insert into registerData values(?,?,?,?)");
			
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setInt(3, salary);
			ps.setString(4, cno);
			
			int i = ps.executeUpdate();
			if (i>0){
				out.println("<h3>Welcome: " + name + "</h3>");
				out.println("<h3>You have successfully registered...</h3>");
				out.println("<a href='login.jsp'>Login</a>");
			}
			
			con.close();
			
			RequestDispatcher rd=request.getRequestDispatcher("ShowRequestHeaders");
			rd.include(request, response); 
		}
		catch(Exception exc){
			System.out.println(exc);
		}
		
		out.close();
	}

}
