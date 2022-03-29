// From "Professional Java Server Programming", Patzer et al.,

// Import Java Libraries
import java.io.*;
import java.util.*;

//Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "attributeServlet", urlPatterns={"/attributeServlet"})
public class attributeServlet extends HttpServlet
{
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
   // Get session object
   HttpSession session = request.getSession();

   String name   = request.getParameter("attrib_name");
   String value  = request.getParameter("name_value");
   String age   = request.getParameter("attrib_age");
   String value2  = request.getParameter("age_value");
   String removeName = request.getParameter("attrib_remove_name");
   String removeAge = request.getParameter("attrib_remove_age");

   if (removeName != null && removeName.equals("on"))
   {
      session.removeAttribute(name);
   }
   if (removeAge != null && removeAge.equals("on"))
   {
      session.removeAttribute(age);
   }
   else
   {
      if ((name != null && name.length() > 0) && (value != null && value.length() > 0) 
        && (age != null && age.length() >0) && (value2 != null && value2.length() > 0))
      {
         session.setAttribute(name, value);
         session.setAttribute(age, value2);
      }

   }

   response.setContentType("text/html");
   PrintWriter out = response.getWriter();

   out.println("<html>");
   // no-cache lets the page reload by clicking on the reload link
   out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
   out.println("<head>");
   out.println(" <title>Session lifecycle</title>");
   out.println("</head>");
   out.println("");

   out.println("<body>");
   out.println("<h1><center>Session attributes</center></h1>");

   // String url = response.encodeURL ("offutt/servlet/attributeServlet");
   String url = response.encodeURL("attributeServlet");
   out.println("<form action=\"" + url + "\" method=\"GET\">");
   out.println("Enter name and value of an attribute");

   out.println(" Name: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_name\">");

   out.println(" Value: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"name_value\">");
   
   out.println("Enter age and value of an attribute");

   out.println(" Age: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"attrib_age\">");

   out.println(" Value: ");
   out.println(" <input type=\"text\" size=\"10\" name=\"age_value\">");
   
   out.println(" <br><input type=\"checkbox\" name=\"attrib_remove_name\">Remove a name");
   out.println(" <br><input type=\"checkbox\" name=\"attrib_remove_age\">Remove an age");
   out.println(" <input type=\"submit\" name=\"update\" value=\"Update\" age=\"update\" value2=\"Update\">");
   out.println("</form>");
   out.println("<hr>");

   out.println("Attributes in this session:");
   Enumeration e = session.getAttributeNames();
   while (e.hasMoreElements())
   {
      String att_name  = (String) e.nextElement();
      String att_value = (String) session.getAttribute(att_name);

      out.print  ("<br><b>Name:</b> ");
      out.println(att_name);
      out.print  ("<br><b>Value:</b> ");
      out.println(att_value);
      
      String att_age  = (String) e.nextElement();
      String att_value2 = (String) session.getAttribute(att_age);

      out.print  ("<br><b>Age:</b> ");
      out.println(att_age);
      out.print  ("<br><b>Value:</b> ");
      out.println(att_value2);
   } //end while

   out.println("</body>");
   out.println("</html>");
   out.close();
} // End doGet
} //End  SessionLifeCycle
