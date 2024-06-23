import javax.servlet.http.HttpServlet;

import java.io.IOException;
	import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
	@WebServlet("/register")
	public class RegisterServlet extends HttpServlet
	{
	 protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	 {
		 PrintWriter pw=res.getWriter();
		 res.setContentType("text/html"); 
		 String bookname=req.getParameter("bn");
		 String bookeditionn=req.getParameter("be");
		 float bookprice=Float.parseFloat(req.getParameter("bp"));
		
		 String bname=req.getParameter("bn");
		 String bedition=req.getParameter("be");
		 float bprice=Float.parseFloat(req.getParameter("bp"));
		 String query="insert into bookdata values(?,?,?)";
		 try
		 {
			 pw.println("hellonlkk");
			 Class.forName("com.mysql.jdbc.Driver");
			 //pw.println("Driver loaded");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookinfo","root","harshi123 ");
			 //pw.print("sql connection set");
			 PreparedStatement ps=con.prepareStatement(query);
			    ps.setString(1, bname);
		        ps.setString(2, bedition);
		        ps.setFloat(3, bprice);
		       int c=ps.executeUpdate();
		       if(c==1)
		       {
		    	   pw.print("<h1> Record is registered successfully</h1>");
		       }
		       else
		       {
		    	   pw.print("<h1> Record is not registered successfully</h1>");
			          
		       }
		 }catch(SQLException e) {
			 pw.println("<h1>"+e.getMessage()+"</h1>");
		 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 pw.println("<a href='Home.html'> Home </a>");
		 pw.println("<br>");		 
		 pw.println("<a href='booklist'> BookList </a>");
		 
	 }
	 
	}
