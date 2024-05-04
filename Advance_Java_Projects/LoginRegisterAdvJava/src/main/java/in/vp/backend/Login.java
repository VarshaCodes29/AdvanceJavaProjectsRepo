package in.vp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/LoginForm")
public class Login extends HttpServlet{
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		PrintWriter out = res.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con  = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_db","root","root");
			PreparedStatement ps = con.prepareStatement("select * from register where email=? and password=?");
			ps.setString(1,email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				res.setContentType("text/html");
				
				HttpSession session = req.getSession();
				session.setAttribute("key_name", rs.getString("name"));
				
				out.println("<h1 style='color:green'>success</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("/Profile.jsp");
				rd.include(req, res);
			}
			else
			{
				res.setContentType("text/html");
				out.println("<h1 style='color:red'>unsuccess</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("/Login.jsp");
				rd.include(req, res);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println(e.getMessage());
		}
		
		
	}

}
