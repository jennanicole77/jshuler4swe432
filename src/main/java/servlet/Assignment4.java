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

// CONSTRUCTOR: no constructor specified (default)
//
// ***************  PUBLIC OPERATIONS  **********************************
// public void doPost ()  --> prints a blank HTML page
// public void doGet ()  --> prints a blank HTML page
//***********************************************************************

@WebServlet(name = "assignment4", urlPatterns= {"/assignment4"})
public class Assignment4 extends HttpServlet
{

// Location of servlet.
static String Servlet = "assignmetn4";

/** *****************************************************
 *  Overrides HttpServlet's doPost().
********************************************************* */
public void doPost (HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
{
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   out.println("POST");
}  // End doPost

/** *****************************************************
 *  Overrides HttpServlet's doGet().
 *  Prints an HTML page with a blank form.
********************************************************* */
public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
{
   response.setContentType("text/html");
   PrintWriter out = response.getWriter();
   Print(out);
} // End doGet

//Print HTML page
private void Print(PrintWriter out) {
    out.println("<html>");
    out.println("<body onload=\"setFocus()\">");
    out.println("<h1 style=\"text-align:center; color:black;\">SWE 432 Assignment 4</h1>");
    out.println("<h2 style=\"text-align:center; color:black;\">Creators: Jenna Shuler and Frank Costantino</h2>");
    out.println("<div style = \"text-align:center\">");
    out.println("<h3 style=\"text-align:center; color:black;\">Use the slider below to change the font size of the information below!</h3>");
    out.println("<input type=\"range\" min=\"15\" max=\"40\" id=\"slider\" onchange=\"changeFontSizeSlider()\" value=\"20\"/>");
    out.println("</div>");
    out.println("<form method=\"post\" action=\"https://cs.gmu.edu:8443/offutt/servlet/formHandler\" name=\"form\" id=\"formId\">");
    out.println("<div style=\"font-size:20px;\" id=\"conatiner\">");
    out.println("<label for=\"characteristics\">Enter a number of block characteristics to be created below (Please enter a valid number above 0):</label>");
    out.println("<input type=\"number\" id=\"characteristics\" name=\"characteristics\" min=1 required onInput=\"enterNumbers()\">");
    out.println("<br><br>");
    out.println("<div id=\"amountOfInputs\"></div>");
    out.println("<center>");
    out.println("<input type=\"submit\"  style=\"color:#37AEE2; font-size: 16px; padding: 1rem 1.75rem; justify-content: center;\">");
    out.println("</center>");
    out.println("</form>");
    out.println("</div>");
    out.println("<script>");
    out.println("let numOfChar = 0;");
    out.println("function setFocus()");
    out.println("{");
    out.println("document.form.characteristics.focus();");
    out.println("}");
    out.println("function enterNumbers(arguements) {");
    out.println("numOfChar = document.getElementById(\"characteristics\").value;");
    out.println("var html = \"\";");
    out.println("for(var i = 1; i<=numOfChar; i++) {");
    out.println("html +=");
    out.println("'<label for=\'characteristics' + i + '\'> Block #' + i +' (Please enter a valid number above 0):</label> <input type=\'number\' id=\'characteristics' + i + '\' name=\'characteristics' + i + '\' min=01 required><br><br>';");
//    out.println("\"<label for=\\'characteristics\' + i + \'\\'> Block #\' + i +\' (Please enter a valid number above 0):</label>\" +");
 //   out.println("\"<input type=\\'number\\' id=\\'characteristics\' + i + \'\\' name=\\'characteristics\' + i + \'\\' min=01 required><br><br>\";");
    out.println("}");
    out.println("document.getElementById(\"amountOfInputs\").innerHTML = html;");
    out.println("}");
    out.println("</script>");
    out.println("<script>");
    out.println("var container = document.getElementById(\"container\");");
    out.println("function changeFontSizeSlider() {");
    out.println("var slider = document.getElementById(\"slider\");");
    out.println("container.style.fontSize = slider.value;");
    out.println("}");
    out.println("</script>");
    out.println("</body>");
    out.println("</html>");
}
}  // End assignment4

