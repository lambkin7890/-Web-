package com.rental.entity;

import org.springframework.stereotype.Component;

@Component
public class LongRentHouse {
	private Long id;//房源id
	private String sn;//房源编号
	private String photo; //房源图片
	private float price; //房源价格
	private String name; //房源名称
	private String address;//房源地址
	private Integer area;//房屋面积
	private String floorRange;//楼层范围
	private Integer totalFloor;//总楼层
	private String introduce;//房源简介
	private Long landlordId;//房东id
	private Integer bedRoomNum;//卧室数量
	private Integer sittingRoomNum;//客厅数量
	private Integer toiletNum;//卫生间数量
	private Integer isEntire;//是否整租  0不是   1是
	private Integer isSouth;//是否朝南  0不可  1可
	private Integer isElevator;//是否有电梯  0不否   1是
	private String createTime;//发布时间
	
	private String landlordName;//房东用户名
	private String landlordRealName;//房东真实姓名
	
	
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLandlordRealName() {
		return landlordRealName;
	}
	public void setLandlordRealName(String landlordRealName) {
		this.landlordRealName = landlordRealName;
	}
	public Integer getTotalFloor() {
		return totalFloor;
	}
	public void setTotalFloor(Integer totalFloor) {
		this.totalFloor = totalFloor;
	}
	public Integer getIsEntire() {
		return isEntire;
	}
	public void setIsEntire(Integer isEntire) {
		this.isEntire = isEntire;
	}
	public Integer getIsSouth() {
		return isSouth;
	}
	public void setIsSouth(Integer isSouth) {
		this.isSouth = isSouth;
	}
	public Integer getIsElevator() {
		return isElevator;
	}
	public void setIsElevator(Integer isElevator) {
		this.isElevator = isElevator;
	}
	public String getLandlordName() {
		return landlordName;
	}
	public void setLandlordName(String landlordName) {
		this.landlordName = landlordName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
	
	public String getFloorRange() {
		return floorRange;
	}
	public void setFloorRange(String floorRange) {
		this.floorRange = floorRange;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public Long getLandlordId() {
		return landlordId;
	}
	public void setLandlordId(Long landlordId) {
		this.landlordId = landlordId;
	}
	
	public Integer getBedRoomNum() {
		return bedRoomNum;
	}
	public void setBedRoomNum(Integer bedRoomNum) {
		this.bedRoomNum = bedRoomNum;
	}
	public Integer getSittingRoomNum() {
		return sittingRoomNum;
	}
	public void setSittingRoomNum(Integer sittingRoomNum) {
		this.sittingRoomNum = sittingRoomNum;
	}
	public Integer getToiletNum() {
		return toiletNum;
	}
	public void setToiletNum(Integer toiletNum) {
		this.toiletNum = toiletNum;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	
}
