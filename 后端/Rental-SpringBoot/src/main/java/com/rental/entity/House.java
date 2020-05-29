package com.rental.entity;

import org.springframework.stereotype.Component;
/**
 * 房屋实体类
 * @author 张宝玲
 *
 */
@Component
public class House {
	private Long id;//房源id
	private String sn;//房间编号
	private String photo; //房源图片
	private float price; //房源价格
	private String name; //房源名称
	private String address;//房源地址
	private Integer liveNum;//可住人数
	private String introduce;//房源简介
	private Long landlordId;//房东id
	private Integer bedRoomNum;//卧室数量
	private Integer toiletNum;//卫生间数量
	private Integer bedNum;//床位数
	private Integer isRealPhoto;//是否实拍  0不是   1是
	private Integer isCooking;//是否可做饭  0不可  1可
	private Integer isParkFree;//是否免费停车  0不否   1是
	private Integer isProject;//是否有投影   0否  1是
	
	private String userPhoto;//房东头像
	private String landlordName;//房东用户名
	
	
	
	public Integer getBedNum() {
		return bedNum;
	}
	public void setBedNum(Integer bedNum) {
		this.bedNum = bedNum;
	}
	public Integer getIsRealPhoto() {
		return isRealPhoto;
	}
	public void setIsRealPhoto(Integer isRealPhoto) {
		this.isRealPhoto = isRealPhoto;
	}
	public Integer getIsCooking() {
		return isCooking;
	}
	public void setIsCooking(Integer isCooking) {
		this.isCooking = isCooking;
	}
	public Integer getIsParkFree() {
		return isParkFree;
	}
	public void setIsParkFree(Integer isParkFree) {
		this.isParkFree = isParkFree;
	}
	public Integer getIsProject() {
		return isProject;
	}
	public void setIsProject(Integer isProject) {
		this.isProject = isProject;
	}
	public Integer getBedRoomNum() {
		return bedRoomNum;
	}
	public void setBedRoomNum(Integer bedRoomNum) {
		this.bedRoomNum = bedRoomNum;
	}
	public String getLandlordName() {
		return landlordName;
	}
	public void setLandlordName(String landlordName) {
		this.landlordName = landlordName;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
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
	public Integer getLiveNum() {
		return liveNum;
	}
	public void setLiveNum(Integer liveNum) {
		this.liveNum = liveNum;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public Integer getToiletNum() {
		return toiletNum;
	}
	public void setToiletNum(Integer toiletNum) {
		this.toiletNum = toiletNum;
	}
	public Long getLandlordId() {
		return landlordId;
	}
	public void setLandlordId(Long landlordId) {
		this.landlordId = landlordId;
	}

	 
}
