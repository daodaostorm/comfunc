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
@Table(name = "serverinfo", uniqueConstraints = { @UniqueConstraint(columnNames = { "serverid" }) })
public class Serverinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String serverid;
	private String serverhost;
	private String serverport;
	private String serverstatus;
	private String createtime;
	private String updatetime;
	



	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




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




	public String getServerstatus() {
		return serverstatus;
	}




	public void setServerstatus(String serverstatus) {
		this.serverstatus = serverstatus;
	}




	public String getCreatetime() {
		return createtime;
	}




	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}




	public String getUpdatetime() {
		return updatetime;
	}




	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}




	@Override
	public String toString() {
		return "Serverinfo [id=" + id + ", serverid=" + serverid + ", serverhost=" + serverhost + ", serverstatus=" + serverstatus + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}

}
