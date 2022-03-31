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

@WebServlet(name = "assignment6", urlPatterns= {"/assignment6"})
public class Assignment6 extends HttpServlet
{

// Location of servlet.
static String Servlet = "assignment6";

/** *****************************************************
 *  Overrides HttpServlet's doPost().
********************************************************* */
public void doPost (HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException
{
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    int N = Integer.parseInt(request.getParameter("characteristics"));
    String html = "";
    out.println("<html>");
    out.println("<body>");
    out.println("<h1 style=\"text-align:center; color:black;\">SWE 432 Assignment 6</h1>");
    out.println("<h2 style=\"text-align:center; color:black;\">Creators: Jenna Shuler and Frank Costantino</h2>");
    out.println("<div style = \"text-align:center\">");
    out.println("<a href=\"https://github.com/jennanicole77/jshuler4swe432/blob/main/src/main/java/servlet/Assignment6.java\">Assignment 6 Github Link</a>");
    out.println("</div>");
    out.println("<label style=\"font-size:20px;\">Number of characteristics: " + N +".</label><br><br>");
    out.println("<div style=\"font-size:20px;\" id=\"conatiner\">");
    for(int i =1; i<=N; i++) {
        String name = request.getParameter("characteristicName" + i);
        int numBlocks = Integer.parseInt(request.getParameter("characteristics" + i)); 
        html += "<label> Characteristic " + name + ": ";
        for(int j =1; j<=numBlocks; j++) {
            html+= name + j;
            if(j<numBlocks)
                html+=", ";
        }
        html += ".</label><br></br>";
    }
    out.println(html);
    
    String option = request.getParameter("abstract");
    if(option.equals("EC")) {
        out.println("console.log(\"EC\")");
        int maxCharacteristic=0;
        for (int charNum=0; charNum<N; charNum++)
        {  // Find the maximum # blocks among the characteristics
            int numBlocks = Integer.parseInt(request.getParameter("characteristics" + charNum)); 
            if (numBlocks>maxCharacteristic)
                maxCharacteristic= numBlocks;
        }
        out.println("<label style=\"font-weight: bold\">"+ maxCharacteristic  +" each-choice abstract tests.</label>");
        for (int testNum=1; testNum<=maxCharacteristic; testNum++)
        {
            html = "";
            html += "<label>Abstract test " + testNum + ": [";
            for (int charNum=0; charNum<N; charNum++)
            {
                String name = request.getParameter("characteristicName" + charNum);
                int numBlocks = Integer.parseInt(request.getParameter("characteristics" + charNum)); 
                html += name;
                if (testNum<=numBlocks)
                    html += testNum;
                else // no more blocks, use wild card
                    html += "*";
                if (charNum<N-1)
                    html += ", ";
            }
            html += "]</label>";
            out.println(html);
        }
    }
    else if(option.equals("BC")) {
        out.println("console.log(\"BC\")");
    }

    out.println("</body></html>");
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
    out.println("<h1 style=\"text-align:center; color:black;\">SWE 432 Assignment 6</h1>");
    out.println("<h2 style=\"text-align:center; color:black;\">Creators: Jenna Shuler and Frank Costantino</h2>");
    out.println("<div style = \"text-align:center\">");
    out.println("<a href=\"https://github.com/jennanicole77/jshuler4swe432/blob/main/src/main/java/servlet/Assignment6.java\">Assignment 6 Github Link</a>");
    out.println("<h3 style=\"text-align:center; color:black;\">Use the slider below to change the font size of the information below.</h3>");
    out.println("<input type=\"range\" min=\"15\" max=\"40\" id=\"slider\" onchange=\"changeFontSizeSlider()\" value=\"20\"/>");
    out.println("</div>");
    out.println("<form method=\"post\" action=\"https://jshuler4swe432.herokuapp.com/assignment6\" name=\"form\" id=\"formId\">");
    out.println("<div style=\"font-size:20px;\" id=\"container\">");
    out.println("<label for=\"characteristics\">Enter a number of characteristics to be created below (Please enter a valid number above 0):</label>");
    out.println("<input type=\"number\" id=\"characteristics\" name=\"characteristics\" min=1 required onInput=\"enterNumbers()\">");
    out.println("<br><br>");
    out.println("<div id=\"amountOfInputs\"></div>");
    out.println("<center>");
    out.println("<label>Please choose which abstract test you would like to see printed.</label>");
    out.println("<label>Default is each-choice test</label><br>");
    out.println("<div>");
    out.println("<div>");
    out.println("<label for=\\\"EC\\\"> Each-Choice Testing Output</label>"); 
    out.println("<input type=\"radio\" id=\"EC\" name=\"abstract\" value=\"EC\" checked>");
    out.println("</div>");
    out.println("<div>");
    out.println("<label for=\\\"BC\\\"> Base-Choice Testing Output</label>"); 
    out.println("<input type=\"radio\" id=\"BC\" name=\"abstract\" value=\"BC\">");
    out.println("</div>");
    out.println("</div>");
    out.println("<br>");
    out.println("<input type=\"submit\"  style=\"color:#37AEE2; font-size: 16px; padding: 1rem 1.75rem; justify-content: center;\">");
    out.println("</div>");
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
    out.println("\"<label for=\\\"characteristicName\" + i + \"\\\"> Characteristic name: </label>\"+ "); 
    out.println("\"<input type=\\\"text\\\" pattern=\\\"[A-Za-z0-9]+\\\" id=\\\"characteristicName\" + i + \"\\\" name=\\\"characteristicName\" + i + \"\\\" min=01 required>\" + ");
    out.println("\"<label for=\\\"charactertistics\" + i + \"\\\"> number of blocks: </label>\" +");
    out.println("\"<input type=\\\"number\\\" id=\\\"characteristics\" + i + \"\\\" name=\\\"characteristics\" + i + \"\\\" min=01 required><br><br>\";");
    out.println("}");
    out.println("html+=\"<label style=\\\"font-size:18px\\\"> **Characteristic names should be letters or numbers only.**</label><br>\"+ "); 
    out.println("\"<label style=\\\"font-size:18px\\\"> **Number of blocks should be a valid number above 0.**</label>\"+ "); 
    out.println("\"<br><br>\";");
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
}  // End assignment6

