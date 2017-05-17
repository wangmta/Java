package miniproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowRequestHeaders
 */
@WebServlet("/ShowRequestHeaders")
public class ShowRequestHeaders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowRequestHeaders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           response.setContentType("text.html");
           PrintWriter out = response.getWriter();
           
           out.println("<!Doctype HTML>");
           out.println("<html>");
           out.println("<head>");
           out.println("<title>Show all request headers</title>");
           out.println("<body>");
           out.println("<h1>All Request Headers</h1>");
           
           out.println("<p>Request Method: " + request.getMethod() + "</p>");
           out.println("<p>Request URI: " + request.getRequestURI() + "</p>");
           out.println("<p>Request URI: " + request.getRequestURL() + "</p>");
           out.println("<p>Request URI: " + request.getProtocol() + "</p>");	
           
           Enumeration<String> headerNames = request.getHeaderNames(); 
        	
           while(headerNames.hasMoreElements()){
        	 String headerName = headerNames.nextElement();
        	 out.println("<p>" + headerName + ": " + request.getHeader(headerName) + "</p>");
           }
           
           out.println("</body>");
           out.println("</head>");
           out.println("</html>");
           
           out.close();
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
