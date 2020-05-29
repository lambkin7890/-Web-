package com.rental.entity;

import org.springframework.stereotype.Component;

/**
 * 短租房源收藏实体类
 * @author 张宝玲
 *
 */
@Component
public class HouseCollection {
	private Long id;//收藏id，设置自增
	private Long userId;//用户id
	private Long houseId;//短租房源Id
	private String tenantName;//用户名
	private String name;//房源名称
	private String address;//房源地址
	private String photo;//房源图片
	private float price;//房源价格
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	
	
}
