import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteUrl")
public class DeleteServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	 {
		 PrintWriter pw=res.getWriter();
		 res.setContentType("text/html"); 
		  String query="delete from bookdata where bookname=? ";
		  String bn=req.getParameter("id");
		 
		  
		 try
		 {
			 Class.forName("com.mysql.cj.jdbc.Driver");
			 //pw.println("Driver loaded");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookinfo","root","harshi123");
			 //pw.print("sql connection set");
			   PreparedStatement ps=con.prepareStatement(query);
			   ps.setString(1, bn);
			 
			   int count=ps.executeUpdate();
			   if(count==1)
			   {
				   pw.println("<h2> Record is Deleted Successfully </h2>");
			   }
			   else
			   {
				   pw.println("<h2> Record is Not Deleted Successfully </h2>");
				      
			   }
			   
			   
			   
		 }catch(Exception e) {
			 pw.println("<h1>"+e.getMessage()+"</h1>");
		 }
		 
		 pw.println("<a href='Home.html'> Home </a>");
		 pw.println("<br>");		 
		 pw.println("<a href='booklist'> BookList </a>"); 
	 }
}

