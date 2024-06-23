import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/booklist")
public class booklistservlet extends HttpServlet
{
	 protected void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException
	 {
		 PrintWriter pw=res.getWriter();
		 res.setContentType("text/html"); 
		  String query="select * from bookdata";
		 try
		 {
			 //
			 Class.forName("com.mysql.jdbc.Driver");
			 //pw.println("Driver loaded");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bookinfo","root","harshi123");
			 //pw.print("sql connection set");
			   PreparedStatement ps=con.prepareStatement(query);
		       ResultSet rs=ps.executeQuery();
		       pw.println("<table border='1' align='center'>");
		       pw.println("<tr>");
		       pw.println("<th> Book Name </th>");
		       pw.println("<th> Book Edition </th>");
		       pw.println("<th> Book Price </th>");		 
		       pw.println("<th> Edit </th>");		 
		       pw.println("<th> Delete </th>");		 
		       
		       pw.println("</tr>");
		       while(rs.next())
		       {
		    	   pw.println("<tr>");
		    	   pw.println("<td>"+rs.getString(1)+"</td>");
		    	   pw.println("<td>"+rs.getString(2)+"</td>");
		    	   pw.println("<td>"+rs.getFloat(3)+"</td>");
		    	   pw.println("<td><a href='editScreen?id="+rs.getString(1)+"'>Edit</a></td>");
		    	   pw.println("<td><a href='deleteUrl?id="+rs.getString(1)+"'>Delete</a></td>");
		    	   
		    	   pw.println("</tr>");
		       }
		       pw.println("</table>");
		 }catch(SQLException e) {
			 pw.println("<h1>"+e.getMessage()+"</h1>");
		 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 pw.println("<a href='Home.html'> Home </a>");
		 
	 }
}
