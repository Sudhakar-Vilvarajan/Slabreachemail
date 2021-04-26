package com.jbhunt.vo;

import java.util.ArrayList;
import java.util.Date;

public class Resultsetvo {

	public ArrayList<String> id=new ArrayList<String>();
	public ArrayList<String> title=new ArrayList<String>();
	public ArrayList<String> status=new ArrayList<String>();
	public ArrayList<String> displayname=new ArrayList<String>();
	public ArrayList<String> severity=new ArrayList<String>();
	public ArrayList<Date> createddate=new ArrayList<Date>();
	public ArrayList<Date> firstassigneddate=new ArrayList<Date>();
	public ArrayList<String> workqueue=new ArrayList<String>();
	public ArrayList<Long> Timebal=new ArrayList<Long>();
	public ArrayList<String> Remainingtime=new ArrayList<String>();
	public ArrayList<String> metflag=new ArrayList<String>();
	public ArrayList<String> getMetflag() {
		return metflag;
	}
	public void setMetflag(ArrayList<String> metflag) {
		this.metflag = metflag;
	}
	public ArrayList<String> getRemainingtime() {
		return Remainingtime;
	}
	public void setRemainingtime(ArrayList<String> remainingtime) {
		Remainingtime = remainingtime;
	}
	public ArrayList<Long> getTimebal() {
		return Timebal;
	}
	public void setTimebal(ArrayList<Long> timebal) {
		Timebal = timebal;
	}
	public ArrayList<String> getWorkqueue() {
		return workqueue;
	}
	public void setWorkqueue(ArrayList<String> workqueue) {
		this.workqueue = workqueue;
	}
	public ArrayList<String> getId() {
		return id;
	}
	public void setId(ArrayList<String> id) {
		this.id = id;
	}
	public ArrayList<String> getTitle() {
		return title;
	}
	public void setTitle(ArrayList<String> title) {
		this.title = title;
	}
	public ArrayList<String> getStatus() {
		return status;
	}
	public void setStatus(ArrayList<String> status) {
		this.status = status;
	}
	public ArrayList<String> getDisplayname() {
		return displayname;
	}
	public void setDisplayname(ArrayList<String> displayname) {
		this.displayname = displayname;
	}
	public ArrayList<String> getSeverity() {
		return severity;
	}
	public void setSeverity(ArrayList<String> severity) {
		this.severity = severity;
	}
	public ArrayList<Date> getCreateddate() {
		return createddate;
	}
	public void setCreateddate(ArrayList<Date> createddate) {
		this.createddate = createddate;
	}
	public ArrayList<Date> getFirstassigneddate() {
		return firstassigneddate;
	}
	public void setFirstassigneddate(ArrayList<Date> firstassigneddate) {
		this.firstassigneddate = firstassigneddate;
	}
	public ArrayList<String> getworkqueue() {
		return workqueue;
	}
	public void setworkqueue(ArrayList<String> workqueue) {
		this.workqueue = workqueue;
	}
	
}
