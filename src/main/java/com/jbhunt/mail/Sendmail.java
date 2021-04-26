package com.jbhunt.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;


import com.jbhunt.util.mail.EmailHandler;
import com.jbhunt.util.mail.EmailMessage;
import com.jbhunt.vo.Resultsetvo;


public class Sendmail {
	@Autowired
	private JavaMailSenderImpl mailProxy;
	  private static final Logger log = Logger.getLogger(Sendmail.class);
	public void sendFailureEmail()
	  {/*

	SimpleMailMessage mail = new SimpleMailMessage();
    mail.setFrom("DoNotReply@jbunt.com");
    mail.setTo("sudhakar.vilvarajan@jbunt.com,sudhakar.vilvarajan@cognizant.com");
    mail.setSentDate(new Date());
    
    StringBuilder body = new StringBuilder();
   
      mail.setSubject( "Tickets going to Breach SLA - Automation Testing - Error");
      body.append("exception occured");
    
   
    mail.setText(body.toString());
    try
    {
      this.mailProxy.send(mail);
    }
    catch (Exception e1)
    {
    	System.out.println("error 1" +e1);
    	e1.printStackTrace();
      log.error("Email Sending is failed");
     // log.error("Invoice procsing is failed. Problem is, Notification Email Program failed to send the invoice processing failue notice.");
    }
	
*/}
	
