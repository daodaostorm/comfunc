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
@Table(name = "answerinfo", uniqueConstraints = { @UniqueConstraint(columnNames = { "answerid" }) })
public class Answerinfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String answerid;
	private String username;
	private String questionid;
	private String questionname;
	private String questiontext;
	private String answerresult;
	private String status;
	private String updatetime;

	


	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public String getAnswerid() {
		return answerid;
	}




	public void setAnswerid(String answerid) {
		this.answerid = answerid;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public String getQuestionid() {
		return questionid;
	}




	public void setQuestionid(String questionid) {
		this.questionid = questionid;
	}




	public String getQuestionname() {
		return questionname;
	}




	public void setQuestionname(String questionname) {
		this.questionname = questionname;
	}




	public String getQuestiontext() {
		return questiontext;
	}




	public void setQuestiontext(String questiontext) {
		this.questiontext = questiontext;
	}




	public String getAnswerresult() {
		return answerresult;
	}




	public void setAnswerresult(String answerresult) {
		this.answerresult = answerresult;
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
		return "Answerinfo [id=" + id + ", questionid=" + questionid + ", questionname=" + questionname + ", answerid=" + answerid + ", username=" + username + ", questiontext=" + questiontext + ", answerresult=" + answerresult + ", status=" + status + ", updatetime=" + updatetime + "]";
	}

}
