package in.vp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/regForm")
public class Register extends HttpServlet{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out = res.getWriter();
		
		String name = req.getParameter("name1");
		String email = req.getParameter("email1");
		String password = req.getParameter("password1");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db","root","root");
			
			PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?)");
			ps.setString(1, name);
			ps.setString(2,email);
			ps.setString(3, password);
			
			int count = ps.executeUpdate();
			if(count > 0)
			{	
				res.setContentType("text/html");
				out.println("<h3 style='color:green'>success</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
				rd.include(req, res);
				
			}
			else
			{
				res.setContentType("text/html");
				out.println("<h3 style='color:red'>unsuccess</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
				rd.include(req, res);
			}
;			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}

}
