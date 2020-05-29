package com.rental.entity;

import org.springframework.stereotype.Component;

/**
 * 用户实体类
 * @author 张宝玲
 *
 */
@Component
public class User {
	private Long id;//用户id，设置自增
	private Long roleId;//用户角色，1是房客，2是房东
	private String username;//用户名，登录名
	private String password;//登录密码
	private String photo;//头像
	private String realPhoto;//真实照片
	private String nickName;//用户昵称
	private String realName;//真实姓名
	private String idCard;//身份证号码
	private int sex;//性别 0,代表未知，1代表男，2代表女
	private String email;//邮箱
	private String mobile;//手机号码
	
	
	public String getRealPhoto() {
		return realPhoto;
	}
	public void setRealPhoto(String realPhoto) {
		this.realPhoto = realPhoto;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	
}
