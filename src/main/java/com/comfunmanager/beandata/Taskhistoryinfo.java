package com.comfunmanager.beandata;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 用户User 
 * 
 * @author angkor
 *
 */
@Entity
@Table(name = "taskhistoryinfo", uniqueConstraints = { @UniqueConstraint(columnNames = { "taskid" }) })
public class Taskhistoryinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String taskid;
	private String serverhost;
	private String serverport;
	private String taskstatus;
	private String updatetime;
	


	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTaskid() {
		return taskid;
	}



	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}



	public String getServerhost() {
		return serverhost;
	}



	public void setServerhost(String serverhost) {
		this.serverhost = serverhost;
	}



	public String getServerport() {
		return serverport;
	}



	public void setServerport(String serverport) {
		this.serverport = serverport;
	}



	public String getTaskstatus() {
		return taskstatus;
	}



	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}



	public String getUpdatetime() {
		return updatetime;
	}



	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}



	@Override
	public String toString() {
		return "Taskhistoryinfo [id=" + id + ", taskid=" + taskid + ", serverhost=" + serverhost + ", taskstatus=" + taskstatus + ", updatetime=" + updatetime + "]";
	}

}
