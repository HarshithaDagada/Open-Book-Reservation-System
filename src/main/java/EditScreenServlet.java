import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	 {
		 PrintWriter pw=res.getWriter();
		 res.setContentType("text/html"); 
		  String query="select * from bookdata where bookname=? ";
		  String id=req.getParameter("id");
		 try
		 {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 //pw.println("Driver loaded");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookinfo","root","harshi123");
			 //pw.print("sql connection set");
			   PreparedStatement ps=con.prepareStatement(query);
			   ps.setString(1, id);
			   
			   ResultSet rs=ps.executeQuery();
			   rs.next();
			   pw.println("<form action='editurl?id=" +id+" ' method='post'>");
			   pw.println("<table border='1' align=center>");
			   pw.println("<tr>");
			   pw.println("<td> Book Name </td>");
			   pw.println("<td><input type='text' name='bookname' value='"+rs.getString(1)+"'></td>");
			   pw.println("</tr>");
			   pw.println("<tr>");
			   pw.println("<td> Book Edition </td>");
			   pw.println("<td><input type='text' name='bookedition' value='"+rs.getString(2)+"'></td>");
			   pw.println("</tr>");
			   pw.println("<tr>");
			   pw.println("<td> Book Price </td>");
			   pw.println("<td><input type='text' name='bookprice' value='"+rs.getFloat(3)+"'></td>");
			   pw.println("</tr>");
			   pw.println("<tr>");
			   pw.println("<td><input type='submit' value='Edit'></td>");
			   pw.println("<td><input type='reset' value='Cancel'></td>");
			   
			   pw.println("</tr>");
			   
			   pw.print("</table>");
		       pw.println("</form>");
		      
		 }catch(Exception e) {
			 pw.println("<h1>"+e.getMessage()+"</h1>");
		 }
		 
		 pw.println("<a href='Home.html'> Home </a>");
		 
	 }


}
