package com.jbhunt.Slabreachemail;

import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jbhunt.dao.dwmartdao;
import com.jbhunt.Slabreachemail.Calculate;
import com.jbhunt.vo.Resultsetvo;

/**
 * spring boot main application
 *
 */
@SpringBootApplication
public class Application implements CommandLineRunner
{
	final static Logger log= Logger.getLogger(Application.class);
    public static void main( String[] args ) throws Exception
    {
        SpringApplication.run(Application.class,args);
    }
    public void run(String... args) throws Exception {
    	
    	dwmartdao dao=new dwmartdao();
      	Resultsetvo rvo=new Resultsetvo();
      	Calculate val=new Calculate();
      	log.info("Program Started");
          System.out.println( "Program Started" );
          String Sql;
          Sql="Select \r\n" + 
          		"incident.Id \r\n" + 
          		",incident.Title \r\n" + 
          		",incident.Status \r\n" + 
          		",userdim.displayname \r\n" + 
          		",case incident.Priority \r\n" + 
          		"when '5' then 'sev5' \r\n" + 
          		"when '4' then 'sev4' \r\n" + 
          		"when '3' then 'sev3' \r\n" + 
          		"when '2' then 'sev2' \r\n" + 
          		"when '1' then 'sev1' \r\n" + 
          		"else 'Others' \r\n" + 
          		"end as Severity \r\n" + 
          		",incident.CreatedDate \r\n" + 
          		",incident.FirstAssignedDate \r\n" + 
          		",incident.ResolvedDate \r\n" + 
          		",incident.TierQueue as WorkQueue \r\n" + 
          		",DATEDIFF(mi,incident.CreatedDate,GETDATE()) as TimeDiff \r\n" + 
          		",case  \r\n" + 
          		"when incident.Priority=1 then 120 - ( DATEDIFF(mi,incident.CreatedDate,getDate()) - (DATEDIFF(week,incident.CreatedDate,getDate())*2*24*60) ) \r\n" + 
          		"when incident.Priority=2 then 240 - ( DATEDIFF(mi,incident.CreatedDate,getDate()) - (DATEDIFF(week,incident.CreatedDate,getDate())*2*24*60) ) \r\n" + 
          		"when incident.Priority=3 then 480 - ( DATEDIFF(mi,incident.CreatedDate,getDate()) - (DATEDIFF(week,incident.CreatedDate,getDate())*2*24*60) ) \r\n" + 
          		"when incident.Priority=4 then 1440 - ( DATEDIFF(mi,incident.CreatedDate,getDate()) - (DATEDIFF(week,incident.CreatedDate,getDate())*2*24*60) ) \r\n" + 
          		"when incident.Priority=5 then 4320 - ( DATEDIFF(mi,incident.CreatedDate,getDate()) - (DATEDIFF(week,incident.CreatedDate,getDate())*2*24*60) ) \r\n" + 
          		"End as 'Resolution_SLA_Met_NotMet' \r\n" + 
          		"From    IncidentDim incident \r\n" + 
          		"JOIN    WorkItemDim workitem on incident.EntityDimKey = workItem.EntityDimKey  \r\n" + 
          		"JOIN    WorkItemAssignedToUserFactvw assignedtouser on workitem.WorkItemDimKey = assignedtouser.WorkItemDimKey  \r\n" + 
          		"JOIN    UserDimvw userdim on assignedtouser.WorkItemAssignedToUser_UserDimKey = userdim.UserDimKey   \r\n" + 
          		"Where   assignedtouser.DeletedDate is null and \r\n" + 
          		"incident.status not in ('IncidentStatusEnum.Resolved','IncidentStatusEnum.Closed') \r\n" + 
          		"and (incident.TierQueue like '%Offshore%'  \r\n" + 
          		"or incident.TierQueue like '%360.Safety%'\r\n" + 
          		"or incident.TierQueue like '%360.Training%'\r\n" + 
          		"or incident.TierQueue like '%Operations.Compliance%')\r\n" + 
          		"order by  Resolution_SLA_Met_NotMet";
        
         rvo = dao.runquery(Sql); 
         val.calculatesla(rvo);
         System.exit(0);
    }
}
