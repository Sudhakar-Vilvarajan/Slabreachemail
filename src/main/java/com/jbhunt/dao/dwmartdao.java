package com.jbhunt.dao;

import com.jbhunt.vo.Resultsetvo;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dwmartdao  {

	public Resultsetvo runquery(String Sql) throws SQLException, ClassNotFoundException {
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
	//Connection conn = DriverManager.getConnection("jdbc:sqlserver://DWDataMart_pidazbi:1433;databaseName=DWDataMart;username=jcnt204;password=nr4th11;integratedSecurity=true;authenticationScheme=JavaKerberos");
	Connection conn = DriverManager.getConnection("jdbc:sqlserver://DWDataMart_pidazbi:1433;database=DWDataMart;user=jcon632;password=happy16;integratedSecurity=true;authenticationScheme=JavaKerberos");
	//System.out.println("test");
	Statement sta = conn.createStatement();
	//Sql = "SELECT TOP 2 * From  IncidentDim";
	ResultSet rs = sta.executeQuery(Sql);
	Resultsetvo rvo= new Resultsetvo();
	/*List <Resultsetvo> rvol = null;
	rvol=*/
	
	//return (List<Resultsetvo>) sta.executeQuery(Sql);
	while (rs.next()) {
		//System.out.println(rs.getTimestamp("CreatedDate"));
		rvo.id.add(rs.getString("Id"));
		rvo.title.add(rs.getString("Title"));
		String status=rs.getString ("Status");
		status=status.replace("IncidentStatusEnum.", "");
		status=status.replace(".Enum","");
		rvo.status.add(status);
		rvo.displayname.add(rs.getString("displayname"));
		rvo.severity.add(rs.getString("Severity"));
		rvo.createddate.add(rs.getTimestamp("CreatedDate"));
		rvo.firstassigneddate.add(rs.getTimestamp("FirstAssignedDate"));
		String workqueue=rs.getString("WorkQueue");
		workqueue=workqueue.replace("IncidentTierQueuesEnum.", "");
		workqueue=workqueue.replace(".Enum","");
		rvo.workqueue.add(workqueue);
	}
	return rvo;
	}
}
	
	

