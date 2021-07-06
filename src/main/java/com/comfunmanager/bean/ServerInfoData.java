package com.comfunmanager.bean;

/**
 * ServerInfoData实体类
 * @author Administrator
 *
 */
public class ServerInfoData {
	
	private String serverid;
	private String serverhost;
	private String serverport;
	private String taskid;
	private int weight;
	
	public String getServerid() {
		return serverid;
	}
	public void setServerid(String serverid) {
		this.serverid = serverid;
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
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getTaskid() {
		return taskid;
	}
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	
	
}
