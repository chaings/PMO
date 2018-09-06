package com.pmo.dashboard.entity;

/**
 * jasdjad3663636
 */

/**
 * 
 * @author Look
 * @since 2018-1-29
 * @version 1.0
 *
 */
public class ApiUser {
	
	private String userName;//认证用户
	private String password;//认证密码
	private String systemId;//系统标识（SpiderNet）
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}	
	

}
