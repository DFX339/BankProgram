package cn.bank.ssm.bean;

import java.util.Date;

/**
 * ��־��
 * @author Administrator
 *
 */
public class Log {
	
	private int id;//��־��id 
	private String logType;//��־����
	private String logContent;//��־����
	private Date operateTime;//��־����ʱ��
	private int userid;
	

	public Log(){}
	
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogContent() {
		return logContent;
	}

	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
	
	
}
