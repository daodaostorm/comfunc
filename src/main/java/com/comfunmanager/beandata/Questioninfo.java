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
@Table(name = "questioninfo", uniqueConstraints = { @UniqueConstraint(columnNames = { "questionid" }) })
public class Questioninfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String questionid;
	private String questionname;
	private String userid;
	private String answerpos;
	private String answerneg;
	private String status;
	private String updatetime;

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getQuestionid() {
		return questionid;
	}



	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getQuestionname() {
		return questionname;
	}



	public void setQuestionname(String questionname) {
		this.questionname = questionname;
	}



	public String getAnswerpos() {
		return answerpos;
	}



	public void setAnswerpos(String answerpos) {
		this.answerpos = answerpos;
	}



	public String getAnswerneg() {
		return answerneg;
	}



	public void setAnswerneg(String answerneg) {
		this.answerneg = answerneg;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getUpdatetime() {
		return updatetime;
	}



	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}



	@Override
	public String toString() {
		return "Questioninfo [id=" + id + ", questionid=" + questionid + ", questionname=" + questionname + ", answerpos=" + answerpos + ", answerneg=" + answerneg + ", status=" + status + ", updatetime=" + updatetime + "]";
	}

}
