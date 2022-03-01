package loans;

// From "Professional Java Server Programming", Patzer et al.,

// Import Servlet Libraries
import javax.servlet.*;
import javax.servlet.http.*;

// Import Java Libraries
import java.io.*;

// Import html and loan helper classes
// import com.wrox.util.*;

public class loanCalculator extends HttpServlet
{
   public void doGet (HttpServletRequest request, HttpServletResponse response)
       throws ServletException, IOException
   {

      // retrieve parameter values as strings
      String principalAsString = request.getParameter ("principal");
      String interestAsString  = request.getParameter ("interest");
      String paymentAsString   = request.getParameter ("payment");

      //  initialize variables to hold floating point values
      float principal, interest, payment;

      //  initialize a variable to hold the loan repayment period
      int months;

      try
      {
         // use the loanTools class to obtain floating point values
         // stringToFloat throws a NumberFormatException, so we'll have
         // to catch it.
         principal = loanTools.stringToFloat(principalAsString);
         interest  = loanTools.stringToFloat(interestAsString);
         payment   = loanTools.stringToFloat(paymentAsString);

         //  use the loanTools class to calculate the loan period
         //  the method throws an IllegalArgumentException, so we'll have to catch it
         months = loanTools.calculateLoanPeriod(principal, interest, payment);
      }

      // If a NumberFormatException was thrown, we want to
      // replace the error message with something more helpful.
      catch (NumberFormatException e)
      {
         handleError(new NumberFormatException("Check that the values entered are numeric"), response);
         return;
      }

      // If any other kind of exception was thrown, we catch it here.
      catch (Exception e)
      {
         handleError(e, response);
         return;
      }

      // If no exceptions were thrown, the code continues here
      // so we can send an acknowledgment to the browser.

      response.setContentType("text/html");
      PrintWriter out = response.getWriter ();

      html h = new html("Loan Calculator: Results\n");
      h.add(html.HEADING, "Loan Calculator Results", false);
      h.add(html.LINE, "", false);
      h.add(html.NORMAL, "Principal Amount: $", false);
      h.add(html.NORMAL, Float.toString(principal), true);
      h.add(html.NORMAL, "Interest:  ", false);
      h.add(html.NORMAL, Float.toString(interest), true);
      h.add(html.NORMAL, "Payment: $", false);
      h.add(html.NORMAL, Float.toString(payment), true);
      h.add(html.NORMAL, "Months Until Payoff: ", false);
      h.add(html.NORMAL, Integer.toString(months), true);
      out.println(h.getPage());
      out.close();

      // The following lines demonstrate logging via
      // the ServletContext log() method

      // ServletContext sc = getServletContext ();
      // sc.log ("Loan period calculated: " + Integer.toString (months));

      // The following line demonstrates logging via
      // the GenericServlet log() method
      // log ("Loan period calculated: " + Integer.toString (months));

   }

   // This version of handleError() sends plain HTML to inform
   // the client of the error.
   private void handleError (Exception e, HttpServletResponse response)
   {
      // You can use response.setStatus () here to inform the client's browser
      // that this response represents an error, not a successful request
      // response.setStatus (400);
      response.setContentType ("text/html");
      try
      {
         PrintWriter out = response.getWriter ();

         html h = new html("Loan Calculator: Error");
         h.add(html.HEADING, "An error has occurred...", false);
         h.add(html.LINE, "", false);
         h.add(html.NORMAL, e.getMessage(), false);
         out.println(h.getPage());
         out.close();
      }
      catch (Exception e1)
      {  // had to add this catch as exception needs to be handled.
         e1.printStackTrace();
      }
   }
}
