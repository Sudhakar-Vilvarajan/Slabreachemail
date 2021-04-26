package com.jbhunt.Slabreachemail;


import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.jbhunt.mail.Sendmail;
import com.jbhunt.vo.Resultsetvo;

public class Calculate {

	final static Logger log= Logger.getLogger(Application.class);
	
	void calculatesla(Resultsetvo rvo)
	{
		log.info("Calculation started");
		Sendmail sm=new Sendmail();
		Calendar crecal = Calendar.getInstance();
		Calendar curcal = Calendar.getInstance();
		for (int i=0;i<rvo.createddate.size();i++)
		{
			Date cred=rvo.getCreateddate().get(i);
			crecal.setTimeInMillis(cred.getTime());
			crecal.add(Calendar.HOUR, -6);
			 System.out.println(rvo.getId().get(i) + "************"+cred);
			 
			 int numberOfDays = 0;
			 long numberOfSeconds = 0;		 
			//Get number of full days
			 //if(crecal.get(Calendar.MONTH)== curcal.get(Calendar.MONTH)){
			 System.out.println(curcal.get(Calendar.DATE)+ "Full days calculation "+crecal.get(Calendar.DATE));
			 while(crecal.get(Calendar.DATE) != curcal.get(Calendar.DATE)){
			     if(Calendar.SATURDAY != crecal.get(Calendar.DAY_OF_WEEK)
			             && Calendar.SUNDAY != crecal.get(Calendar.DAY_OF_WEEK)){
			         numberOfDays++;
			     }
			     crecal.add(Calendar.DATE,1);
			    // crecal.roll(Calendar.DATE, true);
			 }
			 //Get number of hours in the remaining day
			 System.out.println("hours calculation");
			 numberOfSeconds = TimeUnit.MILLISECONDS.toSeconds(curcal.getTimeInMillis() - crecal.getTimeInMillis());
			rvo.Timebal.add(numberOfDays * 24 *60 + (numberOfSeconds/60) );
			
			 System.out.println(i+"Difference = " + 
			         ( numberOfDays * 24 *60 + (numberOfSeconds/60)  ) + " Minutes"); 
			 String mm;
			 String hh;
			 
			if(rvo.getSeverity().get(i).equalsIgnoreCase("sev5"))
			{
				if(rvo.getTimebal().get(i)<=4320)
				{
					rvo.metflag.add("NOT BREACHED");
					mm=String.valueOf((4320-rvo.getTimebal().get(i))% 60);
					hh=String.valueOf((4320-rvo.getTimebal().get(i))/ 60);
					rvo.Remainingtime.add(hh+":"+mm);
				}
				else
				{
					rvo.metflag.add("BREACHED");
				    rvo.Remainingtime.add("00:00:00");
				}
			}
			else if (rvo.getSeverity().get(i).equalsIgnoreCase("sev4"))
			{
				if(rvo.getTimebal().get(i)<=1440)
				{
					rvo.metflag.add("NOT BREACHED");
					mm=String.valueOf((1440-rvo.getTimebal().get(i))% 60);
					hh=String.valueOf((1440-rvo.getTimebal().get(i))/ 60);
					rvo.Remainingtime.add(hh+":"+mm);
				}
				else
				{
					rvo.metflag.add("BREACHED");
				rvo.Remainingtime.add("00:00:00");
				}
			}
			else if (rvo.getSeverity().get(i).equalsIgnoreCase("sev3"))
			{
				if(rvo.getTimebal().get(i)<=480)
				{
					rvo.metflag.add("NOT BREACHED");
					mm=String.valueOf((480-rvo.getTimebal().get(i))% 60);
					hh=String.valueOf((480-rvo.getTimebal().get(i))/ 60);
					rvo.Remainingtime.add(hh+":"+mm);
				}
				else
				{
					rvo.metflag.add("BREACHED");
				rvo.Remainingtime.add("00:00:00");
				}
			}
			else if (rvo.getSeverity().get(i).equalsIgnoreCase("sev2"))
			{
				if(rvo.getTimebal().get(i)<=240)
				{
					rvo.metflag.add("NOT BREACHED");
					mm=String.valueOf((240-rvo.getTimebal().get(i))% 60);
					hh=String.valueOf((240-rvo.getTimebal().get(i))/ 60);
					rvo.Remainingtime.add(hh+":"+mm);
				}
				else {
					rvo.metflag.add("BREACHED");
				rvo.Remainingtime.add("00:00:00");
				}
			}
			else if (rvo.getSeverity().get(i).equalsIgnoreCase("sev1"))
			{
				if(rvo.getTimebal().get(i)<=120)
				{
					rvo.metflag.add("NOT BREACHED");
					mm=String.valueOf((120-rvo.getTimebal().get(i))% 60);
					hh=String.valueOf((120-rvo.getTimebal().get(i))/ 60);
					rvo.Remainingtime.add(hh+":"+mm);
				}
				else
				{
					rvo.metflag.add("BREACHED");
				rvo.Remainingtime.add("00:00:00");
				}
			}
			else
			{
				System.out.println("incorrect severity");
			}
			System.out.println(rvo.getMetflag().get(i));
			System.out.println(rvo.getRemainingtime().get(i));
		}
		log.info("Calculation finished");
		sm.sendSuccessMail(rvo);
	}
}