	 public void sendSuccessMail(Resultsetvo rvo)
	  {
		 log.info("Send Mail");
	    try
	    {
	      EmailMessage emailMessage = new EmailMessage();
	      emailMessage.setFrom("Cognizantautomation@jbhunt.com");
	      String to="";
	     /* String Filepath=System.getenv("Desktop");
	      File file =new File(Filepath+"//SlaBreachEmail//properties.txt");*/
	      File file =new File("properties.txt");
	      BufferedReader br = new BufferedReader(new FileReader(file)); 
	      String st ="";
	      System.out.println(st);	      
	      while ((st = br.readLine()) != null) 
	      {
	    	  to=to+st+","; 
	      }
	        
	     /* Scanner sc=new Scanner(file);
	      while (sc.hasNextLine()) 
	      {
	    	 to=to.append(sc.nextLine());
	    	 to=to.append(",");
	      }*/
	      if(to.isEmpty()) 
	      {
	    	  to="sudhakar.vilvarajan@jbhunt.com";
	    	log.warn("No mail id found");	  
	      }
	      log.info("to mail id" +to);
	      emailMessage.setTo(to.toString());
	      /*emailMessage.setTo("sudhakar.vilvarajan@jbhunt.com,sudhakar.vilvarajan@cognizant.com");
	      /*emailMessage.setTo("Sabarish.Sekhar@jbhunt.com,Arunselvajeromias.Arulappan@cognizant.com,MadanMohan.Challa@cognizant.com,"
	      		+"sudhakar.vilvarajan@jbhunt.com,sudhakar.vilvarajan@cognizant.com");*/
	      /*emailMessage.setTo("MadanMohan.Challa@cognizant.com,Arunselvajeromias.Arulappan@cognizant.com,"
	      		+ "sudhakar.vilvarajan@jbhunt.com,"
	      		+ "Cognizant.Backoffice.Support@jbhunt.com,PS_MKT_OPR_ICS@jbhunt.com");*/
	     
	      StringBuilder body = new StringBuilder();
	      emailMessage.setSubject("Tickets going to Breach SLA");
	      //emailMessage.setMessageText("Customer Invoice Audit: Not in WD List");
	      
	      body.append("<html><head> <style type='text/css'>table, th, td {\r\n" + 
	      		"    border: 1px solid black;\r\n" + 
	      		"}</style></head>"
                  + "<body onload=\"sortTable()\">"
	      		+"<H2>Tickets Going to breach in next 8 hours</H2><br>"
                  +"<h3>Order / Operation Tickets</h3> <br>"
                  + "<table id=\"myTable\" style='border:2px solid black,width:100%'><span style=border-color: black, border-width: 1px>");
	      
	      body.append("<tr>");
    	  body.append("<th width=\"10%\">");
    	  body.append("ID");
    	  body.append("</th>");
    	  body.append("<th width=\"25%\">");
    	  body.append("TITLE");
    	  body.append("</th>");
    	  body.append("<th width=\"10%\">");
    	  body.append("SUPPORT GROUP");
    	  body.append("</th>");
    	  body.append("<th width=\"10%\">");
    	  body.append("SEVERITY");
    	  body.append("</th>");
    	  body.append("<th width=\"15%\">");
    	  body.append("STATUS");
    	  body.append("</th>");
    	  body.append("<th width=\"15%\">");
    	  body.append("ASSIGNED USER");
    	  body.append("</th>");
    	  body.append("<th width=\"15%\">");
    	  body.append("REMAINING TIME (HH:MM)");
    	  body.append("</th>");
    	  body.append("</tr>");
	      
	      for (int j=0;j<rvo.getId().size();j++)
	      {
	    	  if(rvo.getMetflag().get(j).equalsIgnoreCase("NOT BREACHED") && 
	    			  (rvo.getWorkqueue().get(j).contains("Order") 
	    					  ||rvo.getWorkqueue().get(j).contains("Operations")
	    					  ||rvo.getWorkqueue().get(j).contains("360")
	    					  ||rvo.getWorkqueue().get(j).contains("Compliance")))
	    		  
	    	  {
	    		  System.out.println(rvo.getRemainingtime().get(j).split(":")[0]);
	    		  if(Double.parseDouble(rvo.getRemainingtime().get(j).split(":")[0]) <8)
	    		  {
	    	  body.append("<tr>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getId().get(j));
	    	  body.append("</center></td>");
	    	  body.append("<td>");
	    	  body.append(rvo.getTitle().get(j));
	    	  body.append("</td>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getWorkqueue().get(j));
	    	  body.append("</center></td>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getSeverity().get(j));
	    	  body.append("</center></td>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getStatus().get(j));
	    	  body.append("</center></td>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getDisplayname().get(j));
	    	  body.append("</center></td>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getRemainingtime().get(j));
	    	  body.append("</center></td>");
	    	  body.append("</tr>");
	      }
	      }
	      }

	      body.append("</table><br><br>"  
	    		  // Backoffice Table
	    		  +"<h3>Backoffice Tickets</h3> <br>"
+ "<table id=\"myTable\" style='border:2px solid black,width:100%'><span style=border-color: black, border-width: 1px>");
	      
	      body.append("<tr>");
    	  body.append("<th width=\"10%\">");
    	  body.append("ID");
    	  body.append("</th>");
    	  body.append("<th width=\"25%\">");
    	  body.append("TITLE");
    	  body.append("</th>");
    	  body.append("<th width=\"10%\">");
    	  body.append("SUPPORT GROUP");
    	  body.append("</th>");
    	  body.append("<th width=\"10%\">");
    	  body.append("SEVERITY");
    	  body.append("</th>");
    	  body.append("<th width=\"15%\">");
    	  body.append("STATUS");
    	  body.append("</th>");
    	  body.append("<th width=\"15%\">");
    	  body.append("ASSIGNED USER");
    	  body.append("</th>");
    	  body.append("<th width=\"15%\">");
    	  body.append("REMAINING TIME (HH:MM)");
    	  body.append("</th>");
    	  body.append("</tr>");
	      
	      for (int j=0;j<rvo.getId().size();j++)
	      {
	    	  if(rvo.getMetflag().get(j).equalsIgnoreCase("NOT BREACHED") && 
	    			 ( rvo.getWorkqueue().get(j).contains("FinancialServices") ||
	    			   rvo.getWorkqueue().get(j).contains("EmployeeDataServices") ||
	    			   rvo.getWorkqueue().get(j).contains("Backoffice") ||
	    			   rvo.getWorkqueue().get(j).contains("CustomerLegacy")
	    					 ) )
	    		  
	    	  {
	    		  System.out.println(rvo.getRemainingtime().get(j).split(":")[0]);
	    		  if(Double.parseDouble(rvo.getRemainingtime().get(j).split(":")[0]) <8)
	    		  {
	    	  body.append("<tr>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getId().get(j));
	    	  body.append("</center></td>");
	    	  body.append("<td>");
	    	  body.append(rvo.getTitle().get(j));
	    	  body.append("</td>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getWorkqueue().get(j));
	    	  body.append("</center></td>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getSeverity().get(j));
	    	  body.append("</center></td>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getStatus().get(j));
	    	  body.append("</center></td>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getDisplayname().get(j));
	    	  body.append("</center></td>");
	    	  body.append("<td><center>");
	    	  body.append(rvo.getRemainingtime().get(j));
	    	  body.append("</center></td>");
	    	  body.append("</tr>");
	      }
	      }
	      }

	      
	      
	      
	      body.append("</table></body>");
	     
	      
	      body.append("<script>"
	      +"function sortTable() {\r\n" + 
	      "	        var table, rows, switching, i, x, y, shouldSwitch;\r\n" + 
	      "	        table = document.getElementById(\"myTable\");\r\n" + 
	      "	        switching = true;\r\n" + 
	      "	        /*Make a loop that will continue until\r\n" + 
	      "	        no switching has been done:*/\r\n" + 
	      "	        while (switching) {\r\n" + 
	      "	          //start by saying: no switching is done:\r\n" + 
	      "	          switching = false;\r\n" + 
	      "	          rows = table.rows;\r\n" + 
	      "	          /*Loop through all table rows (except the\r\n" + 
	      "	          first, which contains table headers):*/\r\n" + 
	      "	          for (i = 1; i < (rows.length - 1); i++) {\r\n" + 
	      "	            //start by saying there should be no switching:\r\n" + 
	      "	            shouldSwitch = false;\r\n" + 
	      "	            /*Get the two elements you want to compare,\r\n" + 
	      "	            one from current row and one from the next:*/\r\n" + 
	      "	            x = rows[i].getElementsByTagName(\"TD\")[0];\r\n" + 
	      "	            y = rows[i + 1].getElementsByTagName(\"TD\")[0];\r\n" + 
	      "	            //check if the two rows should switch place:\r\n" + 
	      "	            if (Number(x.innerHTML) > Number(y.innerHTML)) {\r\n" + 
	      "	              //if so, mark as a switch and break the loop:\r\n" + 
	      "	              shouldSwitch = true;\r\n" + 
	      "	              break;\r\n" + 
	      "	            }\r\n" + 
	      "	          }\r\n" + 
	      "	          if (shouldSwitch) {\r\n" + 
	      "	            /*If a switch has been marked, make the switch\r\n" + 
	      "	            and mark that a switch has been done:*/\r\n" + 
	      "	            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);\r\n" + 
	      "	            switching = true;\r\n" + 
	      "	          }\r\n" + 
	      "	        }\r\n" + 
	      "	      }\r\n" + 
	      "	      </script>");
	      
	      
	      body.append("</html>");
	      emailMessage.setMessageText(body.toString());
	     emailMessage.setHtmlFormatEnabled(true);
	      
	      
	      EmailHandler handler = EmailHandler.getInstance();
	      handler.send(emailMessage);
	      log.info("email sent successfully");
	      log.info("Mail Sent");
	    }
	    catch (Exception e)
	    {
	      //log.error("Exception in GenerateEmail::sendEmail:", e);
	    	e.printStackTrace();
	    	log.error("error happened while mailingn: " +e.getMessage()+"\n" +e);
	    }
	  }
	
	
}
