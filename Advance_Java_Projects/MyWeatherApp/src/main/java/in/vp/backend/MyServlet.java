package in.vp.backend;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Myservlet")
public class MyServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest req,HttpServletResponse res)
	{
		String inputData = req.getParameter("userInput");
		System.out.println(inputData);
	}

}
